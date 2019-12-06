
# 一、Synchronize 和 Lock 介绍
   
   - Synchronize：Synchronize是Java的关键字，可以作用于方法、代码块上，JVM层次上的控制锁的获取和释放。可以视为一种悲观锁。
   - Lock：其实现类ReentrantLock,相比于Synchronize是JVM层次上的锁控制而Lock是Java层次的控制锁的代码，需要手动释放锁。而ReentrantLock属于乐观锁的代表，其实现机制是CAS理论。
  

# 二、乐观锁和悲观锁

   - 悲观锁：总是假设最坏的情况，认为竞争总是存在，每次拿数据的时候都认为会被修改，因此每次都会先上锁。其他线程阻塞等待释放锁。
   - 乐观锁：总是假设最好的情况，认为竞争总是不存在，每次拿数据的时候都认为不会被修改，因此不会先上锁，在最后更新的时候比较数据有无更新，可通过版本号或CAS实现。
  
# 三、CAS（Compare And Swap）

   - Java1.5开始引入了CAS，主要放在JUC的atomic包下，如下：
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190924161403834.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
   - 以AtomicInteger为例，其中主要方法有：
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190924161855209.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
   实现原理：将内存位置的内容与给定值进行比较，只有在相同的情况下，将该内存位置的内容修改为新的给定值