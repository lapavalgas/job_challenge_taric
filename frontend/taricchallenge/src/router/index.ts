import { createRouter, createWebHistory } from "vue-router";
import HomeLoginView from "@/views/HomeLoginView.vue";
import ClientesView from "@/views/ClientesView.vue";
import SalvarClientesView from "@/views/SalvarClientesView.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeLoginView,
    },
    {
      path: "/salvar",
      name: "salvar",
      component: SalvarClientesView,
    },
    {
      path: "/clientes",
      name: "clientes",
      component: ClientesView,
    },
  ],
});

export default router;
