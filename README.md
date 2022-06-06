# Velib commenter API

## Version
JDK 17

## Build

```bash
mvn clean install
```

## Run dev

```bash
mvn spring-boot:run
```

## Run prod

```bash
java -jar target/velib-commenter-api<version>.jar
```

## Environment variables

- SPRING_DATASOURCE_URL (ex: jdbc:mysql://localhost:3306/databaseName)
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- JWT_SECRET

## Database

MySQL 8