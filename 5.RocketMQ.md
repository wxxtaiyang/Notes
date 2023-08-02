# RocketMQ

# 1. RocketMQ 简介

​         RocketMQ 是阿里巴巴开源的分布式消息中间件。支持事务消息、顺序消息、批量消息、定时消息、消息回溯等。它里面有几个区别于标准消息中件间的概念，如Group、Topic、Queue等。系统组成则由Producer、Consumer、Broker、NameServer等。

作用： ？



## RocketMQ 特点

- 支持发布/订阅（Pub/Sub）和 点对点（P2P）消息模型；

- 在一个队列中可靠的先进先出（FIFO）和严格的顺序传递，RocketMQ 可以保证严格的消息顺序，而ActiveMQ 无法保证；

- 支持拉（Pull）和推（Push）两种消息模式；Push 好理解，比如在消费者端设置 Listener 回调；而 Pull，控制权在于应用，即应用需要主动的调用拉消息方法从 Broker 获取消息，这里面存在一个消费位置记录的问题（如果不记录，会导致消息重复消费）；

- 单一队列百万消息的堆积能力；RocketMQ 提供亿级消息的堆积能力，这不是重点，重点是堆积了亿级的消息后，依然保持写入低延迟；

- 支持多种消息协议，如 JMS、MQTT 等；

- 分布式高可用的部署架构，满足至少一次消息传递语义；RocketMQ 原生就是支持分布式的，而ActiveMQ 原生存在单点性；

- 提供 docker 镜像用于隔离测试和云集群部署；

- 提供配置、指标和监控等功能丰富的 Dashboard。
  

## Broker

​        Broker 其实就是 RocketMQ 服务器，负责存储消息、转发消息。Broker 在 RocketMQ 系统中负责接收从生产者发送来的消息并存储、同时为消费者的拉取请求作准备。Broker 也存储消息相关的元数据，包括消费者组、消费进度偏移和主题和队列消息等。

Broker Server 是 RocketMQ 真正的业务核心，包含了多个重要的子模块：

- 路由模块：整个 Broker 的实体，负责处理来自 clients 端的请求。

- 客户端管理：负责管理客户端(Producer/Consumer)和维护 Consumer 的 Topic 订阅信息

- 存储服务：提供方便简单的 API 接口处理消息存储到物理硬盘和查询功能。

- 高可用服务：高可用服务，提供 Master Broker 和 Slave Broker 之间的数据同步功能。

- 消息索引服务：根据特定的 Message key 对投递到 Broker 的消息进行索引服务，以提供消息的快速查询。
  

## NameServer

NameServer 是一个非常简单的 Topic 路由注册中心，其角色类似 Dubbo 中的 zookeeper，支持 Broker 的动态注册与发现。

主要包括两个功能：

- Broker 管理：NameServer 接受 Broker 集群的注册信息并且保存下来作为路由信息的基本数据。然后提供心跳检测机制，检查 Broker 是否还存活；

- 路由信息管理：给 Producer 和 Consumer 提供服务获取 Broker 列表。每个 NameServer 将保存关于 Broker 集群的整个路由信息和用于客户端查询的队列信息。然后 Producer 和 Conumser 通过 NameServer 就可以知道整个 Broker 集群的路由信息，从而进行消息的投递和消费。
  

# 2. 使用 Docker 快速搭建 RocketMQ 4.4

​        rocketmq 需要部署 broker 与 nameserver ，考虑到分开部署比较麻烦，这里将会使用 docker-compose。另外，还需要搭建一个 web 可视化控制台，可以监控 mq 服务状态，以及消息消费情况，这里使用 rocketmq-console，同样该程序也将使用 docker 安装。

# 3.Windows下RocketMQ的配置和运行

## 3.1下载4.4版本

https://rocketmq.apache.org/zh/download/

https://archive.apache.org/dist/rocketmq/4.4.0/rocketmq-all-4.4.0-bin-release.zip

