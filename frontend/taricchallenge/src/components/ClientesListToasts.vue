<script setup lang="ts">
import { useToastStore } from '@/stores/toastStore';
const toastStore = useToastStore();

</script>
<template>
    <div id="tips">
        <div v-on:click="toastStore.dismissToast('dismiss')" id="tips-toast" v-bind:class="{ toasthidden: toastStore.dismiss }"
            class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>!!!</strong> Clique no cliente para ver os detalhes
            <button type="button" class="btn-close btn-toast" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>
</template>
<style scoped>
.toasthidden {
    opacity: 0;
}
</style>