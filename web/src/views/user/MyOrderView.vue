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
                        <button type="button" class="btn btn-primary" @click="getorderdetail(item)"
                            data-bs-toggle="modal" :data-bs-target="'#showdetail' + item.id">查看详情</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </th>

                    <!-- Modal 删除 -->
                    <div class="modal fade" :id="'showdetail' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">查看详情</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">商品id</th>
                                                <th scope="col">商品名称</th>
                                                <th scope="col">数量</th>
                                                <th scope="col">单价</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr v-for="detail in item.details" :key="detail.id">
                                                <th scope="row">{{ detail.id }}</th>
                                                <td>{{ detail.productname }}</td>
                                                <td>{{ detail.quantity }}</td>
                                                <td>{{ detail.cost }}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>


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

        const getorderdetail = item => {
            let params = {
                orderid: item.id
            }
            api.getorderdetail(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp.orderdetail);
                item.details = resp.orderdetail
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