import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import store from './store'

import './assets/css/normalize.css'
import './assets/css/skeleton.css'

import './assets/scss/app.scss';

import * as VueGoogleMaps from 'vue2-google-maps'

Vue.use(VueGoogleMaps, {
  load: {
    libraries: 'places'
  }
})

Vue.config.productionTip = false


new Vue({
   router,
   store,
  render: h => h(App),
}).$mount('#app')
