package com.wangpiece.ious.dto;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:05
 */
public class Code implements Serializable{

    private static final long serialVersionUID = -5751719304885734688L;

    private Integer id;
    private Integer pid;
    private String name;
    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
