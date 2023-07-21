<script setup lang="ts">
import { useCustomerStore } from '@/stores/customerStore';
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';

const customerStore = useCustomerStore();
const salvarCustomerStore = useSalvarCustomerStore();
const isEditing = ("0".includes(salvarCustomerStore.customerId)) ? false : true;

let data = await customerStore.getCustomerById(salvarCustomerStore.customerId);
salvarCustomerStore.loadCustomerData(data);
if (!isEditing) { salvarCustomerStore.startForm() }

</script>
<template>
    <h5 v-if="isEditing">Editando os dados do cliente</h5>
    <h5 v-else>Cadastro de novo cliente</h5>
    <div class="personaldataform">
        <div class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nomeCompleto" placeholder="Nome completo"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.nome }"
                        @focusout="salvarCustomerStore.form().validaNome()"
                        v-model=salvarCustomerStore.customerDetailToSave.nome @input="salvarCustomerStore.nameMask()">
                    <label class="clr-smooth" for="nomeCompleto">Nome completo</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="cpf" placeholder="CPF" maxlength="11" pattern="\d*"
                        :disabled="isEditing" v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.cpf }"
                        @focusout="salvarCustomerStore.form().validaCpf()"
                        v-model=salvarCustomerStore.customerDetailToSave.cpf v-on:input="salvarCustomerStore.cpfMask()">
                    <label class="clr-smooth" for="cpf">CPF</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-8">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="email" placeholder="E-mail"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.email }"
                        @focusout="salvarCustomerStore.form().validaEmail()"
                        v-model=salvarCustomerStore.customerDetailToSave.email>
                    <label class="clr-smooth" for="email">E-mail</label>
                </div>
            </div>
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="telefone" placeholder="telefone" maxlength="15"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.telefone }"
                        @focusout="salvarCustomerStore.form().validaTelefone()" pattern="\d*"
                        v-model=salvarCustomerStore.customerDetailToSave.telefone
                        @input="salvarCustomerStore.telefoneMask()">
                    <label class="clr-smooth" for="telefone">Telefone</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-3">
                <div class="form-floating mb-2">
                    <input type="text" class="form-control" id="cep" placeholder="CEP" maxlength="9" pattern="\d*"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.cep }"
                        @focusout="salvarCustomerStore.form().validaCep()"
                        v-model=salvarCustomerStore.customerDetailToSave.cep
                        @input="salvarCustomerStore.cepEnderecoMask()">
                    <label class="clr-smooth" for="cep">CEP</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control bg-leve" id="logradouro" placeholder="Logradouro"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.logradouro }"
                        @focusout="salvarCustomerStore.form().validaLogradouro()"
                        v-model=salvarCustomerStore.customerDetailToSave.logradouro>
                    <label class="clr-smooth" for="logradouro">Logradouro</label>
                </div>
            </div>
            <div class="col-3">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="numero" placeholder="Número" maxlength="10"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.numero }"
                        @focusout="salvarCustomerStore.form().validaNumero()" pattern="\d*"
                        v-model=salvarCustomerStore.customerDetailToSave.numero
                        @input="salvarCustomerStore.numeroEnderecoMask()">
                    <label class="clr-smooth" for="numero">Número</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control bg-leve" id="bairro" placeholder="Bairro"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.bairro }"
                        @focusout="salvarCustomerStore.form().validaBairro()"
                        v-model=salvarCustomerStore.customerDetailToSave.bairro>
                    <label class="clr-smooth" for="bairro">Bairro</label>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control bg-leve" id="cidade" placeholder="Cidade"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.cidade }"
                        @focusout="salvarCustomerStore.form().validaCidade()"
                        v-model=salvarCustomerStore.customerDetailToSave.localidade>
                    <label class="clr-smooth" for="cidade">Cidade</label>
                </div>
            </div>
            <div class="col-2">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control bg-leve" id="estado" placeholder="Estado"
                        v-bind:class="{ 'is-invalid': !salvarCustomerStore.formIsValid.estado }"
                        @focusout="salvarCustomerStore.form().validaEstado()"
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
            <button v-on:click="salvarCustomerStore.confirmarSalvar()" type="button"
                class="btn btn-primary btn-form-actions" :class="{ btnhidden: !isEditing }">Salvar</button>
            <button v-on:click="salvarCustomerStore.confirmarSalvar()" type="button"
                class="btn btn-primary btn-form-actions" :class="{ btnhidden: isEditing }">Cadastrar</button>

            <button v-on:click="salvarCustomerStore.confirmarCancelar()" type="button"
                class="btn btn-secondary btn-form-actions">Cancelar</button>
        </div>
        <button v-on:click="salvarCustomerStore.confirmarDeletar(salvarCustomerStore.customerId)" type="button"
            class="btn btn-danger btn-form-actions" :class="{ btnhidden: !isEditing }">Deletar</button>
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

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type=number] {
    -moz-appearance: textfield;
}
</style>