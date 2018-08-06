package com.simple.lambda.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2018年08月06日
 * @modifytime:
 */
public class CollectorEntity<T> {

	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

}
