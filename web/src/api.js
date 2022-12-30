// import Vue from 'vue'
// import router from './router'
import axios from 'axios'


// Vue.prototype.$http = axios
// axios.defaults.baseURL = process.env.NODE_ENV == 'development' ? 'http://127.0.0.1:3000/api' : 'https://app2948.acapp.acwing.com.cn/api'
axios.defaults.baseURL = "http://" + location.hostname + ":8002/api"
// axios.defaults.xsrfHeaderName = 'X-CSRFToken'
// axios.defaults.xsrfCookieName = 'csrftoken'


export default {
  register(data, token) { //前台相关api
    return ajax('user/register', 'post', {
      data
    }, token)
  },
  login(data, token) {
    return ajax('user/login', 'post', {
      data
    }, token)
  },
  getuserinfo(params, token) {
    return ajax('user/getinfo', 'get', {
      params
    }, token)
  },
  getprodlists(params, token) {
    return ajax('prod/list', 'get', {
      params
    }, token)
  },
  getuserorder(params, token) {
    return ajax('user/getorder', 'get', {
      params
    }, token)
  },
  getcategroyalllist(params, token) {
    return ajax('categroy/getalllist', 'get', {
      params
    }, token)
  },
  getorderdetail(params, token) {
    return ajax('user/getorderdetail', 'get', {
      params
    }, token)
  },
  getcart(params, token) {
    return ajax('user/getcart', 'get', {
      params
    }, token)
  },
  addcart(params, token) {
    return ajax('user/addcart', 'get', {
      params
    }, token)
  },
  getaddress(params, token) {
    return ajax('user/getaddress', 'get', {
      params
    }, token)
  },
  deladdress(params, token) {
    return ajax('user/deladdress', 'get', {
      params
    }, token)
  },
  addaddress(data, token) {
    return ajax('user/addaddress', 'post', {
      data
    }, token)
  },
  modifyaddress(data, token) {
    return ajax('user/modifyaddress', 'post', {
      data
    }, token)
  },
  getnews(params, token) {
    return ajax('news/getlist', 'get', {
      params
    }, token)
  },



  getuserlist(params, token) { //后台
    return ajax('manage/user/getuserlist', 'get', {
      params
    }, token)
  },
  getcategroylist(params, token) {
    return ajax('manage/categroy/getlist', 'get', {
      params
    }, token)
  },
  getnewslist(params, token) {
    return ajax('manage/news/getlist', 'get', {
      params
    }, token)
  },
  getprodlist(params, token) {
    return ajax('manage/prod/getlist', 'get', {
      params
    }, token)
  },
  getorderlist(params, token) {
    return ajax('manage/order/getlist', 'get', {
      params
    }, token)
  },
  deleteuser(params, token) {
    return ajax('manage/user/delete', 'get', {
      params
    }, token)
  },
  modifyuser(data, token) {
    return ajax('manage/user/modify', 'post', {
      data
    }, token)
  },
  adduser(data, token) {
    return ajax('manage/user/add', 'post', {
      data
    }, token)
  },


}

/**
 * @param url
 * @param method get|post|put|delete...
 * @param params like queryString. if a url is index?a=1&b=2, params = {a: '1', b: '2'}
 * @param data post data, use for method put|post
 * @returns {Promise}
 */
function ajax(url, method, options, token, headers) {
  if (options !== undefined) {
    var { params = {}, data = {} } = options
  } else {
    params = data = {}
  }
  headers = headers || {}
  if (token) {
    headers.Authorization = "Bearer " + token
  }
  return new Promise((resolve, reject) => {
    axios({
      url,
      method,
      params,
      data,
      headers
    }).then(res => {
      // API正常返回(status=20x), 是否错误通过有无error判断
      resolve(res)
    }, res => {
      // API请求异常，一般为Server error 或 network error
      reject(res)
    })
  })
}
