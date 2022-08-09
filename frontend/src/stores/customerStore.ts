import { defineStore } from "pinia";

export const useCustomerStore = defineStore({
    id: "customer",

    state: () => ({
        URI: "http://127.0.0.1:8080/graphql" as string,

        customers: [] as any,

        customerIdFocus: 1,

    }),

    getters: {},

    actions: {
        loadCustomers: async function (): Promise<any> {
            const body = JSON.stringify({
                query: `
                query {
                    clientes {
                        statusCode
                        msg
                        data{
                            clienteId
                            nome
                            statusCode
                            msg
                            clienteId
                            enderecoCepId
                            cpf
                            nome
                            email
                            telefone
                            cep
                            logradouro
                            bairro
                            localidade
                            uf
                            ibge
                            numero
                            complemento
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
                    this.customers = res.data.clientes.data;
                })
                .catch(err => { console.log(err); });
        },

        getCustomerById: async function (id: any): Promise<any> {
            const body = JSON.stringify({
                query: `
                query {
                    cliente(id: `+ id + `) {
                        statusCode
                        msg
                        clienteId
                        enderecoCepId
                        cpf
                        nome
                        email
                        telefone
                        cep
                        logradouro
                        bairro
                        localidade
                        uf
                        ibge
                        numero
                        complemento
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
                    return res.data.cliente;
                })
                .catch(err => { console.log(err); });
        },

        setCustomerFocus(id: number): void { this.customerIdFocus = id; },

    },
});