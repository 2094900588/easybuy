<template>
    <div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">订单id</th>
                    <th scope="col">订单号</th>
                    <th scope="col">用户地址</th>
                    <th scope="col">下单时间</th>
                    <th scope="col">小计</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in orders" :key="item.id">
                    <th scope="row">{{ item.id }}</th>
                    <td>{{ item.serialnumber }}</td>
                    <td>{{ item.useraddress }}</td>
                    <td>{{ item.createtime }}</td>
                    <th>{{ item.cost }} 元</th>
                    <th>
                        <button type="button" class="btn btn-primary" @click="getorderdetail(item.id)">查看详情</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </th>


                </tr>
            </tbody>
        </table>
    </div>

</template>
<script>
import api from '@/api'
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
    setup() {
        let store = useStore();
        let orders = ref([])

        const pull_page = () => {
            api.getuserorder({}, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    orders.value = resp.userorder
                }
            })
        }

        const getorderdetail = orderid => {
            let params = {
                orderid
            }
            api.getorderdetail(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
            })
        }

        pull_page()

        return {
            orders,
            getorderdetail
        }
    }

}
</script>