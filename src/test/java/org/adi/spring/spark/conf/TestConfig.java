package org.adi.spring.spark.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by achat1 on 9/22/15.
 */
@Configuration
public class TestConfig {

    @Bean public SparkConf sparkConf(){
        return new SparkConf(false);
    }

}
