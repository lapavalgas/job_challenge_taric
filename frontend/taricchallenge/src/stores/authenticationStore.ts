import { defineStore, createPinia } from "pinia";
import { markRaw } from "vue";
import router from "@/router";

const pinia = createPinia()

pinia.use(({ store }) => {
    store.router = markRaw(router)
})

export const useAuthenticationStore = defineStore({
    id: "authentication",
    state: () => ({

        URI: "http://127.0.0.1:8080/graphql" as string,

        isUserLoggedValue: false as boolean,
        user: "",
        pass: "",
    }),
    getters: {
    },
    actions: {
        loginSuccessful: function (statuscode: any): void {
            switch (statuscode) {
                case "200":
                    this.isUserLoggedValue = true;
                    break;
                case "406":
                    this.isUserLoggedValue = true;
                    break;
                case "400":
                    this.isUserLoggedValue = false;
                    break;
                default:
                    this.isUserLoggedValue = false;
                    break;
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

        logoff: async function (): Promise<any> {
            this.isUserLoggedValue = false;
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
            this.routerAuthenticationBehaviour();
        },

        routerAuthenticationBehaviour: function (): void {
            if (!this.isUserLoggedValue) { router.push({ path: '/' }) }
            if (this.isUserLoggedValue) { router.push({ path: '/clientes' }) }
        },

    },
});