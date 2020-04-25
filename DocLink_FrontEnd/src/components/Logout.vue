<template>
  <a v-on:click.prevent="logout" class="button nav-link">Logout</a>
</template>

<script>
export default {
    name: "Logout",
    methods: {
        logout: function() {
            this.$store.dispatch('logout');
            // this.$router.push("/");
        }
    }
}
</script>

<style>

</style>