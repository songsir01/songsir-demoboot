[toc]

# 一、jstat介绍

> Jstat是JDK自带的一个轻量级小工具。全称“Java Virtual Machine statistics monitoring tool”。Jstat位于java的bin目录下，主要利用JVM内建的指令对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控。

# 二、jstat相关命令的使用
## 1、环境
   - 本文中使用的服务器是centos
   - 使用的JDK版本是1.8
   - 使用命令 `ps axu|grep java` 查看到相应java的进程PID为 2236 
   - 所有与容量大小相关的单位都是KB
   
  ##  2、类加载信息相关
  

```
[deploy8@JAVA-DEV-02 ~]$ jstat -class 2236
Loaded  Bytes  Unloaded  Bytes     Time   
115983 226479.4     3898  5221.6      81.24
```
   - Loaded  类加载数量
   - Bytes  已加载类占用空间大小
   - Unloaded  未加载类的数量
   - Bytes     未加载类占用的大小
   - Time   耗时

## 3、类编译统计

```
[deploy8@JAVA-DEV-02 ~]$ jstat -compiler 2236 
Compiled Failed Invalid   Time   FailedType FailedMethod
   67577      3       0   432.91          1 com/mysql/jdbc/AbandonedConnectionCleanupThread run
```
   - Compiled  编译数量
   - Failed  失败数量
   - Invalid   不可用数量
   - Time   时间
   - FailedType 失败类型
   - FailedMethod  失败的方法

## 4、堆垃圾收集统计信息

```
[deploy8@JAVA-DEV-02 ~]$ jstat -gc 2236
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
20480.0 20480.0 269.9   0.0   163840.0 97683.3   319488.0   271892.4  673268.0 661182.8 78048.0 75954.8    508    9.526  18      1.737   11.263
```
   - S0C    当前幸存者空间0容量
   - S1C    当前幸存者空间1容量
   - S0U    幸存者空间0已使用容量
   - S1U    幸存者空间1已使用容量
   - EC      当前的伊甸园空间容量
   - EU      伊甸园空间已使用容量
   - OC      老年代空间容量 
   - OU      老年代已使用容量
   - MC    元空间容量
   - MU    Metaspace已使用容量
   - CCSC   压缩类空间容量
   - CCSU   使用的压缩类空间
   - YGC      新生代垃圾收集活动的数量
   - YGCT    新生代垃圾收集时间
   - FGC    完整GC的数量
   - FGCT    完全垃圾收集时间 
   - GCT   垃圾收集总时间

## 5、堆内存生成和空间容量

```
[deploy8@JAVA-DEV-02 ~]$ jstat -gccapacity 2236
 NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC 
204800.0 204800.0 204800.0 20480.0 20480.0 163840.0   319488.0   319488.0   319488.0   319488.0      0.0 1644544.0 673268.0      0.0 1048576.0  78048.0    508    18
```
   - NGCMN    最小新生代容量
   - NGCMX     最大新生代容量
   - NGC     当前新生代容量
   - S0C   幸存者0区容量
   - S1C       幸存者1区容量
   - EC      伊甸园区容量
   - OGCMN      最小老年代容量
   - OGCMX       最大老年代容量
   - OGC         当前老年代容量
   - OC       当前老年代容量
   - MCMN   最小元空间容量  
   - MCMX      最大元空间容量
   - MC     元空间容量
   - CCSMN    压缩类空间最小容量
   - CCSMX     压缩类空间最大容量
   - CCSC    压缩类空间容量
   - YGC    年轻带GC数量
   - FGC 老年代GC数量
 

## 6、垃圾收集统计摘要

```
[deploy8@JAVA-DEV-02 ~]$ jstat -gcutil 2236
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT   
  1.32   0.00  66.57  85.10  98.20  97.32    508    9.526    18    1.737   11.263
```


- S0：幸存者空间0利用率占空间当前容量的百分比

- S1：幸存者空间1占空间当前容量的百分比

- E：伊甸园空间利用率占空间当前容量的百分比

- O：老年代空间利用率占空间当前容量的百分比

- M：元空间利用率占空间当前容量的百分比

- CCS：压缩的类空间利用率百分比

- YGC：新生代GC事件的数量

- YGCT：新生代垃圾收集时间

- FGC：完整GC事件的数量

- FGCT：完全垃圾收集时间

- GCT：垃圾收集总时间


## 7、新生代垃圾收集统计摘要


```
[deploy8@JAVA-DEV-02 ~]$ jstat -gcnew 2236
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 109342.9    508    9.526
```
- S0C：当前幸存者空间0容量

- S1C：当前幸存者空间1容量

- S0U：幸存者空间0利用率

- S1U：幸存者空间1利用率

- TT：对象在新生代中存活次数。

- MTT：对象在新生代中存活最大次数。

- DSS：期望的幸存区大小

- EC：当前的伊甸园空间容量

- EU：伊甸园空间利用率

- YGC：新生代GC事件的数量

- YGCT：新生代垃圾收集时间


## 8、老年代垃圾收集统计摘要


```
[deploy8@JAVA-DEV-02 ~]$ jstat -gcold 2236
   MC       MU      CCSC     CCSU       OC          OU       YGC    FGC    FGCT     GCT   
673268.0 661182.8  78048.0  75954.8    319488.0    271892.4    508    18    1.737   11.263
```
- MC：元空间容量

- MU：元空间利用率

- CCSC：压缩类空间容量

- CCSU：使用的压缩类空间

- OC：当前老年代空间容量

- OU：老年代空间利用率

- YGC：新生代GC事件的数量

- FGC：完整GC事件的数量

- FGCT：完全垃圾收集时间

- GCT：垃圾收集总时间


## 9、动态打印垃圾收集情况

   - 例如打印新生代情况
   - 每1秒打印一行
   - 每三行打印一次列标题
 
   

```
[deploy8@JAVA-DEV-02 ~]$  jstat -gcnew -h3 2236 1000 
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
20480.0 20480.0  269.9    0.0  6   6 10240.0 163840.0 115009.0    508    9.526
```

# 三、总结

- 以上只列举出了常用的部分命令
- 如有问题，感谢指正
- 参考 [官方文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html)