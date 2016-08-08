package com.huixing.commons;

import com.huixing.commons.functional.Converter;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
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
                .reduce(0L, (x, y) -> x + y);
//                .reduce(Long::sum)
//                .get();
        System.out.println(sum);

    }

    @Test
    public void testLambda() {
        Runnable runnable = () -> {
            System.out.println("test lambda...");
        };
        new Thread(runnable).start();

        Arrays.asList("111", "222", "333").forEach(System.out::println);
        Arrays.asList("111", "222", "333").forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void testOptional() {
        Optional<String> fullName =
                //Optional.ofNullable(null);
                Optional.ofNullable("赵日天");
        System.out.println("is preset:" + fullName.isPresent());
        System.out.println("full name:" + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        fullName.ifPresent(System.out::println);
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

    @Test
    public void testConverter() {
        Converter<String, Integer> converter =
                // from -> Integer.valueOf(from);
                Integer::valueOf;
        Integer convert = converter.convert("1234");
        System.out.println(convert);
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        eval(list, n -> n > 3);
        eval(list, n -> true);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}


