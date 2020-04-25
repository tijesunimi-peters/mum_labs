<template>
  <input class="u-full-width" v-bind:name="name" type="password" v-on:change="onChange" v-bind:value="value" />
</template>

<script>
export default {
  name: "PasswordInput",
  props: ["onChange", "name"],
  data: function() {
    return { value: '' }
  },
  methods: {
  }
}
</script>

<style>

</style>