Build
mvn clean install

docker build -t spring-boot-template .

docker run --network postgres_default --env-file .env -p 8080:8080 spring-boot-template