## 1️⃣ README para AgroHub

# AgroHub - Sistema de Cadastro de Produtor Rural

**Descrição:**
AgroHub é uma API REST completa para gerenciamento de produtores rurais, fazendas, culturas, insumos, máquinas, funcionários e indicadores de produção, incluindo dashboards e relatórios.

## Tecnologias
- Java 17+
- Spring Boot 3+
- Spring Data JPA / Hibernate
- MySQL
- JWT para autenticação
- Flyway para versionamento de banco de dados
- JUnit para testes

## Pré-requisitos
- Java 17 ou superior
- Maven ou Gradle
- MySQL
- Postman ou outro cliente REST

## Configuração do Banco de Dados
1. Crie o banco de dados MySQL:
```sql
CREATE DATABASE agrohub_dev CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
2. Configure o `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agrohub_dev?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
server.port=8080
app.jwt.secret=replace_this_with_a_long_random_secret
app.jwt.expiration-ms=3600000
app.cors.allowed-origins=http://localhost:3000
```

## Executando a aplicação
```bash
mvn spring-boot:run
```
Ou via IDE (Spring Boot Run).

## Endpoints principais
- `/api/usuarios` - CRUD de usuários
- `/api/produtores` - CRUD de produtores
- `/api/fazendas` - CRUD de fazendas
- `/api/culturas` - CRUD de culturas
- `/api/culturaplantada` - Culturas plantadas
- `/api/maquinas` - Máquinas agrícolas
- `/api/funcionarios` - Funcionários
- `/api/insumos` - Insumos
- `/api/estoque-insumos` - Estoque de insumos
- `/api/compras-insumos` - Compras de insumos
- `/api/vendas` - Vendas de produção
- `/api/custos` - Custos operacionais
- `/api/contratos` - Contratos
- `/api/certificacoes` - Certificações
- `/api/alertas` - Alertas de monitoramento
- `/api/dashboard` - Endpoints do dashboard

## Autenticação
Todos os endpoints protegidos usam JWT:
1. Faça login via `/api/auth/login` com `email` e `senha`
2. Receba o token JWT
3. Use `Authorization: Bearer <token>` nos headers das requisições

## Testando com Postman
1. Importe o arquivo `AgroHub.postman_collection.json`
2. Configure variáveis de ambiente:
   - `baseUrl`: http://localhost:8080/api
   - `jwtToken`: token gerado após login

## Testes unitários
```bash
mvn test
```

## Deploy
- Configurar banco de dados de produção
- Gerar JAR
```bash
mvn clean package
java -jar target/agrohub-0.0.1-SNAPSHOT.jar
```
- Configurar variáveis de ambiente para JWT, DB e CORS