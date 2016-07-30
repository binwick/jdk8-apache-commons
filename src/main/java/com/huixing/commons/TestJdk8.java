package com.huixing.commons;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class TestJdk8 {
    @Test
    public void testStream() {
        List<String> strings = Arrays.asList("222", "333", "111", "222");
        List<String> collect1 = strings.stream()
                .sorted(String::compareTo) // 方法引用
                .collect(Collectors.toList());
        System.out.println(collect1);

        //
        List<String> collect2 = strings.stream()
                .distinct() // 去重复
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println(collect2);

        //
        List<Long> collect3 = strings.stream()
                .map(item -> Long.valueOf(item) / 111) // 重新组合新的列表
                .collect(Collectors.toList());
        System.out.println(collect3);

        //
        Long sum = strings.stream()
                .map(item -> Long.valueOf(item) / 111) // 重新组合新的列表
                .reduce(Long::sum)
                .get();
        System.out.println(sum);

    }

    @Test
    public void testLambda() {
        Runnable runnable = () -> {
            System.out.println("test lambda...");
        };

        new Thread(runnable).start();
    }

    @Test
    public void testLocalDate() {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 时间戳 秒
        System.out.println(now.toEpochMilli()); // 时间戳 毫秒

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.plusDays(1));

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.plus(1, ChronoUnit.DAYS));
    }
}
