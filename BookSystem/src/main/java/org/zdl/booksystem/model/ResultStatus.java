package org.zdl.booksystem.model;

public enum ResultStatus {
    Success(200),
    Fail(-2),
    UnLogin(-1);

    private Integer Code;

    ResultStatus(Integer code) {
        Code = code;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }
}
