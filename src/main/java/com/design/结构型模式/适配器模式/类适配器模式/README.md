http://woquanke.com/books/gof/%E4%B8%8D%E5%85%BC%E5%AE%B9%E7%BB%93%E6%9E%84%E7%9A%84%E5%8D%8F%E8%B0%83%E2%80%94%E2%80%94%E9%80%82%E9%85%8D%E5%99%A8%E6%A8%A1%E5%BC%8F%EF%BC%88%E4%B8%89%EF%BC%89.html
类适配器
除了对象适配器模式之外，适配器模式还有一种形式，那就是类适配器模式，类适配器模式和对象适配器模式最大的区别在于适配器和适配者之间的关系不同，
对象适配器模式中适配器和适配者之间是关联关系，而类适配器模式中适配器和适配者是继承关系，类适配器模式结构如图

根据类适配器模式结构图，适配器类实现了抽象目标类接口Target，并继承了适配者类，在适配器类的request()方法中调用所继承的适配者类的specificRequest()方法，实现了适配。
class Adapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}
由于Java、C#等语言不支持多重类继承，因此类适配器的使用受到很多限制，例如如果目标抽象类Target不是接口，而是一个类，就无法使用类适配器；
此外，如果适配者Adapter为最终(Final)类，也无法使用类适配器。
在Java等面向对象编程语言中，大部分情况下我们使用的是对象适配器，类适配器较少使用。



9.5 双向适配器

在对象适配器的使用过程中，如果在适配器中同时包含对目标类和适配者类的引用，适配者可以通过它调用目标类中的方法，
目标类也可以通过它调用适配者类中的方法，那么该适配器就是一个双向适配器，其结构示意图如图

双向适配器的实现较为复杂，其典型代码如下所示：
class Adapter implements Target,Adaptee {
    //同时维持对抽象目标类和适配者的引用
    private Target target;
    private Adaptee adaptee;

    public Adapter(Target target) {
        this.target = target;
    }

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest();
    }

    public void specificRequest() {
        target.request();
    }
}

实际开发中，我们很少使用双向适配器。





