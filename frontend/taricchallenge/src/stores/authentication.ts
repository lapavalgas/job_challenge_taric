import { defineStore } from "pinia";

export const useCounterStore = defineStore({
  id: "counter",
  state: () => ({
    counter: 0,
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
  },
  actions: {
    increment() {
      this.counter++;
    },
  },
});


import { useRoute, useRouter } from "vue-router";

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