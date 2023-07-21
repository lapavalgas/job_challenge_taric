<script setup lang="ts">
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';

const salvarCustomerStore = useSalvarCustomerStore();
const props = defineProps({
    customerId: 0 as any,
});

</script>
<template>
    <div class="btn-card-userdata">

        <router-link v-on:click='salvarCustomerStore.setCustomer(props.customerId)' class="dec" to="/salvar"><span
                style="min-width: 80px; margin: 2px; margin-top: 1rem; " type="button" class="btn btn-primary">
                Editar</span></router-link>
        <br>
        <button v-on:click="salvarCustomerStore.confirmarDeletar(props.customerId)"
            style="min-width: 80px; margin: 2px; margin-top: 1rem; " type="button" class="btn btn-danger">Deletar
        </button>
    </div>
</template>
<style>
.dec {
    color: white;
    text-decoration: none;
}

.dec:hover {
    color: white;
    text-decoration: none;
}
</style>