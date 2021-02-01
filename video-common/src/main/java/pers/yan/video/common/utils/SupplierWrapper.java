package pers.yan.video.common.utils;

@FunctionalInterface
public interface SupplierWrapper<T> {
    T get() throws Exception;
}
