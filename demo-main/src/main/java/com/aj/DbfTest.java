/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DbfTest
 *
 * @author An Jun
 * @date 2021-07-15
 */
public class DbfTest {

    private static final String CHARSET_GBK = "GBK";

    private static final String CHARSET_ASCII = "ASCII";

    private static final String FILE = "D:\\test.dbf";

    private static final String PATH = "D:\\" + System.currentTimeMillis() + ".dbf";

    public static void main(String[] args) throws IOException {
        List<Map<String, String>> fieldList = new ArrayList<>();

        Map<String, String> map01 = new HashMap<>(2);
        map01.put("name", "姓名");
//        map01.put("name", "name");
        map01.put("length", "20");
        Map<String, String> map02 = new HashMap<>(2);
        map02.put("name", "年龄");
//        map02.put("name", "age");
        map02.put("length", "20");
        Map<String, String> map03 = new HashMap<>(2);
        map03.put("name", "性别");
//        map03.put("name", "gender");
        map03.put("length", "20");

        fieldList.add(map01);
        fieldList.add(map02);
        fieldList.add(map03);

        createDbf(PATH, fieldList, CHARSET_GBK);

        writeDbf(PATH, getData(), CHARSET_GBK);

        List<Map<String, String>> maps = readDbf(PATH, CHARSET_GBK);
        for (Map<String, String> entity : maps) {
            String data = "[" + entity.get("姓名") + "," + entity.get("年龄") + "," + entity.get("性别") + "]";
            System.out.println(data);
        }
    }

//    private static List<Map<String, String>> getData() {
//        List<Map<String, String>> result = new ArrayList<>();
//
//        Map<String, String> map01 = new HashMap<>(3);
//        map01.put("name", "aj-0");
//        map01.put("age", "27-0");
//        map01.put("gender", "男-0");
//        Map<String, String> map02 = new HashMap<>(3);
//        map02.put("name", "aj-1");
//        map02.put("age", "27-1");
//        map02.put("gender", "男-1");
//        Map<String, String> map03 = new HashMap<>(3);
//        map03.put("name", "aj-2");
//        map03.put("age", "27-2");
//        map03.put("gender", "男-2");
//        Map<String, String> map04 = new HashMap<>(3);
//        map04.put("name", "aj-3");
//        map04.put("age", "27-3");
//        map04.put("gender", "男-3");
//        Map<String, String> map05 = new HashMap<>(3);
//        map05.put("name", "aj-4");
//        map05.put("age", "27-4");
//        map05.put("gender", "男-4");
//
//        result.add(map01);
//        result.add(map02);
//        result.add(map03);
//        result.add(map04);
//        result.add(map05);
//        return result;
//    }

    private static List<Map<String, String>> getData() {
        List<Map<String, String>> result = new ArrayList<>();

        Map<String, String> map01 = new HashMap<>(3);
        map01.put("姓名", "aj-0");
        map01.put("年龄", "27-0");
        map01.put("性别", "男-0");
        Map<String, String> map02 = new HashMap<>(3);
        map02.put("姓名", "aj-1");
        map02.put("年龄", "27-1");
        map02.put("性别", "男-1");
        Map<String, String> map03 = new HashMap<>(3);
        map03.put("姓名", "aj-2");
        map03.put("年龄", "27-2");
        map03.put("性别", "男-2");
        Map<String, String> map04 = new HashMap<>(3);
        map04.put("姓名", "aj-3");
        map04.put("年龄", "27-3");
        map04.put("性别", "男-3");
        Map<String, String> map05 = new HashMap<>(3);
        map05.put("姓名", "aj-4");
        map05.put("年龄", "27-4");
        map05.put("性别", "男-4");

        result.add(map01);
        result.add(map02);
        result.add(map03);
        result.add(map04);
        result.add(map05);
        return result;
    }

