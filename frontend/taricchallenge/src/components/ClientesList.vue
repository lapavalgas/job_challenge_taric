<script  setup lang="ts">
import ClientesListElement from './ClientesListElement.vue'

</script>
<template>
    <div class=" container py-5 h-100">
        <h5>Clientes</h5>
        <div class="list-group">
            <Suspense>
                <ClientesListElement />
            </Suspense>
        </div>
    </div>
</template>