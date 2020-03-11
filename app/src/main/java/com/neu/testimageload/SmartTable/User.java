package com.neu.testimageload.SmartTable;

/**
 * created by Viki on 2020/2/26
 * system login name : lg
 * created time : 15:37
 * email : 710256138@qq.com
 */
public class User {
    private String name;
    private Integer age;
    private Boolean operation;//用于记录是否被选中的状态

    public User(String name, Integer age, boolean operation){
        this.name = name;
        this.age = age;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getOperation() {
        return operation;
    }

    public void setOperation(Boolean operation) {
        this.operation = operation;
    }
}

