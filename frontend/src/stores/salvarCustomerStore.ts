import { defineStore } from "pinia";
import { useCustomerStore } from "./customerStore";
import { createPinia } from "pinia";
import { markRaw } from "vue";
import router from "@/router";

const pinia = createPinia()

pinia.use(({ store }) => { store.router = markRaw(router); })

export const useSalvarCustomerStore = defineStore({
    id: "salvar",

    state: () => ({
        URI: "http://127.0.0.1:8080/graphql" as string,

        buscaCepBff: "" as string,

        customerDetailToSave: {} as any,

        customerId: 0 as any,

        componentKey: 0,

        formIsValid: {
            nome: true,
            cpf: true,
            email: true,
            telefone: true,
            cep: true,
            logradouro: true,
            numero: true,
            bairro: true,
            cidade: true,
            estado: true,
        }

    }),

    getters: {},

    actions: {
        form: function () {
            return {
                validaNome: () => {
                    if (this.customerDetailToSave.nome.length >= 4) { this.formIsValid.nome = true; } else { this.formIsValid.nome = false; }
                },
                validaCpf: () => {
                    if (this.customerDetailToSave.cpf.length >= 11) { this.formIsValid.cpf = true; } else { this.formIsValid.cpf = false; }
                },
                validaEmail: () => {
                    if (this.customerDetailToSave.email.includes('@') && this.customerDetailToSave.email.includes('.')) { this.formIsValid.email = true; } else { this.formIsValid.email = false; }
                },
                validaTelefone: () => {
                    if (this.customerDetailToSave.telefone.length >= 11) { this.formIsValid.telefone = true; } else { this.formIsValid.telefone = false; }
                },
                validaCep: () => {
                    if (this.customerDetailToSave.cep.length >= 8) { this.formIsValid.cep = true; } else { this.formIsValid.cep = false; }
                },
                validaLogradouro: () => {
                    if (this.customerDetailToSave.logradouro.length >= 4) { this.formIsValid.logradouro = true; } else { this.formIsValid.logradouro = false; }
                },
                validaNumero: () => {
                    if (this.customerDetailToSave.numero.length >= 1) { this.formIsValid.numero = true; } else { this.formIsValid.numero = false; }
                },
                validaBairro: () => {
                    if (this.customerDetailToSave.bairro.length >= 2) { this.formIsValid.bairro = true; } else { this.formIsValid.bairro = false; }
                },
                validaCidade: () => {
                    if (this.customerDetailToSave.localidade.length >= 2) { this.formIsValid.cidade = true; } else { this.formIsValid.cidade = false; }
                },
                validaEstado: () => {
                    if (this.customerDetailToSave.uf.length >= 2) { this.formIsValid.estado = true; } else { this.formIsValid.estado = false; }
                },
            }
        },

        clearForm: function () {
            this.customerDetailToSave = {}
            this.customerId = {}
            this.formIsValid.nome = true;
            this.formIsValid.cpf = true;
            this.formIsValid.email = true;
            this.formIsValid.telefone = true;
            this.formIsValid.cep = true;
            this.formIsValid.logradouro = true;
            this.formIsValid.numero = true;
            this.formIsValid.bairro = true;
            this.formIsValid.cidade = true;
            this.formIsValid.estado = true;
        },

        formValida: function () {
            this.form().validaNome();
            this.form().validaCpf();
            this.form().validaEmail();
            this.form().validaTelefone();
            this.form().validaCep();
            this.form().validaLogradouro();
            this.form().validaNumero();
            this.form().validaBairro();
            this.form().validaCidade();
            this.form().validaEstado();
            const validCustomer = (
                this.formIsValid.nome &&
                this.formIsValid.cpf &&
                this.formIsValid.email &&
                this.formIsValid.telefone &&
                this.formIsValid.cep &&
                this.formIsValid.logradouro &&
                this.formIsValid.numero &&
                this.formIsValid.bairro &&
                this.formIsValid.cidade &&
                this.formIsValid.estado
            );
            if (validCustomer) { return true; } else { return false; }
        },

        startForm: function () {
            this.customerDetailToSave.nome = "";
            this.customerDetailToSave.cpf = "";
            this.customerDetailToSave.email = "";
            this.customerDetailToSave.telefone = "";
            this.customerDetailToSave.cep = "";
            this.customerDetailToSave.logradouro = "";
            this.customerDetailToSave.numero = "";
            this.customerDetailToSave.bairro = "";
            this.customerDetailToSave.localidade = "";
            this.customerDetailToSave.uf = "";
        },

        nameMask: function () { this.customerDetailToSave.nome = this.customerDetailToSave.nome.replace(/[^a-zA-Z ]/g, ""); },

        cpfMask: function () { this.customerDetailToSave.cpf = this.customerDetailToSave.cpf.replace(/[^\d]/g, "").replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4"); },

        telefoneMask: function () { this.customerDetailToSave.telefone = this.customerDetailToSave.telefone.replace(/[^\d]/g, "").replace(/(\d{0})(\d{2})(\d{0})(\d{5})/g, "$1($2)$3 $4-"); },

        // .replace(/(\d{5})/g, "$1-");
        cepEnderecoMask: function () { this.customerDetailToSave.cep = this.customerDetailToSave.cep.replace(/[^\d]/g, ""); this.checkCep(); },

        numeroEnderecoMask: function () { this.customerDetailToSave.numero = this.customerDetailToSave.numero.replace(/[^\d]/g, ""); },

        ufEnderecoMask: function () { this.customerDetailToSave.estado = this.customerDetailToSave.estado.replace(/^\d+$/, ""); },

        forceRender: function () { this.componentKey += 1; },

        confirm: function (txt: string) { return confirm(txt); },

        confirmarSalvar: function () {
            if (this.confirm("Deseja salvar os dados do cliente?")) {
                if (this.formValida()) {
                    this.salvaCustomer();
                } else {
                    alert('Há inconsistencia, verifique os dados do cliente.');
                }
            }
        },

        confirmarDeletar: function (clienteId: string) { if (this.confirm("Deseja deletar os dados do cliente?")) { this.deleteCustomer(clienteId); } },

        confirmarCancelar: function () { if (this.confirm("Deseja cancelar operação?")) { this.back(); } },

        setCustomer: function (customerId: string): void { this.customerDetailToSave = {}; this.customerId = customerId; },

        loadCustomerData: function (data: any): void { this.customerDetailToSave = data; },

        updateCustomerData: function (cep: any): void {
            this.customerDetailToSave.enderecoCepId = null;
            this.customerDetailToSave.bairro = cep.bairro;
            this.customerDetailToSave.cep = (cep.cep);
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
                    this.formValida();
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
                                cpf 
                                statusCode
                                msg
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
                                cpf 
                                statusCode
                                msg
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
                    if (res.data.salvaCliente.statusCode == "401") {
                        alert("Erro inesperado.\n\nVocê não tem permissão para alterar o CPF.")
                    } else {
                        this.clearForm();
                        router.push('/clientes');
                        this.forceRender();
                    }
                })
                .catch(err => { console.log(err); });
        },

        back: function () { this.clearForm(); router.push('/clientes'); this.forceRender(); }

    },
});