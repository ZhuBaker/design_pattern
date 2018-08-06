package com.simple.lambda.func;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * 方法引用所引用的方法的参数列表必须要和函数式接口中抽象方法的参数列表相同（完全一致）
 * 方法引用所引用的方法的的返回值必须要和函数式接口中抽象方法的返回值相同（完全一致）
 * @time: 2018年08月06日
 * @modifytime:
 */
public class MethodReference {

    public static void main(String[] args) {
        doTest1();
        doTest2();
        doTest3();
        doTest4();
    }


    private static void doTest1() {
        Student student = new Student("ZhangSan",23);

        Supplier<String> supplier = () -> student.getName();
        System.out.println("Lambda形式： "+supplier.get());

        Supplier<String> supplier1 = student::getName ;
        System.out.println("方法引用形式： "+supplier1.get());

    }



    public static void doTest2() {
        //传统的lambda表达式
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hi: 我是Lambda表达式实现的！"); //打印：Hi: 我是Lambda表达式实现的！

        //方法引用实现
        consumer = System.out::println;
        consumer.accept("Hello : ZhangSan，我是使用方法引用实现的 ");
    }

    /**
     * 类名::静态方法名
     */
    public static void doTest3() {
        Consumer<String> consumer = (str) -> MethodReference.sayName(str);
        consumer.accept("Hello : XiangYang");
        consumer = MethodReference::sayName;
        consumer.accept("Hello : XiangYang");
    }

    /**
     * 类名::实例方法名
     *
     */
    public static void doTest4() {
        BiPredicate<String,String> biPredicate = (x , y) -> x.equals(y);
        boolean test = biPredicate.test("hello","hi");
        System.out.println(test);

        biPredicate = String::equals;
        test = biPredicate.test("hello","hello");
        System.out.println(test);
    }


    public static void sayName(String name){
        System.out.println(name);
    }


}

