<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-12">
          <mdb-view class="gradient-card-header blue darken-2">
            <h4 class="h4-responsive text-white"> Lists of Doctors</h4>
          </mdb-view>
          <mdb-card-body>
            <table class="table table-striped">
              <thead>
                <tr><th>First Name</th><th>Last Name</th><th>Specialization</th><th>Email</th><th>Action</th></tr>
              </thead>
              <tbody>
                <tr>
                  <td>Mark</td>
                  <td>Otto</td>
                  <td>Oncologist</td>
                  <td>@mdo</td>
                  <button type="button" class="btn btn-primary btn-sm">Aprrove</button>
                  </tr>
                <tr>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                  <button type="button" class="btn btn-primary btn-sm">Aprrove</button>
                </tr>
                <tr>
                  <td>Larry</td>
                  <td>the Bird</td>
                  <td>@twitter</td>
                  <td>@twitter</td>
                  <button type="button" class="btn btn-primary btn-sm">Aprrove</button>
                </tr>
              </tbody>
            </table>
          </mdb-card-body>
        </mdb-card>
      </mdb-col>
    </mdb-row>
  </section>
</template>

<script>
import { mdbRow, mdbCol, mdbCard, mdbView, mdbCardBody} from 'mdbvue'

export default {
  name: 'Tables',
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbView,
    mdbCardBody,
    
  },
  data () {
    return {}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.card.card-cascade .view.gradient-card-header {
  padding: 1rem 1rem;
  text-align: center;
}
.card.card-cascade h3,
.card.card-cascade h4 {
  margin-bottom: 0;
}
</style>
