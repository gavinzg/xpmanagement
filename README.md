# xpmanagement

##Setup locally

1.Run the command below to build the project.

```shell script
mvn clean install
```

2.Run or debug the on `Starter.java` directly to deploy on local

## Local testing

Using postman to trigger get/post request

```shell script
Get experience:
     Get Method: http://localhost:8080/experience/{playerId}
Add experience:
    Post Method: http://localhost:8080/experience/{playerId}
```

## Get image from docker hub
```shell script
docker pull gavinzg/experience:v1
```

## Run docker image to deploy on local
```shell script
docker run -p 8080:8080 gavinzg/experience:v1
```
