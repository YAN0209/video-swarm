package pers.yan.video.common.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pers.yan.video.common.common.ResponseCode;
import pers.yan.video.common.exception.ApiRuntimeException;

import java.util.StringJoiner;

/**
 * @author likaiyan
 * @date 2020/6/11 5:36 下午
 */
@Aspect
@Component
public class ApiParameterValidAspect {

    @Before("execution(* pers.yan.video.*.controller.*.*(..)) && args(.., result))")
    public void doBefore(JoinPoint joinPoint, BindingResult result) throws ApiRuntimeException {
        if (result.hasErrors()) {
            StringJoiner joiner = new StringJoiner(",");
            for (ObjectError error : result.getAllErrors()) {
                if (error instanceof FieldError) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("[");
                    builder.append(((FieldError) error).getField());
                    builder.append("]");
                    builder.append(error.getDefaultMessage());
                    joiner.add(builder);
                }
            }
            throw new ApiRuntimeException(ResponseCode.VALIDATE_FAILED, joiner.toString());
        }

    }

}
