<template>
  <header class="header">
    <Container>
      <div class="row">
        <div class="columns two">
          <router-link to="/" class="nav-link button logo">{{appName}}</router-link>
        </div>
        <div class="columns ten">
          <div class="nav-links dock-right">
            <router-link v-show="!loggedIn" to="/login" class="nav-link button">Login</router-link>
            <router-link v-show="!loggedIn" to="/register" class="nav-link button">Register</router-link>
            <router-link v-show="loggedIn" to="/dashboard" class="nav-link button">Dashboard</router-link>
            <Logout v-show="loggedIn" />
          </div>
        </div>
      </div>
    </Container>
  </header>
</template>

<script>
import Container from "./Container";
import Logout from "./Logout";

export default {
  name: "Header",
  props: ["appName"],
  data: function() {
    return {
      // loggedIn: false
    }
  },

  methods: {
    loggedIn: function() {
      console.log("Header......");
      return this.$store.getters.auth.loggedIn;
    }
  },

  watch: {
    loggedIn(newVal) {
      console.log("State changed");
      return newVal;
    }
  },

  beforeMount: function() {
    this.loggedIn = this.$store.state.auth.loggedIn;
  },


  components: { Container, Logout }
};
</script>

<style scoped>
.header {
  /* background-color: #e1e9eb; */
  background-color: white;
  height: 90px;
  display: flex;
  align-items: center;
}
</style>