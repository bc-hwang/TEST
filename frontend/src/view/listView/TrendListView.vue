<template>
    <div class="arc-body"> 
        <Side class="left-body"></Side>
        <TrendList class="right-body"></TrendList>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import TrendList from '@/components/list/TrendList.vue';

export default {
    name: "TrendListView",
    components: {
    Side,
    TrendList
  },  
}
</script>
<style scoped>
</style>
