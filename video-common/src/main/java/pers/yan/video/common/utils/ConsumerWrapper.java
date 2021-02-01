package pers.yan.video.common.utils;

/**
 * @author likaiyan
 * @date 2020/11/20 10:24 上午
 */
@FunctionalInterface
public interface ConsumerWrapper<T> {
    void accept(T t) throws Exception;
}