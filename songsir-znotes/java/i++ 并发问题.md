# 一、i++ 是线程安全的吗？

## 1、测试环境

   - 使用jdk8
   - 使用Executors创建线程池
   - 线程池容量100执行1000次自增操作
 
## 2、代码验证

### （1）全部代码

```
public class UnsafeDemo {

    private int cnt = 0;

    public void UnSafeAdd() {
        cnt++;
    }

    public int getCnt() {
        return cnt;
    }

    public static void main(String[] args) {
        int exePoolSize = 100;
        int forSize = 1000;
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        ExecutorService exe = Executors.newFixedThreadPool(exePoolSize);
        CountDownLatch countDownLatch = new CountDownLatch(forSize);
        for (int i = 0; i < forSize; i++) {
            Runnable runnable = () -> {
                unsafeDemo.UnSafeAdd();
                countDownLatch.countDown();
            };
            exe.execute(runnable);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exe.shutdown();
        System.out.println(unsafeDemo.getCnt());
    }

}
```
### （2）相关说明
   - 线程池使用Executors.newFixedThreadPool(exePoolSize)创建，创建一个可复用的固定的线程池，exePoolSize表示线程池的容量大小。
   - 使用CountDownLatch倒数器，保证各个线程执行完自增操作再执行输出操作。
   - 通过上面代码，期望的输出值是1000，但是多次测试，输出结果都是小于1000.

### （3）结论

由于内存不可见的原因，如果一个线程运算完后还没刷到主内存，比如从0加到1，此时主内存仍为0，而另一个线程读取到0然后从0加到1，所以当他们执行完将数据刷到主内存，执行了两次从0到1的操作，最终结果是1。

# 二、线程安全的自增操作

## 1、测试环境
   
   - 使用jdk8
   - 使用ThreadPoolExecutor创建线程池
   - 使用AtomicInteger执行自增操作
   

## 2、代码验证

### （1）全部代码

```
public class SafeDemo {

    private static int COREPOOLSIZE = 5;
    private static int MAXIMUMPOOLSIZE = 200;
    private static long KEEPALIVETIME = 0L;
    private static int QUESIZE = 100;
    // 拒绝策略
    // ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常, 默认策略
    // ThreadPoolExecutor.DiscardPolicy：丢弃任务不抛出异常。 
    // ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程） 
    // ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
    private static ThreadPoolExecutor.CallerRunsPolicy rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
    // 尽量不使用Executors创建线程池，Executors有可能导致OOM发生
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(COREPOOLSIZE, MAXIMUMPOOLSIZE, KEEPALIVETIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(QUESIZE), Thread::new, rejectHandler);

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger cnt = new AtomicInteger();
        int forSize = 100000;
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
### （2）相关说明

   - 此时使用ThreadPoolExecutor创建线程池，同样在阿里巴巴代码规范中也明确说明不能使用Executors创建线程池，防止在未知情况下OOM的发生
   - 使用AtomicInteger，这是JUC包下atomic的其中一个代表类，使用CAS保证可见性。
   
