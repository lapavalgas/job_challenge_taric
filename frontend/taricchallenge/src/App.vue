<script setup lang="ts">
import { RouterView, useRoute, useRouter } from "vue-router";
import HeaderItem from "./components/HeaderItem.vue";
import FooterItem from "./components/FooterItem.vue";

const route = useRoute();
const router = useRouter();
var currentRoute = "/";

async function getCurrentRouter(): Promise<any> {
  await router.isReady();
  console.log(route.path)
  currentRoute = route.path;
}

const isUserLoggedValue: boolean = isUserLogged(sessionStorage.getItem('isUserLogged'));

function isUserLogged(sessionValue: any): boolean {
  switch (sessionValue) {
    case null:
      sessionStorage.setItem('isUserLogged', "false");
      return false;
    case "false":
      return false;
    case "true":
      return true;
    default:
      return false;
  }

}
</script>

<template>
  <div v-on:click="getCurrentRouter()">
    <header v-bind:class="{ logoff: !isUserLoggedValue }">
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
  </div>
</template>
<style>
.logoff {
  display: none;
}
</style>