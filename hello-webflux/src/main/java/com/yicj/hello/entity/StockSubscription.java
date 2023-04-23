package com.yicj.hello.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yicj
 * @date: 2023/4/23 16:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockSubscription {

    private String email ;

    private String symbol ;
}
