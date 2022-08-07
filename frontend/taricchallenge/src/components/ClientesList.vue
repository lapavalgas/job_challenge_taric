<script  setup lang="ts">
import ClientesListElement from './ClientesListElement.vue'
import ClientesListNav from './ClientesListNav.vue';

</script>
<template>
    <div class=" container py-5 h-100">
        <h5>Clientes</h5>
        <div class="list-group">
            <ClientesListElement />
        </div>
        <ClientesListNav />
    </div>
</template>