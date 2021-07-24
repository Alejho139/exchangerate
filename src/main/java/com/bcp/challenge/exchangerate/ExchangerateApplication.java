package com.bcp.challenge.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ExchangerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangerateApplication.class, args);

		Integer[] integers = {1, 2, 8, 23, 5, 15, 17, 15};
		List<Integer> integers1 = new ArrayList<>();

		int count = 0;
		for (int i = 0; i < integers.length-1; i++) {

			for (int j = 1; j <= integers[i]; j++) {
				if (integers[i] % j == 0 ) {
					count++;
				}
			}

			if (count == 2) {
				integers1.add(integers[i]);
			}
			count = 0;
		}

		Collections.sort(integers1);
		System.out.println(integers1);
	}

}
