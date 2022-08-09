<script setup lang="ts">
import { useAddressStore } from '@/stores/addressStore';
const addressStore = useAddressStore();

</script>
<template>
    <section id="clientes">
        <br>
        <div>
            <div class="row">
                <div class="col">
                    <RouterLink type="button" class="btn btn-primary " to="/enderecos/listar">
                        Listar endereços da base de dados</RouterLink>
                </div>
                <div class="col">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Digite um CEP" aria-label="CEP"
                           v-on:keyup.enter="addressStore.keyboardEnterSubmit()" maxlength="8" pattern="\d*"
                            v-model="addressStore.inputCep" aria-describedby="button-addon2">
                        <RouterLink type="button" class="btn btn-outline-secondary"
                            v-on:click="addressStore.updateView()" to="/enderecos/buscar">
                            Buscar CEP</RouterLink>
                    </div>
                </div>
                <suspense>
                    <router-view :key="addressStore.update" />
                </suspense>
            </div>
            <br>
        </div>
    </section>
</template>
<style>
/* to="{
                        path: '/enderecos/listar/',
                        name: 'enderecosListar',
                        props: addressStore.listaEnderecos(),
                        query: {enderecosListar: }
                    }" */
</style>