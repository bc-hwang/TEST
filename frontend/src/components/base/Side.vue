<template>
    <section>
        <article v-if="isLogined" class="art-myprofile nav-hidden">
            <span></span>
            <div class="myprofile-wrap">
                <b-avatar></b-avatar>
                <p>{{getUserName}}</p>
                <button class="wellcome-btn">프로필 바로가기</button>
            </div>
        </article>
        <article v-else class="art-wellcome">
            <span></span>
            <div class="side-wellcome">
                <h1>Welcome!</h1>
                <p>DEVerse X DataUs</p>
            </div>
        </article>
        <SideInfo />
        <SideCategory />
        <SideTag />
    </section>
</template>
<script>
/*
헤더에 토글되는 사이드바가 아닌 화면이 클 때 옆에 보여지는 사이드바를 의미한다.
*/
import SideInfo from "@/components/base/SideComponents/SideInfo.vue"
import SideTag from "@/components/base/SideComponents/SideTag.vue"
import SideCategory from "@/components/base/SideComponents/SideCategory.vue"
import {mapGetters} from 'vuex';

const userStore = "userStore"

export default{
    name:"Side",
    components: {
        SideInfo,
        SideTag,
        SideCategory
    },
    computed: {
        ...mapGetters(userStore,['isLogined', 'getUserName']),
    },
}
</script>
<style scoped>
</style>