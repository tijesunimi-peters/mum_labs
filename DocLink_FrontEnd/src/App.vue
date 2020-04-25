<template>
  <div id="app">
    <Header v-bind:appName="appName" />
    <router-view />
    <Footer v-bind:appName="appName" />
  </div>
</template>

<script>
import Header from "./components/Header";
import Footer from "./components/Footer";


export default {
  name: "App",
  data: function() {
    return {
      appName: "DocLink"
    };
  },
  components: {
    Header,
    Footer
  },
  beforeCreate: function() {
    this.$store.dispatch('checkLogin');
  }
};
</script>

<style scoped>
.app {
  background-color: whitesmoke;
}
</style>


