<template>
  <VerticalHeight>
    <div class="register">
      <Container>
        <div class="row">
          <div class="six columns">
            <router-link to="/user-register" class="button button-primary">Register as a user</router-link>
            <div class="six columns">
              <router-link to="/doctor-register" class="button button-primary">Register as a doctor</router-link>
            </div>
            <!-- Always wrap checkbox and radio inputs in a label and use a <span class="label-body"> inside of it -->
          </div>
          <div class="two columns"></div>
        </div>
      </Container>
    </div>
  </VerticalHeight>
</template>

<script>
import Container from "./Container";
import VerticalHeight from "./VerticalHeight";

export default {
  components: { Container, VerticalHeight },
  name: "Register",
  props: {
    msg: String
  }
};
</script>

<style scoped lang="scss">
.register {
  display: flex;
  // padding-top: 100px;
  box-sizing: border-box;
  // background-color: rgba(0,0,0,0.5);
  background-color: #e1e9eb;
  height: 100%;
  align-items: center;
  position: relative;

  &:before {
    position: absolute;
    content: "";
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
  }
}
</style>
