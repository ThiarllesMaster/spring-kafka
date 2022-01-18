package com.kafka.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserData {

    private Integer age;
    private String name;

    UserData() {}

}
