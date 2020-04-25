<template>
  <footer class="footer">
    <Container>
      <div class="row">
        <div class="columns five">
          <span class="copyright">&copy; {{year}} {{appName}}</span>
        </div>
      </div>
    </Container>
  </footer>
</template>

<script>
import Container from './Container'
export default {
  name: "Footer",
  props: ['appName'],
  data: function() {
    return {
      year: (new Date()).getFullYear(),
    }
  },
  components: { Container }
};
</script>

<style scoped>
.footer {
  height: 50px;
  background-color: #efe6d6;
  display: flex;
  align-items: center;
}
.copyright {
  color: black;
}
</style>