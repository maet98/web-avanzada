<template>
    <div>
        <b-table v-if="items.length >0" :fields="fields" :items="items" />
        <h3 v-else>{{ $t("allEquimentReturned") }}</h3>
    </div>
</template>

<script>
export default {
    name: 'NotReturned',
    data() {
        return {
            items: [],
            fields: [
                { key: 'id', sortable: true},
                { key: 'quantity', sortable: true},
                { key: 'name', sortable: true},
                { key: 'client', sortable: true},
                { key: 'days', sortable: true},
            ]
        }
    },
    mounted() {
        this.getItems();
    },
    methods: {
        getItems() {
            window.axios.get("rental/rented")
                .then(ans => {
                    console.log(ans.data);
                    this.items = ans.data
                })
                .catch(err => console.log(err))
        }
    }
}

</script>
