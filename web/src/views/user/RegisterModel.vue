<template>
    <!-- Modal -->
    <div class="modal fade" id="register" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">注册</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form @keydown="key">
                        <div class="input-group">
                            <span class="input-group-text">用户名</span>
                            <input v-model="username" type="text" class="form-control" id="username"
                                placeholder="请输入用户名">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">密码</span>
                            <input v-model="password" type="password" class="form-control" id="password"
                                placeholder="请输入密码">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">确认密码</span>
                            <input v-model="confirmedpassword" type="password" class="form-control"
                                id="confirmedpassword" placeholder="请输再次入密码">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">姓名</span>
                            <input v-model="name" type="text" class="form-control" id="name" placeholder="请输入真实姓名">
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">性别</span>
                            <div style="margin-top: 5px;margin-left: 10px;">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="man" value="1"
                                        v-model="sex" checked>
                                    <label class="form-check-label" for="inlineRadio1">男</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="woman" v-model="sex"
                                        value="0">
                                    <label class="form-check-label" for="inlineRadio2">女</label>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">身份证号</span>
                            <input v-model="idcard" type="text" class="form-control" id="idcard" placeholder="请输入身份证号">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">邮箱</span>
                            <input v-model="email" type="text" class="form-control" id="email" placeholder="请输入邮箱">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">手机</span>
                            <input v-model="phone" type="text" class="form-control" id="phone" placeholder="请输入手机号码">
                        </div>


                        <div class="error-message">{{ error_message }}</div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" @click="register">注册</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import api from '@/api'

export default {
    setup() {
        let username = ref("");
        let password = ref("");
        let confirmedpassword = ref("");
        let name = ref("")
        let sex = ref("1")
        let idcard = ref("")
        let email = ref("")
        let phone = ref("")

        let error_message = ref("")

        const check_info = () => {
            if (username.value.length > 15)
                return "用户名过长（不能超过15个字符）"
            if (username.value.length < 5)
                return "用户名过短（不能少于5个字符）"
            if (password.value.length > 16)
                return "密码过长（不能超过16个字符）"
            if (password.value.length < 6)
                return "密码过短（不能少于6个字符）"
            if (password.value != confirmedpassword.value)
                return "两次密码不一致"
        }

        const key = e => {
            if (e.keyCode == 13) register()
        }

        const register = () => {

            error_message.value = check_info();
            if (error_message.value) return;
            let data = {
                username: username.value,
                password: password.value,
                name: name.value,
                sex: sex.value,
                identitycode: idcard.value,
                email: email.value,
                mobile: phone.value
            };
            // console.log(data);
            api.register(data, "").then(res => {
                let resp = res.data;
                if (resp.result === "success") {
                    username.value = ""
                    password.value = ""
                    confirmedpassword.value = ""
                    name.value = ""
                    sex.value = "1"
                    idcard.value = ""
                    email.value = ""
                    phone.value = ""
                    Modal.getInstance("#register").hide()
                } else {
                    error_message.value = resp.result;
                }
            })
        }


        return {
            username,
            password,
            confirmedpassword,
            name,
            sex,
            idcard,
            email,
            phone,
            error_message,
            register,
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