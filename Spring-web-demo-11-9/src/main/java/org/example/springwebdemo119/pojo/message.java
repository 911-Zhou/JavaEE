package org.example.springwebdemo119.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class message {
    private Integer id;
    private String  from;
    private String to;
    private String message;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
