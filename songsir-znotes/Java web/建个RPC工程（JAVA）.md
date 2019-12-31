# 一、简介
## 1、项目构成
   - songsir-rpc-dependencies：主要控制第三方依赖的引入和版本相关控制
   - songsir-rpc-parent：继承songsir-rpc-dependencies，下面包含6个model，分别是songsir-rpc-bean、songsir-rpc-common、songsir-rpc-consumer-dao、songsir-rpc-consumer-service、songsir-rpc-provider-dao、songsir-rpc-provider-service
   - songsir-rpc-bean：实体类模块
   - songsir-rpc-common：公共类模块，包括工具类、枚举类等
   - songsir-rpc-consumer-dao：消费者服务的持久层
   - songsir-rpc-consumer-service：消费者的接口类
   - songsir-rpc-provider-dao：服务提供者的持久层
   - songsir-rpc-provider-service：服务提供者的接口类
   - songsir-rpc-provider--web：服务提供者示例（web项目）
   - songsir-rpc-consumer-web：服务消费者示例（web项目）
 
 图示如下：
 ![dependencies](https://img-blog.csdnimg.cn/20191225170045618.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ![parent](https://img-blog.csdnimg.cn/20191225170130562.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ![provider](https://img-blog.csdnimg.cn/20191225170159567.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ![consumer](https://img-blog.csdnimg.cn/20191225170223448.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 # 二、项目搭建
 ## 1、songsir-rpc-dependencies

   - 创建maven项目，引入SpringBoot依赖：
   
```
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath/>
    </parent>
```
接下来，使用\<dependencyManagement>标签声明以后项目中需要用到的第三方依赖，项目参考：[https://github.com/songsir01/songsir-rpc-dependencies.git](https://github.com/songsir01/songsir-rpc-dependencies.git)

## 2、songsir-rpc-parent
新建项目，并依次创建各个model。继承songsir-rpc-dependencies，同样使用\<dependencyManagement>标签声明各个model的依赖，方便生产者和消费者服务的引用。
项目参考：[https://github.com/songsir01/songsir-rpc-parent.git](https://github.com/songsir01/songsir-rpc-parent.git)

## 3、songsir-rpc-provider--web
新建maven项目，继承songsir-rpc-parent，引入songsir-rpc-provider-service、spring-boot-starter-web、dubbo-spring-boot-starter等依赖。

新建启动类，增加注解@DubboComponentScan(basePackages = {"com.songsir.rpc.provider.service.impl"})设置dubbo扫描报名。

```
@SpringBootApplication
@DubboComponentScan(basePackages = {"com.songsir.rpc.provider.service.impl"})
public class App extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... args) {
        System.out.println("App provider is running!");
    }
}
```
增加配置文件：

```
server.port=8080
dubbo.registry.protocol=zookeeper
dubbo.registry.address=192.168.43.134:2181
dubbo.registry.id=dubbo-consumer
dubbo.application.name=songsir-rpc-consumer
dubbo.protocol.port=20882
dubbo.protocol.name=dubbo
dubbo.protocol.status=server
dubbo.protocol.id=dubbo
```

最后写一个测试方法，方便消费者测试。

```
@Service(version = "1.0.0")
public class TestProviderServiceImpl implements TestProviderService {
    @Override
    public String getHelloWorld(String msg) {
        return "Hello World!";
    }
}
```
项目参考：[https://github.com/songsir01/songsir-rpc-provider-web.git](https://github.com/songsir01/songsir-rpc-provider-web.git)

## 4、songsir-rpc-consumer-web
新建maven项目，继承songsir-rpc-parent，引入songsir-rpc-provider-service、songsir-rpc-consumer-service、spring-boot-starter-web、dubbo-spring-boot-starter等依赖。
增加配置文件：

```
server.port=8080
dubbo.registry.protocol=zookeeper
dubbo.registry.address=192.168.43.134:2181
dubbo.registry.id=dubbo-consumer
dubbo.application.name=songsir-rpc-consumer
dubbo.protocol.port=20882
dubbo.protocol.name=dubbo
dubbo.protocol.status=server
dubbo.protocol.id=dubbo
```
新建测试Controller：

```
@RestController
public class TestProviderController {

    @Reference(version = "1.0.0", check = false)
    private TestProviderService testProviderService;
    
    @RequestMapping("testConsumer")
    public String testConsumer() {
        return testProviderService.getHelloWorld("");
    }
}
```
项目参考：[https://github.com/songsir01/songsir-rpc-consumer-web.git](https://github.com/songsir01/songsir-rpc-consumer-web.git)
# 三、最后
   - 项目大致框架已经完成，分别启动provider和consumer即可测试服务是否可用
   - 本项目在已安装zookeeper的前提下创建
   - 可使用dubbo-admin查看服务的注册和消费情况
   - 详细信息请参考我的github [https://github.com/songsir01](https://github.com/songsir01)
