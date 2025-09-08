
# Source code structure

```
spring-boot-template/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/software/open/source/spring/template/
│   │   │       ├── apis/
│   │   │       │   ├── controllers/         # REST controllers
│   │   │       │   └── models/              # DTOs, request/response models
│   │   │       ├── exception/               # Custom exceptions & handlers
│   │   │       ├── io/
│   │   │       │   ├── entities/            # JPA entities
│   │   │       │   └── repositories/        # Spring Data repositories
│   │   │       ├── mapper/                  # MapStruct mappers
│   │   │       ├── services/                # Service interfaces & impl
│   │   │       ├── Application.java         # Main application class
│   │   └── resources/
│   │       ├── db/
│   │       │   └── changelog/               # Liquibase migrations
│   │       └── application.yaml             # Application config
├── docker-compose.yml
├── k8s/                                     # Kubernetes configs
├── Dockerfile                               # Docker build file
└── README.md                                # Project documentation
```

Build
mvn clean install

docker build -t spring-boot-template .

docker run --network postgres_default --env-file .env -p 8080:8080 spring-boot-template
