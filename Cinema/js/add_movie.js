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
  title: '',
  durationH: 0,
  durationM: 0,
  minimumAge: 0,
  searchNameActor: '',
  listSearchActor: [],
  listActor: [],
  searchNameDirector: '',
  listSearchDirector: [],
  listDirector: [] ,
  searchNameLanguage: '',
  listSearchLanguage: [],
  listLanguage: [],
  searchNameCity: '',
  listSearchCity: [],
  listCity: [],
  days: []
}

let vm = new Vue({
  el: '#app',
  data: function () {
    return data
  },
  created : function() {

  },
  methods: {
    searchActor: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/actor/getByName',
        mode: 'cors',
        data: {
          name: this.searchNameActor
        }
      }).then(function (e) {
        console.log(e);
        data.listSearchActor = e.data
      })
    },
    createActor: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/actor/add',
        mode: 'cors',
        data: {
          name: this.searchNameActor
        }
      })
    },
    addActor: function (i) {
      this.listActor.push(this.listSearchActor[i])
      this.searchNameActor = ''
      search()
    },
    removeActor: function (i) {
      this.listActor.splice(i, 1)
    },


    searchDirector: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/director/getByName',
        mode: 'cors',
        data: {
          name: this.searchNameDirector
        }
      }).then(function (e) {
        console.log(e);
        data.listSearchDirector = e.data
      })
    },
    createDirector: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/director/add',
        mode: 'cors',
        data: {
          name: this.searchNameDirector
        }
      })
    },
    addDirector: function (i) {
      this.listDirector.push(this.listSearchDirector[i])
      this.searchNameDirector = ''
      search()
    },
    removeDirector: function (i) {
      this.listDirector.splice(i, 1)
    },

    searchLanguage: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/language/getByName',
        mode: 'cors',
        data: {
          name: this.searchNameLanguage
        }
      }).then(function (e) {
        console.log(e);
        data.listSearchLanguage = e.data
      })
    },
    createLanguage: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/language/add',
        mode: 'cors',
        data: {
          name: this.searchNameLanguage
        }
      })
    },
    addLanguage: function (i) {
      this.listLanguage.push(this.listSearchLanguage[i])
      this.searchNameLanguage = ''
      search()
    },
    removeLanguage: function (i) {
      this.listLanguage.splice(i, 1)
    },

    searchCity: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/city/getByName',
        mode: 'cors',
        data: {
          name: this.searchNameCity
        }
      }).then(function (e) {
        console.log(e);
        data.listSearchCity = e.data
      })
    },
    createCity: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/city/add',
        mode: 'cors',
        data: {
          name: this.searchNameCity
        }
      })
    },
    addCity: function (i) {
      this.listCity.push(this.listSearchCity[i])
      this.searchNameCity = ''

      search()
    },
    removeCity: function (i) {
      this.listCity.splice(i, 1)
    },

    addMovie: function () {
      axios({
        method: 'post',
        url: '/rest.first/rest/movie/add',
        mode: 'cors',
        data: {
          title: this.title,
          duration: this.durationH + 'h' + this.durationM,
          minimumAge: this.minimumAge,
          listActor: this.listActor,
          listDirector: this.listDirector,
          listLanguage: this.listLanguage,
          listCity: this.listCity,
          days: this.days,
        }
      })
    }
  }
})
