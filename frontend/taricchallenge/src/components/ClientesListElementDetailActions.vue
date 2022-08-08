<script setup lang="ts">
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
const salvarCustomerStore = useSalvarCustomerStore();
const props = defineProps({
    customerId: 0 as any,
});

function deleteCustomer() {
    salvarCustomerStore.deleteCustomer(props.customerId);
    salvarCustomerStore.forceRender();
}

</script>
<template>
    <div class="btn-card-userdata">
        <button style="min-width: 80px; margin: 2px; margin-top: 1rem; " type="button" class="btn btn-primary">
            <router-link v-on:click='salvarCustomerStore.setCustomer(props.customerId)' class="dec" to="/salvar">
                Editar</router-link>
        </button>
        <br>
        <button v-on:click="deleteCustomer()" style="min-width: 80px; margin: 2px; margin-top: 1rem; " type="button"
            class="btn btn-danger">Deletar </button>
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