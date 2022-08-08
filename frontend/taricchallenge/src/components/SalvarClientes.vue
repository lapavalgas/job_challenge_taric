<script setup lang="ts">
import { useCustomerStore } from '@/stores/customerStore';
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
const customerStore = useCustomerStore();
const salvarCustomerStore = useSalvarCustomerStore();

const isEditing = ("0".includes(salvarCustomerStore.customerId)) ? false : true;

let data = await customerStore.getCustomerById(salvarCustomerStore.customerId);
salvarCustomerStore.loadCustomerData(data);


function deleteCustomer() {
    salvarCustomerStore.deleteCustomer(salvarCustomerStore.customerId);
}

</script>
<template>
    <h5>Clientes </h5>
    <div class="personaldataform">
        <div id="cliente" class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nomeCompleto" placeholder="Nome completo"
                        v-model=salvarCustomerStore.customerDetailToSave.nome>
                    <label class="clr-smooth" for="nomeCompleto">Nome completo</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="cpf" class="form-control" id="cpf" placeholder="CPF"
                        v-model=salvarCustomerStore.customerDetailToSave.cpf>
                    <label class="clr-smooth" for="cpf">CPF</label>
                </div>
            </div>
        </div>

        <div id="contato" class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="email" placeholder="E-mail"
                        v-model=salvarCustomerStore.customerDetailToSave.email>
                    <label class="clr-smooth" for="email">E-mail</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="telefone" placeholder="telefone"
                        v-model=salvarCustomerStore.customerDetailToSave.telefone>
                    <label class="clr-smooth" for="telefone">Telefone</label>
                </div>
            </div>
        </div>

        <div id="cep" class="row">
            <div class="col-3">
                <div class="form-floating mb-2">
                    <input type="text" class="form-control" id="cep" placeholder="CEP"
                        v-model=salvarCustomerStore.customerDetailToSave.cep @input="salvarCustomerStore.checkCep()">
                    <label class="clr-smooth" for="cep">CEP</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input disabled type="text" class="form-control bg-leve" id="logradouro" placeholder="Logradouro"
                        v-model=salvarCustomerStore.customerDetailToSave.logradouro>
                    <label class="clr-smooth" for="logradouro">Logradouro</label>
                </div>
            </div>
            <div class="col-3">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="numero" placeholder="Número"
                        v-model=salvarCustomerStore.customerDetailToSave.numero>
                    <label class="clr-smooth" for="numero">Número</label>
                </div>
            </div>
        </div>
        <div id="cep" class="row">
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input disabled type="text" class="form-control bg-leve" id="bairro" placeholder="Bairro"
                        v-model=salvarCustomerStore.customerDetailToSave.bairro>
                    <label class="clr-smooth" for="bairro">Bairro</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input disabled type="text" class="form-control bg-leve" id="cidade" placeholder="Cidade"
                        v-model=salvarCustomerStore.customerDetailToSave.localidade>
                    <label class="clr-smooth" for="cidade">Cidade</label>
                </div>
            </div>
            <div class="col-2">
                <div class="form-floating mb-3">
                    <input disabled type="text" class="form-control bg-leve" id="estado" placeholder="Estado"
                        v-model=salvarCustomerStore.customerDetailToSave.uf>
                    <label class="clr-smooth" for="estado">Estado</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="complemento" placeholder="Complemento"
                        v-model=salvarCustomerStore.customerDetailToSave.complemento>
                    <label class="clr-smooth" for="complemento">Complemento</label>
                </div>
            </div>
        </div>
    </div>

    <div id="form-actions">
        <div>
            <button v-on:click="salvarCustomerStore.salvaCustomer()" type="button"
                class="btn btn-primary btn-form-actions" :class="{ btnhidden: !isEditing }">Salvar</button>
            <button v-on:click="salvarCustomerStore.salvaCustomer()" type="button"
                class="btn btn-primary btn-form-actions" :class="{ btnhidden: isEditing }">Cadastrar</button>

            <button v-on:click="salvarCustomerStore.back()" type="button"
                class="btn btn-secondary btn-form-actions">Cancelar</button>
        </div>
        <button v-on:click="deleteCustomer()" type="button" class="btn btn-danger btn-form-actions"
            :class="{ btnhidden: !isEditing }">Deletar</button>
    </div>
</template>
<style>
.bg-leve {
    background-color: #e9ecef4f;

}

.btnhidden {
    display: none;
}

.clr-smooth {
    color: #00000070;
}
</style>