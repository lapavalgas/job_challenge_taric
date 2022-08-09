<script setup lang="ts">
import { useAuthenticationStore } from '@/stores/authenticationStore';
const authentication = useAuthenticationStore();
window.addEventListener('keydown', (event) => {
    authentication.keyboardEnterSubmit(event);
});

</script>
<template>
    <section id="clientes">
        <div id="login" class="container py-5 h-100">
            <div id="login-card">
                <h5>Sign in</h5><br>
                <div class="col">
                    <div class="form-floating mb-3">
                        <input v-model="authentication.user" type="text" class="form-control" id="userLoginInput"
                            placeholder="user">
                        <label for="userLoginInput">Usuário</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input v-model="authentication.pass" type="password"
                            class="form-control" id="passLoginInput" placeholder="password">
                        <label for="passLoginInput">Senha</label>
                    </div>
                    <div class="form-floating mb-3">
                        <button @click="authentication.login()" id="btn-login" class="btn btn-primary btn-lg btn-block"
                            type="submit">LOGIN</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>