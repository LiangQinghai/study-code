package cn.liangqinghai.study.concurrent;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Liang
 * @date 2020/2/23
 */
public class AtomicOperation {

    public static void main(String[] args) {

        Counter counter = new Counter();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            threads.add(new Thread(() -> {
                counter.unsafeCount();
                counter.safeCount();
            }));
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.counterWithoutAtomic);

        System.out.println(counter.counterWithAtomic.get());

    }

    @Data
    static class Counter {

        private int counterWithoutAtomic;

        private AtomicInteger counterWithAtomic;

        public Counter() {
            this.counterWithAtomic = new AtomicInteger(0);
            this.counterWithoutAtomic = 0;
        }

        public void safeCount() {

            for (;;) {
                int i = this.counterWithAtomic.get();
                boolean ifSet = this.counterWithAtomic.compareAndSet(i, ++i);
                if (ifSet) {
                    break;
                }
            }

        }

        public void unsafeCount() {
            this.counterWithoutAtomic += 1;
        }

    }

}
