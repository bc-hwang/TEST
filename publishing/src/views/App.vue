<template>
<div>
  <NavArea></NavArea>
    <template v-if="false">
        <WritingArea class="p-body"></WritingArea>
    </template>
    <template v-else >
      <div class="arc-body">
        <SideArea class="left-body"></SideArea>
        <router-view class="right-body"></router-view>
      </div>
    </template>
  <FooterArea class="app-footer "></FooterArea>

</div>
</template>

<script>
  import NavArea from '../components/NavArea'
  import SideArea from '../components/SideArea'
  import FooterArea from '../components/FooterArea'
  import WritingArea from '../components/WritingArea'

  export default {
  components: {
    NavArea,
    SideArea,
    FooterArea,
    WritingArea
  },
  // …
};







</script>

<style>
</style>
