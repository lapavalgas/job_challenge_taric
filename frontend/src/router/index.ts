import { createRouter, createWebHistory } from "vue-router";
import HomeLoginView from "@/views/HomeLoginView.vue";
import ClientesView from "@/views/ClientesListView.vue";
import SalvarClientesListView from "@/views/SalvarClientesView.vue"
import EnderecosView from "@/views/EnderecosView.vue";
import EnderecosListarResultVue from "@/components/EnderecosListar.vue";
import EnderecosBuscaResultVue from "@/components/EnderecosBuscaResult.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeLoginView,
    },
    {
      path: "/salvar/",
      name: "salvar",
      component: SalvarClientesListView,
    },
    {
      path: "/clientes",
      name: "clientes",
      component: ClientesView,
    },
    {
      path: "/enderecos",
      name: "enderecos",
      component: EnderecosView,
      children: [
        {
          path: "listar/",
          name: "enderecosListar",
          component: EnderecosListarResultVue,
        },
        {
          path: "buscar",
          name: "buscarEnderecos",
          component: EnderecosBuscaResultVue,
        },
      ]
    },
  ],
});

export default router;
