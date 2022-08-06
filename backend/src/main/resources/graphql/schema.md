## Schemas

1. Login

```graphql
mutation {
    login(user:"taric", pass:"taric1234") {
        cookie
        statusCode
        msg
    }
}

```

2. Logoff

```graphql
query {
    logoff{
        statusCode
        msg
    }
}
```

3. Valida CEP

```graphql
mutation {
    addressByCEP(cep: "88101260") {
        cep
        logradouro
        bairro
        localidade
        uf
        ibge
        statusCode
        msg
    }
}
```

4. Get Client list

```graphql
query {
    customers {
        statusCode
        msg
        data{
            clienteId
            nome
        }
    }
}

query {
    customers {
        statusCode
        msg
        data{
            clienteId
            nome
            statusCode
            msg
            clienteId
            enderecoCepId
            cpf
            nome
            email
            telefone
            cep
            logradouro
            bairro
            localidade
            uf
            ibge
            numero
            complemento
        }
    }
}
```

5. Get client details

```graphql
query {
    customer(id: 1) {
        statusCode
        msg
        clienteId
        enderecoCepId
        cpf
        nome
        email
        telefone
        cep
        logradouro
        bairro
        localidade
        uf
        ibge
        numero
        complemento
    }
}
```

6. add / save customer

```graphql
mutation{
    saveCliente(
        data:{
            cpf: "00000000002"
            nome: "Rafael Genericuser da Silva"
            email: "dasilva123@customer.com"
            telefone: "(000) 111111111"
            cep: "11111111"
            logradouro: "Rua da Ficção e Meu Teste"
            numero: "N. 101"
            complemento: "Ilha das Flores, Ap. 10"
            bairro: "Cenouras"
            localidade: "Abacaxi"
            uf: "sc"
            ibge: "20000"
        }){
        clienteId
        enderecoCepId
    }
}
```

7. deletar customer

```graphql
mutation {
    deleteCliente(id: "1"){statusCode msg}
}
```

8. listar enderecos

```graphql
query {
    enderecosCep {
        statusCode
        msg
        data{
            enderecoCepId
            cep
            logradouro
            bairro
            localidade
            uf
            ibge
            clientesAssociados
        }
    }
}
```