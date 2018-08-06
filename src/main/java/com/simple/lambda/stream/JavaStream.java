package com.simple.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class JavaStream {
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();

		int count = 0;
		for (Person person : persons) {
			if(person.getAge() > 20) {
				count ++;
			}
		}
		Predicate predicate = new Predicate<Person>() {
			@Override
			public boolean test(Person o) {
				return o.getAge() > 20 ;
			}
		};
		long count1 = persons.stream()
				.filter(predicate)
				.count();
		long count2 = persons.stream()
				.filter(person -> person.getAge() > 20)
				.count();
	}
}
