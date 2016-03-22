package com.github.tonydeng.demo.java8;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by tonydeng on 15/6/2.
 */
public class DateTest extends BaseTest {
    @Test
    public void clockTest(){
        Clock clock = Clock.systemDefaultZone();
        long millis =clock.millis();
        log.info("millis:'{}'",millis);
        Instant instant = clock.instant();

        Date legacyDate = Date.from(instant);

        log.info("legacyDate:'{}'",legacyDate);

    }

    @Test
    public void timezonesTest(){
        log.info("getAvailableZoneIds:'{}'",ZoneId.getAvailableZoneIds());

        ZoneId zone = ZoneId.of("Asia/Shanghai");

        log.info("zone getRules:'{}'",zone.getRules());
    }
}
