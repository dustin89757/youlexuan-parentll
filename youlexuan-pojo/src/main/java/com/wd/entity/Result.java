package com.wd.entity;

import lombok.Data;

import java.io.Serializable;

/*返回结果封装类*/
@Data
public class Result implements Serializable {
    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
