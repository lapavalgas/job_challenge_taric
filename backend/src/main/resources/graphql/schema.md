## Schemas

1. login

```graphql
mutation {
    login(user:"admin", pass:"admin") {
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
    buscaCep(cep: "00000000") {
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
    cliente(id: 0) {
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
            enderecoCepId: "00"
            cpf: "00000000002"
            nome: "Nome de Usuário"
            email: "email@de.usuario"
            telefone: "(000) 00000-0000"
            cep: "11111111"
            logradouro: "Rua do usuario"
            numero: "000"
            complemento: "Na casa do usuario"
            bairro: "Bairrro do usuario"
            localidade: "Cidade do usuario"
            uf: "DF"
            ibge: "0"
        }){
        clienteId
        enderecoCepId
    }
}
```

7. deleteCliente(id: {id})

```graphql
mutation {
    deleteCliente(id: "0"){statusCode msg}
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