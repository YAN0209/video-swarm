package pers.yan.video.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author likaiyan
 * @date 2020/11/20 10:06 上午
 */
@Slf4j
public class LambdaUtils {

    public static <T> Consumer<T> wrapper(ConsumerWrapper<T> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T, R> Function<T, R> wrapper(FunctionWrapper<T, R> function){
        return i -> {
            try{
                return function.apply(i);
            }catch (Exception ex){
                log.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        };
    }

}
