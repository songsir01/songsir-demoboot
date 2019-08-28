
# 1、问题
 开发中发现这种情况，中文变成了奇怪的字符，由 &# + 数字 + 分号组成，比如订单列表变成了 \&#35746;\&#21333;\&#21015;\&#34920;
 这种称为NCR（numeric character reference）
>  字符值引用（numeric character reference, NCR）是在标记语言SGML以及派生的如HTML与XML中常见的一种转义序列结构，用来表示Unicode的通用字符集 (UCS)中的单个字符. NCR可以表示在一个特定文档中不能直接编码的字符，而该标记语言阅读器软件把每个NCR当作一个字符来处理。

# 2、NCR字符转换

使用StringEscapeUtils将普通汉字转为NCR：
```
public class SongsirTest {
    public static void main(String[] args) {
        String s = "订单列表";
        System.out.println(StringEscapeUtils.escapeHtml(s));
    }
}
输出结果：
&#35746;&#21333;&#21015;&#34920;
```
使用StringEscapeUtils将NCR转为普通字符：

```
public static void main(String[] args) {
    String s = "&#35746;&#21333;&#21015;&#34920;";
    System.out.println(StringEscapeUtils.unescapeHtml(s));
}
输出结果：订单列表  
```
# 3、在前端将NCR转为普通字符

```
var title = "&#35746;&#21333;&#21015;&#34920;"
var regex = /&#(\d+);/g;
title = title.replace(regex, function(_, $1) {
    return String.fromCharCode($1);
})

```
注：系统为了防止注入攻击后台将请求转为了NCR导致汉字在前端展示异常。

参考[：https://segmentfault.com/a/1190000005109547](https://segmentfault.com/a/1190000005109547)