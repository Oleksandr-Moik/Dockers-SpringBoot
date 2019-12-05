# Dockers-SpringBoot. Spring Boot project with using Dockers
Three sb-services in each in own dokcer contsainer where they coneting to mysql-db in another container.

## For start docker containers:
Run comand line and write and execute the commands below
### 1. This command will build the docker images
```
$ bash gradle_build_servise.sh
```
### 2. This command will run created images in containter and connect this containers in one working network
```
$ docker-compose up --build
```
### for Run containers in the background
```$ docker-compose up -d -- build```
