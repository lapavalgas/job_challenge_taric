<script setup lang="ts">
const props = defineProps({
    customerDetail: {} as any,
});

</script>
<template>
    <div class="personaldata">
        <span class="personaldatatxt" style="font-weight: 600; text-decoration: underline;">Dados do cliente</span> <br>
        <span class="CPF">CPF: {{ props.customerDetail.cpf }}</span> <br>
        <span class="email">{{ props.customerDetail.email }}</span> <br>
        <span class="telefone">{{ props.customerDetail.telefone }}</span> <br>
        <span class="birth"><br></span>
    </div>
</template>