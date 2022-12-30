import { createStore } from 'vuex'
import ModuleUser from './user'

export default createStore({
  state: {
    cartnum: 0
  },
  getters: {
  },
  mutations: {
    updateCartNum(state, cartnum) {
      state.cartnum = cartnum;
    },
  },
  actions: {
  },
  modules: {
    user: ModuleUser
  }
})
