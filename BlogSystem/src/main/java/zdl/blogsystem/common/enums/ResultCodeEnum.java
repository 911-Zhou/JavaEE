package zdl.blogsystem.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResultCodeEnum {
 SUCCESS(200,"操作成功"),
 FAIL(-1,"请求失败"),
 BAD_REQUEST(400, "请求参数错误"),
 NOT_FOUND(404, "资源未找到"),
 SERVER_ERROR(500, "服务器内部错误"),
 VALIDATE_FAILED(422, "参数校验失败"),
 UNLOGIN(401,"未登录或登录已过期");


 @Getter
 int code;
 @Getter
 private final String message;
}