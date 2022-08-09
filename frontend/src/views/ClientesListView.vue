<script setup lang="ts">
import ClientesToasts from "../components/ClientesListToasts.vue";
import ClientesList from "@/components/ClientesList.vue";

</script>
<template>
  <section id="clientes">
    <ClientesToasts />
    <suspense>
      <ClientesList />
    </suspense>
  </section>
</template>