<template>
  <b-modal id="login" v-model="showModal" centered hide-footer>
    <img src="@/assets/img/deverse-logo.png" />
    <p>Deverse 로그인 하기</p>
    <div class="login-m-wrap">
      <button @click="redirect(githubLogin)" type="button">
        <span><b-icon icon="github"></b-icon></span> 깃허브
      </button>
      <button @click="redirect(googleLogin)" type="button">
        <span><b-icon icon="google"></b-icon></span> 구글
      </button>
      <button @click="redirect(facebookLogin)" type="button">
        <span><b-icon icon="facebook"></b-icon></span> 페이스북
      </button>
    </div>
  </b-modal>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
const userStore = 'userStore';
export default {
  name: "Login",
  data() {
    return {
      githubLogin: `http://${process.env.VUE_APP_URL}:${process.env.VUE_APP_PROXY_PORT}/oauth2/authorize/github?redirect_uri=${process.env.VUE_APP_REDIRECT_URI}`,
      googleLogin: `http://${process.env.VUE_APP_URL}:${process.env.VUE_APP_PROXY_PORT}/oauth2/authorize/google?redirect_uri=${process.env.VUE_APP_REDIRECT_URI}`,
      facebookLogin: `http://${process.env.VUE_APP_URL}:${process.env.VUE_APP_PROXY_PORT}/oauth2/authorize/facebook?redirect_uri=${process.env.VUE_APP_REDIRECT_URI}`,
    };
  },
  methods: {
    ...mapMutations(userStore, ['setLoginShow']),
    redirect(url) {
      window.location.href = url;
    },
  },
  computed: {
    ...mapGetters(userStore, ['getLoginShow']),
    showModal: {
      get(){
        return this.getLoginShow;
      },
      set(){
        this.setLoginShow({});
      }
    }
  }
};
</script>

<style scoped>
</style>
