package com.aj.shardingjdbc.news.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class PreciseModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表，这里代表ds2020~ds2021
        Long time = preciseShardingValue.getValue();
        if (Objects.isNull(time)) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按年路由
        for (String each : collection) {
            //获取到年份
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(time * 1000));
            int year = instance.get(Calendar.YEAR);
            String value = String.valueOf(year);
            if (each.endsWith(value)) {
                //这里返回回去的就是最终需要查询的库名
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
