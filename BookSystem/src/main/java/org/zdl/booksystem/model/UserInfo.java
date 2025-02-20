package org.zdl.booksystem.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer ID;
    private String userName;
    private String password;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
