package com.snnlab.springclouddataflowbatch.job;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "singlef2fjob")
public class SingleF2FJobConfigInfo {

    private String resourcePath;
    private String name;
    private Integer chunkSize;
}
