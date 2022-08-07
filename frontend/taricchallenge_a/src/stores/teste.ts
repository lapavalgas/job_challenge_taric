import { defineStore } from 'pinia';

export const teste = defineStore('teste', {
    state: () => {
        return { teste : "rafael"}
    },
    // could also be defined as
    // state: () => ({ count: 0 })
    actions: {

    }
})