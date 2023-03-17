<template>
    <div style="width:100vw;">
        <!-- <button type="button" class="btn btn-primary" style="margin-left: 30px;">管理我的地址</button> -->
        <table class="table table-sm " style="text-align: center;width: 65%;margin:0 auto;">
            <thead>
                <tr>
                    <th><input class="form-check-input" type="checkbox" @change="selectall"></th>
                    <th scope="col">序号</th>
                    <th scope="col">商品名称</th>
                    <th scope="col">数量</th>
                    <th scope="col">单价</th>
                    <th scope="col">合计</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in carts" :key="index">
                    <th><input class="form-check-input" type="checkbox" v-model="item.ischecked" @change="updatetotal">
                    </th>
                    <th scope="row">{{ index + 1 }}</th>
                    <td>{{ item.name }}</td>
                    <td>
                        <button type="button" class="btn btn-secondary"
                            @click="addnum(item.productId, item.quantity + 1)">+</button>
                        <input v-model="item.quantity" type="text" style="width: 40px;"
                            @change="addnum(item.productId, item.quantity)">
                        <button type="button" class="btn btn-secondary"
                            @click="addnum(item.productId, item.quantity - 1)">-</button>
                    </td>
                    <td>{{ item.price }}</td>
                    <td>{{ item.quantity * item.price }}</td>
                </tr>
                <tr>
                    <th colspan="5">合计</th>
                    <th>{{ total }}</th>
                </tr>
            </tbody>
        </table><br />
        <div style="padding-left: 30px;">
            <h4 style="float: left;">配送至</h4>
            <select class="form-select" v-model="addressid" aria-label="Default select example"
                style="width: 200px;float: left;margin-left: 30px;">
                <option v-for="item in address" :value="item.id" :key="item.id">{{ item.remark }}({{ item.address }})
                </option>
            </select>
            <button type="button" class="btn btn-primary" style="margin-left: 30px;float: left;"
                @click="buy">下单</button>
        </div>

    </div>
</template>

<script>
import api from '@/api'
import { ref } from 'vue';
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
    setup() {
        let store = useStore();
        let carts = ref([])
        let address = ref([])
        let addressid = ref(0);
        // let total = ref(0)
        const pull_page = () => {
            api.getcart({}, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    if (carts.value.length > 0)
                        for (let i of resp.cart)
                            for (let j of carts.value)
                                if (i.productId == j.productId) {
                                    i.ischecked = j.ischecked;
                                    break;
                                }
                    carts.value = resp.cart
                }
            })
        }


        const getcart = () => {
            if (store.state.user.is_login) {
                api.getcart({}, store.state.user.token).then(res => {
                    let resp = res.data;
                    if (resp.result === "success") {
                        store.commit("updateCartNum", resp.cart.length);
                    }
                })
            }
        }

        const addnum = (productId, quantity) => {
            if (quantity >= 0) {
                let params = {
                    productId,
                    quantity
                }
                api.addcart(params, store.state.user.token).then(res => {
                    let resp = res.data;
                    if (resp.result === "success") {
                        // alerts.value = true;
                        // alert_message.value = item.name + "加入购物车成功"
                        getcart()
                        pull_page()
                    }
                })
            }
        }

        const getaddress = () => {
            api.getaddress({}, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    address.value = resp.address
                    addressid.value = address.value[0].id
                }
            })
        }

        const selectall = (status) => {
            let cart = []
            for (let item of carts.value) {
                item.ischecked = status.target.checked
                cart.push(item)
            }
            carts.value = cart
        }
        let total = computed(() => {
            let s = 0
            for (let i of carts.value) {
                if (i.ischecked) {
                    s += i.quantity * i.price
                }
            }
            return s
        });

        const buy = () => {
            let cart = []
            let total = 0
            for (let i of carts.value) {
                if (i.ischecked) {
                    cart.push({
                        productId: i.productId,
                        quantity: i.quantity
                    })
                    total += i.quantity * i.price
                }
            }
            if (total == 0) {
                alert("你还未选择任何商品")
                return
            }
            let data = {
                cart,
                addressid: addressid.value
            }
            api.buy(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    alert("下单成功")
                }
            })
        }

        pull_page()
        getaddress()

        return {
            carts,
            addnum,
            buy,
            address,
            addressid,
            selectall,
            total
        }
    }
}

</script>


<style scoped>

</style>