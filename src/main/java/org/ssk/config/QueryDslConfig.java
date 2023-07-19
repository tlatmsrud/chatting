package org.ssk.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {

    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