    /*
            Map<String, String> map01 = new HashMap<>();
        map01.put("姓名", "aj");
        map01.put("年龄", "27");
        map01.put("性别", "男");
        Map<String, String> map02 = new HashMap<>();
        map02.put("姓名", "aj-1");
        map02.put("年龄", "27-1");
        map02.put("性别", "男-1");
        Map<String, String> map03 = new HashMap<>();
        map03.put("姓名", "aj-2");
        map03.put("年龄", "27-2");
        map03.put("性别", "男-2");
        Map<String, String> map04 = new HashMap<>();
        map04.put("姓名", "aj-3");
        map04.put("年龄", "27-3");
        map04.put("性别", "男-3");
        Map<String, String> map05 = new HashMap<>();
        map05.put("姓名", "aj-4");
        map05.put("年龄", "27-4");
        map05.put("性别", "男-4");
     */

    private static void readDbf() {
        try {
            String[] fieldName = getFieldName(FILE, CHARSET_GBK);
            for (String s : fieldName) {
                System.out.println(s);
            }
            List<Map<String, String>> getRowList = readDbf(FILE, CHARSET_GBK);
            for (Map<String, String> entity : getRowList) {
                System.out.println(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建dbf
     *
     * @param path:文件路径
     * @param fieldList：字段
     * @param charsetName  编码字符集
     */
    public static void createDbf(String path, List<Map<String, String>> fieldList, String charsetName)
            throws IOException {
        DBFField[] fields = new DBFField[fieldList.size()];
        int index = 0;
        for (Map<String, String> fieldMap : fieldList) {
            DBFField field = new DBFField();
            //字段名称
            field.setName(fieldMap.get("name"));
            //指定字段类型为字符串
            field.setType(DBFDataType.CHARACTER);
            //指定长度
            field.setLength(Integer.parseInt(fieldMap.get("length")));
            fields[index] = field;
            index++;
        }
        //定义DBFWriter实例用来写DBF文件
        DBFWriter dbfWriter = new DBFWriter(new FileOutputStream(path), Charset.forName(charsetName));
        //设置字段
        dbfWriter.setFields(fields);
        //写入dbf文件并关闭
        dbfWriter.close();
    }

    /**
     * 获取字段名
     */
    public static String[] getFieldName(String path, String charsetName) throws IOException {
        DBFReader dbfReader = new DBFReader(new FileInputStream(path), Charset.forName(charsetName));
        //获取字段数量
        int fieldCount = dbfReader.getFieldCount();
        String[] fieldName = new String[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fieldName[i] = dbfReader.getField(i).getName();
        }
        dbfReader.close();
        return fieldName;
    }

    /**
     * 写dbf文件
     *
     * @param path:dbf文件路径
     * @param rowList:要写入的记录行
     * @param charsetName：字符集
     */
    public static void writeDbf(String path, List<Map<String, String>> rowList, String charsetName)
            throws IOException {
        DBFWriter dbfWriter = new DBFWriter(new File(path));
        //获取字段
        String[] fieldName = getFieldName(path, charsetName);
        for (Map<String, String> rowMap : rowList) {
            Object[] rowData = new Object[fieldName.length];
            for (int i = 0; i < rowData.length; i++) {
                //根据字段来排列指，不然可能出现错位情况
                rowData[i] = rowMap.get(fieldName[i]);
            }
            //添加记录（此时并没有写入文件）
            dbfWriter.addRecord(rowData);
        }
        //写入dbf文件并保存关闭
        dbfWriter.close();
    }


    /**
     * 读dbf记录
     */
    public static List<Map<String, String>> readDbf(String path, String charsetName) throws IOException {
        List<Map<String, String>> rowList = new ArrayList<>();
        DBFReader dbfReader = new DBFReader(new FileInputStream(path), Charset.forName(charsetName));
        Object[] rowValues;
        while ((rowValues = dbfReader.nextRecord()) != null) {
            Map<String, String> rowMap = new HashMap<>(16);
            for (int i = 0; i < rowValues.length; i++) {
                rowMap.put(dbfReader.getField(i).getName(), String.valueOf(rowValues[i]).trim());
            }
            rowList.add(rowMap);
        }
        dbfReader.close();
        return rowList;
    }

}
