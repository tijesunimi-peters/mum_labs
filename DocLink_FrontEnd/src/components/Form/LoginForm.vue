<template>
  <form v-on:submit.prevent="submit">
    <h1 class="login-title">Login</h1>
    <div class="row">
      <div class="six columns">
        <label for="email">Email</label>
        <TextInput required name="email" v-bind:email="email" v-bind:onChange="onChange" />
      </div>
    </div>
    <div class="row">
      <div class="six columns">
        <label for="password">Password</label>
        <PasswordInput name="password" v-bind:password="password" v-bind:onChange="onChange" />
      </div>
    </div>
    <button class="button button-primary" type="submit">Login</button>
  </form>
</template>

<script>
import TextInput from "./TextInput";
import PasswordInput from "./PasswordInput";

export default {
  name: "LoginForm",
  props: ["onSubmit"],
  components: { TextInput, PasswordInput },
  data: function() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    loggedIn: function() {
      console.log(this.$store.state.auth);
      return this.$store.state.auth.loggedIn;
    },
    error: function() {
      if (!this.error) return this.error;
      if (Array.isArray(this.error)) {
        const errorText = this.error.reduce((acc, item) => {
          acc += item;
          return acc;
        });

        return errorText;
      }
    },
    submit: function() {
      this.$store.dispatch("login", {
        email: this.email,
        password: this.password
      });
    },
    onChange: function(event) {
      this[event.target.name] = event.target.value;
    }
  },
  watch: {
    loggedIn: function(newVal, oldVal) {
      console.log(newVal, oldVal);
    }
  }
};
</script>

<style lang="scss" scoped>
.login-title {
  color: white;
}
</style>