<template>
    <div class="arc-body"> 
        <Side class="left-body"></Side>
        <QnaDetail class="right-body"></QnaDetail>
    </div>
</template>

<script>
import Side from '@/components/base/Side.vue';
import QnaDetail from '@/components/detail/Detail.vue';

export default {
    name: "DetailView",
    components: {
    Side,
    QnaDetail
  },  
}
</script>
<style scoped>
</style>
