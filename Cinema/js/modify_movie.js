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
  days: [],
  movie: []
}

let vm = new Vue({
  el: '#app',
  data: function () {
    return data
  },
  created : function() {
    axios({
      method: 'get',
      url: '/rest.first/rest/movie/getAllMovie',
      mode: 'cors'
    }).then(function (e) {
      console.log(e);
      data.movie = e.data
    })
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
    addActor: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/actor/addToMovie',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_actor: this.listSearchActor[j].id
        }
      }).then(function (e) {
        console.log(e);
        this.movie[i].actor.push(this.listSearchActor[j])
        this.searchNameActor = ''
      })

    },
    removeActor: function (i, j) {

      axios({
        method: 'post',
        url: '/rest.first/rest/actor/removeActorMovie',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_actor: this.movie[i].actor[j].id
        }
      }).then(function (e) {
        data.movie[i].actor.splice(j, 1)
      })
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
    addDirector: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/director/addToMovie',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_director: this.listSearchDirector[j].id
        }
      }).then(function (e) {

        data.movie[i].director.push(data.listSearchDirector[j])
        data.searchNameDirector = ''
      })

    },
    removeDirector: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/director/remove',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_director: this.movie[i].director[j].id
        }
      }).then(function (e) {
        data.movie[i].director.splice(j, 1)
      })
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
    addLanguage: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/language/addToMovie',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_language: this.listSearchLanguage[j].id
        }
      }).then(function (e) {

        data.movie[i].language.push(data.listSearchLanguage[j])
        data.searchNameLanguage = ''
      })

    },
    removeLanguage: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/language/remove',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_language: this.movie[i].language[j].id
        }
      }).then(function (e) {
        data.movie[i].language.splice(j, 1)
      })
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
    addCity: function (i, j) {
      axios({
        method: 'post',
        url: '/rest.first/rest/city/addToMovie',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_city: this.listSearchCity[j].id
        }
      }).then(function (e) {
        console.log(e);
        data.movie[i].city.push(data.listSearchCity[j])
        data.searchNameCity = ''
      })


    },
    removeCity: function (i, j) {

      axios({
        method: 'post',
        url: '/rest.first/rest/city/remove',
        mode: 'cors',
        data: {
          id_movie: this.movie[i].id,
          id_city: this.movie[i].city[j].id
        }
      }).then(function (e) {
        console.log(e);
        data.movie[i].city.splice(j, 1)
      })
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
    },
    changeMovie: function (label, i) {
      axios({
        method: 'post',
        url: '/rest.first/rest/movie/updateMovie',
        mode: 'cors',
        data: {
          label: label,
          value: this.movie[i][label],
          id: this.movie[i].id
        }
      })
    }
  }
})
