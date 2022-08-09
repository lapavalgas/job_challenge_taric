import { defineStore, createPinia } from "pinia";
import { markRaw } from "vue";
import router from "@/router";
import { useSalvarCustomerStore } from "./salvarCustomerStore";

const pinia = createPinia()

pinia.use(({ store }) => { store.router = markRaw(router); });

export const useAuthenticationStore = defineStore({
    id: "authentication",

    state: () => ({

        URI: "http://127.0.0.1:8080/graphql" as string,

        isUserLoggedValue: false as boolean,

        user: "",

        pass: "",

    }),

    getters: {},

    actions: {
        setSession: function (value: boolean) { this.isUserLoggedValue = value; sessionStorage.setItem("isUserLoggedValue", "" + this.isUserLoggedValue); },

        getSession: function () { return (sessionStorage.getItem("isUserLoggedValue") == "true") ? true : false; },

        killSession: function () { this.isUserLoggedValue = false; sessionStorage.setItem("isUserLoggedValue", "undefined"); },

        authenticationPersistOnReload: function () {
            if (this.getSession()) { this.setSession(true); this.routerAuthenticationBehaviour(); } else {
                this.setSession(false);
                this.routerAuthenticationBehaviour();
            }
        },

        keyboardEnterSubmit: function (event: any) { if (event.keyCode == 13) { this.login(); } },

        routerAuthenticationBehaviour: function (): void {
            const salvarCustomerStore = useSalvarCustomerStore();
            if (!this.isUserLoggedValue) {
                alert("Falha na autenticação.");
                router.push({ path: '/' })
                salvarCustomerStore.forceRender();
            }
            if (this.isUserLoggedValue) {
                router.push({ path: '/clientes' })
                salvarCustomerStore.forceRender();
            }
        },

        loginSuccessful: function (statuscode: any): void {
            switch (statuscode) {
                case "200": this.setSession(true); break;
                case "406": this.setSession(true); break;
                case "400": this.setSession(false); break;
                default: this.setSession(false); break;
            }
        },

        login: async function (): Promise<any> {
            const body = JSON.stringify({
                query: `
                mutation {
                  login(user: "`+ this.user + `", pass: "` + this.pass + `") {
                    statusCode
                    msg
                  }
                }
              `
            });
            await fetch(this.URI, {
                method: 'POST',
                headers: { Accept: "application/json", "Content-Type": "application/json", },
                body: body
            })
                .then((res) => { return res.json() })
                .then(res => {
                    this.loginSuccessful(res.data.login.statusCode);
                })
                .catch(err => { console.log(err); });

            this.routerAuthenticationBehaviour();
        },

        confirmLogoff: function () {
            if (confirm("Deseja fazer logout?")) { this.logoff(); }
        },

        logoff: async function (): Promise<any> {
            const body = JSON.stringify({
                query: `
                mutation {
                    logoff {
                        statusCode
                    }
                }
                `
            });
            await fetch(this.URI, {
                method: 'POST',
                headers: { Accept: "application/json", "Content-Type": "application/json", },
                body: body
            })
                .then((res) => { return res.json() })
                .catch(err => { console.log(err); });
            this.killSession();
            router.push({ path: '/' })
            
        },
    },
});