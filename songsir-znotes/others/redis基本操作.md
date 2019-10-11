
# 一、Redis环境
## 1、环境：CentOS7.0，redis-4.0.2

## 2、首先启动redis服务

```
[root@songsir03 redis]# cd redis-4.0.2/src/
[root@songsir03 src]# ./redis-server
```
然后启动客户端可执行命令操作：（上个窗口保证没有关闭的情况下，新开一个窗口，
进入redis的src下执行redis-cli）
（redis服务窗口默认情况下在关闭时会关闭服务，在后台运行redis服务可参考 
https://blog.csdn.net/a8240357/article/details/80367291）

```
[root@songsir03 src]# ./redis-cli
```
# 二、Redis数据结构

Redis有五种数据结构，分别为string （字符串）、list （列表）、
set（集合）、hash（哈希） 和 zset（有序集合）。
    
## 1、string（字符串）

### （1）键值对

```
127.0.0.1:6379> set name songsir
OK
127.0.0.1:6379> get name
"songsir"
127.0.0.1:6379> exists name
(integer) 1
127.0.0.1:6379> del name
(integer) 1
127.0.0.1:6379> get name
(nil)
127.0.0.1:6379> exists name
(integer) 0
```
### （2）使用mset和mget批量操作

```
127.0.0.1:6379> mset name1 songsir1 name2 songsir2
OK
127.0.0.1:6379> mget name1 name2
1) "songsir1"
2) "songsir2"
127.0.0.1:6379> 
```
### （3）过期时间设置和set扩展

```
127.0.0.1:6379> set name songsir
OK
127.0.0.1:6379> get name
"songsir"
127.0.0.1:6379> expire name 6         # 6s后过期
(integer) 1
127.0.0.1:6379> get name
(nil)
127.0.0.1:6379> setex name 5 songsir
OK
127.0.0.1:6379> get name
(nil)
127.0.0.1:6379> setex name 5 songsir
OK
127.0.0.1:6379> get name
"songsir"
127.0.0.1:6379> get name
(nil)
127.0.0.1:6379> 
127.0.0.1:6379> setnx name songsir   # 如果name不存在，则执行set操作
(integer) 1
127.0.0.1:6379> get name
"songsir"
127.0.0.1:6379> setnx name songsir2
(integer) 0                          # name存在，新的set不成功
127.0.0.1:6379> get name
"songsir"
```
### （4）计数

如果value是整数，可以自增自检操作，最大值 = 9223372036854775807，超过这个数报错
    
```
127.0.0.1:6379> set song 22
OK
127.0.0.1:6379> incr song 
(integer) 23
127.0.0.1:6379> incrby song 22
(integer) 45
127.0.0.1:6379> incrby song -22
(integer) 23
127.0.0.1:6379> set song 9223372036854775807
OK
127.0.0.1:6379> incr song
(error) ERR increment or decrement would overflow
```
## 2、list （列表）

Redis的列表类似（不是等同）Java的LinkedList，链表结构，插入和删除速度快，查询慢
    
### （1）队列，左进右出，先进先出

```
127.0.0.1:6379> rpush books java c python
(integer) 3
127.0.0.1:6379> llen books
(integer) 3
127.0.0.1:6379> lpop books
"java"
127.0.0.1:6379> lpop books
"c"
127.0.0.1:6379> lpop books
"python"
127.0.0.1:6379> lpop books
(nil)
```
### （2）栈，左进左出，先进后出
    
（栈相当于一个一端封闭的管子，数据一个个进去之后，压栈一个个弹出）
    
```
127.0.0.1:6379> rpush books java c python
(integer) 3
127.0.0.1:6379> rpop books
"python"
127.0.0.1:6379> rpop books
"c"
127.0.0.1:6379> rpop books
"java"
127.0.0.1:6379> rpop books
(nil)
```
### （3）慢操作

lindex相当于java的get（），遍历链表，性能低；
ltrim截取 保留字段；其中参数index可以为负数，-1表示倒数第一个，-2表示倒数第二个。
    
```
127.0.0.1:6379> rpush books java c python
(integer) 3
127.0.0.1:6379> lindex books 1            # 获取下标为1
"c"
127.0.0.1:6379> lrange books 0 -1         # 遍历所有，慎用
1) "java"
2) "c"
3) "python"
127.0.0.1:6379> ltrim books 1 -1          # 截取下标为1到最后
OK
127.0.0.1:6379> lrange books 0 -1
1) "c"
2) "python"
```
## 3、hash（字典）

