一、 CallbackFilter 作用

在CGLib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。

在JDK动态代理中并没有类似的功能，对InvocationHandler接口方法的调用对代理类内的所以方法都有效。

