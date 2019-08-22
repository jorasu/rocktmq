package com.springboot.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserContent {
    private String username;
    private String pwd;
}
