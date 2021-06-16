/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * NioMain
 *
 * @author An Jun
 * @date 2021-06-07
 */
@SuppressWarnings("ALL")
public class NioMain {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.register(selector, SelectionKey.OP_WRITE);

        SocketChannel channel = null;
        selector.select();

//        byte[] bytes = "hello world".getBytes();
//        byte[] bytes = new byte[]{1, 2, 3, 4};
//        final ByteBuffer buffer = ByteBuffer.wrap(bytes);

//        FileOutputStream out = new FileOutputStream("D:\\tmp\\hello.txt");
//        FileChannel channel = out.getChannel();
//
//        ByteBuffer buffer = ByteBuffer.wrap("hello world 001".getBytes());
//        channel.write(buffer);
//        channel.force(true);

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
