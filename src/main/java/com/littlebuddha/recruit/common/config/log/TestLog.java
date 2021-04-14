package com.littlebuddha.recruit.common.config.log;

import com.littlebuddha.recruit.test.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestLog {

    private static final Logger LOG = LoggerFactory.getLogger(TestLog.class);

    @Bean
    public Person logMethod() {
        LOG.info("==========print log==========");
        return new Person();
    }
}
