package cn.liangqinghai.study.concurrent;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Liang
 * @date 2020/2/23
 */
public class AtomicOperation {

    public static void main(String[] args) {

//        Counter counter = new Counter();
//
//        List<Thread> threads = new ArrayList<>();
//
//        for (int i = 0; i < 100000; i++) {
//            threads.add(new Thread(() -> {
//                counter.unsafeCount();
//                counter.safeCount();
//            }));
//        }
//
//        threads.forEach(Thread::start);
//
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println(counter.counterWithoutAtomic);
//
//        System.out.println(counter.counterWithAtomic.get());

        AtomicInteger i = new AtomicInteger(1);
        List<Integer> integers = Stream.generate(i::getAndIncrement).limit(20).collect(Collectors.toList());

        integers.stream().limit(80).forEach(System.out::println);

    }

    static class Counter {

        private int counterWithoutAtomic;

        public int getCounterWithoutAtomic() {
            return counterWithoutAtomic;
        }

        public Counter setCounterWithoutAtomic(int counterWithoutAtomic) {
            this.counterWithoutAtomic = counterWithoutAtomic;
            return this;
        }

        public AtomicInteger getCounterWithAtomic() {
            return counterWithAtomic;
        }

        public Counter setCounterWithAtomic(AtomicInteger counterWithAtomic) {
            this.counterWithAtomic = counterWithAtomic;
            return this;
        }

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
