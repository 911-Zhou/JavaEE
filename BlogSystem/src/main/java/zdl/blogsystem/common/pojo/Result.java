package zdl.blogsystem.common.pojo;

import lombok.Data;
import zdl.blogsystem.common.enums.ResultCodeEnum;

import java.util.Stack;

/**
 * 统一结果返回类
 */

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T>Result success(T data){
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result fail(String errMsg){
        Result result = new Result();
        result.setMessage(ResultCodeEnum.FAIL.getMessage() + errMsg);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        return result;
    }

    public static <T>Result fail(String errMsg,T data){
        Result result = new Result();
        result.setMessage(ResultCodeEnum.FAIL.getMessage() + errMsg);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setData(data);
        return result;
    }


    public static Result unLogin(){
        Result result = new Result();
        result.setCode(ResultCodeEnum.UNLOGIN.getCode());
        result.setMessage(ResultCodeEnum.UNLOGIN.getMessage());
        return result;
    }

    public static Result serverError(String errMsg){
        Result result = new Result();
        result.setMessage(ResultCodeEnum.SERVER_ERROR.getMessage() + ";" + errMsg);
        result.setCode(ResultCodeEnum.SERVER_ERROR.getCode());
        return result;
    }

    public static Result validateFailed(String message){
        Result result = new Result();
        result.setCode(ResultCodeEnum.VALIDATE_FAILED.getCode());
        result.setMessage(ResultCodeEnum.VALIDATE_FAILED.getMessage()+"," + message);
        return result;
    }

    public static Result  noResourceFound(String message){
        Result result = new Result();
        result.setCode(ResultCodeEnum.NOT_FOUND.getCode());
        result.setMessage(ResultCodeEnum.NOT_FOUND.getMessage() + "," + message);
        return result;
    }
}
