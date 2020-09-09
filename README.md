# xpmanagement

##Setup locally

### Database setup

1.Pull postgres image
```shell script
docker pull postgres
```
2.Run docker
```shell script
docker run -d \
    - P
    --name postgres \
    -e POSTGRES_PASSWORD=1234 \
    -e PGDATA=/var/lib/postgresql/data \
    -v /custom/mount:/var/lib/postgresql/data \
    postgres
```
Note: Download `postgresql.zip` from github, unzip to your local, then change `/custom/mount` to your local data storage path


### Project setup

#### Option 1: Run locally
1.Run the command below to build the project.

```shell script
mvn clean install
```

2.Run or debug on `Starter.java` directly to deploy on local

Note: You need to update the postgresql port in `hibernate.cfg.xml` as the port is changing as long as a new container created.

#### Option 2: Run docker
##### Get image from docker hub
```shell script
docker pull gavinzg/experience:v1
```

##### Run docker image to deploy on local
```shell script
docker run -p 8080:8080 gavinzg/experience:v1
```

## Local testing

Use postman to trigger get/post request

```shell script
Get experience:
     Get Method: http://localhost:8080/experience/{playerId}
Add experience:
    Post Method: http://localhost:8080/experience/{playerId}
```

Also you can find the postman collection from github.
