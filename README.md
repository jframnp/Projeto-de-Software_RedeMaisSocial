# Plataforma Voluntários e ONGs

## Setup
- Java 17+, Maven
- Run: mvn spring-boot:run
- H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb)

## Endpoints (Use Postman)
- Cadastro: POST /afiliacao/etapa1 (dados básicos), /etapa2 (detalhes), /etapa3 (perfil), /etapa4 (finalizar)
- Aprovação: GET /aprovacao/pendentes, GET /aprovacao/detalhes/{id}, POST /aprovacao/aprovar/{id}

## Correspondência UML
- Veja Wiki para diagramas.