
# 一、vi编辑器相关

 - 光标移动到句首：^
 - 光标移动到句尾：$
 - 显示文本行号：:set nu
 - 光标移动到指定n行：nG

# 二、日志查看技巧

- 根据关键字查询tomcat日志并显示关键字在日志中的行号：cat -n catalina.out | grep "关键字"
- 根据关键字动态展示tomcat日志并显示关键字在日志中的行号：tail -f catalina.out | grep "关键字"
- 查询展示关键字上下n行日志并显示关键字在日志中的行号：cat -n catalina.out | grep -n "关键字"
- 查询到关键字所在行号后，编辑日志（vi catalina.out）
- 显示日志行号：：set nu
- 定位到关键字所在行号：nG
- 向上翻页：ctrl + b
- 向下翻页：ctrl + f

**然后就可以愉快的查看关键字附近的各种日志了。**

# 三、tomcat操作

## 1、查看文件列表

 - ll 相当于 ls -l
```
[root@kong songsir]# ll
total 8
drwxrwxrwx 9 root root 4096 Aug 22 15:05 apache-tomcat-8.0.33-25081-25080-25082
drwxrwxrwx 9 root root 4096 Jul 16 12:20 my-tomcat8-7175-7180-7179
```
## 2、查看文件夹大小
 - du -sh /logs，查看logs文件夹大小

```
[deploy8@pic134 ~]$ du -sh logs
54G     logs
# 当前文件夹总大小
[deploy8@pic134 ~]$ du -sh
86G   
```
 - 列出该文件夹下的子文件夹的大小

```
[deploy8@pic134 logs]$ du -h --max-depth=1
16G     ./oms_core_maven
38G     ./oms_sdc_maven
54G     .
```
max-depth等于几表示，几层子文件夹。

## 3、查看带有某个关键字的tomcat的进程情况


```
[root@kong songsir]# ps axu | grep 25080
root      2326  2.8  4.3 2131008 88752 pts/0   Sl   16:56   0:05 /home/jdk1.8.0_73/bin/java -Djava.util.logging.config.file=/songsir/apache-tomcat-8.0.33-25081-25080-25082/conf/logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -server -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=512m -Djava.endorsed.dirs=/songsir/apache-tomcat-8.0.33-25081-25080-25082/endorsed -classpath /songsir/apache-tomcat-8.0.33-25081-25080-25082/bin/bootstrap.jar:/songsir/apache-tomcat-8.0.33-25081-25080-25082/bin/tomcat-juli.jar -Dcatalina.base=/songsir/apache-tomcat-8.0.33-25081-25080-25082 -Dcatalina.home=/songsir/apache-tomcat-8.0.33-25081-25080-25082 -Djava.io.tmpdir=/songsir/apache-tomcat-8.0.33-25081-25080-25082/temp org.apache.catalina.startup.Bootstrap start
root      2356  0.0  0.0  61172   784 pts/0    S+   16:59   0:00 grep 25080
```
可以看出来哪个tomcat在启动，用户root后面的数字表示进程号。

## 4、重启tomcat

```
[root@kong apache-tomcat-8.0.33-25081-25080-25082]# kill -9 2326; ./bin/startup.sh; tail -f ./logs/catalina.out
```
kill -9 强制杀死进程，./bin/startup.sh 进入bin目录下启动tomcat， tail -f ./logs/catalina.out 动态展示日志输出情况

# 四、文件操作

## 1、复制

### （1）复制文件

使用 cp 源文件 目的文件，比如把b.txt复制一份到a.txt
```
[root@kong test]# ll
total 4
-rw-r--r-- 1 root root 13 Dec  3 14:37 b.txt
[root@kong test]# cat b.txt 
Hello World!
[root@kong test]# cp b.txt a.txt
[root@kong test]# ll
total 8
-rw-r--r-- 1 root root 13 Dec  3 14:39 a.txt
-rw-r--r-- 1 root root 13 Dec  3 14:37 b.txt
[root@kong test]# cat a.txt 
Hello World!
```
### （2）复制文件夹

使用cp -r 源文件家  目的文件夹

比如复制test文件夹到test2文件夹

```
[root@kong songsir]# cp -r test test2
```

### （3）远程复制（从其他服务器复制到当前）
使用scp命令，比如把jdk从192.168.101.114服务器的testuser用户下的jdk复制到当前目录

```
scp testuser@192.168.101.114:/home/testuser/software/jdk1.7.0_79.tar.gz ./
```
然后输入用户密码即可复制

## 2.移动

使用mv 源路径 目的路径

比如将test下的a.txt移动到test2文件夹下

```
[root@kong test]# mv a.txt ../test2/
```

## 3、删除

### （1）删除文件

删除test下的b.txt
```
[root@kong test]# rm b.txt 
```
删除test下所有文件（谨慎操作）

```
[root@kong test]# rm *
```
### （2）删除文件夹

删除test文件夹

```
[root@kong songsir]# rm -rf test
```
清空用户下所有数据

```
[root@kong songsir]# rm -rf /*
```
***在公司正式环境下这样操作*，就拜了个拜！**

# 五、系统相关

## 1、查看内存情况

```
[root@localhost ~]# free -m
              total        used        free      shared  buff/cache   available
Mem:            472          74         254           4         143         347
Swap:          1023           0        1023
```

## 2.文件大小

```
[root@localhost ~]# df -h
Filesystem           Size  Used Avail Use% Mounted on
/dev/mapper/cl-root   18G  1.9G   17G  11% /
devtmpfs             226M     0  226M   0% /dev
tmpfs                237M     0  237M   0% /dev/shm
tmpfs                237M  4.6M  232M   2% /run
tmpfs                237M     0  237M   0% /sys/fs/cgroup
/dev/sda1           1014M  139M  876M  14% /boot
tmpfs                 48M     0   48M   0% /run/user/0
```
end...
后续追加