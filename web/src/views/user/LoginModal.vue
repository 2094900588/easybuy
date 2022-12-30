<template>
    <!-- Modal -->
    <div class="modal fade" id="login" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">登录</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form @keydown="key">
                        <div class="mb-3">
                            <label for="username" class="form-label">用户名</label>
                            <input v-model="username" type="text" class="form-control" id="loginusername"
                                placeholder="请输入用户名">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">密码</label>
                            <input v-model="password" type="password" class="form-control" id="loginpassword"
                                placeholder="请输入密码">
                        </div>
                        <div class="error-message">{{ error_message }}</div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" @click="login">登录</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { useStore } from 'vuex'
import api from '@/api'

export default {
    setup() {
        let username = ref("");
        let password = ref("");
        let error_message = ref("")
        let store = useStore();

        const check_info = () => {
            if (username.value.length > 15)
                return "用户名过长（不能超过15个字符）"
            if (username.value.length < 5)
                return "用户名过短（不能少于5个字符）"
            if (password.value.length > 16)
                return "密码过长（不能超过16个字符）"
            if (password.value.length < 5)
                return "密码过短（不能少于5个字符）"
        }

        const key = e => {
            if (e.keyCode == 13) login()
        }

        // const getinfo = () => {
        //     api.getuserinfo({},store.state.user.token).then(res => {
        //         let resp = res.data;
        //         console.log(resp);
        //     })
        // }

        const login = () => {
            error_message.value = check_info();
            if (error_message.value) return;
            let data = {
                username: username.value,
                password: password.value,
            };
            api.login(data, "").then(res => {
                let resp = res.data;
                if (resp.result === "success" && resp.token) {
                    localStorage.setItem("jwt_token", resp.token);
                    store.commit("updateToken", resp.token);
                    // getinfo();
                    store.dispatch("getinfo");
                    Modal.getInstance("#login").hide()
                } else {
                    error_message.value = "未知错误";
                }
            }, () => {
                error_message.value = "用户名或密码错误";

            })
        }


        return {
            username,
            password,
            error_message,
            login,
            key

        }
    }
}
</script>

<style scoped>
.error-message {
    color: red;
}
</style>