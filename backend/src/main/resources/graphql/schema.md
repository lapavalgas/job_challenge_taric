## Schemas

1. login

```graphql
mutation {
    login(user:"taric", pass:"taric1234") {
        cookie
        statusCode
        msg
    }
}

```

2. logoff

```graphql
mutation {
    logoff{
        statusCode
        msg
    }
}
```

3. buscaCep

```graphql
mutation {
    buscaCep(cep: "88101260") {
        statusCode
        msg
        enderecoCepId
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

4. clientes

```graphql
query {
    clientes {
        statusCode
        msg
        data{
            clienteId
            nome
        }
    }
}
```
```graphql
query {
    clientes {
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

5. cliente(id: {id})

```graphql
query {
    cliente(id: 1) {
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

6. salvaCliente(data: {data})

```graphql
mutation{
    salvaCliente(
        data:{
            enderecoCepId: "12"
            cpf: "00000000002"
            nome: "Rafael Genericuser da Silva"
            email: "dasilva123@cliente.com"
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

7. deleteCliente(id: {id})

```graphql
mutation {
    deleteCliente(id: "1"){statusCode msg}
}
```

8. enderecosCep

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