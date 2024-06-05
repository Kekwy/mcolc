<template>
    <img ref="img" />
  </template>
  <script>
  export default {
    name: 'authImg',
    props: {
      authSrc: {
        type: String,
        required: false,
        default: ''
      }
    },
    mounted() {
      this.getImg()
    },
    // 方法
    methods: {
      getImg() {
        // 获取需携带的token，看自己系统怎么获取的
        let token = window.localStorage.getItem('access_token')
        Object.defineProperty(Image.prototype, 'authsrc', {
          writable: true,
          enumerable: true,
          configurable: true
        })
        let img = this.$refs.img
        let request = new XMLHttpRequest()
        request.responseType = 'blob'
        request.open('get', this.authSrc, true)
        // 携带请求头
        request.setRequestHeader('Authorization', token)
        request.onreadystatechange = e => {
          if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
            img.src = URL.createObjectURL(request.response)
            img.onload = () => {
              URL.revokeObjectURL(img.src)
            }
          }
        }
        request.send(null)
      }
    },
    // 监听
    watch: {
      // vue2这里表示父组件是对象属性，子组件不会主动调用，需要深度监听一下调用
      authSrc: {
        deep: true,
        handler(n, o) {
          if (n) {
            this.getImg()
          }
        }
      }
    }
  }
  </script>