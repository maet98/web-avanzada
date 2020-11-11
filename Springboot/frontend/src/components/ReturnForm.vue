<template>
    <form class="container">
        <h2> Return Form: </h2>
        <b-input-group v-for="order in rental.orderList" :key="order.id" :prepend="order.equiment.name" class="mb-2">
            <b-form-input type="number" min="0" v-model="order.quantity" max="order.quantity"></b-form-input>
          </b-input-group>
        <h3> Costo: {{ costo }} </h3>
        <button class="btn btn-primary" type="submit"> Devolver </button> 
    </form>
</template>

<script>

export default {
    name: "returnForm",
    props: ['id'],
    data() {
        return {
            rental: null,
            err: false,
            costo: 0
        }
    },
    mounted() {
        this.getRental();
    },
    methods: {
        getRental() {
            window.axios.get("rental/"+ this.$props.id)
                .then( ans => {
                    this.rental = ans.data;
                    this.calculate();
                }) 
                .catch( err => {
                    console.log(err);
                    this.err = true;
                })
        },
        calculate() {
            var orders = this.rental.orderList;
            console.log(orders);
            var total = 0;
            for(let i = 0; i < orders.length; i++) {
                total += orders[i].quantity * orders[i].equiment.cost;
            }
            this.costo = total;
        }
    }

}

</script>
