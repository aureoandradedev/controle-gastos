# 💰 Controle de Gastos API

API REST desenvolvida com Spring Boot para gerenciamento de gastos pessoais com autenticação JWT.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- PostgreSQL
- Swagger (OpenAPI)
- Lombok

---

## 🔐 Funcionalidades

### 👤 Usuário
- Cadastro de usuário
- Login com autenticação JWT
- Senha criptografada com BCrypt

### 💸 Gastos
- Criar gasto
- Listar gastos do usuário logado
- Atualizar gasto
- Deletar gasto
- Cada usuário só acessa seus próprios dados

---

## 🔒 Segurança

- Autenticação via JWT
- Proteção de endpoints
- Controle de acesso por usuário logado
- Senhas armazenadas de forma segura (hash)

---

## 📄 Documentação (Swagger)

Após iniciar a aplicação, acesse a documentação da API em:

http://localhost:8084/swagger-ui/index.html
