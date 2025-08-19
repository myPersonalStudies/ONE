# 🎯 FórumHub API

Uma API REST completa para gerenciamento de fóruns desenvolvida com Spring Boot. Este projeto implementa um sistema de tópicos de discussão com autenticação JWT e operações CRUD completas.

## 🚀 Tecnologias Utilizadas

    Java 17
    Spring Boot 3.1.0
    Spring Security (Autenticação JWT)
    Spring Data JPA (Persistência)
    MySQL (Banco de dados)
    Bean Validation (Validações)
    Maven (Gerenciamento de dependências)

## 📋 Funcionalidades
### 🔐 Autenticação e Usuários

    ✅ Cadastro de usuários
    ✅ Login com JWT
    ✅ Gerenciamento de perfil
    ✅ Criptografia de senhas (BCrypt)

### 📝 Gestão de Tópicos

    ✅ Criar tópicos
    ✅ Listar tópicos (com paginação)
    ✅ Visualizar tópico específico
    ✅ Atualizar tópicos (apenas autor)
    ✅ Excluir tópicos (apenas autor)
    ✅ Validação de duplicatas

### ️ Segurança

    ✅ Autenticação JWT
    ✅ Autorização baseada em recursos
    ✅ Proteção contra duplicatas
    ✅ Validações de negócio

## ️ Arquitetura do Projeto

```angular181html

src/main/java/com/alura/forumhub/
├── controller/           # Controladores REST
│   ├── UsuarioController.java
│   ├── TopicoController.java
│   └── AutenticacaoController.java
├── domain/              # Entidades e regras de negócio
│   ├── usuario/         # Domínio de usuários
│   │   ├── Usuario.java
│   │   ├── UsuarioRepository.java
│   │   └── [DTOs...]
│   └── topico/          # Domínio de tópicos
│       ├── Topico.java
│       ├── TopicoRepository.java
│       ├── StatusTopico.java
│       └── [DTOs...]
└── infra/               # Infraestrutura
    └── security/        # Configurações de segurança
        ├── SecurityConfigurations.java
        ├── SecurityFilter.java
        ├── TokenService.java
        └── AutenticacaoService.java

```


## 📚 Documentação da API

### 🔓 Endpoints Públicos

- Cadastrar Usuário

```json

POST /usuarios
Content-Type: application/json

{
    "login": "usuario123",
    "senha": "minhasenha",
    "nome": "João Silva",
    "email": "joao@email.com"
}
```

- Fazer Login

```json

POST /login
Content-Type: application/json

{
    "login": "usuario123",
    "senha": "minhasenha"
}
```

- Resposta

```json

{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Endpoints Autenticados

> **Importante**: Todos os endpoints abaixo requerem o header:
Authorization: Bearer {seu-token-jwt}

### 👤 Usuários

| Método | Endpoint         |                                 Descrição                                 |
| :----:| -----------------:|:-------------------------------------------------------------------------:|
| GET    | /usuarios/perfil |                       Ver perfil do usuário logado                        | 
| PUT    | /usuarios/perfil |                             Atualizar perfil                              |
| DELETE | /usuarios/perfil |                               Excluir conta                               |

### 📝 Tópicos


| Método | Endpoint         |              Descrição               |
|:------:| -----------------:|:------------------------------------:|
|  POST  | /topicos |          Criar novo tópico           | 
|  GET   | /topicos |      Listar tópicos (paginado)       | 
|  GET   | /topicos/{id} |                  Buscar tópico por ID)                   | 
|  PUT   | /usuarios/perfil |     Atualizar tópico (só autor)      |
| DELETE | /usuarios/perfil |      Excluir tópico (só autor)       |

## Validações Implementadas

### Usuários

- Login e email únicos
- Todos os campos obrigatórios
- Formato de email válido
- Verificação de senha atual para alterações

### Tópicos

- Título e mensagem obrigatórios
- Verificação de duplicatas (título + mensagem)
- Apenas autor pode editar/excluir
- Curso obrigatório