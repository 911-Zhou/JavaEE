package zdl.blogsystem.common.excepiton;


import lombok.Getter;
import zdl.blogsystem.common.enums.ResultCodeEnum;
import zdl.blogsystem.common.pojo.Result;

public class BlogException extends RuntimeException {
    @Getter
    private String message;
    @Getter
    private int code;

    public BlogException() {
    }

    public BlogException(int code) {
        this.code = code;
    }

    public BlogException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public BlogException(String message) {
        this.message = message;
    }
}