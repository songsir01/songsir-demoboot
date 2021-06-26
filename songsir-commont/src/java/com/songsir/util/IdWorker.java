package com.songsir.util;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:47 2021/6/24
 * @Description:
 * @Copyright Copyright (c) 2021, songsir01@163.com All Rights Reserved.
 */

public class IdWorker {

    // 服务器ID
    private long workerId;
    // 数据中心ID
    private long datacenterId;
    // 序号
    private long sequence;

    //    private long twepoch = System.currentTimeMillis();
    // 起始时间戳
    private long twepoch = 1624675406444L;
    // 服务器位数
    private long workerIdBits = 5L;
    // 数据中心位数
    private long datacenterIdBits = 5L;
    // 最大服务器数量
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 最大数据中心数量
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    // 序号位数
    private long sequenceBits = 12L;
    // 服务器左移位数 12位
    private long workerIdShift = sequenceBits;
    // 数据中心左移位数 17位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间戳左移位数 22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    // 序号最大 4095，每毫秒最大支持生成ID数量
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    // 上次时间戳
    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long datacenterId, long sequence) {
        // 服务器数量限制
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        // 数据中心数量限制
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d, \n",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 此时 sequence = 4096 & 4095 = 0，sequence超过最大值，获取下一毫秒ID
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        System.out.println("timestamp - twepoch = " + (timestamp - twepoch));
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) throws InterruptedException {
        IdWorker worker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println("uid is :" + worker.nextId());
        }
        // -1L ^ (-1L << 12) = 4095
        System.out.println(-1L ^ (-1L << 12));
        System.out.println(1 & 4095);
        System.out.println(2 & 4095);
        System.out.println(4095 & 4095);
        System.out.println(4096 & 4095);
    }

    // 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00010100  (timestamp - twepoch) = 20
    // 00000000 00000000 00000000 00000000 00000101 00000000 00000000 00000000  时间戳左移22位
    // 00000000 00000000 00000000 00000000 00000000 00000010 00000000 00000000  数据中心ID左移17位
    // 00000000 00000000 00000000 00000000 00000000 00000000 00010000 00000000  服务器ID左移12位
    // 00000000 00000000 00000000 00000000 00000101 00000010 00010000 00000000  或运算，最终结果 84021248

}
