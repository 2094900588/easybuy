<template>
    <h3>
        <center>新闻列表</center>
    </h3>
    <div class="accordion">
        <div class="accordion-item" v-for="item in news" :key="item.id">
            <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                    :data-bs-target="'#collapseOne' + item.id" aria-expanded="false" aria-controls="collapseOne">
                    {{ item.title }}
                </button>
            </h2>
            <div :id="'collapseOne' + item.id" class="accordion-collapse collapse" aria-labelledby="headingOne"
                data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    {{ item.content }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import api from '@/api'
import { ref } from 'vue';

export default {
    setup() {
        let news = ref([])
        api.getnews().then(res => {
            let resp = res.data;
            if (resp.result === "success") {
                news.value = resp.news
            }
        })
        return {
            news
        }
    }
}
</script>

<style scoped>

</style>