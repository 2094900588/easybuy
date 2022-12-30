<template>
  <div>
    <div class="card item" style="width: 16rem;" v-for="item in prods" :key="item.id">
      <img src="" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">{{ item.name }}</h5>
        <p class="card-text">{{ item.price }}元</p>
        <button type="button" class="btn btn-primary">加入购物车</button>

        <button type="button" class="btn btn-info" style="margin-left: 12px;color:white;">查看详情</button>
      </div>
    </div>
  </div>


</template>

<script>
// @ is an alias to /src
import api from '@/api'
import { ref } from 'vue';

export default {
  name: 'HomeView',
  components: {
  },
  setup() {
    let prods = ref([]);
    let current_page = 1;
    let page_size = 20;
    let params = {
      page: current_page,
      pagesize: page_size
    }
    api.getprodlists(params).then(res => {
      let resp = res.data;
      prods.value = resp.prod;
    })

    return {
      prods
    }
  }
}
</script>

<style scoped>
.item {
  float: left;
  margin-left: 25px;
  margin-top: 30px;
}
</style>