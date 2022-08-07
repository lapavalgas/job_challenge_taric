<script setup lang="ts">
import ClientesListDetailPersonalData from './ClientesListElementDetailPersonalData.vue';
import ClientesListDetailAddressData from './ClientesListElementDetailAddressData.vue';
import ClientesListDetailsActions from './ClientesListElementDetailActions.vue'

</script>
<template>
    <div class="collapse" id="userUUID1">
        <div class="card card-body card-userdata">
            <ClientesListDetailPersonalData />
            <br>
            <ClientesListDetailAddressData />

            <ClientesListDetailsActions />
        </div>
    </div>
</template>