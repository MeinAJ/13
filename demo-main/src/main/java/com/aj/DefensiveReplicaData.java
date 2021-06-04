/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DefinsiveReplicaData
 *
 * @author An Jun
 * @date 2021-06-04
 */
@SuppressWarnings("ALL")
public /** final修改不可继承 */ final class DefensiveReplicaData {

    private final/** final修改不可更改 */ List<Long> data;

    public static void main(String[] args) {
        DefensiveReplicaData definsiveReplicaData = new DefensiveReplicaData();
        List<Long> dataDefensive = definsiveReplicaData.getDataDefensive();
        //会抛java.lang.UnsupportedOperationException
        dataDefensive.add(1L);
    }

    DefensiveReplicaData() {
        data = new ArrayList<>();
    }

    public List<Long> getData() {
        return this.data;
    }

    public List<Long> getDataDefensive() {
        //做防御性复制
        return Collections.unmodifiableList(data);
    }

}
