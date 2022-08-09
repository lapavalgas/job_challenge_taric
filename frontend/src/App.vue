<script setup lang="ts">
import { RouterView } from "vue-router";
import HeaderItem from "./components/HeaderItem.vue";
import FooterItem from "./components/FooterItem.vue";
import { useAuthenticationStore } from "./stores/authenticationStore";
import { useSalvarCustomerStore } from '@/stores/salvarCustomerStore';
import { onBeforeMount } from "vue";
const authentication = useAuthenticationStore();
const salvarCustomerStore = useSalvarCustomerStore();


onBeforeMount(() => { authentication.authenticationPersistOnReload(); });



</script>
<template>
  <div>
    <header v-on:click="salvarCustomerStore.forceRender()" v-bind:class="{ logoff: !authentication.isUserLoggedValue }">
      <div class="fixed header">
        <HeaderItem :key="salvarCustomerStore.componentKey" />
      </div>
    </header>
    <main>
      <div class="main-container">
        <RouterView :key="salvarCustomerStore.componentKey" />
      </div>
    </main>
    <footer>
      <div class="fixed footer">
        <FooterItem :key="salvarCustomerStore.componentKey" />
      </div>
    </footer>
  </div>
</template>
<style>
.logoff {
  display: none;
}
</style>