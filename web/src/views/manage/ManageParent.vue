<template>
    <div style="float: left;background-color:beige;height:90vh;width: 15vw;text-align: center;">
        <!-- <nav class="nav flex-column">
            <a :class="route_name == 'adduser' || route_name == 'listuser' ? 'nav-link item1 active' : 'nav-link item1'"
                aria-current="page" data-bs-toggle="collapse" href="#usermanage">用户管理</a>
            <div class="collapse" id="usermanage">
                <router-link class="nav-link item" :to="{ name: 'adduser' }">新增用户</router-link>
                <router-link class="nav-link item" :to="{ name: 'listuser' }">用户列表</router-link>
            </div>
            <a :class="route_name == 'addcategroy' || route_name == 'listcategroy' ? 'nav-link item1 active' : 'nav-link item1'"
                aria-current="page" data-bs-toggle="collapse" href="#classmanage">分类管理</a>
            <div class="collapse" id="classmanage">
                <router-link class="nav-link item" :to="{ name: 'addcategroy' }">新增分类</router-link>
                <router-link class="nav-link item" :to="{ name: 'listcategroy' }">分类列表</router-link>
            </div>
            <a :class="route_name == 'addprod' || route_name == 'listprod' ? 'nav-link item1 active' : 'nav-link item1'"
                aria-current="page" data-bs-toggle="collapse" href="#goodsmanage">商品管理</a>
            <div class="collapse" id="goodsmanage">
                <router-link class="nav-link item" :to="{ name: 'addprod' }">新增商品</router-link>
                <router-link class="nav-link item" :to="{ name: 'listprod' }">商品列表</router-link>
            </div>
            <a :class="route_name == 'addorder' || route_name == 'listorder' ? 'nav-link item1 active' : 'nav-link item1'"
                aria-current="page" data-bs-toggle="collapse" href="#ordermanage">订单管理</a>
            <div class="collapse" id="ordermanage">
                <router-link class="nav-link item" :to="{ name: 'addorder' }">新增订单</router-link>
                <router-link class="nav-link item" :to="{ name: 'listorder' }">订单列表</router-link>
            </div>
            <a :class="route_name == 'addnews' || route_name == 'listnews' ? 'nav-link item1 active' : 'nav-link item1'"
                aria-current="page" data-bs-toggle="collapse" href="#newsmanage">新闻管理</a>
            <div class="collapse" id="newsmanage">
                <router-link class="nav-link item" :to="{ name: 'addnews' }">新增新闻</router-link>
                <router-link class="nav-link item" :to="{ name: 'listnews' }">新闻列表</router-link>
            </div>
        </nav> -->
        <nav class="nav flex-column">
            <router-link :class="route_name == 'listuser' ? 'nav-link item1 active' : 'nav-link item1'"
                :to="{ name: 'listuser' }">用户管理</router-link>
            <router-link
                :class="route_name == 'addcategroy' || route_name == 'listcategroy' ? 'nav-link item1 active' : 'nav-link item1'"
                :to="{ name: 'listcategroy' }">分类管理</router-link>
            <router-link
                :class="route_name == 'addprod' || route_name == 'listprod' ? 'nav-link item1 active' : 'nav-link item1'"
                :to="{ name: 'listprod' }">商品管理</router-link>
            <router-link
                :class="route_name == 'addorder' || route_name == 'listorder' ? 'nav-link item1 active' : 'nav-link item1'"
                :to="{ name: 'listorder' }">订单管理</router-link>
            <router-link
                :class="route_name == 'addnews' || route_name == 'listnews' ? 'nav-link item1 active' : 'nav-link item1'"
                :to="{ name: 'listnews' }">新闻管理</router-link>
        </nav>
    </div>
    <div style="float: left; width:80vw;">
        <router-view />
    </div>

</template>

<script>
import { useRoute } from 'vue-router'
import { computed } from 'vue';

export default {
    setup() {
        const route = useRoute();
        let route_name = computed(() => route.name);
        return {
            route_name
        }
    }
}


</script>

<style scoped>
.item1 {
    font-size: 20px;
    padding-top: 20px;
    padding-left: 0px;
    /* padding-right: 30px; */
    color: black;
}

.item {
    font-size: 15px;
    margin-left: 0px;
}

.active {
    color: #4272d7;
}

.item1:hover {
    color: #4272d7;
}
</style>