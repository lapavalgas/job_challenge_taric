<script setup lang="ts">
import ClientesDetail from "./ClientesListElementDetail.vue";
import { useCustomerStore } from '@/stores/customerStore';
import { onBeforeMount } from "vue";
const customerStore = useCustomerStore();
onBeforeMount(() => {
    customerStore.loadCustomers();
});

</script>
<template>

    <span v-for='customer in customerStore.customers' :key='customer.clienteId' :item='customer.id'
        class="customerListMargin">
        <button class="list-group-item list-group-item-action" type="button" data-bs-toggle="collapse"
            :data-bs-target="'#userCDID' + customer.clienteId" aria-expanded="false"
            :aria-controls="'userCDID' + customer.clienteId">
            {{ customer.nome }} 
        </button>
        <Suspense>
            <ClientesDetail :customerId="customer.clienteId" />
        </Suspense>
    </span>
    <br>
</template>