package com.yicj.hello.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yicj
 * @date: 2023/4/16 19:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    String body;

}
