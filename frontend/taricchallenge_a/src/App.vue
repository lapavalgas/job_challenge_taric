<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";


import { teste } from './stores/teste';

const ttt = teste();




</script>

<template>

  <p>teste {{ ttt.teste }} </p>
  <RouterView />
</template>

<style scoped>
</style>
