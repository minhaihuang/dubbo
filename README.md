# dubbo


dubbo快速入门案例：

为了更好的明确分工，该入门案例共分为三个模块。
1，api模块。该模块只声明服务接口。
2，provider模块，服务提供者模块，用于实现api模块声明的接口。
3，consumer模块，消费者模块，该模块负责消费服务接口。

api模块目录架构
===

dubbo-api
  -src
  --main
  ---java
  ----com.hhm.dubbo.api
  -----UserService
  ---resources
  --test
  
UserService声明如下：
public interface UserService {
    String sayHello(String name);
}
只有一个声明sayHello接口

provier模块架构
===

dubbo-provider
  -src
  --main
  ---java
  ----com.hhm.dubbo.provider
  -----UserServiceImpl
  -----Main
  ---resources
  ----provider.xml
  --test
  
其中UserServiceImpl负责实现api的接口。Main负责启动该服务。provider里面是包含了dubbo的配置，用于暴露该服务。分别如下：
UserServiceImpl
---
public class UserServiceImpl implements UserService {
    public String sayHello(String name) {
        return "hello,"+name;
    }
}

Main
---
/**
 * 启动dubbo提供者
 */
public class Main {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/provider.xml");
        context.start();
        System.out.println(" Dubbo service server started!");
        System.in.read();
    }
}

provider.xml
---
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:dubbbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo
       http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <!-- 提供方应用信息，用于计算依赖关系 -->
    <dubbo:application name="dubbo2provider"/>

    <!-- 使用zookeeper注册中心暴露服务地址 --><!--资源有限，就不用多个注册中心了-->
    <dubbo:registry address="zookeeper://192.168.1.100:2181"></dubbo:registry>

    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="20880"></dubbo:protocol>

    <!-- 声明需要暴露的服务接口 -->
    <dubbo:service ref="userService" interface="com.hhm.dubbo2.api.UserService"></dubbo:service>

    <!-- 和本地bean一样实现服务 -->
    <bean id="userService" class="com.hhm.dubbo2.provider.UserServiceImpl"></bean>

</beans>

consumer模块
===

consumer模块架构如下
dubbo-provider
  -src
  --main
  ---java
  ----com.hhm.dubbo.comsumer
  -----UserConsumer
  ---resources
  ----consumer.xml
  --test
  
  UserConsumer
  ---
  public class UserConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/consumer.xml");
        context.start();
    }
}

consumer.xml
---
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" xmlns:dubbbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo
       http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <!-- 提供方应用信息，用于计算依赖关系 -->
    <dubbo:application name="dubbo2consumer"/>

    <!-- 使用zookeeper注册中心暴露服务地址 --><!--资源有限，就不用多个注册中心了-->
    <dubbo:registry address="zookeeper://192.168.1.100:2181" ></dubbo:registry>

    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference interface="com.hhm.dubbo2.api.UserService" id="userService"></dubbo:reference>

</beans>

测试
===

先启动provider的Main。
再启动consumer。
结果如下：
hello,hhm

若一个结果有多个实现会有什么结果
===
再增加一个provider2。跟provider一样，倒是实现的时候就不是hello了。而是hi。

同时启动provider和provider2，在consumer多次调用sayHello接口，发现会有不同的返回结果。
hi,hhm!
hello,hhm
hello,hhm

结论
===
若一个结果有多种实现，会返回不同的结果



