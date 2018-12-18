/*let searchname = {
props: {
  type: {type: String, default:'actor'},
  name: String,
  f: Object
},
  template: `
  <div class="searchName">
    <p> bonjour </p> <input type="number" v-model="f.id" />
  </div>
  `
}*/


let data = {
  pseudo: '',
  password: '',
  password2: ''
}

let vm = new Vue({
  el: '#app',
  data: function () {
    return data
  },
  created : function() {

  },
  methods: {
    login: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/account/login',
        data: {
          pseudo: this.pseudo,
          password: this.password
        },
        v1: 'var'
      }).then(function (e) {
        if(e.data.success === "true"){
          console.log("succes");
          window.location.replace('http://localhost:8080/rest.first/rest/')

           //window.location.reload()
         }
      })
    },
    register: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/account/register',
        mode: 'cors',
        data: {
          pseudo: this.pseudo,
          password: this.password
        },
        v1: 'var'
      }).then(function (e) {

        if(e.data.success === "true") {
          window.location.replace('http://localhost:8080/rest.first/rest/')
          //window.location.reload()
        }
      })
    }
  }
})
