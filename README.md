## Clean Architecture Example

### Blog post

Example based on: 
https://medium.com/slalom-engineering/clean-architecture-with-java-11-f78bba431041

### Pre-requisite

Java 11


### Compile

`./gradlew clean build`

### Run Spring example

`java -jar application/spring-app/build/libs/spring-app-1.0.0.jar`

### Run Vertx example

`java -jar application/vertx-app/build/libs/vertx-app-1.0.0-fat.jar`

### Use the webbapps

#### Create User
```
POST: http://localhost:8080/users
Body:
{
  "email": "test@test.com",
  "password": "mypassword",
  "lastName": "Doe",  
  "firstName": "John"
}
```

#### Get all users
```
GET: http://localhost:8080/users
```

#### Get one user
```
GET: http://localhost:8080/users/0675171368e011e882d5acde48001122
```

#### Change Config to use Mongo or Mysql
In Config package you can change the repository implementation in order to use 
MongoDb or MySql. Previous to do that, you need to up containers located in docker/docker-compose.yml.
Just locate your terminal in folder "docker/"  and run:
```
docker-compose up -d
```
