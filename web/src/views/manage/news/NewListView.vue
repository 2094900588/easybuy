<template>
    <div>
        <div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="">添加新闻</button>
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
                    <th scope="col">标题</th>
                    <th scope="col">内容</th>
                    <th scope="col">创建时间</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in news" :key="item.id">
                    <th>{{ item.id }}</th>
                    <td>{{ item.title }}</td>
                    <td style="word-break:break-all;word-wrap:break-word;">{{ item.content }}</td>
                    <td>{{ item.createtime }}</td>
                    <td>
                        <button type="button" class="btn btn-primary">修改</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </td>
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
</template>

<script>
import api from '@/api'
import { ref } from 'vue';
import { useStore } from 'vuex';
export default {
    setup() {
        let store = useStore();
        let news = ref([]);
        let current_page = 1;
        let total = 0;
        let page_size = ref(10);
        let pages = ref([]);

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
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
            api.getnewslist(params, store.state.user.token).then(res => {
                let resp = res.data;
                console.log(resp);
                if (resp.result === "success") {
                    news.value = resp.news;
                    total = resp.count;
                    update_pages();
                }
            })
            current_page = page;
        }

        pull_page(current_page);

        return {
            news,
            pages,
            page_size,
            current_page,
            click_page,
            pull_page
        }
    }
}
</script>