package com.yicj.hello.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: yicj
 * @date: 2023/4/22 21:02
 */
@Data
@ConfigurationProperties(prefix = "mytask.pool")
public class MyThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
