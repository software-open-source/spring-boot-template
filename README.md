# Current source code structure

```
spring-boot-template/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/software/open/source/spring/template/
│   │   │       ├── domain/
│   │   │       │   ├── models/
│   │   │       │   │   ├── commands/         # Command objects 
│   │   │       │   │   └── responses/        # Response DTOs
│   │   │       │   └── ports/
│   │   │       │       ├── in/              # Input ports (use cases)
│   │   │       │       └── out/             # Output ports (repositories)
│   │   │       ├── infrastructure/
│   │   │       │   ├── persistence/
│   │   │       │   │   ├── adapters/        # Repository implementations
│   │   │       │   │   ├── entities/        # JPA entities
│   │   │       │   │   ├── mapper/          # Object mappers
│   │   │       │   │   └── repositories/    # Spring Data repositories
│   │   │       │   └── web/
│   │   │       │       └── controllers/     # REST controllers
│   │   │       └── Application.java         # Main application class
│   │   └── resources/
│   │       ├── db/
│   │       │   └── changelog/               # Liquibase migrations
│   │       └── application.yaml             # Application config
├── docker-compose.yml
├── k8s/                                     # Kubernetes configs
└── .env                                     # Environment variables
```

Build
mvn clean install

docker build -t spring-boot-template .

docker run --network postgres_default --env-file .env -p 8080:8080 spring-boot-template
