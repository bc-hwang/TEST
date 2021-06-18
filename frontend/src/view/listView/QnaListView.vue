<template>
    <div class="arc-body">
        <Side class="left-body"></Side>
        <QnaList class="right-body"></QnaList>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import QnaList from '@/components/list/QnaList.vue';

export default {
    name: "QnaListView",
    components: {
    Side,
    QnaList
  },  
}
</script>
<style scoped>
</style>
