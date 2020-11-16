<template>
    <form class="container" @submit.prevent="submit">
        <h2> Return Form: </h2>
        <b-input-group v-for="(order, index) in rental.orderList" :key="order.id" :prepend="order.equiment.name" class="mb-2">
            <b-form-input type="number" min="0" :id="order.id" @change="calculate" v-model="order.quantity" :max="originalRental[index]"></b-form-input>
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
            originalRental: null,
            days: 0,
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
                    var arr = [];
                    for(let i = 0;i < this.rental.orderList.length; i++) {
                        arr.push(this.rental.orderList[i].quantity);
                    }
                    this.originalRental = arr;
                    this.calculateDays();
                    this.calculate();
                }) 
                .catch( err => {
                    console.log(err);
                    this.err = true;
                })
        },
        calculateDays() {
            var date1 = new Date(this.rental.dateOfRental)
            var date2 = new Date()
            var Difference_In_Time = date2.getTime() - date1.getTime(); 
            var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24) ; 
            this.days = parseInt(Math.ceil(Difference_In_Days));
        },
        calculate() {
            var orders = this.rental.orderList;
            var total = 0;
            for(let i = 0; i < orders.length; i++) {
                total += orders[i].quantity * orders[i].equiment.cost * this.days;
            }
            this.costo = total;
        },
        submit() {
            window.axios.post("/rental/return",this.rental)
                .then(ans => {
                    console.log(ans);
                    this.$router.go(-1);
                }).catch( err => {
                    console.log(err);
                });
        },
    },
    computed: {

    }
}

</script>
