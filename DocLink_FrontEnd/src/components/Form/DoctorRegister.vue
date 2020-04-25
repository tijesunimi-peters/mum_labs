<template>
  <VerticalHeight>
    <div class="doctor-register">
      <Container>
        <div class="row">
          <div class="ten columns">
            <form v-on:submit.prevent="submit">
              <div class="row">
                <h2>Register as a Doctor</h2>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="exampleEmailInput">Firstname</label>
                  <input class="u-full-width" v-model="firstName" type="text" />
                </div>
                <div class="six columns">
                  <label for="exampleEmailInput">Lastname</label>
                  <input class="u-full-width" v-model="lastName" type="text"  />
                </div>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="exampleEmailInput">Your email</label>
                  <input
                    class="u-full-width"
                    type="email"
                    placeholder="test@mailbox.com"
                    id="exampleEmailInput"
                    v-model="email"
                  />
                </div>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="exampleEmailInput">Your password</label>
                  <input
                    class="u-full-width"
                    type="password"
                    placeholder="******"
                    id="exampleEmailInput"
                  />
                </div>
              </div>
              <div class="row">
                <div class="six columns">
                  <label for="exampleEmailInput">Years of Experience</label>
                  <input class="u-full-width" type="number" placeholder="2" id="exampleEmailInput" />
                </div>
              </div>

              <!-- <div class="row">
                <label for="exampleMessage">Specialization</label>
                <textarea
                  class="u-full-width"
                  placeholder="General, Oncologist"
                  id="exampleMessage"
                ></textarea>
              </div>-->

              <div class="row">
                <div class="six columns">
                  <label for="roleInput">Country</label>
                  <select class="u-full-width" id="roleInput">
                    <option value="Option 1">USA</option>
                    <option value="Option 2">Australia</option>
                    <option value="Option 2">Ethiopia</option>
                    <option value="Option 2">Nigeria</option>
                    <option value="Option 2">Nepal</option>
                  </select>
                </div>

                <div class="six columns">
                  <label for="roleInput">State</label>
                  <select class="u-full-width" id="roleInput">
                    <option value="Option 1">Iowa</option>
                    <option value="Option 2">California</option>
                    <option value="Option 2">New York</option>
                  </select>
                </div>
              </div>

              <input class="button-primary" type="submit" value="Register" />
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

export default {
  components: { Container, VerticalHeight },
  name: "DoctorRegister",
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
      userRole: "ROLE_DOCTOR",
      experience: null
    };
  },
  methods: {
    submit: function() {
      this.$store.commit("reigster", JSON.stringify({
        email: this.email,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName,
        country: this.country,
        state: this.state,
        userRole: this.userRole,
        experience: this.experience
      }));
    }
  }
};
</script>


<style scoped lang="scss">
.doctor-register {
  display: flex;

  box-sizing: border-box;
  // background-color: rgba(0,0,0,0.5);
  background-color: #e1e9eb;
  height: 100%;
  align-items: center;
  position: relative;
  overflow: scroll;

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
