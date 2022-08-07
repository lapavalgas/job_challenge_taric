<script setup lang="ts">
import LoginItem from '@/components/LoginItem.vue';
import { useFetchData } from '@/stores/fetchData';

const apiFetchData = useFetchData();

var banana = await apiFetchData.login("lala", "");

</script>
<template>
    <LoginItem />

    teste: {{ banana }}
</template>


