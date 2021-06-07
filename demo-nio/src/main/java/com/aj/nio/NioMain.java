/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * NioMain
 *
 * @author An Jun
 * @date 2021-06-07
 */
@SuppressWarnings("ALL")
public class NioMain {

    public static void main(String[] args) throws IOException {
//        byte[] bytes = "hello world".getBytes();
//        byte[] bytes = new byte[]{1, 2, 3, 4};
//        final ByteBuffer buffer = ByteBuffer.wrap(bytes);

        FileOutputStream in = new FileOutputStream("D:\\tmp\\hello.txt");
        FileChannel channel = in.getChannel();
        FileLock lock = channel.lock(0, Long.MAX_VALUE, false);

        ByteBuffer buffer = ByteBuffer.wrap("hello world 001".getBytes());
        channel.write(buffer);

        lock.release();

//        for (int i = 0; i < 10; i++) {
//            new Thread(){
//                @Override
//                public void run() {
//                    try {
//                        channel.write(buffer);
//                        buffer.rewind();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }

//        buffer.put(new byte[]{2, 3, 4, 5});
//        buffer.clear();
//
//        print(buffer);
//
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//
//        buffer.flip();
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//        System.out.println(buffer.get());
//
//        buffer.rewind();
//        System.out.println(buffer.get());
    }

    private static void print(ByteBuffer buffer) {
        System.out.println("position=" + buffer.position());
        System.out.println("limit=" + buffer.limit());
        System.out.println("capacity=" + buffer.capacity());
    }

}
