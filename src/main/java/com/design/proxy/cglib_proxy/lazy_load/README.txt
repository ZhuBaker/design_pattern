ExecLazyLoderMain
一、作用：
说到延迟加载，应该经常接触到，尤其是使用Hibernate的时候，本篇将通过一个实例分析延迟加载的实现方式。
LazyLoader接口继承了Callback，因此也算是CGLib中的一种Callback类型。

二、示例：
首先定义一个实体类LoaderBean，该Bean内有一个需要延迟加载的属性PropertyBean。

loaderNameA
123
after get...
LazyLoader loadObject() ...
lazy-load object propertyName!
11
after...
lazy-load object propertyName!


注意，第一次获取property bean的属性时，会触发代理类回调方法。第二次再获取property bean的属性时，就直接返回属性值而不会再次触发代理类回调方法了。

可见，延迟加载原理：
对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，就会自动触发代理类回调）。




改进版延迟加载Dispatcher
ExecDispatcherMain
一、作用：

上一篇文章中，介绍了延迟加载器LazyLoader。本篇介绍另一种延迟加载接口Dispatcher。

Dispatcher接口同样继承于Callback，也是一种回调类型。

但是Dispatcher和LazyLoader的区别在于：LazyLoader只在第一次访问延迟加载属性时触发代理类回调方法，而Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法。


