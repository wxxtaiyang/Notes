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

### IOC

ICO\DI（控制翻转\依赖注入）是spring中比较核心的功能，也是其称霸java框架的主要原因之一。

容器实例化的三种方式

**1、构造器：** ICO默认实现的方式，就是构造器实现，同时在没有指定的情况下，使用无参的构造器。如果我们覆盖了无参构造器，则会出现无法注入bean的情况。

**2、静态工程：** 产生对象是一个工厂类中的静态工厂方式实现

**3、实例化工厂：** 产生对象是又一个工厂类中的方法进行创建的。只不过实例化工厂需要交给IOC容器进行创建。

实习控制反转的体现之一：依赖注入

可以控制bean对象初始属性对应的值，通过xml配置获取注解可对bean对象注入初始值。

注入方式：构造器注入、自动注入、setter注入

**主动注入注解：**

1、@Autowired：字段、构造器、set方法。装配是根据属性值Class类型装配，与属性名无关。当接口有多个实现类时，程序无法得知该装配那个实体类，通过注解@Qualifier(value = "")声明装配的实体类对象。

2、@Resource：字段、set方法。按照属性名实现自动装配，如果未找到，则按class类型装配。当接口存在多个实现类时，则通过属性name直接指定具体装配的实体类

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

### 扫描器

为了减少配置操作，也是为了减少项目中的xml，spring提供了扫描器和注解配合使用。

扫描器引入：http://www.springframework.org/schema/context
   			 http://www.springframework.org/schema/context/spring-context.xsd 

添加扫描器标签：<context:component-scan base-package="需要扫描的包"/>

引入注解： ① @Repository：习惯用于持久层

​					② @Service：习惯用于业务层

​					③ @Controller：习惯用于接口层

​					⑤ @Component：习惯用于其他层，需要托管给spirng容器管理类

扫描器开启时，通过注解引入bean，那些没有被以上注解则无法被装配置容器中。扫描器与xml存在对同一个bean引入，xml 优先级更高。

### Bean生命周期

springIOC的主要作用在于解耦。IOC是作为spring容器提供的核心技术。

Spring容器创建Bean只需要三个步骤：定义Bean、创建Bean容器/工厂、获取Bean对象

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

### SpringBoot

**概念：** 约定优于配置，简单来说就是你所期待的配置与约定的配置一致，那么就可以不做任何配置，约定不符合期待时才需要对约定进行替换配置。

**特征：** 

1. SpringBoot Starter：他将常用的依赖分组进行了整合，将其合并到一个依赖中，这样就可以一次性添加到项目的Maven或Gradle构建中。

2,使编码变得简单，SpringBoot采用 JavaConfig的方式对Spring进行配置，并且提供了大量的注解，极大的提高了工作效率，比如@Configuration和@bean注解结合，基于@Configuration完成类扫描，基于@bean注解把返回值注入IOC容器。

3.自动配置：SpringBoot的自动配置特性利用了Spring对条件化配置的支持，合理地推测应用所需的bean并自动化配置他们。

4.使部署变得简单，SpringBoot内置了三种Servlet容器，Tomcat，Jetty,undertow.我们只需要一个Java的运行环境就可以跑SpringBoot的项目了，SpringBoot的项目可以打成一个jar包。

**热部署： **通过引入spring-bootdevtools插件，可以实现不重启服务器情况下，对项目进行即时编译。

实现步骤：1、在pom.xml文件中添加如部署依赖

​					2、IDEA热部署工具设置：打开settings，打勾下图	![image-20230904110257527](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20230904110257527.png)

​					3、按住'Ctrl+Shift+Alt+/'，在Maintenance选项框中打开Registry页面，找到'compiler.automake.allow.when.app.running' 将对应的value打勾，最后单击close关闭。

#### 多配置文件使用

通常springboot配置文件存放在resources文件夹下面，配置文件可存在多个。

**配置文件存放位置：** springboot 的配置文件名字默认叫做application，支持的配置文件后缀有：properties、yaml和yml；默认优先级为properties > yml > yaml。

​	springboot默认读取配置文件的四个位置（优先级从上到下递减）：

​				项目根目录下/config目录

​				项目根目录

​				类路径/config目录下

