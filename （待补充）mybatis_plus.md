### 介绍

MyBatis-Plus （简称 MP ）是⼀个 MyBatis 的增强⼯具，在 MyBatis 的基础上只做增强不做改变，-为简化开发、提⾼效率⽽⽣。

### 特性

**⽆侵⼊：**只做增强不做改变，引⼊它不会对现有⼯程产⽣影响，如丝般顺滑
**损耗⼩：**启动即会⾃动注⼊基本 CURD，性能基本⽆损耗，直接⾯向对象操作
**强⼤的 CRUD 操作：**内置通⽤ Mapper、通⽤ Service，仅仅通过少量配置即可实现单表⼤部分CRUD 操作，更有强⼤的条件构造器，满⾜各类使⽤需求
**⽀持 Lambda 形式调⽤：**通过 Lambda 表达式，⽅便的编写各类查询条件，⽆需再担⼼字段写错
**⽀持主键⾃动⽣成：**⽀持多达 4 种主键策略（内含分布式唯⼀ ID ⽣成器 - Sequence），可⾃由配置，完美解决主键问题
**⽀持 ActiveRecord 模式：**⽀持 ActiveRecord 形式调⽤，实体类只需继承 Model 类即可进⾏强⼤的 CRUD 操作
**⽀持⾃定义全局通⽤操作：**⽀持全局通⽤⽅法注⼊（ Write once, use anywhere ）
**内置代码⽣成器：**采⽤代码或者 Maven 插件可快速⽣成 Mapper 、 Model 、 Service 、 Controller 层代码，⽀持模板引擎，更有超多⾃定义配置等您来使⽤
**内置分⻚插件：**基于 MyBatis 物理分⻚，开发者⽆需关⼼具体操作，配置好插件之后，写分⻚等同于普通 List 查询
**分⻚插件⽀持多种数据库：**⽀持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、 Postgre、SQLServer 等多种数据库
**内置性能分析插件：**可输出 Sql 语句以及其执⾏时间，建议开发测试时启⽤该功能，能快速揪出慢查询
**内置全局拦截插件：**提供全表 delete 、 update 操作智能分析阻断，也可⾃定义拦截规则，预防误操作

### Mapper接口

在传建mapper接口时需要继承BaseMapper<~>

### 主要实现类

#### QueryWarpper

主要用于条件查询

queryWrapper.lt（）——小于

queryWrapper.le（）——小于等于

queryWrapper.gt（）——大于

queryWrapper.ge（）——大于等于

queryWrapper.eq（）——等于

queryWrapper.ne（）——不等于

queryWrapper.betweeen（“age”,10,20）——age在值10到20之间

queryWrapper.notBetweeen（“age”,10,20）——age不在值10到20之间

queryWrapper.like（“属性”,“值”）——模糊查询匹配值‘%值%’

queryWrapper.notLike（“属性”,“值”）——模糊查询不匹配值‘%值%’

queryWrapper.likeLeft（“属性”,“值”）——模糊查询匹配最后一位值‘%值’

queryWrapper.likeRight（“属性”,“值”）——模糊查询匹配第一位值‘值%’

queryWrapper.isNull（）——值为空或null

queryWrapper.isNotNull（）——值不为空或null

queryWrapper.in（“属性”，条件，条件 ）——符合多个条件的值

queryWrapper.notIn(“属性”，条件，条件 )——不符合多个条件的值

queryWrapper.or（）——或者

queryWrapper.and（）——和

queryWrapper.orderByAsc(“属性”)——根据属性升序排序

queryWrapper.orderByDesc(“属性”)——根据属性降序排序

queryWrapper.inSql(“sql语句”)——符合sql语句的值

queryWrapper.notSql(“sql语句”)——不符合SQL语句的值

queryWrapper.esists（“SQL语句”）——查询符合SQL语句的值

queryWrapper.notEsists（“SQL语句”）——查询不符合SQL语句的值

#### Page

主要用于分页。（主要属性初始值：size=10、current=1、total=0）