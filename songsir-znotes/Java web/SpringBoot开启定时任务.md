[toc]
# 一、SpringBoot开启定时任务

SpringBoot零配置开启定时任务，有两种情况：

 - 在启动类添加注解@EnableScheduling
 - 在指定的配置类上添加注解@EnableScheduling

如果定时任务全部提取成一个项目，建议在启动类上面添加注解；如果某个项目中有个别定时任务，那么只需要在指定的类上面添加注解即可。

代码如下：

```
@Configuration
@EnableScheduling
public class SongsirDemoJob {

    private static final Logger log = LoggerFactory.getLogger(SongsirDemoJob.class);
    // 每30秒执行一次
    @Scheduled(cron = "*/30 * * * * ?")
    public void work() {
        long start = System.currentTimeMillis();
        log.info("\r\n***************开始执行定时任务*****************");
        // doSomething
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("耗时：" + (System.currentTimeMillis() - start));
        log.info("\r\n***************定时任务结束*****************\n\n\n");
    }
}
```
执行效果如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181123152009726.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
上面使用了Cron表达式，下面简单介绍一下。

# 二、Cron表达式

 - cron表达式由6个或者7个域组成，中间由空格隔开
 - 6个域分别表示 Seconds Minutes Hours DayofMonth Month DayofWeek
 - 7个域分别表示 Seconds Minutes Hours DayofMonth Month DayofWeek Year
 - 其中*表示任意值，比如在Minutes使用 *，表示任意分钟都执行， */2表示每两分钟执行一次， */30每30分钟执行一次
 - 上面定时任务我使用了 */30 * * * * ?,即表示每30秒执行一次。
 
具体请参考博文：[https://www.cnblogs.com/junrong624/p/4239517.html](https://www.cnblogs.com/junrong624/p/4239517.html)

其中Cron表达式配置与反解析请使用：
[http://cron.qqe2.com/](http://cron.qqe2.com/)

我的博客：
[https://blog.csdn.net/SongSir001/article/details/84394600](https://blog.csdn.net/SongSir001/article/details/84394600)