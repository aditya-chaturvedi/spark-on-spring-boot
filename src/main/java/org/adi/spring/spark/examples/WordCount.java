package org.adi.spring.spark.examples;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by achat1 on 9/23/15.
 * Just an example to see if it works.
 */
@Component
public class WordCount {

    @Autowired
    private JavaSparkContext javaSparkContext;

    @Value("${input.file}")
    private String inputFile;

    @Value("${input.threshold}")
    private int threshold;


    public void count() {

        JavaRDD<String> tokenized = javaSparkContext.textFile(inputFile).flatMap((s1) -> Arrays.asList(s1.split(" ")));

        // count the occurrence of each word
        JavaPairRDD<String, Integer> counts = tokenized
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((i1, i2) -> i1 + i2);

        // filter out words with less than threshold occurrences
        JavaPairRDD<String, Integer> filtered = counts.filter(tup -> tup._2() >= threshold);

        // count characters
        JavaPairRDD<Character, Integer> charCounts = filtered.flatMap(
                s -> {
                    Collection<Character> chars = new ArrayList<>(s._1().length());
                    for (char c : s._1().toCharArray()) {
                        chars.add(c);
                    }
                    return chars;
                }
        ).mapToPair(c -> new Tuple2<>(c, 1))
                .reduceByKey((i1, i2) -> i1 + i2);

        System.out.println(charCounts.collect());
    }
}
