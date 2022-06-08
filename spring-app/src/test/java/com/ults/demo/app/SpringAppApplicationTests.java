package com.ults.demo.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class SpringAppApplicationTests {

	@Test
	void contextLoads() {

		String str = "Hello World";
		System.out.println(str.substring(7, str.length()));

		String[] abc = new String[10];

		IntStream.of(1,2,3,4,5,6).filter(x -> x%2==0 ).forEach(y -> System.out.println(y));

		IntStream.range(0,101).forEach(System.out::println);

		Stream.of("Hello","World").forEach(y -> System.out.println(y));

	}

}