Redis的hash相当于Java的HashMap，数据结构和HashMap基本一样，
不过hash的value只能是字符串。
    
```
127.0.0.1:6379> hset books java "Java从入门到放弃"
(integer) 1
127.0.0.1:6379> hset books c "c从精通到入土"
(integer) 1
127.0.0.1:6379> hgetall books
1) "java"
2) "Java\xe4\xbb\x8e\xe5\x85\xa5\xe9\x97\xa8\xe5\x88\xb0\xe6\x94\xbe\xe5\xbc\x83"
3) "c"
4) "c\xe4\xbb\x8e\xe7\xb2\xbe\xe9\x80\x9a\xe5\x88\xb0\xe5\x85\xa5\xe5\x9c\x9f"
```
上面发现中文乱码了，只需要启动redis-cli时在后面加上--raw即可
    
```
[root@songsir03 src]# redis-cli --raw
127.0.0.1:6379> hgetall books
java
Java从入门到放弃
c
c从精通到入土
127.0.0.1:6379> hget books java
Java从入门到放弃
```
## 4、set（集合）

Redis的集合相当于Java的HashSet，无序唯一的。
    
```
127.0.0.1:6379> sadd books java
1
127.0.0.1:6379> sadd books java
0
127.0.0.1:6379> sadd books java c python
2
127.0.0.1:6379> smembers books            # 无序
java
python
c
127.0.0.1:6379> spop books                # 随机弹出一个（无序）
python
```
## zset（有序集合）

下面zset值存储books的名称，score存储价格
    
```
127.0.0.1:6379> zadd books 9.0  "java 从入门到放弃"
1
127.0.0.1:6379> zadd books 9.1 "c"
1
127.0.0.1:6379> zadd books 9.9 "python"
1
127.0.0.1:6379> zrange books 0 -1
java 从入门到放弃
c
python
127.0.0.1:6379> zscore books "c"                # 获取c的价格
9.0999999999999996                              # score采用double存储，精度失真
127.0.0.1:6379> zrank books "c"                 # 获取c的次序
1
127.0.0.1:6379> zrank books "java 从入门到放弃"  
0
```
# 三、SpringBoot项目中使用redisTemplate操作Redis

## 1、工具代码如下

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
 
 
@Component
public class SongsirRedisTemplate<E> {
 
    @Autowired
    private RedisTemplate redisTemplate;
 
    // 序列化工具类
    @Autowired
    private SerializeUtil<E> serialize;
 
    public static ListOperations<String, String> list;
 
    /**
     * 根据键值获取value
     */
    public E get(String key) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        return key == null ? null : serialize.unserialize(opsForValue.get(key));
    }
 
    /**
     * 设置键值对(无超时时间)
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, E value) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        try {
            opsForValue.set(key, serialize.serialize(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * 设置键值对 (设置有效时间,以秒为单位)
     * @param key
     * @param value
     * @param expire
     * @return boolean
     */
    public boolean set(String key, E value, long expire) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        try {
            opsForValue.set(key, serialize.serialize(value), expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * 根据key删除键值对
     * @param key
     * @return
     */
    public boolean delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
 
 
    /**
     * lpush 进队列
     * @param key
     * @param value
     * @return
     */
    public Long lpush(String key, String value) {
        list = redisTemplate.opsForList();
        try {
            Long leftPush = list.leftPush(key, value);
            return leftPush;
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(-1);
        }
    }
 
    /**
     * rpop 出队列
     * @param key
     * @return
     */
    public String rpop(String key) {
        list = redisTemplate.opsForList();
        try {
            return list.rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
     * 返回指定队列(list)名队列长度
     * @param key
     * @return
     */
    public Long llen(String key) {
        list = redisTemplate.opsForList();
        return list.size(key);
    }
}
```

## 2、其中用到的序列化工具类如下
    
```
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
 
@Component
public class SerializeUtil<E> {
	
	public String serialize(E object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			
			String str = baos.toString("ISO-8859-1");
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
 
	@SuppressWarnings("unchecked")
	public E unserialize(String serializeStr) {
		String readStr = "";
		if(serializeStr == null || "".equals(serializeStr)) {
			return null;
		}
		try {
			readStr = URLDecoder.decode(serializeStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectInputStream ois = null;
		InputStream  bais = null;
		try {
			bais = new ByteArrayInputStream(readStr.getBytes("ISO-8859-1"));
			ois = new ObjectInputStream(bais);
			return (E) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				bais.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
```