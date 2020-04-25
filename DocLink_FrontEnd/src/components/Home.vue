<template>
  <div class="homepage">
    <Banner />
  </div>
</template>

<script>
import Banner from "./Banner"

export default {
  name: 'Doclink',
  props: {
    msg: String
  },
  components: {
    Banner
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
