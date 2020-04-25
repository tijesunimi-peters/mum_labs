<template>
  <VerticalHeight>
    <div class="user-register">
      <Container>
        <div class="row">
          <div class="ten columns">
            <form v-on:submit.prevent="submit">

              <div class="row">
                <h2>Register</h2>
              </div>
              <div class="row">
                <div class="three columns">
                  <label for="firstName">Firstname</label>
                  <input class="u-full-width" v-model="firstName" type="text" id="firstName" />
                </div>
                <div class="three columns">
                  <label for="lastName">Lastname</label>
                  <input class="u-full-width" type="text" v-model="lastName" id="lastName" />
                </div>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="email">Your email</label>
                  <input
                    class="u-full-width"
                    type="email"
                    placeholder="test@mailbox.com"
                    id="email"
                    v-model="email"
                  />
                </div>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="password">Your password</label>
                  <input
                    class="u-full-width"
                    type="password"
                    placeholder="******"
                    id="password"
                    v-model="password"
                  />
                </div>
              </div>

              <div class="row">
                <div class="six columns">
                  <label for="country">Country</label>
                  <select v-model="country" class="u-full-width" id="country">
                    <option value="USA">USA</option>
                    <option value="Australia">Australia</option>
                    <option value="Ethiopia">Ethiopia</option>
                    <option value="Nigeria">Nigeria</option>
                    <option value="Nepal">Nepal</option>
                  </select>
                </div>
              </div>

              <div class="row">
                <div class="six columns">
                  <label for="state">State</label>
                  <select class="u-full-width" id="state" v-model="state">
                    <option value="Iowa">Iowa</option>
                    <option value="Califonia">California</option>
                    <option value="New York">New York</option>
                  </select>
                </div>
              </div>

              <input class="button button-primary" type="submit" value="Register" />
            </form>
          </div>
        </div>
      </Container>
    </div>
  </VerticalHeight>
</template>

<script>
import Container from "../Container";
import VerticalHeight from "../VerticalHeight";
import { REGISTRATION_URL } from '../../constants';

export default {
  components: { Container, VerticalHeight },
  name: "UserRegister",
  props: {
    msg: String
  },
  data: function() {
    return {
      firstName: null,
      lastName: null,
      country: null,
      state: null,
      email: null,
      password: null,
      userRole: "ROLE_PATIENT"
    }
  },
  methods: {
    submit: function() {
      const details = JSON.stringify({
        email: this.email,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName,
        country: this.country,
        state: this.state,
        userRole: this.userRole
      });

      const context = this;

      fetch(REGISTRATION_URL, {
        method: "post",
        body: details,
        mode: "cors",
        headers: {
          "Content-Type": "application/json"
        }
      }).then(res => res.json())
      .then(response => {
        console.log(response);
        context.$router.push({path: '/login'});
      }).catch(() => false)
    }
  }
};
</script>

<style scoped lang="scss">
.user-register {
  display: flex;
  // padding-top: 100px;
  box-sizing: border-box;
  // background-color: white;
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
    background-color: rgba(0, 0, 0, 0);
  }
}
</style>
