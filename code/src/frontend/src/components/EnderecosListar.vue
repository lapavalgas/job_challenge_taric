<script setup lang="ts">
import { useAddressStore } from '@/stores/addressStore';
const addressStore = useAddressStore();

const listaEnderecos = addressStore.listaEnderecos.data.enderecosCep.data;

</script>
<template>

    <br>
    <div class="card card-enderecos-margin" v-for='endereco in listaEnderecos' :key='endereco.enderecoCepId'
        :item='endereco.enderecoCepId'>
        <div class="card-body">
            <div class="enderecodata">
                <span v-if="endereco.logradouro" class="logradouro"> <strong>{{ endereco.logradouro }}</strong>, </span>
                <span v-if="endereco.numero" class="numero"> {{ endereco.numero }}, </span>
                <span v-if="endereco.bairro" class="bairro"> {{ endereco.bairro }}, </span>
                <span v-if="endereco.localidade" class="localidade"> {{ endereco.localidade }}, </span>
                <span v-if="endereco.uf" class="uf"> {{ endereco.uf }}, </span>
                <span v-if="endereco.cep" class="cep">CEP: {{ endereco.cep }}</span>
                <span v-if="endereco.complemento" class="complemento"> {{ endereco.complemento }}</span>
            </div>
        </div>
    </div>
</template>
<style>
.card-enderecos-margin {
    margin: 10px;
}

.card-enderecos-margin:hover {
    background-color: #89aed010;
}
</style>