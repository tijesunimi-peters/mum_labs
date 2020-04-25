<template>
  <section id="profile">
    <mdb-row>
      <mdb-col md="3">
        <mdb-card cascade narrow>
          <mdb-view>
            <img src="https://mdbootstrap.com/img/Photos/Horizontal/People/6-col/img%20%283%29.jpg" alt="Project" class="img-fluid"/>
            <mdb-mask overlay="white-slight" waves/>
          </mdb-view>
          <mdb-card-body class="text-center">
            <mdb-card-title class="font-bold mb-2">
              <strong>Alice Mayer</strong>
            </mdb-card-title>
            <h5 class="indigo-text">
              <strong>Psychologist </strong>
            </h5>
          
          </mdb-card-body>
        </mdb-card>
      </mdb-col>
      <mdb-col md="5">
        <form>
  <div class="form-group">
    <label for="exampleFormControlInput1">Firstname</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
    <label for="exampleFormControlInput1">Lastname</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
    <label for="exampleFormControlInput1">Age</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
    <label for="exampleFormControlInput1">Gender</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Male">
    <label for="exampleFormControlInput1">Specialization</label>
    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="">
    <div class="form-group">
    <label for="exampleFormControlTextarea1">Experience</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
  </div>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">Country</label>
    <select class="form-control" id="exampleFormControlSelect1">
      <option>Nepal</option>
      <option>USA</option>
      <option>Ethiopia</option>
      <option>Nigeria</option>
      <option>Krosova</option>
    </select>
    <button type="button" class="btn btn-primary">Edit Details</button>
  </div>
        </form>
      </mdb-col>

      <!-- <mdb-col md="9">
        <section class="text-center pb-3">
          <mdb-row class="d-flex justify-content-center"> --> 
           <!-- <mdb-col lg="6" xl="5" class="mb-3">
              <mdb-card cascade narrow class="d-flex mb-5">
                <mdb-view>
                  <img src="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-profile-page.jpg" alt="Project" class="img-fluid"/>
                  <mdb-mask overlay="white-slight" waves/>
                </mdb-view>
                <mdb-card-body>
                  <mdb-card-title class="font-bold mb-3">
                    <strong>Project name</strong>
                  </mdb-card-title>
                  <mdb-card-text>Some quick example text to build on the card title and make up the bulk of the card's content.</mdb-card-text>
                </mdb-card-body>
                <mdb-card-footer class="links-light profile-card-footer">
                  <span cla ss="right">
                    <a class="p-2" href="#profile">
                      Live Preview
                      <mdb-icon icon="photo" class="ml-1"/>
                    </a>
                  </span>
                </mdb-card-footer>
              </mdb-card>
            </mdb-col> -->
            <!-- <mdb-col lg="6" xl="5" class="mb-3">
              <mdb-card cascade narrow class="d-flex mb-5">
                <mdb-view>
                  <img src="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-signup.jpg" alt="Project" class="img-fluid"/>
                  <mdb-mask overlay="white-slight" waves/>
                </mdb-view>
                <mdb-card-body>
                  <mdb-card-title class="font-bold mb-3">
                    <strong>Project name</strong>
                  </mdb-card-title>
                  <mdb-card-text>Some quick example text to build on the card title and make up the bulk of the card's content.</mdb-card-text>
                </mdb-card-body>
                <mdb-card-footer class="links-light profile-card-footer">
                  <span class="right">
                    <a class="p-2" href="#profile">
                      Live Preview
                      <mdb-icon icon="photo" class="ml-1"/>
                    </a>
                  </span>
                </mdb-card-footer>
              </mdb-card>
            </mdb-col> -->
          <!-- </mdb-row>
          <mdb-row class="d-flex justify-content-center"> -->
            <!-- <mdb-col lg="6" xl="5" class="mb-3"> -->
              <!-- <mdb-card cascade narrow class="d-flex mb-5">
                <mdb-view>
                  <img src="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-pricing.jpg" alt="Project" class="img-fluid"/>
                  <mask overlay="white-slight" waves/>
                </mdb-view>
                <mdb-card-body>
                  <mdb-card-title class="font-bold mb-3">
                    <strong>Project name</strong>
                  </mdb-card-title>
                  <mdb-card-text>Some quick example text to build on the card title and make up the bulk of the card's content.</mdb-card-text>
                </mdb-card-body>
                <mdb-card-footer class="links-light profile-card-footer">
                  <span class="right">
                    <a class="p-2" href="#profile">
                      Live Preview
                      <mdb-icon icon="photo" class="ml-1"/>
                    </a>
                  </span>
                </mdb-card-footer>
              </mdb-card> -->
            <!-- </mdb-col> -->
            <!-- <mdb-col lg="6" xl="5" class="mb-3">
              <mdb-card cascade narrow class="d-flex mb-5">
                <mdb-view>
                  <img src="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-landing.jpg" alt="Project" class="img-fluid"/>
                  <mask overlay="white-slight" waves/>
                </mdb-view>
                <mdb-card-body>
                  <mdb-card-title class="font-bold mb-3">
                    <strong>Project name</strong>
                  </mdb-card-title>
                  <mdb-card-text>Some quick example text to build on the card title and make up the bulk of the card's content.</mdb-card-text>
                </mdb-card-body>
                <mdb-card-footer class="links-light profile-card-footer">
                  <span class="right">
                    <a class="p-2" href="#profile">
                      Live Preview
                      <mdb-icon icon="photo" class="ml-1"/>
                    </a>
                  </span>
                </mdb-card-footer>
              </mdb-card>
            </mdb-col> -->
            <!-- <mdb-col lg="12">
              <div class="text-center">
                <mdb-pagination circle color="blue">
                  <mdb-page-item disabled>First</mdb-page-item>
                  <mdb-page-nav prev></mdb-page-nav>
                  <mdb-page-item active>1</mdb-page-item>
                  <mdb-page-item>2</mdb-page-item>
                  <mdb-page-item>3</mdb-page-item>
                  <mdb-page-item>4</mdb-page-item>
                  <mdb-page-item>5</mdb-page-item>
                  <mdb-page-nav next></mdb-page-nav>
                  <mdb-page-item disabled>Last</mdb-page-item>
                </mdb-pagination>
              </div>
            </mdb-col> -->
          <!-- </mdb-row>
        </section> -->
      <!-- </mdb-col> -->
    </mdb-row>
  </section>
</template>

<script>
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbView, mdbMask, mdbCardTitle} from 'mdbvue'

export default {
  name: 'Profile',
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbView,
    mdbMask,
    mdbCardTitle,
    // mdbCardText,
    // mdbCardFooter,
    // mdbIcon,
    // mdbBtn,
    // mdbPagination,
    // mdbPageNav,
    // mdbPageItem
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>
.profile-card-footer {
  background-color: #F7F7F7 !important;
  padding: 1.25rem;
}
.card.card-cascade .view {
  box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.15), 0 3px 12px 0 rgba(0, 0, 0, 0.15);
}
</style>
