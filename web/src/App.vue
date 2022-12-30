<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav> -->
  <NavBar></NavBar>
  <router-view />
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import NavBar from '@/components/NavBar.vue'
import { useStore } from 'vuex'

export default {
  components: {
    NavBar
  },
  setup() {
    let store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (!store.state.user.token && jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("getinfo");
    }
  }
}
</script>

<style>

</style>
