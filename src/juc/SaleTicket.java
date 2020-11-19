package juc;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-18 20:03
 * @Description:
 */

public class SaleTicket {

    public static void main(String[] args) throws Exception {
    //    Ticket ticket = new Ticket();
        PrintThread printThread = new PrintThread();
//2人售票
//        while (ticket.getCount() > 0) {
//            new Thread(ticket::send, "售票员A").start();
//            new Thread(ticket::send, "售票员B").start();
//        }

//双生产双消费
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "AAA").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "BBB").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "CCC").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "DDD").start();



        /**
         *
         * @Description:
         * 多线程之间按顺序调用，实现A->B->C
         * 三个线程启动，要求如下：
         *
         * AA打印5次，BB打印10次，CC打印15次
         * 接着
         * AA打印5次，BB打印10次，CC打印15次
         * ......来10轮
         *
         */

//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print5()), "A").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print10()), "B").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print15()), "C").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(1)), "A").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(2)), "B").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(3)), "C").start();


    }
}
