import { defineStore } from "pinia";
import router from "@/router";

export const useAddressStore = defineStore({
    id: "address",

    state: () => ({
        URI: "http://127.0.0.1:8080/graphql" as string,

        inputCep: "" as string,
        buscaCepResult: {} as any,
        statusCode: "500" as string,

        listaEnderecos: [] as any,

        update: 0,

    }),

    getters: {

    },

    actions: {

        keyboardEnterSubmit: function () {
            this.updateView();
            router.push('/enderecos/buscar');
        },

        updateView: function () {
            if (this.inputCep == "") { this.inputCep = "00000000" }
            this.buscaCep();
            setTimeout(() => { this.update += 1; }, 500);
            setTimeout(() => { this.update += 1; }, 1000);
            setTimeout(() => { this.update += 1; }, 3000);
            setTimeout(() => { this.update += 1; }, 6000);
            setTimeout(() => { this.update += 1; }, 12000);
        },

        buscaCep: async function (): Promise<any> {
            const body = JSON.stringify({
                query: `
                mutation {
                    buscaCep(cep: "`+ this.inputCep + `") {
                        statusCode
                        msg
                        enderecoCepId
                        cep
                        logradouro
                        bairro
                        localidade
                        uf
                        ibge
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
                .then((res) => { return res.json() })
                .then(res => {
                    this.buscaCepResult = res.data;
                    return res.data;
                })
                .catch(err => { console.log(err); });
        },

        buscaListaEnderecos: async function (): Promise<any> {
            const body = JSON.stringify({
                query: `
                    query {
                        enderecosCep {
                            statusCode
                            msg
                            data{
                                enderecoCepId
                                cep
                                logradouro
                                bairro
                                localidade
                                uf
                                ibge
                                clientesAssociados
                            }
                        }
                    }
                  `
            });
            return fetch(this.URI, {
                method: 'POST',
                headers: { Accept: "application/json", "Content-Type": "application/json", },
                body: body
            })
                .then((res) => { return res.json() })
                .then(res => {
                    this.listaEnderecos = res;
                    // this.statusCode = res.buscaCep.statusCode;
                    return res;
                })
                .catch(err => { console.log(err); });
        },

    }
});