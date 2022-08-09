<script setup lang="ts">
import { useAuthenticationStore } from '@/stores/authenticationStore';
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
import { useAddressStore } from '@/stores/addressStore';

const addressStore = useAddressStore();
const authentication = useAuthenticationStore();
const salvarCustomerStore = useSalvarCustomerStore();
const customerId = '0' as string;

function clientes() { salvarCustomerStore.clearForm(); }
function cadastrar() { salvarCustomerStore.clearForm(); salvarCustomerStore.setCustomer(customerId); }
function logout() { salvarCustomerStore.clearForm(); authentication.confirmLogoff() }

</script>
<template>
    <nav class="navbar navbar-expand-lg bg-app" aria-label="nav">
        <div class="container-fluid">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item nav-item-margin-left">
                    <RouterLink @click='clientes()' class="nav-link link-app" to="/clientes">
                        Clientes</RouterLink>
                </li>
                <li class="nav-item nav-item-margin-left">
                    <RouterLink @click='cadastrar()' class="nav-link link-app" to="/salvar">Cadastrar novo cliente
                    </RouterLink>
                </li>
                <li class="nav-item nav-item-margin-left">
                    <RouterLink @click='addressStore.buscaListaEnderecos()' class="nav-link link-app" to="/enderecos">
                        Endereços
                    </RouterLink>
                </li>
            </ul>
            <button type="button" class="btn btn-sm btn-warning btn-margin-right link-app">
                <RouterLink @click="logout()" class="nav-link link-app" to="/">LOGOUT</RouterLink>
            </button>
        </div>
    </nav>
</template>