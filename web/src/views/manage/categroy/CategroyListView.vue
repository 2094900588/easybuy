<template>
    <div>
        <div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addcategroy"
                @click="changecate(addcategroy)">添加分类</button>
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
                    <th scope="col">分类</th>
                    <th scope="col">父级编号</th>
                    <th scope="col">分类级别</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in categroy" :key="item.id">
                    <th>{{ item.id }}</th>
                    <td>{{ item.name }}</td>
                    <td>{{ id_to_name(item.parentid) }}</td>
                    <td>{{ item.type }}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            :data-bs-target="'#modifycategroy' + item.id" @click="changecate(item)">修改</button>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                            :data-bs-target="'#deletecategroy' + item.id">删除</button>
                    </td>

                    <!-- Modal 删除 -->
                    <div class="modal fade" :id="'deletecategroy' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">删除分类</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    确定删除分类 {{ item.name }}({{ item.id }})？
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger"
                                        @click="delcategroy(item.id)">删除</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal 修改 -->
                    <div class="modal fade" :id="'modifycategroy' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">修改分类</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group">
                                        <span class="input-group-text">分类</span>
                                        <input v-model="item.name" type="text" class="form-control"
                                            placeholder="请输入分类名">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">父级分类</span>
                                        <select class="form-select" v-model="item.parentid"
                                            aria-label="Default select example">
                                            <option value=0>无</option>
                                            <option v-for="option in item.cates" :key="option.id" :value="option.id">
                                                {{ option.name }}
                                            </option>
                                        </select>
                                    </div>

                                    <div class="input-group">
                                        <span class="input-group-text">分类级别</span>
                                        <select class="form-select" v-model="item.type"
                                            aria-label="Default select example" @change="changecate(item)">
                                            <option value=1>一级分类</option>
                                            <option value=2>二级分类</option>
                                            <option value=3>三级分类</option>
                                        </select>
                                    </div>


                                    <div class="error-message">{{ item.error_message }}</div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary"
                                        @click="savecategroy(item)">保存</button>
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

    <!-- Modal 添加 -->
    <div class="modal fade" id="addcategroy" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">添加分类</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-text">分类</span>
                        <input v-model="addcategroy.name" type="text" class="form-control" placeholder="请输入分类名">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">父级分类</span>
                        <select class="form-select" v-model="addcategroy.parentid" aria-label="Default select example">
                            <option value=0>无</option>
                            <option v-for="option in addcategroy.cates" :key="option.id" :value="option.id">
                                {{ option.name }}
                            </option>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text">分类级别</span>
                        <select class="form-select" v-model="addcategroy.type" aria-label="Default select example"
                            @change="changecate(addcategroy)">
                            <option value=1>一级分类</option>
                            <option value=2>二级分类</option>
                            <option value=3>三级分类</option>
                        </select>
                    </div>


                    <div class="error-message">{{ addcategroy.error_message }}</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="categroyadd(addcategroy)">添加</button>
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
        let categroy = ref([]);
        let current_page = 1;
        let total = 0;
        let page_size = ref(10);
        let pages = ref([]);
        let addcategroy = ref([]);
        let cates = [];

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            else if (page === 0) page = current_page
            let max_pages = parseInt(Math.ceil(total / page_size.value));
            if (page >= 1 && page <= max_pages)
                pull_page(page);
        }

        const update_pages = () => {
            let max_pages = parseInt(Math.ceil(total / page_size.value));
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
            api.getcategroyalllist().then(res => {
                let resp = res.data;
                cates = resp.categroy;
            })
            api.getcategroylist(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
                if (resp.result === "success") {
                    categroy.value = resp.categroy;
                    total = resp.count;
                    update_pages();
                }
            })
            current_page = page;
        }

        const id_to_name = id => {
            for (let i of cates)
                if (i.id === id) return i.name
            return ""
        }

        const changecate = item => {
            item.cates = []
            for (let i of cates) {
                if (item.type == i.type + 1) {
                    item.cates.push(i)
                }
            }
        }

        const delcategroy = categroyid => {
            api.deletecategroy({ id: categroyid }, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#deletecategroy" + categroyid).hide()
                }
            })
        }

        const savecategroy = item => {
            let data = {
                id: item.id,
                name: item.name,
                parentId: item.parentid,
                type: item.type
            }
            api.modifycategroy(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#modifycategroy" + item.id).hide()
                }
            })
        }

        const categroyadd = item => {
            let data = {
                name: item.name,
                parentId: item.parentid,
                type: item.type
            }
            api.addcategroy(data, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#addcategroy").hide()
                    addcategroy.value = {}
                }
            })
        }

        pull_page(current_page);

        return {
            categroy,
            pages,
            page_size,
            current_page,
            addcategroy,
            click_page,
            id_to_name,
            pull_page,
            cates,
            changecate,
            delcategroy,
            savecategroy,
            categroyadd
        }
    }
}
</script>