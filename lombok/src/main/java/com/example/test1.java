package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成getter,setter,toString等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor //生成无参构造函数
@Builder
public class test1 {
    String name;
    String age;
    String sex;
}
