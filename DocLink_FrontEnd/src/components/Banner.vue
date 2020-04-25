<template>
  <VerticalHeight>
    <div class="banner">
      <Container>
        <div class="row">
          <div class="columns twelve">
            <BannerAnnouncement />
          </div>
        </div>
      </Container>
    </div>
  </VerticalHeight>
</template>

<script>
import Container from "./Container";
import BannerAnnouncement from "./BannerAnnouncement";
import VerticalHeight from "./VerticalHeight";

export default {
  name: "Banner",
  components: {
    Container,
    BannerAnnouncement,
    VerticalHeight
  }
};
</script>

<style scoped>
.banner {
  height: 100%;
  background-color: #a98c6a;
  display: flex;
  align-items: center;
  position: relative;
}

.banner::before {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #a98c6a;
}
</style>