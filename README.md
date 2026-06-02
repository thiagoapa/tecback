# IESPFLIX — API BackEnd

Projeto da disciplina **BackEnd - Tecnologias Web**  
Professor: Rodrigo Fujioka | UNIESP  
Aluno: Thiago

---

## Descrição

API REST para o sistema IESPFLIX — catálogo de conteúdos, usuários, favoritos, planos e assinaturas.

---

## Stack

- Java 21
- Spring Boot 3.5
- Spring Web (APIs REST)
- Spring Data JPA
- Bean Validation + Custom Validators
- Lombok
- H2 Database
- SpringDoc OpenAPI (Swagger UI)
- Spring Security Crypto (BCrypt)

---

## Como rodar

```bash
# Definir o JAVA_HOME (Windows)
$env:JAVA_HOME="C:\Users\Thiago\.jdks\temurin-21.0.10"

# Rodar a aplicação
.\mvnw.cmd spring-boot:run

# Só compilar
.\mvnw.cmd -q -DskipTests compile
```

A API fica disponível em `http://localhost:8080`

---

## Documentação

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2
  - JDBC URL: `jdbc:h2:file:~/teckback20262`
  - User: `sa` | Password: (vazio)

---

## Endpoints

### Conteúdo
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/conteudos` | Criar conteúdo |
| GET | `/conteudos?tipo=FILME&genero=Ficção&q=inception` | Listar com filtros |
| GET | `/conteudos/{id}` | Buscar por id |
| PUT | `/conteudos/{id}` | Atualizar |
| DELETE | `/conteudos/{id}` | Excluir |

### Usuário
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/usuarios` | Criar usuário (senha BCrypt) |
| GET | `/usuarios?page=0&size=10` | Listar paginado por nome |
| GET | `/usuarios/{id}` | Buscar por id |
| PUT | `/usuarios/{id}` | Atualizar |

### Plano
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/planos` | Criar plano |
| GET | `/planos` | Listar todos |
| GET | `/planos/{codigo}` | Buscar por código (BASICO/PADRAO/PREMIUM) |

### Favorito
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/favoritos/usuario/{id}/conteudo/{id}` | Adicionar favorito |
| DELETE | `/favoritos/usuario/{id}/conteudo/{id}` | Remover favorito |
| GET | `/favoritos/usuario/{id}` | Listar favoritos por usuário |

### Assinatura
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/assinaturas` | Criar assinatura |
| PATCH | `/assinaturas/{id}/cancelar` | Cancelar |
| GET | `/assinaturas?status=ATIVA` | Consultar por status |

### Método de Pagamento
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/metodos-pagamento` | Cadastrar |
| GET | `/metodos-pagamento/usuario/{id}` | Consultar por usuário |
| DELETE | `/metodos-pagamento/{id}` | Remover |

### Funcionario
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/funcionarios` | Criar |
| GET | `/funcionarios` | Listar |
| GET | `/funcionarios/{id}` | Buscar por id |
| PUT | `/funcionarios/{id}` | Atualizar |
| DELETE | `/funcionarios/{id}` | Excluir |
| GET | `/funcionarios/cep/{cep}` | Buscar endereço via ViaCEP |

---

## Critérios de aceite implementados

- [x] Modelo relacional fiel às tabelas da spec
- [x] Lombok em todas as entidades (`@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@EqualsAndHashCode`)
- [x] Custom Validator: `@GeneroValido`
- [x] Validações: `@NotBlank`, `@Email`, `@Min`, `@Max`, `@Pattern`, `@Size`
- [x] 7 consultas JPQL personalizadas
- [x] APIs REST implementadas (Conteudo, Usuario, Plano, Favorito, Assinatura, MetodoPagamento)
- [x] Paginação (`Pageable`) em `/usuarios`
- [x] Filtros em `/conteudos` (tipo, genero, q)
- [x] Tratamento global de exceções (`@RestControllerAdvice`)
- [x] Logs em todos os Services (`@Slf4j`)
- [x] Integração com serviço externo: ViaCEP (`/funcionarios/cep/{cep}`)
- [x] Senha armazenada com BCrypt

---

## Consultas JPQL implementadas

1. Filtrar por gênero (case-insensitive), ordenar por título
2. Top N conteúdos por relevância
3. Conteúdos lançados após ano X
4. Busca por palavra-chave no título/sinopse
5. Favoritos recentes de um usuário
6. Contar assinaturas ativas por plano
7. Conteúdos com trailer_url não nulo
8. Listar todos ordenados por título

---

## Entidades

| Entidade | Tabela | Descrição |
|----------|--------|-----------|
| Conteudo | conteudos | Catálogo de filmes e séries |
| Usuario | usuarios | Clientes da plataforma |
| Plano | planos | BASICO / PADRAO / PREMIUM |
| Favorito | favoritos | PK composta usuario+conteudo |
| Assinatura | assinaturas | Liga usuario ao plano |
| MetodoPagamento | metodos_pagamento | Cartões tokenizados |
| Funcionario | funcionario | Integração ViaCEP |
