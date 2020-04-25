import Vue from 'vue'
import Vuex from 'vuex'
import { LOGIN_URL } from './constants';


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    auth: {
      loggedIn: false,
      token: null,
      user: null
    }
  },
  mutations: {
    reLogin(state, userDetails) {
      state.auth.token = userDetails.token;
      state.auth.loggedIn = !!userDetails.token;
      state.auth.user = JSON.parse(userDetails.user);

      console.log(state);
    },
    login(state, loginDetails) {
      console.log(loginDetails, " FROM login mutation");
      localStorage.setItem("token", loginDetails.token);
      localStorage.setItem("user", JSON.stringify(loginDetails.user));
      state.token = loginDetails.token;
      state.user = loginDetails.user;
    },
    logout: function (state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.auth = {
        loggedIn: false,
        token: null,
        user: null
      }
    }
  },
  getters: {
    auth: function (state) {
      return state.auth.loggedIn;
    }
  },
  actions: {
    checkLogin({ commit }) {
      console.log('Checking if user is logged in....');
      let token = window.localStorage.getItem('token');
      let user = window.localStorage.getItem('user');
      
      commit('reLogin', { token, user });
    },

    async login({ commit }, credentials) {
      console.log(credentials);
      await fetch(LOGIN_URL, {
        method: "POST",
        body: JSON.stringify(credentials),
        mode: "cors",
        headers: {
          "Content-Type": "application/json"
        }
      })
        .then(res => res.json())
        .then(res => {
          
          commit("login", res);
          // console.log(arguments);
          // context.$router.push("/dashboard");
        })
        .catch(() => {
          // console.log();
          // context.error = res.subErrors;
        });
    },

    logout({commit}) {
      commit('logout');
    }
  }
})
