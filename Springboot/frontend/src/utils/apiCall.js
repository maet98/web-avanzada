import axios from "axios"

const instance = axios({})

const postRental = (rental) => {
    axios.post("/rental",rental)
        .then(res => {
            console.log(res.data);
        }).catch(err =>{
            console.log(err);
        })
}