## 3.2配置

- 环境变量   ROCKETMQ_HOME   路径：D:\Java\rocketmq-all-4.4.0-bin-release

- 修改runserver.cmd

部署完成后进入bin目录，修改runserver.cmd的JAVA_OPT为，大小可自己调整

```
rem set "JAVA_OPT=%JAVA_OPT% -server -Xms2g -Xmx2g -Xmn1g"
set "JAVA_OPT=%JAVA_OPT% -server -Xms256m -Xmx512m"
```

- 修改runbroker.cmd

修改runbroker.cmd的JAVA_OPT为，大小可自己调整

```
rem set "JAVA_OPT=%JAVA_OPT% -server -Xms2g -Xmx2g -Xmn1g"
set "JAVA_OPT=%JAVA_OPT% -server -Xms256m -Xmx512m"
```

## 3.3启动

```
D:\Java\rocketmq-all-4.4.0-bin-release\bin>mqnamesrv.cmd
```



```
D:\Java\rocketmq-all-4.4.0-bin-release\bin>mqbroker.cmd -n localhost:9876
```



![image-20230305204448605](E:\传一工作目录\4.0课纲\四阶段\SpringCloudAlibaba微服务\5.RocketMQ\5.RocketMQ.assets\image-20230305204448605.png)

rocketmq启动 错误: 找不到或无法加载主类 Files\Java\jdk1.8.0_181\lib；C:\Program

打开 **runbroker.cmd ，**将CLASSPATH 加上英文双引号，重启即可。

## 4.搭建MQUI界面

官网下载项目

https://gitcode.net/mirrors/apache/rocketmq-dashboard?utm_source=csdn_github_accelerator

https://github.com/apache/rocketmq-dashboard

![image-20230305205211645](E:\传一工作目录\4.0课纲\四阶段\SpringCloudAlibaba微服务\5.RocketMQ\5.RocketMQ.assets\image-20230305205211645.png)



下载后将其解压，命名rocketmq_dashboard_8600，搞到IDEA中跑起来，这是一个springboot项目，然后就是访问  http://localhost:8600/#/









![image-20230305200155337](E:\传一工作目录\4.0课纲\四阶段\SpringCloudAlibaba微服务\5.RocketMQ\5.RocketMQ.assets\image-20230305200155337.png)

# 4. 在 Spring 项目中引入 RocketMQ 客户端

## 4.1**添加 pom 文件依赖**：

```xml
        <dependency>
        <groupId>org.apache.rocketmq</groupId>
        <artifactId>rocketmq-spring-boot-starter</artifactId>
        <version>2.1.0</version>
    </dependency>
```

## 4.2**在 application.yml 添加配置**：

```yaml
rocketmq:
  name-server: 127.0.0.1:9876
  producer:
    group: producer-demo1
  consumer:
    group: consumer-demo1
```

## 4.3生产者producer

```java
package com.chengfurong.handler;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RocketProducerHandler
 * @Description TODO
 * @Author chengfurong
 * @Date 2022/3/25 9:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/rocket/producer")
public class RocketProducerHandler {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/send")
    public String send(){

        for(int i=0;i<10;i++){
            rocketMQTemplate.convertAndSend("rocket-send1","rocket-testA-"+i);
        }

        return "send ok";
    }
}

```

## 4.4消费者consumer

```java
package com.chengfurong.service.impl;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName RocketConsumerListener
 * @Description TODO
 * @Author chengfurong
 * @Date 2022/3/25 9:21
 * @Version 1.0
 **/
@RocketMQMessageListener(topic = "rocket-send1",consumerGroup ="${rocketmq.consumer.group}")
@Component
public class RocketConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("consumer 收到消息：" + s);
    }
}
```

## 4.5运行

http://localhost:9000/rocket/producer/send

# 5.主流消息队列应用场景

