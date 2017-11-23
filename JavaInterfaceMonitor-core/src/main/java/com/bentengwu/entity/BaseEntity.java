package com.bentengwu.entity;

import com.bentengwu.RootObject;

import java.io.IOException;

/**
 * The base entity for entity
 * @Author <a href="bentengwu@163.com">thender.xu</a>
 * @Date 2017/11/23 16:05.
 */
public class BaseEntity extends RootObject{
    /**
     * The type of bizz
     */
    protected String bizzType ;


    public String getBizzType() {
        return bizzType;
    }

    public void setBizzType(String bizzType) {
        this.bizzType = bizzType;
    }
}
