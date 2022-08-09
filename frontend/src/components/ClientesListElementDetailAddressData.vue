<script setup lang="ts">
const props = defineProps({
    customerDetail: {} as any,
});

</script>
<template>
    <div class="enderecodata">
        <span class="personaldatatxt" style="font-weight: 600; text-decoration: underline;">Endereço</span> <br>
        <span class="logradouro"> {{ props.customerDetail.logradouro }}</span>,
        <span class="numero"> {{ props.customerDetail.numero }}</span>, <br>
        <span class="bairro"> {{ props.customerDetail.bairro }}</span>,
        <span class="localidade">{{ props.customerDetail.localidade }}</span>,
        <span class="uf">{{ props.customerDetail.uf }}</span> <br>
        <span class="cep">CEP: {{ props.customerDetail.cep }}</span> <br>
        <span class="complemento">{{ props.customerDetail.complemento }}</span>
    </div>
</template>