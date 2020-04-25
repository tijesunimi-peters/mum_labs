<template>
  <input class="u-full-width" v-bind:name="name" type="text" v-on:change="onChange" v-bind:value="value" />
</template>

<script>
export default {
  name: "TextInput",
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