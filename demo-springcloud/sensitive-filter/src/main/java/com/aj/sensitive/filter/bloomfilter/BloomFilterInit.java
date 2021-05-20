package com.aj.sensitive.filter.bloomfilter;

import com.aj.sensitive.filter.domain.Sensitive;
import com.aj.sensitive.filter.lock.DistributionLock;
import com.aj.sensitive.filter.service.SensitiveService;
import com.aj.sensitive.filter.trie.TrieTree;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BloomFilterInit {

    public static void init(ApplicationContext applicationContext) {
        SensitiveService sensitiveService = applicationContext.getBean(SensitiveService.class);
        RedissonClient redissonClient = applicationContext.getBean(RedissonClient.class);
        DistributionLock distributionLock = applicationContext.getBean(DistributionLock.class);
        List<Sensitive> sensitiveList = sensitiveService.listAll();
        Set<String> sensitiveWordSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(sensitiveList)) {
            System.out.println("尝试获取锁");
            if (distributionLock.tryLock()) {
                try {
                    System.out.println("敏感词数量=" + sensitiveList.size());
                    System.out.println("获取锁成功");
                    RBloomFilter<Object> filter = redissonClient.getBloomFilter("sensitive-word-bloom-filter-test");
                    filter.tryInit(100000L, 0.01);
                    for (Sensitive sensitive : sensitiveList) {
                        if (!StringUtils.isEmpty(sensitive.getWord()) && sensitiveWordSet.add(sensitive.getWord())) {
                            System.out.println("word=" + sensitive.getWord());
                            filter.add(sensitive.getWord());
                        }
                        //同时将敏感词转成trie树
                        TrieTree.getInstance.insert(sensitive.getWord());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    distributionLock.unLock();
                    System.out.println("释放锁成功");
                }
            }
        }
        //help gc
        sensitiveWordSet = null;


    }

}
