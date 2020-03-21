
# 一、使用线程池优势

## 1、new Thread弊端

   - 每次 new Thread 都要新建对象，性能差
   - 线程无法统一管理，可能新建过多线程导致OOM发生
   - 缺少线程池的一些高级功能，例如定期执行、线程中断等
   

## 2、线程池优势

   - 线程可以复用，减少对象创建、消亡开销，提高性能
   - 有效控制最大并发线程数，提高资源利用率，避免多资源竞争，避免阻塞
   - 提供定时执行、定期执行、并发数控制等功能
   

# 二、ThreadPoolExecutor

## 1、核心参数

   - corePoolSize：核心线程数
   - maximumPoolSize：最大线程数
   - workQueue：阻塞队列，存储等待执行的任务
   - keepAliveTime：线程没有任务执行时保持存活时间
   - unit：keepAliveTime的时间单位
   - threadFactory：线程工厂
   - rejectHandler：拒绝策略，当阻塞队列满了时，没有空闲的线程池，此时需要一种策略处理当前任务
   
## 2、rejectHandler拒绝策略

   - ThreadPoolExecutor.AbortPolicy：丢弃任务并抛出RejectedExecutionException异常，默认策略
   - ThreadPoolExecutor.DiscardPolicy：丢弃任务不抛出异常
   - ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）。
   - ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
   
## 3、ThreadPoolExecutor主要方法

   - execute()：提交任务，交给线程池处理
   - submit()：提交任务，能够返回结果，相当于execute + future
   - shutdown()：关闭线程池，等待任务都执行完
   - shutdownNow()：关闭线程池，不等待任务执行完
  
## 4、ThreadPoolExecutor线程池使用示例

比如多线程执行自增操作：


```
public class SafeDemo {

    private static int COREPOOLSIZE = 5;
    private static int MAXIMUMPOOLSIZE = 200;
    private static long KEEPALIVETIME = 0L;
    private static int QUESIZE = 100;
    
    private static ThreadPoolExecutor.DiscardOldestPolicy rejectHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
    // 尽量不使用Executors创建线程池，Executors有可能导致OOM发生
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(COREPOOLSIZE, MAXIMUMPOOLSIZE, KEEPALIVETIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(QUESIZE), Thread::new, rejectHandler);
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger cnt = new AtomicInteger();
        int forSize = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(forSize);
        for (int i = 0; i < forSize; i++) {
            Runnable runnable = () -> {
                cnt.incrementAndGet();
                countDownLatch.countDown();
            };
            poolExecutor.execute(runnable);
        }
        countDownLatch.await();
        System.out.println(cnt.get());
    }
}
```
# 三、线程池大小合理配置

假设CPU数量为n，那么当处理CPU密集型任务时，线程池大小可设置参考值为n + 1；

当处理IO密集型任务时，参考值可为2 * n；

具体情况需根据实际情况调整，比如设置完大小后，观察任务执行情况和主机资源使用情况等进行调整，保证线程池大小更加合理。


别来无恙！


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200321103436602.jpg#pic_center)