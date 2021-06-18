<template>
  <div>
    <Header></Header>
    <router-view></router-view>  
    <FooterArea class="app-footer"></FooterArea>
  </div>
</template>

<script>
import Header from '@/components/base/Header.vue';
import FooterArea from '@/components/base/FooterArea'

export default {
  name: 'App',
  components: {
    Header,
    FooterArea
  },
}
</script>

<style scoped>
</style>