消息队列（Message Queue，简称 MQ）是构建分布式互联网应用的基础设施，通过 MQ 实现的松耦合架构设计可以提高系统可用性以及可扩展性，是适用于现代应用的最佳设计方案。MQ 产品生态丰富，多个子产品线联合打造金融级高可用消息服务以及对物联网的原生支持，覆盖金融保险、(新)零售、物联网、移动互联网、传媒泛娱乐、教育、物流、能源、交通等行业。本文对现在主流消息队列做一个总结。消息队列中间件是分布式系统中重要的组件，主要实现异步消息，应用解耦，流量削峰及消息通讯等功能。下面举例说明在实际应用中消息队列是如何使用的。

## 5.1、消息队列应用场景

### 1、异步处理

以用户注册，并且需要注册邮件和短信为例。用户注册后，需要发送注册邮件和注册短信。传统的做法有两种：串行和并行方式。如下图所示：

1）串行方式：将注册信息写入数据库成功后，发送注册邮件，再发送注册短信。以上三个任务全部完成后，返回给客户端。

![img](RocketMQ.assets/aa18972bd40735fab98ceccc96beebba0e2408bb.png)



2）并行方式：将注册信息写入数据库成功后，发送注册邮件的同时，发送注册短信。以上三个任务完成后，返回给客户端。与串行的差别是，并行的方式可以提高处理的时间。假设三个业务节点每个使用50毫秒钟，不考虑网络等其他开销，则串行方式的时间是150毫秒，并行的时间可能是100毫秒。因为CPU在单位时间内处理的请求数是一定的，假设CPU1秒内吞吐量是100次。则串行方式1秒内CPU可处理的请求量是7次（1000ms/150ms），并行方式处理的请求量是10次（1000ms/100ms）

引入消息队列，将不是必须的业务逻辑，异步处理。改造后的架构如下：

![img](RocketMQ.assets/dcc451da81cb39db19691494d1f9ed2da91830bb.png)

按照以上约定，用户的响应时间相当于是注册信息写入数据库的时间，也就是50毫秒。注册邮件，发送短信写入消息队列后，直接返回，因此写入消息队列的速度很快，基本可以忽略，因此用户的响应时间可能是50毫秒。

因此架构改变后，系统的吞吐量提高到每秒20 QPS。比串行提高了3倍，比并行提高了两倍。

### 2、应用解耦

以用户下单购买业务为例。

用户下单后，订单系统需要通知库存系统。传统的做法是，订单系统调用库存系统的接口。如下图

![img](RocketMQ.assets/960a304e251f95caa419c4c635f99b37650952ce.png)

传统模式的缺点：

1）假如库存系统无法访问，则订单减库存将失败，从而导致订单失败。

2）订单系统与库存系统耦合。

引入应用消息队列后的方案，如下图：

![img](RocketMQ.assets/b8014a90f603738d18f54c514ff55458f919ec7b.png)

1）订单系统：用户下单后，订单系统完成持久化处理，将消息写入消息队列，返回用户订单下单成功。

2）库存系统：订阅下单的消息，采用拉/推的方式，获取下单信息，库存系统根据下单信息，进行库存操作。

假如：在下单时库存系统不能正常使用。也不影响正常下单，因为下单后，订单系统写入消息队列就不再关心其他的后续操作了。实现订单系统与库存系统的应用解耦。

### 3、流量削峰

流量削峰也是消息队列中的常用场景，一般在秒杀或团抢活动中使用广泛。秒杀活动，一般会因为流量过大，导致流量暴增，应用挂掉。为解决这个问题，需要在应用前端加入消息队列。

1）可以控制活动的人数。

2）可以缓解短时间内高流量压垮应用。

![img](RocketMQ.assets/a5c27d1ed21b0ef450e40b41222ab5d380cb3e9d.png)

1）用户的请求，服务器接收后，首先写入消息队列。假如消息队列长度超过最大数量，则直接抛弃用户请求或跳转到错误页面。

