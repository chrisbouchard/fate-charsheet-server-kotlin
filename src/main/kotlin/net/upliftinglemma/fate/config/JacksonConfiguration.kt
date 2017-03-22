package net.upliftinglemma.fate.config

import com.fasterxml.jackson.datatype.guava.GuavaModule
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration {

    @Bean
    fun guavaModule() = GuavaModule()

    @Bean
    fun hibernateModule() = Hibernate5Module()

}
