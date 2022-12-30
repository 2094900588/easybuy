<template>
    <div>
        <div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#adduser">添加用户</button>
            <select class="form-select" v-model="page_size" aria-label="Default select example"
                style="width: 100px;float: right;" @change="pull_page(current_page)">
                <option value=5>5</option>
                <option value=10>10</option>
                <option value=20>20</option>
            </select>
        </div>


        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">用户名</th>
                    <th scope="col">登录名</th>
                    <th scope="col">性别</th>
                    <th scope="col">手机号</th>
                    <th scope="col">用户类型</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id">
                    <th>{{ user.id }}</th>
                    <td>{{ user.username }}</td>
                    <td>{{ user.loginname }}</td>
                    <td>{{ user.sex == 1 ? '男' : '女' }}</td>
                    <td>{{ user.mobile }}</td>
                    <td>{{ user.type == 1 ? '超级管理员' : '普通用户' }}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            :data-bs-target="'#modifyuser' + user.id">修改</button>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                            :data-bs-target="'#deleteuser' + user.id">删除</button>
                    </td>

                    <!-- Modal 删除 -->
                    <div class="modal fade" :id="'deleteuser' + user.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">删除用户</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    确定删除用户 {{ user.username }}({{ user.id }})？
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" @click="deluser(user.id)">删除</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- Modal 修改 -->
                    <div class="modal fade" :id="'modifyuser' + user.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">修改用户</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group">
                                        <span class="input-group-text">登录名</span>
                                        <input v-model="user.loginname" type="text" class="form-control"
                                            placeholder="请输入登录名">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">密码</span>
                                        <input v-model="user.password" type="password" class="form-control"
                                            placeholder="请输入密码">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">姓名</span>
                                        <input v-model="user.username" type="text" class="form-control"
                                            placeholder="请输入真实姓名">
                                    </div>

                                    <div class="input-group">
                                        <span class="input-group-text">性别</span>
                                        <div style="margin-top: 5px;margin-left: 10px;">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" :name="'sex' + user.id"
                                                    value=1 v-model="user.sex">
                                                <label class="form-check-label" for="inlineRadio1">男</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" :name="'sex' + user.id"
                                                    v-model="user.sex" value=0>
                                                <label class="form-check-label" for="inlineRadio2">女</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">身份证号</span>
                                        <input v-model="user.identitycode" type="text" class="form-control"
                                            placeholder="请输入身份证号">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">邮箱</span>
                                        <input v-model="user.email" type="text" class="form-control"
                                            placeholder="请输入邮箱">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">手机</span>
                                        <input v-model="user.mobile" type="text" class="form-control"
                                            placeholder="请输入手机号码">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">用户类型</span>
                                        <div style="margin-top: 5px;margin-left: 10px;">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" :name="'type' + user.id"
                                                    value=0 v-model="user.type">
                                                <label class="form-check-label" for="inlineRadio1">普通用户</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" :name="'type' + user.id"
                                                    v-model="user.type" value=1>
                                                <label class="form-check-label" for="inlineRadio2">超级管理员</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="error-message">{{ user.error_message }}</div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" @click="saveuser(user)">保存</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>



                </tr>
            </tbody>
        </table>

        <nav aria-label="Page navigation example" style="right: 30px;bottom: 10px;position: fixed;">
            <ul class="pagination" style="float: right;">
                <li class="page-item" @click="click_page(-2)">
                    <a class="page-link" href="#">前一页</a>
                </li>
                <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number"
                    @click="click_page(page.number)">
                    <a class="page-link" href="#">{{ page.number }}</a>
                </li>
                <li class="page-item" @click="click_page(-1)">
                    <a class="page-link" href="#">后一页</a>
                </li>
            </ul>
        </nav>
    </div>



    <!-- Modal 修改 -->
    <div class="modal fade" id="adduser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">添加用户</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-text">登录名</span>
                        <input v-model="useradd.loginname" type="text" class="form-control" placeholder="请输入登录名">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">密码</span>
                        <input v-model="useradd.password" type="password" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">姓名</span>
                        <input v-model="useradd.username" type="text" class="form-control" placeholder="请输入真实姓名">
                    </div>

                    <div class="input-group">
                        <span class="input-group-text">性别</span>
                        <div style="margin-top: 5px;margin-left: 10px;">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addsex" value=1
                                    v-model="useradd.sex">
                                <label class="form-check-label" for="inlineRadio1">男</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addsex" v-model="useradd.sex"
                                    value=0>
                                <label class="form-check-label" for="inlineRadio2">女</label>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">身份证号</span>
                        <input v-model="useradd.identitycode" type="text" class="form-control" placeholder="请输入身份证号">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">邮箱</span>
                        <input v-model="useradd.email" type="text" class="form-control" placeholder="请输入邮箱">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">手机</span>
                        <input v-model="useradd.mobile" type="text" class="form-control" placeholder="请输入手机号码">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">用户类型</span>
                        <div style="margin-top: 5px;margin-left: 10px;">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addtype" value=0
                                    v-model="useradd.type">
                                <label class="form-check-label" for="inlineRadio1">普通用户</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="addtype" v-model="useradd.type"
                                    value=1>
                                <label class="form-check-label" for="inlineRadio2">超级管理员</label>
                            </div>
                        </div>
                    </div>
                    <div class="error-message">{{ useradd.error_message }}</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="add_user">添加</button>
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
        let users = ref([]);
        let current_page = 1;
        let total_users = 0;
        let page_size = ref(10);
        let pages = ref([]);

        let useradd = ref({
            loginname: "",
            password: "",
            username: "",
            sex: 1,
            identitycode: "",
            email: "",
            mobile: "",
            type: 0,
            error_message: ""
        })

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            let max_pages = parseInt(Math.ceil(total_users / page_size.value));
            if (page >= 1 && page <= max_pages)
                pull_page(page);
        }

        const update_pages = () => {
            let max_pages = parseInt(Math.ceil(total_users / page_size.value));
            let new_pages = [];
            for (let i = current_page - 2; i <= current_page + 2; i++) {
                if (i >= 1 && i <= max_pages) {
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : ""
                    });
                }
            }
            pages.value = new_pages;
        }

        const pull_page = page => {
            let params = {
                page,
                pagesize: page_size.value
            }
            api.getuserlist(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
                if (resp.result === "success") {
                    users.value = resp.users;
                    total_users = resp.count;
                    update_pages();
                }
            })
            current_page = page;
        }

        const deluser = userid => {
            api.deleteuser({ userid }, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    Modal.getInstance("#deleteuser" + userid).hide()
                }
                pull_page(current_page);
            })
        }

        const saveuser = user => {
            let data = {
                userid: user.id,
                username: user.loginname,
                password: user.password,
                name: user.username,
                sex: user.sex,
                identitycode: user.identitycode,
                email: user.email,
                mobile: user.mobile,
                type: user.type
            }
            api.modifyuser(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    Modal.getInstance("#modifyuser" + user.id).hide()
                    pull_page(current_page);
                } else {
                    user.error_message = resp.message;
                }
            })
        }

        const add_user = () => {
            let data = {
                username: useradd.value.loginname,
                password: useradd.value.password,
                name: useradd.value.username,
                sex: useradd.value.sex,
                identitycode: useradd.value.identitycode,
                email: useradd.value.email,
                mobile: useradd.value.mobile,
                type: useradd.value.type
            }
            api.adduser(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    useradd.value = {
                        loginname: "",
                        password: "",
                        username: "",
                        sex: 1,
                        identitycode: "",
                        email: "",
                        mobile: "",
                        type: 0,
                        error_message: ""
                    }

                    Modal.getInstance("#adduser").hide()
                    pull_page(current_page);
                } else {
                    useradd.value.error_message = resp.message;
                }
                console.log(useradd);
            })
        }

        pull_page(current_page);

        return {
            users,
            pages,
            page_size,
            current_page,
            useradd,
            click_page,
            deluser,
            saveuser,
            pull_page,
            add_user
        }
    }
}
</script>

<style scoped>
.error-message {
    color: red;
}
</style>