<script setup lang="ts">
import { useAuthenticationStore } from '@/stores/authenticationStore';
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
const authentication = useAuthenticationStore();
const salvarCustomerStore = useSalvarCustomerStore();

const customerId = '0' as string;

</script>
<template>
    <nav class="navbar navbar-expand-lg bg-app" aria-label="nav">
        <div class="container-fluid">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item nav-item-margin-left">
                    <RouterLink class="nav-link link-app" to="/clientes">Clientes</RouterLink>
                </li>
                <li class="nav-item nav-item-margin-left">
                    <RouterLink v-on:click='salvarCustomerStore.setCustomer(customerId)' class="nav-link link-app"
                        to="/salvar">Cadastrarnovo cliente </RouterLink>
                </li>
            </ul>
            <button type="button" class="btn btn-sm btn-warning btn-margin-right link-app">
                <RouterLink @click="authentication.logoff()" class="nav-link link-app" to="/">LOGOUT</RouterLink>
            </button>
        </div>
    </nav>
</template>