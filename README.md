# Velib commenter API

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

- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- JWT_SECRET

## Database

MySQL 8