​				类路径下![四个配置文件的存放位置](https://img-blog.csdnimg.cn/4386efa2098048b6b08c100ec5df0fa8.png)

一般情况下，我们会将项目不可更改的配置信息放在application.yml（主配置文件）中，可更改配置文件信息放在其他配置文件中。选用springboot其他配置文件方式（例如：application-dev.yml）

1、将多套配置都放在一个配置文件中，一个配置文件中配置多套环境；环境之间使用 --- 分割开

```yml
#通用配置 ,设置启用那种环境以及编写一些公共的配置
spring:
  profiles:
    active: dev   #根据名字不同，表示当前启用不同的环境
server:
  servlet:
    context-path: /demo  #假设这是公共的配置，也就是三种环境都有的相同配置可以提出来
---
#配置测试环境
spring:
  config:
    activate:
      on-profile: test  #给这套环境起名为test
server:
  port: 8081
---
#配置开发环境
spring:
  config:
    activate:
      on-profile: dev  #给这套环境起名为dev
server:
  port: 8082
```

2、将多个配置环境配置一个单独的文件，在application.yml中指定选用的配置文件。（如spring.profiles.active: dev）

3、在pom.xml文件下配置profile内容。配置后可通过maven选择运行所需环境

```powershell
<build>
	<!--分别设置开发，本地，生产环境-->
    <profiles>
        <!-- 本地环境 -->
        <profile>
            <!--定义id与maven打包时候的参数对应-->
            <id>local</id>
            <activation>
                <!--默认激活，true：激活，false：不激活-->
                <activeByDefault>true</activeByDefault>
            </activation>
            <!--配置变量，在property或者yml中使用@xxx@进行引用-->
            <properties>
                <!--配置变量名及变量值，变量名可以任意定义-->
                <environment>local</environment>
            </properties>
        </profile>
        <!-- 用户体验环境 -->
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>false</activeByDefault>
            </activation>
            <properties>
                <environment>dev</environment>
            </properties>
        </profile>
        <!-- 生产环境 -->
        <profile>
            <id>prod</id>
            <activation>
                <activeByDefault>false</activeByDefault>
            </activation>
            <properties>
                <environment>prod</environment>
            </properties>
        </profile>     
</build>
```

4、通过idea启动时选择启动项要运行的配置环境。			

#### 启动注解SpringBootApplication

这是一个组合注解，相较于其他传统的spring应用，springboot只需要一个注解就可以实现自动导入、扫描、装配等。因为该注解其中有三个核心注解：**@SpringBootConfiguration，@EnableAutoConfiguration，@ComponentScan**。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210624171039140.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxOTYwNjIz,size_16,color_FFFFFF,t_70#pic_center)

**@SpringBootConfiguration：** 实际上就是一个@Configuration 注解，让当前类作为一个配置类交由 Spring 的 IOC 容器进行管理。

**@EnableAutoConfiguration：** 这个注解是自动配置的关键，它是由 @AutoConfigurationPackage 和 @Import 注解组成的复合注解。![在这里插入图片描述](https://img-blog.csdnimg.cn/20210624172249455.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxOTYwNjIz,size_16,color_FFFFFF,t_70#pic_center)

​		@AutoConfigurationPackage：获得SpringBootApplication 标注类的包名，并把该包下的所有组件扫描到spring容器中

​		@Import：导入AutoConfigurationImportSelector类，通过getCandidateConfigurations 来获取候选的 Bean，并将其存为一个集合，最后经过去重，校验等一系列操作之后，被封装成 AutoConfigurationEntry 对象返回。

**@ComponentScan：** 扫描注解。用于定义 Spring 的扫描路径，等价于在 xml 文件中配置 context:component-scan。

其他四个注解： **@Target**用来表示注解作用范围，超过这个作用范围，编译的时候就会报错。

​							**@Retention**是用来修饰注解的，注解的注解，也称为元注解。@Retention修饰注解，用来表示注解的生命周期，生命周期的长短取决于@Retention的属性RetentionPolicy指定的值。

​							**@Documented**注解是一个标记注解，用于指示将被注解的元素包含在生成的Java文档中。

​							**@Inherited**是一个标识，用来修饰注解，如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解

#### 启动

【以下不详解，如需要请查看网址：https://zhuanlan.zhihu.com/p/362984115】

第一步：获取并启动监听器

第二步：构造应用上下文环境

第三步：初始化应用上下文

第四步：刷新应用上下文前的准备阶段

第五步：刷新应用上下文

第六步：刷新应用上下文后的扩展接口

#### 缓存

五个核心接口：

​	**CachingProvider**（缓存提供者）：创建、配置、获取、管理和控制多个CacheManager。

​	**CacheManager**（缓存管理器）：创建、配置、获取、管理和控制多个唯一命名的Cache，Cache存在于CacheManager的上下文中。一个CacheManager仅对应一个CachingProvider。

​	**Cache**（缓存）：是由CacheManager管理的，CacheManager管理Cache的生命周期，Cache存在于CacheManager的上下文中，是一个类似map的数据结构，并临时存储以key为索引的值。一个Cache仅被一个CacheManager所拥有

​	**Entry**（缓存键值对）：是一个存储在Cache中的key-value对。

​	**Expiry**（缓存时效）：每一个存储在Cache中的条目都有一个定义的有效期。一旦超过这个时间，条目就自动过期，过期后，条目将不可以访问、更新和删除操作。缓存有效期可以通过ExpiryPolicy设置。

#### 缓存注解

**@Cacheable：** 开启缓存查询 会将查询出来的值存到缓存中

**@CachePut：** 既调用方法，又更新缓存数据；一般用于更新操作。在更新缓存时一定要和想更新的缓存有相同的缓存名称和相同的key

**@CacheEvict：** 缓存清除，清除缓存时要指明缓存的名字和key，相当于告诉数据库要删除哪个表中的哪条数据，key默认为参数的值

**@CacheConfig：** 标注在类上，抽取缓存相关注解的公共配置，可抽取的公共配置有缓存名字、主键生成器等(如注解中的属性所示)