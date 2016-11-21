# Speedy
[![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg)](https://github.com/hjcenry/game_server_archetype/blob/master/LICENSE)
[![Build Status](https://img.shields.io/travis/weibocom/motan/master.svg?label=Build)]()

# Overview

Speedy is a maven-archetype as a generic framework for java game server.As the name "Speedy" indicates,it means fast and efficient development
for Java game server.As so far,the first version only includes _Logic Server(process the game logic),Router Server(process login and choose server in the server list),
File Server(process the hot fix for the game client)_ and _Pay Server(process the pay from the bought in the game)_ .At fist,this is only my personal framework,
and used to build project with some general modules like database module and network module,so that I don't need to build these modules again and again.
However,When demand increases,we need more modules.

This archetype now is in the initial phase and has some bugs.In my thought,I just want to share the framework so that I can help others be faster and quicker 
when developing a game server.But personal power is so limited.So to achieve my goal,I need you help me to approve my framework.It's good to submit the issues
or push requests through github.Let's do it together!

# Features

- Supports Http,Socket,WebSocket protocol.
- Integrates Mysql and Mongo Database.
- Provides Redis and Memecache nosql database.
- Provides Motan and JsonRpc RPC framework.
- Contains a complete set of GM management system.

# Quick Start

The quick start gives very basic example of running game server on the same machine. For the detailed information about using and developing Speedy, please jump to Documents.

The minimum requirements to run the quick start are:
```
JDK 1.7 or above
Maven3
```

## Logic Server 

Logic server process the game logics,all logic interfaces integrated at this part.You need to add your own logic interfaces into this part.The following steps lead you start the basic service of the server,
more service is closed and you need to open them in source code.More information,please jump to Document.
1.Install the maven archetype.
Enter the target/generated-sources/archetype directory and install the archetype.
```
mvn install
```
2.Create project with the archetype installed.
```
mvn archetype:generate -DarchetypeCatalog=local
```
choose the following item:
```
local -> com.kidbear.archetype:logical-archetype (logical-archetype)
```
3.Update propertie files in src/main/resource.
net.properties:define some properties about network.
```
port = 9191
ip=127.0.0.1
```
server.properties:define some properties about the server.
```
# server id
serverId = 1
# whether open the payment
payOpen = false
# login server info
loginName = router_server
loginServer = 127.0.0.1
loginPort = 82
# pay server info
payServer = 127.0.0.1
payPort = 8500
# redis info
redisServer = 127.0.0.1:6440
redisPwd = 123456
# cache server info
cacheServer = 127.0.0.1:11211
```
4.Build the war package by maven
Enter the root directory and run the install command.
```
mvn install
```
5.Deploy the war by tomcat.
6.Enter the GM management system.
This system contains the game manage functions you develope your self.
```
http://127.0.0.1:8080/logic/admin/index
```
7.Telnet the game logic port
This port is the entry your game logic service at.
```
telnet 127.0.0.1 9191
```

## Router Server

Router server process the login and choose server in the server list.
1.Install the maven archetype.
Enter the target/generated-sources/archetype directory and install the archetype.
```
mvn install
```
2.Create project with the archetype installed.
```
mvn archetype:generate -DarchetypeCatalog=local
```
choose the following item:
```
local -> com.kidbear.archetype:router-archetype (router-archetype)
```
3.Update propertie files in src/main/resource.
server.properties:define some properties about the server.
```
# login server info
loginServer = 127.0.0.1
loginPort = 81
# file server info
fileIp=127.0.0.1
filePort=8300
# redis server info
redisServer = 127.0.0.1:6440
redisPwd = 123456
# cache server info
cacheServer = 127.0.0.1:11211
```
version.properties:define the version about the client.
```
# base version of channel 0
0=1.3
# base version of channel 1
1=1.3
```
4.Build the war package by maven
Enter the root directory and run the install command.
```
mvn install
```
5.Deploy the war by tomcat.
6.Enter the GM management system.
This system contains the game manage functions you develope your self.
```
http://127.0.0.1:8080/router/admin/index
```

## File Server

File Server process the hot fix for the game client.
1.Install the maven archetype.
Enter the target/generated-sources/archetype directory and install the archetype.
```
mvn install
```
2.Create project with the archetype installed.
```
mvn archetype:generate -DarchetypeCatalog=local
```
choose the following item:
```
local -> com.kidbear.archetype:file-archetype (file-archetype)
```
3.Update propertie files in src/main/resource.
net.properties:define some properties about network.
```
port = 8300
ip=127.0.0.1
```
server.properties:define some properties about the server.
```
# server id
serverId = 1
# login server info
loginServer = 127.0.0.1
loginPort = 82
# redis info
redisServer = 127.0.0.1:6440
redisPwd = 123456
# cache server info
cacheServer = 127.0.0.1:11211
```
4.Build the war package by maven
Enter the root directory and run the install command.
```
mvn install
```
5.Deploy the war by tomcat.
6.Enter the GM management system.
This system contains the game manage functions you develope your self.
```
http://127.0.0.1:8080/file/admin/index
```
7.Telnet the file port
This port used for file service in the game.
```
telnet 127.0.0.1 8300
```

## Pay Server

Pay server process the pay from the bought in the game
1.Install the maven archetype.
Enter the target/generated-sources/archetype directory and install the archetype.
```
mvn install
```
2.Create project with the archetype installed.
```
mvn archetype:generate -DarchetypeCatalog=local
```
choose the following item:
```
local -> com.kidbear.archetype:pay-archetype (pay-archetype)
```
3.Update propertie files in src/main/resource.
server.properties:define some properties about the server.
```
# server id
serverId = 1
# login server info
loginServer = 127.0.0.1
loginPort = 82
# redis info
redisServer = 127.0.0.1:6440
redisPwd = 123456
# cache server info
cacheServer = 127.0.0.1:11211
```
4.Build the war package by maven
Enter the root directory and run the install command.
```
mvn install
```
5.Deploy the war by tomcat.
6.Enter the GM management system.
This system contains the game manage functions you develope your self.
```
http://127.0.0.1:8080/pay/admin/index
```

# Documents

- [Wiki](https://github.com/hjcenry/game_server_archetype/wiki)
- [Wiki(中文)](https://github.com/hjcenry/game_server_archetype/wiki/%E4%B8%AD%E6%96%87%E6%96%87%E6%A1%A3)

# Contributors

- 何金成(@hjcenry)

# License

Java Game Server Archetype is released under the MIT.