package com.qf.test;

import com.alibaba.fastjson.JSON;
import com.qf.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen
 * @data 2020/8/5 14:43
 */
public class JedisTest {
    public static void main(String[] args) {
        //Jedis jedis = new Jedis("106.14.99.129", 6379);
        Jedis jedis = new Jedis("192.168.64.129", 6379);
        jedis.set("username", "jack");
        System.out.println(jedis.get("username"));
    }

    @Test
    public void testJedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.64.128", 6379);
        Jedis jedis = jedisPool.getResource();
        User user = new User("张三", 28);
        jedis.set("user", JSON.toJSONString(user));
        String s = jedis.get("user");
        System.out.println(s);
    }

    @Test
    public void jedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "106.14.99.129", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.sadd("set", "jack", "rose", "tom", "jerry");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);
    }
}
