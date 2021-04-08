import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        byte[] array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[2 * 1024 * 1024];

//        byte[] array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[128 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];
//
//        System.gc();
//        print();
//        stackOom();
//        heapOom();
        metaspaceSize();
    }

    private static void metaspaceSize() {
        Main main = new Main();
        Main main1 = new Main();

        Class<? extends Main> aClass = main.getClass();
        Class<? extends Main> aClass1 = main1.getClass();
        System.out.println("1");
    }

    private static void heapOom() {
        List<Main> list = new ArrayList<>();

        while (true) {
            list.add(new Main());
        }
    }

    private static void stackOom() {
        int i = 0;
        System.out.println(i);
        stackOom();
    }

//    public static void print() throws InterruptedException {
//        List<Data> datas = new ArrayList<Data>();
//
//        for (int i = 0; i < 10000; i++) {
//            datas.add(new Data());
//        }
//        Thread.sleep(3600000);
//    }
//
//    static class Data {
//
//    }

}
