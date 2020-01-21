
作者：固安李庆海
链接：https://www.jianshu.com/p/620a9b15a619
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

# 简介
JSON Web token简称JWT， 是用于对应用程序上的用户进行身份验证的标记。也就是说, 使用 JWTS 的应用程序不再需要保存有关其用户的 cookie 或其他session数据。此特性便于可伸缩性, 同时保证应用程序的安全。

在身份验证过程中, 当用户使用其凭据成功登录时, 将返回 JSON Web token, 并且必须在本地保存 (通常在本地存储中)。每当用户要访问受保护的路由或资源 (端点) 时, 用户代理(user agent)必须连同请求一起发送 JWT, 通常在授权标头中使用Bearer schema。后端服务器接收到带有 JWT 的请求时, 首先要做的是验证token。

# JWT的格式

JWT就是一个字符串，经过加密处理与校验处理的字符串，形式为：A.B.C
A由JWT头部信息header加密得到
B由JWT用到的身份验证信息json数据加密得到
C由A和B加密得到，是校验部分

# 怎样使用token？

可以放到HTTP请求的请求头中，通常是Authorization字段。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200121105439984.png)
# JWT 实战

## 加入Maven jwt 依赖


```
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>0.9.1</version>
</dependency>
```

## 在application.proterties中加入配置


```
# 加密yan
jwt.secret=A0B1C2D3E4F5G6H7I8J9KALBMCNDOEPFQ0R1S2T3U4V5W6X7Y8Z9
# tocken 过期时间，单位秒
jwt.expire=300
# 需要认证的url，多个URL使用英文逗号,分割
jwt.authorised-urls=/apis/fis/redis/**
```

## JwtHelper工具类


```
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
    private Long EXPIRATION_TIME;
    private String SECRET;
    private final String TOKEN_PREFIX = "Bearer";
    private final String HEADER_STRING = "Authorization";

    public JwtHelper(String secret, long expire) {
        this.EXPIRATION_TIME = expire;
        this.SECRET = secret;
        System.out.println("正在初始化Jwthelper，expire="+expire);
    }

    public JSONObject generateToken(Map<String, Object> claims) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, EXPIRATION_TIME.intValue());
        Date d = c.getTime();
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(d)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        JSONObject json = new JSONObject();
        json.put("token",TOKEN_PREFIX + " " + jwt);
        json.put("token-type", TOKEN_PREFIX);
        json.put("expire-time",new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(d) );
        return json;
    }

    public Map<String, Object> validateTokenAndGetClaims(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        System.out.println("token is:"+token);
        if (token == null) {
            return null;
        } 
        Map<String, Object> body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        return body;
    }
}
```

## JWT过滤器JwtFilter


```
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

/**
 * JWT过滤器
 * 
 * @author 李庆海
 *
 */
public class JwtFilter implements Filter {
    private JwtHelper jwtHelper;
    private List<String> urls = null;
     private static final org.springframework.util.PathMatcher pathMatcher = new AntPathMatcher();
    public JwtFilter(JwtHelper jwtHelper, String[] authorisedUrls) {
        this.jwtHelper = jwtHelper;
        urls = Arrays.asList(authorisedUrls);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value()); // HttpStatus.SC_NO_CONTENT = 204
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token");
            httpResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        }
        String spath = httpRequest.getServletPath();
        try {
            // 验证受保护的接口
            for (String url : urls) {
                if (pathMatcher.match(url, spath)) {
                    Object token = jwtHelper.validateTokenAndGetClaims(httpRequest);
                    if (token != null) {
                        chain.doFilter(request, response);
                        return;
                    }else{
                         httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未授权或者授权已经过期");
                         return;
                    }
                }else{
                    chain.doFilter(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
```

## 配置JWT


```
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yd.fis.client.jwt.JwtFilter;
import cn.com.yd.fis.client.jwt.JwtHelper;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private long expire;

    @Value("${jwt.authorised-urls}")
    private String[] authorisedUrls;

    @Bean
    public JwtHelper jwtHelperBean() {
        return new JwtHelper(secret, expire);
    }

    @Bean
    public FilterRegistrationBean basicFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtFilter filter = new JwtFilter(jwtHelperBean(), authorisedUrls);
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
```

# 在Controller中使用JWT

此处仅为说明jwt的用法，在实际应用时可以根据具体的业务需要加入不同的或者更多的参数，一并作为claims进行参数传递。

```
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.yd.fis.client.jwt.JwtHelper;
import cn.com.yd.fis.client.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("${api-url}/auth")
public class AuthorizeController {

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Object login(String loginName,String password) throws Exception {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("loginName", loginName);
        if ("1".equals(password)) {
            return JsonResult.success(jwtHelper.generateToken(claims));
        } else {
            return JsonResult.fail("登录帐号或者登录密码错误");
        }
    }
}
```

# 辅助工具类JsonResult


```
import com.alibaba.fastjson.JSONObject;

public class JsonResult {
    public static JSONObject success(Object obj) {
        JSONObject json = new JSONObject();
        json.put("state", true);
        json.put("msg", "成功");
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }

    public static JSONObject fail(Object obj) {
        JSONObject json = new JSONObject();
        json.put("state", false);
        json.put("msg", "失败");
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }

    public static JSONObject toJSONObject(boolean state, String msg, Object obj) {
        JSONObject json = new JSONObject();
        json.put("state", state);
        json.put("msg", msg);
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }
}
```
作者：固安李庆海 链接：https://www.jianshu.com/p/620a9b15a619 来源：简书 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。