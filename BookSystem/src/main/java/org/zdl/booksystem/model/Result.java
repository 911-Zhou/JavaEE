package org.zdl.booksystem.model;

import lombok.Data;

@Data
public class Result<T> {
    private ResultStatus ErrCode;
    private String ErrMsg;
    private T Data;

    public static <T>Result success(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setErrMsg("");
        result.setErrCode(ResultStatus.Success);
        return result;
    }


    //未登录
    public static Result unLogin(){
        Result result = new Result();
        result.setData(null);
        result.setErrMsg("未登录");
        result.setErrCode(ResultStatus.UnLogin);
        return result;
    }

    //请求错误
    public static Result Fail(){
        Result result = new Result();
        result.setErrCode(ResultStatus.Fail);
        result.setErrMsg("请求错误");
        result.setData(null);
        return result;
    }
}
