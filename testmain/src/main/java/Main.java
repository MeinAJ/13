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
        print();
    }

    public static void print() throws InterruptedException {
        List<Data> datas = new ArrayList<Data>();

        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(3600000);
    }

    static class Data {

    }

}
