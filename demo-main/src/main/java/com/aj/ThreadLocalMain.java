package com.aj;

public class ThreadLocalMain {

    static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal1 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal2 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal3 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal4 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal5 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal6 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal7 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal8 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal9 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal10 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal11 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal12 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal13 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal14 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal15 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal16 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal17 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal18 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal19 = new InheritableThreadLocal();
    static InheritableThreadLocal inheritableThreadLocal20 = new InheritableThreadLocal();
    ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        inheritableThreadLocal.set("1");
        inheritableThreadLocal1.set("1");
        inheritableThreadLocal2.set("1");
        inheritableThreadLocal3.set("1");
        inheritableThreadLocal4.set("1");
        inheritableThreadLocal5.set("1");
        inheritableThreadLocal6.set("1");
        inheritableThreadLocal7.set("1");
        inheritableThreadLocal8.set("1");
        inheritableThreadLocal9.set("1");
        inheritableThreadLocal10.set("1");
        inheritableThreadLocal11.set("1");
        inheritableThreadLocal12.set("1");
        inheritableThreadLocal13.set("1");
        inheritableThreadLocal14.set("1");
        inheritableThreadLocal15.set("1");
        inheritableThreadLocal16.set("1");
        inheritableThreadLocal17.set("1");
        inheritableThreadLocal18.set("1");
        inheritableThreadLocal19.set("1");
        inheritableThreadLocal20.set("1");
    }

    private static void thread(int i) {
        new Thread() {
            @Override
            public void run() {
                inheritableThreadLocal.set(i);
                System.out.println(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
