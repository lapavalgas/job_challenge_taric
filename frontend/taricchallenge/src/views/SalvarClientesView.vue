<script setup lang="ts">
import FormCadastrarEditarClientes from '@/components/SalvarClientes.vue';

</script>
<template>
    <section id="clientes">
        <div class=" container py-5 h-100">
            <suspense>
                <FormCadastrarEditarClientes />
            </suspense>
        </div>
    </section>
</template>