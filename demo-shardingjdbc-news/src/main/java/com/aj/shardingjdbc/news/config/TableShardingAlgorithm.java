package com.aj.shardingjdbc.news.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表，这里代表t_news_01 到 t_news_12
        Long time = preciseShardingValue.getValue();
        if (Objects.isNull(time)) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按月路由
        for (String each : collection) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(time * 1000));
            int month = instance.get(Calendar.MONTH) + 1;
            if (each.endsWith(getMonth(month))) {
                return each;
            }
        }
        return null;
    }

    private String getMonth(int month) {
        if (month < 10) {
            return "0" + month;
        }
        return month + "";
    }
}
