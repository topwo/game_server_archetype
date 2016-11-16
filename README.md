Speedy(undone)
==============

Generic Java game server framework
----------------------------------

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
- Contains a complete set of GM management system.

# Quick Start

The quick start gives very basic example of running game server on the same machine. For the detailed information about using and developing Speedy, please jump to Documents.

The minimum requirements to run the quick start are:
```
JDK 1.7 or above
Maven3
IDE like Eclipse or Itellij Idea
```

## Installation
The framework include four projects for now,all the projects are built by maven,I create the maven-archetype in every project's target diectory.
Before you use the framework,you need install the maven archetype in your local environment.

## Logic Server 
Logic server process the game logics,all logic interfaces integrated at this part.You need to add your own logic interfaces into this part.

## Router Server
Router server process the login and choose server in the server list.

## File Server
File Server process the hot fix for the game client.

## Pay Server
Pay server process the pay from the bought in the game

# Documents

Wiki
Wiki(中文)
Contributors

何金成(@hjcenry)

# License

Java Game Server Archetype is released under the MIT.