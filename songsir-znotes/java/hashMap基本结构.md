
# 一、结构

   - hashMap采用键值对存储，底层使用数组额链表的结构。
   - HashMap初始化容量是16，即初始化数组大小为16，数组的每个位置都可以看个一个桶，每个桶存放一个链表Entry，Entry包含了四个字段，key、value、next和hash，采用拉链法解决冲突，如下图所示：
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191014151850967.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
   - 拉链法，HashMap的put操作时，比如插入<K1, V1>时，先计算K1的hashCode = hash，hash与hashMap容量length - 1进行与运算得到元素放的位置在哪个桶，当有元素放入相同的桶时，链表下拉成为链，如上图位置标号为5的桶。
   - hashMap允许插入为null的键值对，由于null无法调用hashCode()方法，所以hashMap强制使用第0个桶存放为null的键值对。其中实现方法如下：
   

```
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
}
```
# 二、扩容
   - 当存放键值对数量超过临界值时，hashMap容量扩容为原来的2倍，其中主要参数有：
   - capacity	：hashMap容量大小，初始默认值为16；
   - size：hashMap键值对数量；
   - threshold：size临界值，当size超过这个值时，触发扩容；
   - loadFactor：hashMap负载因子，容量所能使用的比例大小，hashMap默认值为0.75，也就是当键值对超过总容量的75%的时候会进行扩容。
   
   
# 三、get过程
   - 首先根据key计算hash值
   - 然后获取相应的数组下标，hash & （length - 1）
   - 最后遍历相应数组位置的链表，直到找到相等的key