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
        Ticket ticket = new Ticket();

//        while (ticket.getCount() > 0) {
//            new Thread(ticket::send, "售票员A").start();
//            new Thread(ticket::send, "售票员B").start();
//        }


        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "AAA").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "BBB").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "CCC").start();
        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "DDD").start();
    }
}
