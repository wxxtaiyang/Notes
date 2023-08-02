### 简介

Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。

**控制反转（IOC）**：是面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。将**对象的创建**权力交付给spring容器实现，我们在使用对象时直接从容器获取。

**面向切面（AOP）**：通过分离应用的业务逻辑与系统级服务进行内聚性的开发。在不改变源代码的基础上添加新的业务功能逻辑。核心原理是使用动态代理模式在方法执行前后或出现异常时加入相关逻辑。

### 三大核心思想

**AOP（面向切面）、IOC（控制反转）、DI（依赖注入）**

AOP（面向切面）：基于OOP（面向对象）基础之上进行横向开发

​	优点：提高代码的可重用性、业务代码编码更简洁、业务代码维护更高效、业务功能扩展更便捷

IOC（控制反转）：传统模式中，资源的主控权在类中，类需要哪一项资源直接就自己创建出来。在IOC模式下，类所需要的资源统一由spring提供，主动变被动。

DI（依赖注入）：应用程序运行依赖的资源由spring为其提供，资源进入应用程序的方式称为注入

​	（eg：DI是IOC的一种体现形式）

### 七大模块

Spring core：这是Spring框架最基础的部分，它提供了依赖注入（DependencyInjection）特征来实现容器对Bean的管理。

Spring context：基于bean，提供上下文信息。这个模块扩展了BeanFactory的概念，增加了对国际化（I18N）消息、事件传播以及验证的支持。

Spring dao：提供JDBC的抽象层，消除冗长的JDBC编码和解析数据工厂特有的错误，提供事务管理

Spring orm：提供了常用的“对象/关系”映射API的集成层。包括Hibernate、Mybatis等

Spring aop：提供了对面向切面编程的丰富支持。

Spring web：提供了基础的web开发的上下文信息，可与其他web进行集成

Spring web MVC：提供了web应用的model-view-controller全功能的实现

### 事务的实现方式

spring事务实现的方式分为两种：编程式事务管理、声明式事务管理

​	**编程式事务** ：在代码中进行事务控制。优点：精度高。缺点：代码耦合度高

​	**声明式事务** ：通过@Transactional注解实现事务控制

事务的操作本来应该由数据库进行控制，但是为了方便用户进行业务逻辑的控制，spring对事务功能进行了扩展实现。

Spring的事务管理是通过AOP代理实现的，对被代理对象的每个方法进行拦截，在方法执行前启动事务，在方法执行完成后根据是否有异常及异常的类型进行提交或回滚。

**实现原理** ：当在某个类或者方法上使用@Transactional注解后，spring会基于该类生成一个代理对象，并将这个代理对象作为bean。当调用这个代理对象的方法时，如果有事务处理，则会先关闭事务的自动功能，然后执行方法的具体业务逻辑，如果业务逻辑没有异常，那么代理逻辑就会直接提交，如果出现任何异常，那么直接进行回滚操作。当然我们也可以控制对哪些异常进行回滚操作。

### Bean生命周期

springIOC的主要作用在于解耦。IOC是作为spring容器提供的核心技术。

bean生命周期：

1. Spring启动，查找并加载需要被Spring管理的bean，进行Bean的实例化
2. Bean实例化后对将Bean的引入和值注入到Bean的属性中
3. 如果Bean实现了BeanNameAware接口的话，Spring将Bean的Id传递给setBeanName()方法
4. 如果Bean实现了BeanFactoryAware接口的话，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入
5. 如果Bean实现了ApplicationContextAware接口的话，Spring将调用Bean的setApplicationContext()方法，将bean所在应用上下文引用传入进来。
6. 如果Bean实现了BeanPostProcessor接口，Spring就将调用他们的postProcessBeforeInitialization()方法。
7. 如果Bean 实现了InitializingBean接口，Spring将调用他们的afterPropertiesSet()方法。类似的，如果bean使用init-method声明了初始化方法，该方法也会被调用
8. 如果Bean 实现了BeanPostProcessor接口，Spring就将调用他们的postProcessAfterInitialization()方法。
9. 此时，Bean已经准备就绪，可以被应用程序使用了。他们将一直驻留在应用上下文中，直到应用上下文被销毁。
10. 如果bean实现了DisposableBean接口，Spring将调用它的destory()接口方法，同样，如果bean使用了destory-method 声明销毁方法，该方法也会被调用。

### IOC



### AOP

AOP面向切面编程，可以说是OOP（面向对象编程）的补充和完善。使用“横切”技术，把软件分为两个部分：核心关注点和横切关注点。

​	业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。

​	横切关注点的一个特点是经常发生在核心关注点多处，而且各处基本相似。AOP的作用在于分离系统中的关注点，将核心关注点和横切关注点分离。

**核心概念** ：1、通知：定义了切面是**什么**和**何时**使用

​					2、切入点：通知发生的**位置**

​					3、切面：通知和切入点共同组成了切面

​					4、目标：被**切入对象**

​					5、代理：应用通知的对象

​					6、织入：将切面应用到目标对象并导致代理对象创建过程。

**面向切面实现方式** ：

1、通过xml配置

```xml
<!--    目标对象-->
<bean id="lianFeng" class="com.cykj.aop.LianFeng" />
<!--    事件 -->
<bean id="sleppBefore" class="com.cykj.aop.SleepBefore"/>
<!--    配置切入点和通知事件-->
 <bean id="sleepAdvisor" 	class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
<!--        切入事件-->
		<property name="advice" ref="sleppBefore"/>
<!--        切入点-->
        <property name="pattern" value=".*sleep"/>
</bean>

<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
```

2、通过注解

```java
@Aspect // 设置为切面
@Component
public class SleepAdvisor {
    // 定义切入点（可定义多个）
    // * 匹配任意数量字符的重复
    // ..在类模型中匹配任意数量子包，在方法中匹配任意数量参数
    // + 匹配指定类型的子类型，仅能作为后缀放在类后面
    @Pointcut("execution(public * *.sleep*(..))")
    public void pointcut(JoinPoint joinPoint){
    }
	
    @Before("pointcut(joinPoint)")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("睡前敷面膜");
    }
}
//前置增强（目标方法执行前） @Before
//后置增强（目标方法执行后） @AfterReturning
//环绕增强（目标方法前后同时执行） @Around
//异常抛出增强（目标方法抛出异常后增强） @AfterThrowing
//最终增强（无论如何最后都增强） @After
相同通知的执行顺序是：从上向下
AfterThrowing和Around不能同时出现，同时出现是AfterThrowing失效。
```

