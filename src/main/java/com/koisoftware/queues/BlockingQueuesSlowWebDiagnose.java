package com.koisoftware.queues;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.DoubleStream;

public class BlockingQueuesSlowWebDiagnose {
    //Suppose you have two independent blocking queues that will keep getting new data,
    // the new data will always be greater than the last element of the queue it is going to.
    // You can only use getNext() to get the data from these two blocking queues and each data
    // can be fetched only once. Write a program to output all pairs of data from these
    // two blocking queues that have difference smaller than 1.
    //Example：
    //Q1:  {0.2, 1.4, 3.0}
    //Q2:  {1.0, 1.1, 3.5}
    //Output: [0.2, 1.0], [1.4, 1.0], [0.2, 1.1], [1.4, 1.1], [3.0, 3.5].

    //Follow Ups
    //What if there are multiple queues, such as 10 queues
    //  1.It should use a list to store the data from each blocking queue.
    //  2.Lock is the same, it should lock before the calculation and release the lock afterwards.
    //  3.The thread input should be like (List<List<Double>> queues, int qIndex)
    //  4.Each thread need to maintain a list of indexes which point to the un-explored position of other queues.
    //  5.Every time a thread get a new data, start from the current index and find the last index suite the condition of each other queues.

    public class ReadFromQueues implements Runnable {
        private final BlockingQueue<Double> inputQueue;
        private final List<List<Double>> res;

        private final Queue<Double> q1;
        private final Queue<Double> q2;

        private final ReentrantLock lock;

        public ReadFromQueues(BlockingQueue<Double> bq, ReentrantLock lock, List<List<Double>> res, Queue<Double> q1, Queue<Double> q2) {
            this.inputQueue = bq;
            this.lock = lock;
            this.res = res;
            this.q1 = q1;
            this.q2 = q2;
            new Thread(this, "ReadFromQueues").start();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    double timestamp = inputQueue.take();
                    if (timestamp < 0) break;
                    lock.lock();
                    calculate(timestamp);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void calculate(double ts) {
            q1.add(ts);
            if (!q2.isEmpty()) {
                while (!q2.isEmpty() && ts - q2.peek() >= 1) q2.poll();
                for (double t : q2) {
                    if (Math.abs(ts - t) < 1.0) {
                        res.add(Arrays.asList(ts, t));
                        System.out.printf("Debug: Thread %s ts1: %f ts2: %f \n", Thread.currentThread().getId(), ts, t);
                    } else break;
                }
            }
        }
    }

    public class WriteToQueue implements Runnable {
        BlockingQueue<Double> bq;
        DoubleStream ds;

        public WriteToQueue(BlockingQueue<Double> bq, DoubleStream ds) {
            this.bq = bq;
            this.ds = ds;
            new Thread(this, "WriteToQueue").start();
        }

        @Override
        public void run() {
            Iterator<Double> iter = this.ds.iterator();
            while (iter.hasNext()) {
                double next = iter.next();
                bq.add(next);
                System.out.printf("Debug: Thread %s value %f \n", Thread.currentThread().getId(), next);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BlockingQueuesSlowWebDiagnose obj = new BlockingQueuesSlowWebDiagnose();
        // two streams, push data to two queues
        DoubleStream.Builder b1 = DoubleStream.builder();
        b1.accept(0.2);
        b1.accept(1.4);
        b1.accept(3.0);
        b1.accept(-1.0);
        DoubleStream ds1 = b1.build();

        DoubleStream.Builder b2 = DoubleStream.builder();
        b2.accept(1.0);
        b2.accept(1.1);
        b2.accept(3.5);
        b2.accept(-1.0);
        DoubleStream ds2 = b2.build();

        ReentrantLock lock = new ReentrantLock();
        BlockingQueue<Double> bq1 = new ArrayBlockingQueue<>(4);
        BlockingQueue<Double> bq2 = new ArrayBlockingQueue<>(4);

        Queue<Double> q1 = new LinkedList<>();
        Queue<Double> q2 = new LinkedList<>();

        List<List<Double>> res = new ArrayList<>();

        obj.new WriteToQueue(bq1, ds1);
        obj.new WriteToQueue(bq2, ds2);
        obj.new ReadFromQueues(bq1, lock, res, q1, q2);
        obj.new ReadFromQueues(bq2, lock, res, q2, q1);

        Thread.sleep(10000);

        for (List<Double> l : res) {
            System.out.println(l);
        }
    }
}
