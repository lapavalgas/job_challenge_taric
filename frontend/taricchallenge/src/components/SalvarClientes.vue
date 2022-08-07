<script setup lang="ts">

</script>
<template>
    <h5>Clientes</h5>
    <div class="personaldataform">
        <div id="cliente" class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Nome completo</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">CPF</label>
                </div>
            </div>
        </div>

        <div id="contato" class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">E-mail</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Telefone</label>
                </div>
            </div>
        </div>

        <div id="cep" class="row">
            <div class="col-3">
                <div class="form-floating mb-2">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">CEP</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Logradouro</label>
                </div>
            </div>
            <div class="col-3">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Número</label>
                </div>
            </div>
        </div>
        <div id="cep" class="row">
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Bairro</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Cidade</label>
                </div>
            </div>
            <div class="col-2">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Estado</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Complemento</label>
                </div>
            </div>
        </div>
    </div>

    <div id="form-actions">
        <div>
            <button type="button" class="btn btn-primary btn-form-actions">Salvar</button>

            <button type="button" class="btn btn-secondary btn-form-actions">Cancelar</button>
        </div>
        <button type="button" class="btn btn-danger btn-form-actions">Deletar</button>
    </div>
</template>