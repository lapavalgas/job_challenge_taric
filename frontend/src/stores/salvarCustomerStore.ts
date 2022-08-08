import { defineStore } from "pinia";
import { useCustomerStore } from "./customerStore";
import { createPinia } from "pinia";
import { markRaw } from "vue";
import router from "@/router";

const pinia = createPinia()

pinia.use(({ store }) => {
    store.router = markRaw(router)
})

export const useSalvarCustomerStore = defineStore({
    id: "salvar",
    state: () => ({

        URI: "http://127.0.0.1:8080/graphql" as string,

        buscaCepBff: "" as string,

        customerDetailToSave: {} as any,

        customerId: 0 as any,

        componentKey: 0,

    }),

    getters: {

    },

    actions: {

        forceRender: function () {
            this.componentKey += 1;
        },

        setCustomer: function (customerId: string): void {
            this.customerDetailToSave = {};
            this.customerId = customerId;
        },

        loadCustomerData: function (data: any): void { this.customerDetailToSave = data; },

        updateCustomerData: function (cep: any): void {
            this.customerDetailToSave.enderecoCepId = null;
            this.customerDetailToSave.bairro = cep.bairro;
            this.customerDetailToSave.cep = cep.cep;
            this.customerDetailToSave.enderecoCepId = cep.enderecoCepId;
            this.customerDetailToSave.ibge = cep.ibge;
            this.customerDetailToSave.localidade = cep.localidade;
            this.customerDetailToSave.logradouro = cep.logradouro;
            this.customerDetailToSave.msg = cep.msg;
            this.customerDetailToSave.statusCode = cep.statusCode;
            this.customerDetailToSave.uf = cep.uf;
            this.customerDetailToSave.numero = "";
            this.customerDetailToSave.complemento = "";
        },

        checkCep: async function () {
            if (this.customerDetailToSave.cep.length === 8) {
                let cep = await this.buscaCep(this.customerDetailToSave.cep);
                if (this.customerDetailToSave.cep !== cep.cep) {
                    this.updateCustomerData(cep);
                    console.log("LOG: OpenCEP updated the Customer CEP Data. ");
                }
            }
        },

        buscaCep: async function (cep: string): Promise<any> {
            const body = JSON.stringify({
                query: `
                mutation {
                    buscaCep(cep: "`+ cep + `") {
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
                    this.buscaCepBff = res.data.buscaCep;
                    return res.data.buscaCep;
                })
                .catch(err => { console.log(err); });
        },


        deleteCustomer: async function (customerId: any): Promise<any> {
            const body = JSON.stringify({
                query: `
                mutation {
                    deleteCliente(id: "`+ customerId + `"){statusCode msg}
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
                    this.back();
                })
                .catch(err => { console.log(err); });
        },


        salvaCustomer: async function (): Promise<any> {
            const isEditing = ("0".includes(this.customerId)) ? false : true;
            let body = undefined;
            if (isEditing) {
                body = JSON.stringify({
                    query: `
                    mutation{
                        salvaCliente(
                            data:{
                                clienteId: "`+ this.customerDetailToSave.clienteId + `"
                                cpf: "`+ this.customerDetailToSave.cpf + `"
                                nome: "`+ this.customerDetailToSave.nome + `"
                                email: "`+ this.customerDetailToSave.email + `"
                                telefone: "`+ this.customerDetailToSave.telefone + `"
                                cep: "`+ this.customerDetailToSave.cep + `"
                                logradouro: "`+ this.customerDetailToSave.logradouro + `"
                                numero: "`+ this.customerDetailToSave.numero + `"
                                complemento: "`+ this.customerDetailToSave.complemento + `"
                                bairro: "`+ this.customerDetailToSave.bairro + `"
                                localidade: "`+ this.customerDetailToSave.localidade + `"
                                uf: "`+ this.customerDetailToSave.uf + `"
                                ibge: "`+ this.customerDetailToSave.ibge + `"
                            }){
                            clienteId
                            enderecoCepId
                        }
                    }
                      `
                });
            } else {
                body = JSON.stringify({
                    query: `
                    mutation{
                        salvaCliente(
                            data:{
                                cpf: "`+ this.customerDetailToSave.cpf + `"
                                nome: "`+ this.customerDetailToSave.nome + `"
                                email: "`+ this.customerDetailToSave.email + `"
                                telefone: "`+ this.customerDetailToSave.telefone + `"
                                cep: "`+ this.customerDetailToSave.cep + `"
                                logradouro: "`+ this.customerDetailToSave.logradouro + `"
                                numero: "`+ this.customerDetailToSave.numero + `"
                                complemento: "`+ this.customerDetailToSave.complemento + `"
                                bairro: "`+ this.customerDetailToSave.bairro + `"
                                localidade: "`+ this.customerDetailToSave.localidade + `"
                                uf: "`+ this.customerDetailToSave.uf + `"
                                ibge: "`+ this.customerDetailToSave.ibge + `"
                            }){
                            clienteId
                            enderecoCepId
                        }
                    }
                      `
                });
            }
            return fetch(this.URI, {
                method: 'POST',
                headers: { Accept: "application/json", "Content-Type": "application/json", },
                body: body
            })
                .then((res) => { return res.json() })
                .then(res => {
                    console.log(res)
                    this.customerDetailToSave = {}
                    this.customerId = {}
                    router.push('/clientes')
                    this.forceRender();
                })
                .catch(err => { console.log(err); });
        },

        back: function () {
            this.customerDetailToSave = {}
            this.customerId = {}
            router.push('/clientes')
            this.forceRender();

        }

    },
});

