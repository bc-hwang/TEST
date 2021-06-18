<template>
  <div></div>
</template>

<script>
import { mapMutations, mapActions } from "vuex";
const userStore = "userStore";
export default {
  name: "LoginCheck",
  created() {
    console.log("hi");
    this.checkToken();
  },
  methods: {
    ...mapMutations(userStore, ["login"]),
    ...mapActions(userStore, ['getUserInfo']),
    checkToken() {
      const token = this.$route.query.token;
      if (token !== undefined) {
        this.login({token});
        this.getUserInfo({});
      }
    },
  },
};
</script>

<style scoped>
</style>
