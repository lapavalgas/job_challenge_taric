<script setup lang="ts">
import { RouterView } from "vue-router";
import HeaderItem from "./components/HeaderItem.vue";
import FooterItem from "./components/FooterItem.vue";
import { useAuthenticationStore } from "./stores/authenticationStore";
const authentication = useAuthenticationStore();

</script>
<template>
  <header v-bind:class="{ logoff: !authentication.isUserLoggedValue }">
    <div class="fixed header">
      <HeaderItem />
    </div>
  </header>

  <main>
    <div class="main-container">
      <RouterView />
    </div>
  </main>
  <footer>
    <div class="fixed footer">
      <FooterItem />
    </div>
  </footer>
</template>
<style>
.logoff {
  display: none;
}
</style>