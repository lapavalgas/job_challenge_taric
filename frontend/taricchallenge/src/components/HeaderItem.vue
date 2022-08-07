<script setup lang="ts">
import { useAuthenticationStore } from '@/stores/authenticationStore';
const authentication = useAuthenticationStore();

</script>
<template>
    <nav class="navbar navbar-expand-lg bg-app" aria-label="nav">
        <div class="container-fluid">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item nav-item-margin-left">
                    <RouterLink class="nav-link link-app" to="/clientes">Clientes</RouterLink>
                </li>
                <li class="nav-item nav-item-margin-left">
                    <RouterLink class="nav-link link-app" to="/salvar">Cadastrar novo cliente</RouterLink>
                </li>
            </ul>
            <button type="button" class="btn btn-sm btn-warning btn-margin-right link-app">
                <RouterLink @click="authentication.logoff()" class="nav-link link-app" to="/">LOGOUT</RouterLink>
            </button>
        </div>
    </nav>
</template>