<template>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addaddress">添加地址</button>
    <div>
        <table class="table table-sm " style="text-align: center;width: 65%;margin:0 auto;">
            <thead>
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">地址</th>
                    <th scope="col">创建时间</th>
                    <th scope="col">是否默认</th>
                    <th scope="col">备注</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in address" :key="index">
                    <th scope="row">{{ item.id }}</th>
                    <td>{{ item.address }}</td>
                    <td>{{ item.createtime }}</td>
                    <td>{{ item.isdefault == 1 ? "是" : "否" }}</td>
                    <td>{{ item.remark }}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            :data-bs-target="'#modifyaddress' + item.id">修改</button>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                            :data-bs-target="'#deleteaddress' + item.id">删除</button>
                    </td>

                    <!-- Modal 删除 -->
                    <div class="modal fade" :id="'deleteaddress' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">删除地址</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    确定删除地址 {{ item.remark }}({{ item.address }})？
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger"
                                        @click="deladdress(item.id)">删除</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal 修改 -->
                    <div class="modal fade" :id="'modifyaddress' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">修改地址</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group">
                                        <span class="input-group-text">地址</span>
                                        <input v-model="item.address" type="text" class="form-control"
                                            placeholder="请输入地址">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">是否默认</span>
                                        <div style="margin-top: 5px;margin-left: 10px;">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio"
                                                    :name="'isdefault' + item.id" value=1 v-model="item.isdefault">
                                                <label class="form-check-label" for="inlineRadio1">是</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio"
                                                    :name="'isdefault' + item.id" value=0 v-model="item.isdefault">
                                                <label class="form-check-label" for="inlineRadio2">否</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">备注</span>
                                        <input v-model="item.remark" type="text" class="form-control"
                                            placeholder="请输入备注">
                                    </div>

                                    <div class="error-message">{{ item.error_message }}</div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary"
                                        @click="modifyaddress(item)">保存</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>


                </tr>
            </tbody>
        </table>
    </div>
    <!-- Modal 添加 -->
    <div class="modal fade" id="addaddress" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">添加地址</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-text">地址</span>
                        <input v-model="addaddress.address" type="text" class="form-control" placeholder="请输入地址">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">是否默认</span>
                        <div style="margin-top: 5px;margin-left: 10px;">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addisdefault" value=1
                                    v-model="addaddress.isdefault">
                                <label class="form-check-label" for="inlineRadio1">是</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addisdefault" value=0
                                    v-model="addaddress.isdefault">
                                <label class="form-check-label" for="inlineRadio2">否</label>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">备注</span>
                        <input v-model="addaddress.remark" type="text" class="form-control" placeholder="请输入备注">
                    </div>

                    <div class="error-message">{{ addaddress.error_message }}</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="addAddress">保存</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

import api from '@/api'
import { ref } from 'vue';
import { useStore } from 'vuex';

import { Modal } from 'bootstrap/dist/js/bootstrap'
export default {
    setup() {

        let store = useStore();
        let address = ref([])
        let addaddress = ref([])

        const pull_page = () => {
            api.getaddress({}, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    address.value = resp.address
                }
                console.log(resp);
            })
        }

        const deladdress = (id) => {
            let params = {
                id
            }
            api.deladdress(params, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    pull_page()
                    Modal.getInstance("#deleteaddress" + id).hide()

                }
            })
        }

        const modifyaddress = (item) => {
            let data = {
                id: item.id,
                address: item.address,
                remark: item.remark,
                isdefault: item.isdefault
            }
            api.modifyaddress(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    pull_page()
                    Modal.getInstance("#modifyaddress" + item.id).hide()
                }
            })
        }

        const addAddress = () => {
            let data = {
                address: addaddress.value.address,
                remark: addaddress.value.remark,
                isdefault: addaddress.value.isdefault
            }
            api.addaddress(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    pull_page()
                    Modal.getInstance("#addaddress").hide()
                    addaddress.value = []
                }
            })
        }

        pull_page()
        return {
            address,
            addaddress,
            deladdress,
            modifyaddress,
            addAddress
        }
    }
}

</script>

<style scoped>

</style>