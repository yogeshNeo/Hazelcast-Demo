package com.hazelcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class HazelcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastApplication.class, args);
        log.info("======================  HazelcastApplication Running ============================");
    }

}
