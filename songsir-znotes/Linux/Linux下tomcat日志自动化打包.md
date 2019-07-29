[toc]

# 一、场景

## 1、环境

CentOS 7.0，Tomcat 8.0.33 ，（jdk-1.8.0_171）

## 2、目的

随着时间推移，tomcat下的日志文件catalina.out越来越大，当查看日志时效率低下。所以，为了方便日志的查阅，将catalina.out文件按天定时分割打成tar包。也方便了以后按时间清理不必要的日志文件。

# 二、Linux相关配置

## 1、相关环境配置

首先，在CentOS 7.0上配置好JDK环境，安装好Tomcat。Linux下JDK配置具体可参考：[Linux下的JDK配置](https://blog.csdn.net/SongSir001/article/details/80254451)

我的Tomcat如下：两个Tomcat分别为apache-tomcat-8.0.33-25081-25080-25082和apache-tomcat-8.0.33-26081-26080-26082

```
[root@songsir02 songsir]# ll
total 233140
drwxr-xr-x.  9 root    root          160 Oct 19 03:29 apache-tomcat-8.0.33-25081-25080-25082
drwxr-xr-x.  9 root    root          160 Oct 19 00:09 apache-tomcat-8.0.33-26081-26080-26082
-rw-r--r--.  1 root    root      9252868 Aug 28 03:34 apache-tomcat-8.0.33.updated.tar.gz
drwxr-xr-x.  8    1002    1002       255 Mar 28  2018 jdk1.8.0_171
-rw-r--r--.  1 root    root    194421880 Aug 28 03:33 jdk-1.8.0_171-shopin.tar.gz
drwxr-xr-x.  2 root    root            6 Oct 18 23:21 rabbitmq
-rw-rw-r--.  1 songsir songsir       662 Oct 19 00:28 tomcatLog.sh
drwxr-xr-x. 11    1001    1001      4096 Aug 28 04:21 zookeeper-3.4.10
-rw-r--r--.  1 root    root     35042811 Aug 28 04:08 zookeeper-3.4.10.tar.gz
```
## 2、创建打包脚本

新建tomcatLog.sh文件，在里面添加命令如下：

```
dir=/home/songsir/
log=/logs/
date=$(date +%F-%H)
for i in  apache-tomcat-8.0.33-25081-25080-25082 apache-tomcat-8.0.33-26081-26080-26082
do
        cd $dir
        if [ -d $i ]
        then
                cd /tmp
                cd $dir$i$log && cp -fr catalina.out catalina.out.$date.bak&& tar -czf catalina.out.$date.tar.gz catalina.out.$date.bak\
                && cat catalina.out > catalina_old.out \
                && rm -fr catalina.out.$date.bak \
                &&cat /dev/null > catalina.out
                #echo $i $dir$i$log"catalina.out"
        else
                echo "directory is not "
        fi
done
```
上面命令只需要dir改成自己的Tomcat所在目录，然后把Tomcat名称改成自己的Tomcat名称即可。

## 3、创建定时任务命令

```
[root@songsir02 songsir]# crontab -e
```
添上命令保存即可

```
# 分　 时　 日　 月　 周　 命令   */2表示每两天
# 每两天的一点执行一次
1 1 */2 * * /bin/sh /home/songsir/tomcatLog.sh
```
# 三、执行效果

```
[root@songsir02 songsir]# cd apache-tomcat-8.0.33-25081-25080-25082/logs
[root@songsir02 logs]# ll
total 48
-rw-r--r--. 1 root root     0 Oct 19 03:48 catalina_old.out
-rw-rw-rw-. 1 root root     0 Oct 19 03:48 catalina.out
-rw-r--r--. 1 root root   128 Oct 19 03:48 catalina.out.2018-10-19-03.tar.gz
```
打包好的日志文件如上：catalina.out.2018-10-19-03.tar.gz

