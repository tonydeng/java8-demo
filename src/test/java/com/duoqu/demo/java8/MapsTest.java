package com.duoqu.demo.java8;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Created by tonydeng on 15/6/2.
 */
public class MapsTest extends BaseTest {

    @Test
    public void foreachTest() {
        Map<Integer, String> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> log.info("key:'{}', value:'{}'", id, val));
    }

    @Test
    public void functionTest() {
        Map<Integer, String> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        log.info("key 3 value:'{}'", map.get(3));

        log.info("map computeIfPresent:'{}'", map.computeIfPresent(9, (num, val) -> null));

        log.info("key 9 containsKey:'{}'", map.containsKey(9));

        log.info("computeIfAbsent 23 :'{}'", map.computeIfAbsent(23, num -> "val" + num));

        log.info("map.containsKey(23):'{}'", map.containsKey(23));

        log.info("computeIfAbsent 3:'{}'", map.computeIfAbsent(3, num -> "bam"));
        log.info("get 3:'{}'", map.get(3));

        map.remove(3,"val");

        log.info("remove get 3:'{}'",map.get(3));

        map.remove(3,"val3");

        log.info("remove get 3:'{}'",map.get(3));

        log.info("getDefault:'{}'",map.getOrDefault(43,"not found"));

        log.info("merge:'{}'",map.merge(9,"val9", (value,newValue) -> value.concat(newValue)));

        log.info("merge:'{}'",map.merge(9,"count", (value,newValue) -> value.concat(newValue)));
    }
}
