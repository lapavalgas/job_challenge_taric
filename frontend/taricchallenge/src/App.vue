<script setup lang="ts">
import { RouterView } from "vue-router";
import HeaderItem from "./components/HeaderItem.vue";
import FooterItem from "./components/FooterItem.vue";
import { useAuthenticationStore } from "./stores/authenticationStore";
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
const authentication = useAuthenticationStore();
const salvarCustomerStore = useSalvarCustomerStore();


</script>
<template>
  <div>
    <header v-on:click="salvarCustomerStore.forceRender()" v-bind:class="{ logoff: !authentication.isUserLoggedValue }">
      <div class="fixed header">
        <HeaderItem />
      </div>
    </header>
    <main>
      <div class="main-container">
        <RouterView :key="salvarCustomerStore.componentKey" />
      </div>
    </main>
    <footer>
      <div class="fixed footer">
        <FooterItem />
      </div>
    </footer>
  </div>
</template>
<style>
.logoff {
  display: none;
}
</style>