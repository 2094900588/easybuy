<template>
    <div>
        <div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addprod">添加商品</button>
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
                    <th scope="col">商品名称</th>
                    <th scope="col">单价</th>
                    <th scope="col">库存</th>
                    <th scope="col">一级分类</th>
                    <th scope="col">二级分类</th>
                    <th scope="col">三级分类</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in prod" :key="item.id">
                    <th>{{ item.id }}</th>
                    <td>{{ item.name }}</td>
                    <td>{{ item.price }}</td>
                    <td>{{ item.stock }}</td>
                    <td>{{ id_to_name(item.categorylevel1id) }}</td>
                    <td>{{ id_to_name(item.categorylevel2id) }}</td>
                    <td>{{ id_to_name(item.categorylevel3id) }}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            :data-bs-target="'#modifyprod' + item.id">修改</button>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                            :data-bs-target="'#deleteprod' + item.id">删除</button>
                    </td>






                    <!-- Modal 删除 -->
                    <div class="modal fade" :id="'deleteprod' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">删除商品</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    确定删除商品 {{ item.name }}({{ item.id }})？
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" @click="delprod(item.id)">删除</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal 修改 -->
                    <div class="modal fade" :id="'modifyprod' + item.id" tabindex="-1"
                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">修改商品</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group">
                                        <span class="input-group-text">商品名称</span>
                                        <input v-model="item.name" type="text" class="form-control"
                                            placeholder="请输入商品名称">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">单价</span>
                                        <input v-model="item.price" type="text" class="form-control"
                                            placeholder="请输入单价">
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-text">库存</span>
                                        <input v-model="item.stock" type="text" class="form-control"
                                            placeholder="请输入库存">
                                    </div>

                                    <div class="input-group">
                                        <span class="input-group-text">一级分类</span>
                                        <select class="form-select" v-model="item.categorylevel1id"
                                            aria-label="Default select example">
                                            <option value=0>无</option>
                                            <option v-for="option in Level1" :key="option.id" :value="option.id">
                                                {{ option.name }}
                                            </option>
                                        </select>
                                    </div>

                                    <div class="input-group">
                                        <span class="input-group-text">二级分类</span>
                                        <select class="form-select" v-model="item.categorylevel2id"
                                            aria-label="Default select example">
                                            <option value=0>无</option>
                                            <option v-for="option in Level2(item.categorylevel1id)" :key="option.id"
                                                :value="option.id">
                                                {{ option.name }}
                                            </option>
                                        </select>
                                    </div>

                                    <div class="input-group">
                                        <span class="input-group-text">三级分类</span>
                                        <select class="form-select" v-model="item.categorylevel3id"
                                            aria-label="Default select example">
                                            <option value=0>无</option>
                                            <option v-for="option in Level3(item.categorylevel2id)" :key="option.id"
                                                :value="option.id">
                                                {{ option.name }}
                                            </option>
                                        </select>
                                    </div>

                                    <div class="error-message">{{ item.error_message }}</div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" @click="saveprod(item)">保存</button>
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
    <div class="modal fade" id="addprod" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">添加商品</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-text">商品名称</span>
                        <input v-model="addprod.name" type="text" class="form-control" placeholder="请输入商品名称">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">单价</span>
                        <input v-model="addprod.price" type="text" class="form-control" placeholder="请输入单价">
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">库存</span>
                        <input v-model="addprod.stock" type="text" class="form-control" placeholder="请输入库存">
                    </div>

                    <div class="input-group">
                        <span class="input-group-text">一级分类</span>
                        <select class="form-select" v-model="addprod.categorylevel1id"
                            aria-label="Default select example">
                            <option value=0>无</option>
                            <option v-for="option in Level1" :key="option.id" :value="option.id">
                                {{ option.name }}
                            </option>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text">二级分类</span>
                        <select class="form-select" v-model="addprod.categorylevel2id"
                            aria-label="Default select example">
                            <option value=0>无</option>
                            <option v-for="option in Level2(addprod.categorylevel1id)" :key="option.id"
                                :value="option.id">
                                {{ option.name }}
                            </option>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text">三级分类</span>
                        <select class="form-select" v-model="addprod.categorylevel3id"
                            aria-label="Default select example">
                            <option value=0>无</option>
                            <option v-for="option in Level3(addprod.categorylevel2id)" :key="option.id"
                                :value="option.id">
                                {{ option.name }}
                            </option>
                        </select>
                    </div>

                    <div class="error-message">{{ addprod.error_message }}</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="prodadd(addprod)">添加</button>
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
import { computed } from 'vue';
import { Modal } from 'bootstrap/dist/js/bootstrap'

export default {
    setup() {
        let store = useStore();
        let prod = ref([]);
        let current_page = 1;
        let total = 0;
        let page_size = ref(10);
        let pages = ref([]);
        let cates = ref([]);
        let addprod = ref({});
        const Level1 = computed(() => {
            let cate = []
            for (let i of cates.value) {
                if (i.type === 1) cate.push(i)
            }
            return cate
        });
        const Level2 = computed(() => (level1) => {
            let cate = []
            if (level1 == 0) return []
            for (let i of cates.value) {
                if (i.parentid == level1) cate.push(i)
            }
            return cate
        });
        const Level3 = computed(() => (level2) => {
            let cate = []
            if (level2 == 0) return []
            for (let i of cates.value) {
                if (i.parentid == level2) cate.push(i)
            }
            return cate
        });
        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            else if (page === 0) page = current_page;
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
                cates.value = resp.categroy;
            })
            api.getprodlist(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
                if (resp.result === "success") {
                    prod.value = resp.prod;
                    total = resp.count;
                    update_pages();
                }
            })
            current_page = page;
        }

        const id_to_name = id => {
            for (let i of cates.value)
                if (i.id === id) return i.name
            return ""
        }

        const saveprod = item => {
            let data = {
                id: item.id,
                name: item.name,
                description: item.description,
                price: item.price,
                stock: item.stock,
                categoryLevel1Id: item.categorylevel1id,
                categoryLevel2Id: item.categorylevel2id,
                categoryLevel3Id: item.categorylevel3id,
                fileName: item.filename,
                isDelete: item.isdelete
            }
            api.modifyprod(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#modifyprod" + item.id).hide()
                }
            })

        }

        const delprod = id => {
            api.deleteprod({ id }, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#deleteprod" + id).hide()
                }
            })
        }

        const prodadd = item => {
            let data = {
                name: item.name,
                description: item.description,
                price: item.price,
                stock: item.stock,
                categoryLevel1Id: item.categorylevel1id,
                categoryLevel2Id: item.categorylevel2id,
                categoryLevel3Id: item.categorylevel3id,
                fileName: item.filename,
                isDelete: item.isdelete
            }
            api.addprod(data, store.state.user.token).then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    click_page(0);
                    Modal.getInstance("#addprod").hide()
                    addprod.value = {}
                }
            })
        }

        pull_page(current_page);

        return {
            prod,
            pages,
            page_size,
            current_page,
            click_page,
            pull_page,
            id_to_name,
            Level1,
            Level2,
            Level3,
            saveprod,
            delprod,
            addprod,
            prodadd
        }
    }
}
</script>