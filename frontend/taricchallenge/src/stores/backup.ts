import { defineStore } from "pinia";

interface Data {
  clienteId?: string;
  cpf?: string;
  nome?: string;
  email?: string;
  telefone?: string;
  enderecoCepId?: string;
  cep?: string;
  logradouro?: string;
  bairro?: string;
  localidade?: string;
  uf?: string;
  ibge?: string;
  numero?: string;
  complemento?: string;
  msg?: string;
  statusCode?: string;
  exception?: string;
  error?: string;
  cookie?: string;
  data?: [Data];
  clientesAssociados?: [Data];
};

export const useFetchData = defineStore({
  id: "fetchData",
  state: () => ({
    URI: "http://127.0.0.1:8080/graphql" as string,
    data: {} as Data,

  }),
  getters: {

  },
  actions: {

    login: async function (user: string, pass: string): Promise<Data> {
      user = "taric";
      pass = "taric1234";
      const body = JSON.stringify({
        query: `
          mutation {
            login(user: "taric", pass: "taric1234") {
              statusCode
              msg
            }
          }
        `
      });

      return fetch(this.URI, {
        method: 'POST',
        headers: { Accept: "application/json", "Content-Type": "application/json", },
        body: body
      })
        .then((response) => response.json());
    },

    logoff: function (): Data {
      return { nome: "teste" };
    },

    getCustomersList: function () {
      return { nome: "teste" };
    },

    getCustomerById: function (id: string): Data {
      return { nome: "teste" };
    },

    saveCustomerById: function (data: Data): Data {
      return { nome: "teste" };
    },

    deleteCustomerById: function (id: string): Data {
      return { nome: "teste" };
    },

    buscaCep: function (cep: string): Data {
      return { nome: "teste" };
    },

    getAddressList: function (): Data {
      return { nome: "teste" };
    }

  }

},
);

