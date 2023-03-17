import { createStore } from 'vuex'
import ModuleUser from './user'

export default createStore({
  state: {
    cartnum: 0,
    prods: []
  },
  getters: {
  },
  mutations: {
    updateCartNum(state, cartnum) {
      state.cartnum = cartnum;
    },
    updateProds(state, prods) {
      state.prods = prods
    }
  },
  actions: {
  },
  modules: {
    user: ModuleUser
  }
})
