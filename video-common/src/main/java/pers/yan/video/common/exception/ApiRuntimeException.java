package pers.yan.video.common.exception;

import lombok.Getter;
import pers.yan.video.common.common.ResponseCode;


/**
 * @author likaiyan
 * @date 2020/4/1 10:13 上午
 */
@Getter
public class ApiRuntimeException extends RuntimeException {

    private ResponseCode responseCode;

    public ApiRuntimeException(ResponseCode responseCode, String message){
        super(message);
        this.responseCode = responseCode;
    }

    public ApiRuntimeException(ResponseCode responseCode){
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public ApiRuntimeException(String message){
        super(message);
        this.responseCode = ResponseCode.FAILED;
    }
}
