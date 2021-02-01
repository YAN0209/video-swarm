package pers.yan.video.common.utils;

@FunctionalInterface
public interface FunctionWrapper<T, R> {
    R apply(T t) throws Exception;
}
