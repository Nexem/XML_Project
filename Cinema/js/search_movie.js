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
  movie: [],
  language: [],
  languageSelect: 0,
  title: ""
}

let vm = new Vue({
  el: '#app',
  data: function () {
    return data
  },
  created : function() {
    console.log(this.languageSelect)
    axios({
      method: 'post',
      url: '/rest.first/rest/movie/getAllMovie',
      mode: 'cors',
      data: {
        id: this.languageSelect,
        title: this.title
      }
    }).then(function (e) {
      data.movie = e.data
    })
    axios({
      method: 'get',
      url: '/rest.first/rest/language/getAll',
      mode: 'cors'
    }).then(function (e) {
      data.language = e.data
    })
  },
  methods: {

    search: function() {
      axios({
        method: 'post',
        url: '/rest.first/rest/movie/getAllMovie',
        mode: 'cors',
        data: {
          id: this.languageSelect,
          title: this.title
        }
      }).then(function (e) {
        data.movie = e.data
      })
    }

  }
})
