package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-02 21:41
 * @Description:
 */

class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//CAS -> Unsafe -> CAS底层思想 -> ABA -> 原子引用更新 -> 如何规避ABA问题

public class AbaDemo {
    //原子引用其实和原子包装类是差不多的概念，就是将一个java类，用原子引用类进行包装起来，那么这个类就具备了原子性
    private static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User u1 = new User("张三", 19);
        User u2 = new User("jack", 20);
        //正常
        // extracted(u1, u2);
        //验证ABA 问题
        //    aba(u1, u2);

        //解决ABA
        //AtomicStampedReference
        //时间戳原子引用，来这里应用于版本号的更新，也就是每次更新的时候，需要比较期望值和当前值，以及期望版本号和当前版本号
        // 传递两个值，一个是初始值，一个是初始版本号
        System.out.println("=====解决ABA=====");
        AtomicStampedReference<User> stampedReference = new AtomicStampedReference<>(u1, 1);
        abaOk(u1, u2, stampedReference);

    }

    private static void abaOk(User u1, User u2, AtomicStampedReference<User> stampedReference) {
        new Thread(() -> {
            //获取版本号
            int stampOne = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号" + stampOne); //t3	 第1次版本号1
            // 暂停t3一秒钟
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 传入4个值，期望值，更新值，期望版本号，更新版本号
            System.out.println("修改是否成功：" + stampedReference.compareAndSet(u1, u2, stampOne, stampOne + 1) + "\t" + stampedReference.getReference()); //修改是否成功：true	User{name='jack', age=20}
            int stampTwo = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号" + stampTwo); //t3	 第2次版本号2
            System.out.println("修改是否成功：" + stampedReference.compareAndSet(u2, u1, stampTwo, stampTwo + 1) + "\t" + stampedReference.getReference()); //修改是否成功：true	User{name='张三', age=19}
            System.out.println(Thread.currentThread().getName() + "\t 第3次版本号" + stampedReference.getStamp()); //t3	 第3次版本号3
        }, "t3").start();

        new Thread(() -> {
            //获取版本号
            int stampOne = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号" + stampOne);//t4	 第1次版本号1
            try {
                //等待上面ABA完成
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("修改是否成功：" + stampedReference.compareAndSet(u1, u2, stampOne, stampOne + 1)); //修改是否成功：false
            System.out.println("当前最新version：" + stampedReference.getStamp()); //当前最新version：3
            System.out.println("当前value：" + stampedReference.getReference());  //当前value：User{name='张三', age=19}
        }, "t4").start();

    }

    private static void aba(User u1, User u2) {
        atomicReference.set(u1);
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get());//true	User{name='jack', age=20}
            System.out.println(atomicReference.compareAndSet(u2, u1) + "\t" + atomicReference.get());//true	User{name='张三', age=19}
        }).start();
        new Thread(() -> {
            try {
                //等待上面ABA完成
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //CAS 成功  但是u1在期间被修改过
            System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get());//true	User{name='jack', age=20}
        }).start();

    }

    private static void extracted(User u1, User u2) {
        atomicReference.set(u1);
        System.out.println(atomicReference.get()); //User{name='张三', age=19}
        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get()); //true	User{name='jack', age=20}
        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get());//false	User{name='jack', age=20}
    }
}
