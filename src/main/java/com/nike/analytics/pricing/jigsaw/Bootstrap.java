package com.nike.analytics.pricing.jigsaw;

import com.nike.analytics.pricing.jigsaw.examples.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private WordCount wordCount;

    @Override
    public void run(String... args) throws Exception {
        wordCount.count();
    }

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
