<template>
  <div class="vertical-height dock-full">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "VerticalHeight"
}
</script>

<style scoped lang="scss">
  .vertical-height {

    &.dock-full {
      height: calc(100vh - 140px);
    }

  }
</style>