2）秒杀业务根据消息队列中的请求信息，再做后续处理。

### 4、消息通讯

消息通讯是指，消息队列一般都内置了高效的通信机制，因此也可以用作消息通讯。比如实现点对点消息队列，或者聊天室等。

![img](RocketMQ.assets/0eb30f2442a7d933a66245e1aea4351a71f00156.png)

以上实际是消息队列的两种消息模式，点对点或发布订阅模式。

## 5.2、常用消息队列各项对比

**生产者消费者模式（Producer-Consumer）**

ActiveMQ-支持，RabbitMQ-支持，RocketMQ-支持，Kafka-支持。

**发布订阅模式（Publish-Subscribe）**

ActiveMQ-支持，RabbitMQ-支持，RocketMQ-支持，Kafka-支持。

**请求回应模型（Request-Reply）**

ActiveMQ-支持，RabbitMQ-支持，RocketMQ-不支持，Kafka-不支持。

**API完备性**

ActiveMQ-高，RabbitMQ-高，RocketMQ-高，Kafka-高。

**多语言支持**

ActiveMQ-支持，RabbitMQ-支持，RocketMQ-只支持JAVA，Kafka-支持。

**单机吞吐量**

ActiveMQ-万级，RabbitMQ-万级，RocketMQ-万级，Kafka-十万级。

**消息延迟**

ActiveMQ-无，RabbitMQ-微秒级，RocketMQ-毫秒级，Kafka-毫秒级。

**可用性**

ActiveMQ-高（主从），RabbitMQ-高（主从），RocketMQ-非常高（分布式），Kafka-非常高（分布式）。

**消息丢失**

ActiveMQ-低，RabbitMQ-低，RocketMQ-理论上不会丢失，Kafka-理论上不会丢失。

**文档的完备性**

ActiveMQ-高，RabbitMQ-高，RocketMQ-高，Kafka-高。

**提供快速入门**

ActiveMQ-有，RabbitMQ-有，RocketMQ-有，Kafka-有。

**社区活跃度**

ActiveMQ-高，RabbitMQ-高，RocketMQ-中，Kafka-高。

**商业支持**

ActiveMQ-无，RabbitMQ-无，RocketMQ-阿里云，Kafka-阿里云。

## 5.3、ActiveMQ、RabbitMQ、RocketMQ、Kafka

市场各主流消息队列对比：

**ActiveMQ**

历史悠久的开源项目，已经在很多产品中得到应用，实现了JMS1.1规范，可以和spring-jms轻松融合，实现了多种协议，不够轻巧（源代码比RocketMQ多），支持持久化到数据库，对队列数较多的情况支持不好。

**RabbitMQ**

它比Kafka成熟，支持AMQP事务处理，在可靠性上，RabbitMQ超过Kafka，在性能方面超过ActiveMQ。

**RocketMQ**

RocketMQ是阿里开源的消息中间件，目前在Apache孵化，使用纯Java开发，具有高吞吐量、高可用性、适合大规模分布式系统应用的特点。RocketMQ思路起源于Kafka，但并不是简单的复制，它对消息的可靠传输及事务性做了优化，目前在阿里集团被广泛应用于交易、充值、流计算、消息推送、日志流式处理、binglog分发等场景，支撑了阿里多次双十一活动。因为是阿里内部从实践到产品的产物，因此里面很多接口、API并不是很普遍适用。其可靠性毋庸置疑，而且与Kafka一脉相承（甚至更优），性能强劲，支持海量堆积。

**Kafka**

Kafka设计的初衷就是处理日志的，不支持AMQP事务处理，可以看做是一个日志系统，针对性很强，所以它并没有具备一个成熟MQ应该具备的特性。Kafka的性能（吞吐量、tps）比RabbitMQ要强，如果用来做大数据量的快速处理是比RabbitMQ有优势的。
