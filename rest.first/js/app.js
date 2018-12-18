let searchname = {
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
}


let data = {
  date : '',
  f: {
    id: 4,
    nom: 't'
  }
}

let vm = new Vue({
  el: '#app',
  components: { searchname },
  data: function () {
    return data
  },
  created : function() {
    moment.locale()
      this.date = moment().startOf('isoWeek').utcOffset(8).add(0, 'm').format('LLL')
      axios({
        method: 'post',
        url: 'http://localhost:8080/rest.first/rest/hello',
        mode: 'cors',
        data: {
          firstName: 'Fred',
          lastName: 'Flintstone'
        }
      }).then(function (e) {
        console.log(e);
      })

  },
  methods: {
  }
})
