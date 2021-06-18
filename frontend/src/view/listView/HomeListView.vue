<template>
    <div class="arc-body"> 
        <Side class="left-body"></Side>
        <HomeList class="right-body"></HomeList>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import HomeList from '@/components/list/HomeList.vue';

export default {
    name: "HomeListView",
    components: {
    Side,
    HomeList
  },  
}
</script>
<style scoped>
</style>
