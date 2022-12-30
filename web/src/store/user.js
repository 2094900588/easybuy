import api from "@/api";
export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        usertype: 0,
        is_login: false,
        modalStatus: {
            mode: 'login',
            visible: false
        }
    },
    getters: {
    },
    mutations: {
        updateUser(state, user) {
            state.usertype = user.usertype;
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        },
        updatePullingInfo(state, pulling_info) {
            state.pulling_info = pulling_info;
        }
    },
    actions: {
        // login(context,data) {
        // },
        getinfo(context) {
            api.getuserinfo({}, context.state.token).then(res => {
                let resp = res.data;
                console.log(resp);
                context.commit("updateUser", {
                    ...resp,
                    photo: "",
                    is_login: true
                })
            })
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    modules: {
    }
}