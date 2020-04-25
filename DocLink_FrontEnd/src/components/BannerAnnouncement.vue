<template>
  <div class="banner-announcement">
    <div class="banner-announcement-content">
      <div class="row">
        <div class="columns twelve">
          <h1 class="ba-header">Announcement Title</h1>
        </div>
      </div>
      <div class="row">
        <div class="columns twelve">
          <p class="ba-excerpt">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
        </div>
      </div>
      <div class="row">
        <div class="columns twelve">
          <button type="button" class="button button-primary">Follow us</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BannerAnnouncement"
};
</script>

<style lang="scss" scoped>
.banner-announcement {
  overflow: hidden;

  &-content {
    padding: 20px 0;
    width: 100%;
    min-height: inherit;
    box-sizing: border-box;
  }

  .ba-header, .ba-excerpt {
    color: white;
    }
}
</style>