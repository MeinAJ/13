import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class Main {

    public static void main(String[] args) {

//        JedisShardInfo shardInfo = new JedisShardInfo("192.168.0.103", "7001");
        JedisShardInfo shardInfo = new JedisShardInfo("192.168.2.52", "6379");
//        shardInfo.setPassword("redis-pass");
        Jedis jedis = new Jedis(shardInfo);
//        Long test = jedis.incrBy("test", 1);
//        System.out.println(test);

        String info = jedis.info();
        System.out.println("info=" + info);

//        jedis.setnx("k1", "v1");
//        System.out.println(jedis.get("k1"));
//
//        String mset = jedis.mset("article:1:title", "新闻标题", "article:1:auth", "aj");
//        List<String> mget = jedis.mget("article:1:title", "article:1:auth");
//        String mset1 = jedis.mset("article:1:title", "新闻标题1", "article:1:auth", "aj1");
//        List<String> mget1 = jedis.mget("article:1:title", "article:1:auth");
//
//        System.out.println(mset);
//        System.out.println(mget);
//        System.out.println(mset1);
//        System.out.println(mget1);
//
//        System.out.println(jedis.strlen("article:1:title"));
//        System.out.println(jedis.getrange("article:1:title", 0, 5));
//
//        jedis.del("log_record");
//        jedis.setnx("log_record", "");
//        for (int i = 0; i < 10; i++) {
//            jedis.append("log_record", "第" + i + "条\n");
//        }
//        System.out.println(jedis.get("log_record"));
//
//        for (int i = 0; i < 10; i++) {
//            long orderId = jedis.incr("order_id");
//            System.out.println("order_id:" + orderId);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            long count = jedis.incr("article:1");
//            System.out.println("点赞次数:" + count);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            long count = jedis.decr("article:1");
//            System.out.println("点赞次数:" + count);
//        }
//
//        String article1 = String.valueOf(System.currentTimeMillis());
//        Long article_zan = jedis.hsetnx("article_zan", article1, "0");
//        System.out.println(article_zan);
//        for (int i = 0; i < 152; i++) {
//            jedis.hincrBy("article_zan", article1, 3);
//        }
//        System.out.println(jedis.hget("article_zan", article1));
//
//        Map<String, String> map = new HashMap<>();
//        map.put("title", "标题");
//        map.put("author", "作者");
//        long id = System.currentTimeMillis();
//        jedis.hmset("article::" + id, map);
//        System.out.println(jedis.hexists("article::" + id, "title"));
//
//        for (int i = 0; i < 10; i++) {
//            jedis.lpush("qianggou", String.valueOf(i));
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(jedis.rpop("qianggou"));
//        }
//    }

//        jedis.setbit("setbit", 0, false);
//        jedis.setbit("setbit", 1, false);
//        jedis.setbit("setbit", 2, false);
//        jedis.setbit("setbit", 3, false);
//        jedis.setbit("setbit", 4, true);
//        jedis.setbit("setbit", 5, true);
//        jedis.setbit("setbit", 6, true);
//        jedis.setbit("setbit", 7, true);
//
//        String luaStr =
//                "for i=2,ARGV[1],1 do " +
//                    "if redis.call('getbit', KEYS[1], ARGV[i]) == 1 then " +
//                        "return i " +
//                    "end;" +
//                "end";
//        String luaStr = "getbit KEYS[1]";
//        String luaStr = "return {KEYS[1],KEYS[1],ARGV[1],ARGV[2]}";
//        Object result = jedis.eval(luaStr, Arrays.asList("setbit"), Arrays.asList("6", "0", "1", "2", "3", "7"));
//        System.out.println(result);
    }

}
