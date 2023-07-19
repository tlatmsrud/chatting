package org.ssk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */
@SpringBootApplication
@EnableJpaAuditing
public class ChattingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChattingApplication.class, args);
    }

}

