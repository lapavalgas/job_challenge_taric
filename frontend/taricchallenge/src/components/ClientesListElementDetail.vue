<script setup lang="ts">
import ClientesListDetailPersonalData from './ClientesListElementDetailPersonalData.vue';
import ClientesListDetailAddressData from './ClientesListElementDetailAddressData.vue';
import ClientesListDetailsActions from './ClientesListElementDetailActions.vue'
import { useCustomerStore } from '@/stores/customerStore';
const props = defineProps({
    customerId: String,
});
const customerStore = useCustomerStore();
const customerDetail = await customerStore.getCustomerById(props.customerId);

</script>
<template>
    <div class="collapse" :id="'userCDID' + props.customerId">
        <div class="card card-body card-userdata">
            <ClientesListDetailPersonalData :customerDetail=customerDetail />
            <br>
            <ClientesListDetailAddressData :customerDetail=customerDetail />

            <ClientesListDetailsActions :customerId=props.customerId />
        </div>
    </div>
</template>
