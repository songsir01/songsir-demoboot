[toc]
# 一、安装
 - 新建虚拟机
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173314972.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 选择系统文件
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173536345.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 自定义硬件
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173602898.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 开始安装
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173611139.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 选择语言
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173618309.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 选择磁盘
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173626997.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173639478.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 创建用户
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109173646357.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
完成后重启
# 二、设置

## 1、ip
 - 输入ifconfig

```
[root@localhost ~]# ifconfig
-bash: ifconfig: command not found
```
ifconfig命令不可使用

 - 输入ip addr
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109172423759.png)

发现并没有ip地址

 - 配置网卡信息
```
[root@localhost /]# cd /etc/sysconfig/network-scripts/
```
```
[root@localhost network-scripts]# ll
total 228
-rw-r--r--. 1 root root   280 Jan  9  2019 ifcfg-ens33
-rw-r--r--. 1 root root   254 Jan  2  2018 ifcfg-lo
lrwxrwxrwx. 1 root root    24 Jan  9  2019 ifdown -> ../../../usr/sbin/ifdown
-rwxr-xr-x. 1 root root   654 Jan  2  2018 ifdown-bnep
...
```
 - 编辑ifcfg-ens33
```
[root@localhost network-scripts]# vi ifcfg-ens33
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
BOOTPROTO=dhcp
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=4ab88041-d8cb-4e74-a000-c5adcddcc146
DEVICE=ens33
ONBOOT=no
```
将ONBOOT=no的no改为yes，保存退出
 
 - 重启网卡服务

```
[root@localhost network-scripts]# service network restart
Restarting network (via systemctl):                        [  OK  ]
```

 - 查看ip


```
[root@localhost network-scripts]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:0c:29:2c:55:db brd ff:ff:ff:ff:ff:ff
    inet 192.168.30.130/24 brd 192.168.30.255 scope global noprefixroute dynamic ens33
       valid_lft 1759sec preferred_lft 1759sec
    inet6 fe80::c085:2aa2:5b33:814d/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
```
发现ip已存在

 - 安装ifconfig命令
```
[root@localhost ~]# yum install net-tools
```
一路y，完成

 - 使用ifconfig查看ip
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190109172653514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)

## 2、桥接
### （1）虚拟机设置
 - 虚拟机-->编辑-->虚拟网络编辑器，将VMnet1修改为桥接模式，确定。如下：
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110101105905.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ### （2）centOS设置
 -  选中指定CentOS，设置，将网络设置为刚才的桥接模式，如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110101500370.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ### （3）接下来配置固定IP
  - 首先查看本地的网络配置：ip、子网掩码、网关
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/2019011010295436.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 然后进入虚拟机，配置相关信息：
```
[root@localhost ~]# cd /etc/sysconfig/network-scripts/
```
```
[root@localhost network-scripts]# vi ifcfg-ens33 
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110102051598.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 将BOOTPROTO=dhcp修改为BOOTPROTO=static
 - 然后，根据本地IP、网关添加到上面配置：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110102502569.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 保存，退出，重启网卡

```
[root@localhost network-scripts]# service network restart
Restarting network (via systemctl):                        [  OK  ]
```
### （4）验证
 - ping一下百度试试

```
[root@localhost network-scripts]# ping www.baidu.com
PING www.a.shifen.com (61.135.169.125) 56(84) bytes of data.
64 bytes from 61.135.169.125 (61.135.169.125): icmp_seq=1 ttl=57 time=7.85 ms
64 bytes from 61.135.169.125 (61.135.169.125): icmp_seq=2 ttl=57 time=13.1 ms
64 bytes from 61.135.169.125 (61.135.169.125): icmp_seq=3 ttl=57 time=20.6 ms
```
桥接固定IP完成。