import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ManageParent from '@/views/manage/ManageParent'
import ManageHome from '@/views/manage/ManageHome'
import UserAddView from '@/views/manage/user/UserAddView'
import UserListView from '@/views/manage/user/UserListView'
import CategroyAddView from '@/views/manage/categroy/CategroyAddView'
import CategroyListView from '@/views/manage/categroy/CategroyListView'
import NewAddView from '@/views/manage/news/NewAddView'
import NewListView from '@/views/manage/news/NewListView'
import OrderAddView from '@/views/manage/order/OrderAddView'
import OrderListView from '@/views/manage/order/OrderListView'
import ProdAddView from '@/views/manage/prod/ProdAddView'
import ProdListView from '@/views/manage/prod/ProdListView'
import MyOrderView from '@/views/user/MyOrderView'



const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/myorder',
    name: 'myorder',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: MyOrderView//() => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/manage',
    component: ManageParent,
    children: [
      {
        path: '',
        name: 'managehome',
        component: ManageHome
      },
      {
        path: 'user/add',
        name: 'adduser',
        component: UserAddView
      },
      {
        path: 'user/list',
        name: 'listuser',
        component: UserListView
      },
      {
        path: 'categroy/add',
        name: 'addcategroy',
        component: CategroyAddView
      },
      {
        path: 'categroy/list',
        name: 'listcategroy',
        component: CategroyListView
      },
      {
        path: 'news/add',
        name: 'addnews',
        component: NewAddView
      },
      {
        path: 'news/list',
        name: 'listnews',
        component: NewListView
      },
      {
        path: 'order/add',
        name: 'addorder',
        component: OrderAddView
      },
      {
        path: 'order/list',
        name: 'listorder',
        component: OrderListView
      },
      {
        path: 'prod/add',
        name: 'addprod',
        component: ProdAddView
      },
      {
        path: 'prod/list',
        name: 'listprod',
        component: ProdListView
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
