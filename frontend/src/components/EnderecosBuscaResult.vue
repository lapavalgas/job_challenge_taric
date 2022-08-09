<script setup lang="ts">

import { useAddressStore } from '@/stores/addressStore';

const addressStore = useAddressStore();

let buscaCep = addressStore.buscaCepResult.buscaCep;

</script>
<template>
    <br>
    <div class="card card-enderecos-margin">
        <div class="card-body">
            <div v-if="buscaCep.statusCode == '200'" class="enderecodata">
                <span v-if="buscaCep.logradouro" class="logradouro"><strong> {{ buscaCep.logradouro }}</strong>, </span>
                <span v-if="buscaCep.numero" class="numero"> {{ buscaCep.numero }}, </span>
                <span v-if="buscaCep.bairro" class="bairro"> {{ buscaCep.bairro }}, </span>
                <span v-if="buscaCep.localidade" class="localidade"> {{ buscaCep.localidade }}, </span>
                <span v-if="buscaCep.uf" class="uf"> {{ buscaCep.uf }}, </span>
                <span v-if="buscaCep.cep" class="cep">CEP: {{ buscaCep.cep }}</span>
                <span v-if="buscaCep.complemento" class="complemento"> {{ buscaCep.complemento }}</span>
            </div>
            <div v-else class="enderecodata">
                <span v-if="buscaCep.statusCode" class="logradouro"> <strong>{{ buscaCep.statusCode }}</strong>, </span>
                <span v-if="buscaCep.msg" class="numero"> {{ buscaCep.msg }}, </span>
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