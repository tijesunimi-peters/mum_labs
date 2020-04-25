<template>
  <VerticalHeight>
    <div class="login">
      <Container>
        <div class="row">
          <div class="ten columns">
            <div class="login-form-wrapper">
              <LoginForm />
            </div>
          </div>
        </div>
      </Container>
    </div>
  </VerticalHeight>
</template>

<script>
import Container from "./Container";
import VerticalHeight from "./VerticalHeight";
import LoginForm from "./Form/LoginForm";

export default {
  name: "Login",
  props: {
    msg: String
  },
  components: { Container, VerticalHeight, LoginForm }
};
</script>

<style scoped lang="scss">
.login {
  display: flex;
  // padding-top: 100px;
  box-sizing: border-box;
  // background-color: rgba(0,0,0,0.5);
  background-color: #e1e9eb;
  height: 100%;
  align-items: center;
  position: relative;

  &:before {
    position:absolute;
    content: "";
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
  }
}
</style>
