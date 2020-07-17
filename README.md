# O que é

API restfull  com os seguintes serviços: autenticação, consultar pessoas e livros.

## Tecnologias

- Java 8
- Spring Boot 2.3.1.RELEASE
- FlyWay
- Imagem docker Mysql 5.7.22

## Requisitos dev

- Eclipse ou Intellij IDEA
- JDK 1.8


## Como usar (dev)

- Na pasta raiz, executar:

```bash
docker-compose up --build
```

## Como gerar os testes e o html de cobertura

- Na pasta raiz, executar:

```bash
./mvnw verify
```
- Abrir o arquivo target/site/jacoco/index.html

