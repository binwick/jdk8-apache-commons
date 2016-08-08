package com.huixing.commons.functional;


@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
