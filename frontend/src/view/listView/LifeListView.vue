<template>
    <div class="arc-body"> 
        <Side class="left-body"></Side>
        <LifeList class="right-body"></LifeList>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import LifeList from '@/components/list/LifeList.vue';

export default {
    name: "LifeListView",
    components: {
    Side,
    LifeList
  },  
}
</script>
<style scoped>
</style>
