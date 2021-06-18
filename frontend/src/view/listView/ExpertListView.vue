<template>
    <div class="arc-body"> 
        <Side class="left-body"></Side>
        <ExpertList class="right-body"></ExpertList>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import ExpertList from '@/components/list/ExpertList.vue';

export default {
    name: "ExpertListView",
    components: {
    Side,
    ExpertList
  },  
}
</script>
<style scoped>
</style>
