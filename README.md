# Spring Cloud Consul

Spring Cloud Consul provides Consul integrations for Spring Boot apps through autoconfiguration and binding to the Spring Environment and other Spring programming model idioms. With a few simple annotations you can quickly enable and configure the common patterns inside your application and build large distributed systems with Hashicorp’s Consul. The patterns provided include Service Discovery, Distributed Configuration and Control Bus.

## Features

Spring Cloud Consul features:

- Service Discovery: instances can be registered with the Consul agent and clients can discover the instances using Spring-managed beans

- Supports Ribbon, the client side load-balancer via Spring Cloud Netflix

- Supports Spring Cloud LoadBalancer, a client side load-balancer provided by the Spring Cloud project

- Supports Zuul, a dynamic router and filter via Spring Cloud Netflix

- Distributed Configuration: using the Consul Key/Value store

- Control Bus: Distributed control events using Consul Events

A Consul datacenter contains both clients and servers agents. These agents participate in a LAN gossip pool and RPC forwarding that enables the distributed exchange of information throughout your environment. This information distribution provides the foundation for Consul's service discovery and service mesh capabilities.

Consul security features include the Access Control List (ACL) system, TLS encryption, and gossip encryption. These security features protect Consul communication against eavesdropping, tampering, and spoofing. Check the Consul security model for more information.

## Setup

### pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### application.yml

```yaml
spring:
  application:
    name: spring-consul
  consul:
    host: localhost
    port: 8500
    discovery:
      instance-id: ${spring.application.name}:${random.value}
      service-name: ${spring.application.name}
      health-check-critical-timeout: "1m"
      health-check-path: /actuator/health
      health-check-interval: 10s
```

## Start the Consul server

Run this command to start all services in the correct order.

```bash
$ docker-compose up -d
```

## View the Consul UI

Navigate to [http://localhost:8500/](http://localhost:8500/) on your browser to access the Consul UI.

Click on the "Nodes" option in the top navigation bar to go to the nodes page. There you'll find an overview of the entire datacenter including the health status of each node, IP address, number of registered services, and a leader badge indicating which node is hosting the leading Consul server.

## Access Consul containers

You can interact with your containerized Consul datacenter with the Docker CLI.
You can execute Consul CLI commands from your local terminal to your Consul containers using the docker exec command.

```shell script
$ docker exec consul-client consul members
```

You can also issue commands inside of your container by opening an interactive shell and using the Consul CLI included in the container.

```shell script
$ docker exec -it consul-client /bin/sh
```

Once inside the consul-client container, you can use the consul members command to verify you have access to the Consul datacenter.

```shell script
$ consul members
```

You can find the leader either from the UI using the leader badge shown nearby the server node acting as leader ur using the consul operator command.

```shell script
$ docker exec consul-client consul operator raft list-peers
```

## Run the application

Run this command to start two instances of the application. 

```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9091
```

## Stop the Consul server

```bash
docker-compose down
```
