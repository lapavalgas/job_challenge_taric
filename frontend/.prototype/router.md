
- **Login** `/login`
    1. bff gera hash em sessão
    2. retorna hash ao cliente
    3. cliente salva em cookies
    4. cliente autentica hash no bff
    - Error: usuário inválido, error genérico 400

- **Clientes** rota front: `clientes` 
    1. consome bff na rota GET: `clientes/listar`

- **Clientes** Botão expandir front
    1. consome bff na rota GET: `clientes/{id}`

- **Clientes** Botão editar 
    1. carrega rota front `clientes` repassando os dados do cliente

- **Cadastrar** rota front: `clientes/cadastrar` (SEM DADOS)
    1. o componente deverá carregar em branco
    2. o botão deletar não deve aparecer
    3. consome bff na rota POST: `clientes/cadastrar`
    4. comunicação de dados com graphql 

- **Cadastrar** rota front: `clientes/cadastrar` (COM DADOS)
    1. o componente deverá carregar os dados no formulário
    2. o botão deleta deverá aparecer
    3. consome bff na rota PATCH: `clientes/{id}/editar`
    4. comunicação de dados com graphql 

- **botão deletar** front
    1. consome o bff na rota DELETE: `clientes/{id}/deletar`