<!-- # Desafio técnico da TARIC startup Zitrus Healthtech  -->

# Technical Showcase for Job Interview

## ZIP Code showcase

Esse desafio foi proposto para avaliar as competências técnicas em desenvolvimento fullstack.

Agosto, 2022 (de 4 de agosto a 09 de agosto)

Foi solicitado como desafio técnico desenvolver uma aplicação de cadastro de clientes que consumisse a API do Correios para validar os dados de endereço pelo CEP. Era requisito ter frontend com Vuejs, backend com Java e infraestrutura dockerizada, conforme detalhado a seguir:

- **Features**:
- [x] Autenticação por login e senha
- [x] Listar clientes, cadastrar clientes, excluir clientes e visualizar detalhes
- [x] Validação do endereço do cliente pelo [OpenCEP](https://opencep.com/)

- **Backend**:
- [x] Desenvolver o backend em [Spring Boot](https://spring.io/projects/spring-boot) / Java
- [x] Utilizar um banco de dados relacional (utilizei o [H2](https://www.h2database.com/html/main.html)) 
- [x] Utilizar [Graphql](https://graphql.org/) para o sistema de comunicação das APIs
- [x] Disponibilizar na API rotas para cadastro e consumo de clientes e endereços
- [x] Desenvolver cobertura de testes unitários para o backend (opcional)
- [x] Disponibilizar o backend em imagem Docker e docker compose 

- **Frontend**: 
- [x] Desenvolver o frontend em [VueJs](https://vuejs.org/) com [Vuex](https://vuex.vuejs.org/) / [Pinia](https://pinia.vuejs.org/) 
- [x] Desenvolver tela de login, cadastro, listagem de clientes e visualização
- [x] Utilizar o sistema de subrotas
- [x] Desenvolver cobertura de testes para o frontend (opcional)
- [x] Disponibilizar o frontend em imagem Docker e docker compose

## Como iniciar a aplicação

1. Instalar o [docker](https://www.docker.com/)


2. Clonar o [projeto](https://github.com/lapavalgas/technical_showcase_zip_code)


3. Na raiz do projeto, rodar o comando:
```shell  
$ docker-compose up
```

4. O frontend roda no link [http://localhost:4173/](http://localhost:4173/)
   1. **user**: admin 
   2. **password**: admin 


6. Os testes e2e do frontend foram desenvolvidos com [Cypress](https://docs.cypress.io/) 
    1. É possível executar os testes para ver a aplicação funcionando
    2. Na raiz do diretório de frontend, rodar o comando:
```shell  
$ npx cypress run
```


5. O backend roda no link [http://localhost:8080/](http://localhost:8080/)
   1. no link [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql) é possível rodar querys no backend da aplicação
   2. no diretório [resources do backend](https://github.com/lapavalgas/technical_showcase_zip_code/tree/main/backend/src/main/resources/graphql) é possível ver as querys utilizadas para essa aplicação


7. Os testes unitários do backend foram desenvolvidos com [jUnit](https://junit.org/junit5/)/[Spring](https://spring.io/projects/spring-boot)
    
    2. Na raiz do diretório de backend, rodar o comando: 
```shell  
$ mvn test
```
