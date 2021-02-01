package pers.yan.video.common.utils;

@FunctionalInterface
public interface PredicateWrapper<T> {
    boolean test(T t) throws Exception;
}