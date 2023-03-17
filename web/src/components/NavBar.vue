<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">易购商城</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link class="nav-link active" aria-current="page"
                            :to="{ name: 'home' }">Home</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'news' }">新闻</router-link>
                    </li>
                    <!-- <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li> -->
                    <!-- <li class="nav-item">
                        <a class="nav-link disabled">Disabled</a>
                    </li> -->

                    <input class="form-control me-2" type="search" placeholder="请输入查找的内容" aria-label="Search"
                        style="width: 180px;" v-model="keyword" @keydown="enter">
                    <button class="btn btn-outline-success" type="button" @click="getprod(keyword)">搜索</button>

                </ul>
                <div>
                    <div v-if="$store.state.user.is_login">
                        <div style="float: left;margin-top: 10px;margin-right: 30px;">
                            <button type="button" class="btn btn-light position-relative"
                                style="width: 40px;padding-left: 10px;height: 40px;"
                                @click='router.push({ name: "mycart" })'>
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                                    class="bi bi-cart3" viewBox="0 0 30 30">
                                    <path
                                        d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                                </svg>
                                <span v-if="$store.state.cartnum > 0"
                                    class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                    {{ $store.state.cartnum }}
                                    <!-- <span class="visually-hidden">unread messages</span> -->
                                </span>

                            </button>

                        </div>

                        <ul class="navbar-nav" style="float: left;">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    {{ $store.state.user.username }}
                                </a>

                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li>
                                        <router-link class="dropdown-item" :to="{ name: 'myorder' }">我的订单</router-link>
                                    </li>
                                    <li>
                                        <router-link class="dropdown-item"
                                            :to="{ name: 'myaddress' }">我的地址</router-link>
                                    </li>
                                    <li v-if="$store.state.user.usertype === 1">
                                        <router-link class="dropdown-item"
                                            :to="{ name: 'managehome' }">后台管理</router-link>
                                    </li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <ul class="navbar-nav" v-else>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#login">登录</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#register">注册</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <login-modal />
    <register-model />
</template>


<script>
// import { useRoute } from 'vue-router'
// import { computed } from 'vue';
import router from '@/router/index'
import api from '@/api';
import { useStore } from 'vuex';
import LoginModal from '../views/user/LoginModal.vue';
import RegisterModel from '../views/user/RegisterModel.vue';
import { ref } from 'vue';

export default {
    components: { LoginModal, RegisterModel },
    setup() {
        const store = useStore();
        let keyword = ref("");
        // const route = useRoute();
        // let route_name = computed(() => route.name);
        const logout = () => {
            store.dispatch("logout");
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


        const getprod = (keyword, level1, level2, level3) => {
            let params = {
                keyword,
                level1,
                level2,
                level3
            }
            api.getprodlists(params).then(res => {
                let resp = res.data;
                store.commit("updateProds", resp.prod);
                // router.push({ name: "home" })
            })
        }

        const enter = e => {
            if (e.key == "Enter")
                getprod(keyword.value);
        }

        getprod();
        setTimeout(getcart, 200);
        return {
            // route_name,
            logout,
            router,
            getprod,
            keyword,
            enter
        };
    }
}

</script>

<style scoped>

</style>