<template>
    <section class="sidenav-area">
        <div class="sidenav-header">
            <img src="@/assets/img/dataus-logo.png" style="width:6rem;">
            <button v-b-toggle.sidebar ><b-icon class="sidenav-icon" icon="x"></b-icon></button>
        </div>
        <div v-if="isLogined" class="login-status-wrap">
            <span></span>
            <div class="login-status">
                <div class="sidenav-myprofile">
                    <b-avatar></b-avatar>
                    <p>{{getUserName}}</p>
                </div>
                <div class="wellcome-wrap">
                    <button @click="logout({})" class="wellcome-btn">로그아웃</button>
                    <button class="wellcome-btn">프로필 바로가기</button>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="sidenav-well">
                <a href="#">
                    <span></span>
                    <div class="sidenav-well-inner">
                        <div class="well-contents-wrap">
                            <h1>Welcome!</h1>
                        </div>
                        <div class="icon-box">
                            <div class="right-icon"></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="login-out-wrap">
                <div class="sidenav-logout-status">
                    <p @click="setLoginShow({})">로그인하기</p>
                </div>
            </div>
        </div>
        <SideInfo />
        <SideCategory />
        <SideTag />
        <article class="sidenav-footer">
            <div class="sidenav-sns">
                <div class="youtube-wrap">
                    <b-icon icon="youtube"></b-icon>
                </div>
                <div class="facebook-wrap">
                    <b-icon icon="facebook"></b-icon>
                </div>
            </div>
        </article>
    </section>
</template>
<script>
/*
헤더에 토글되는 사이드바만을 의미한다.
*/
import SideInfo from "@/components/base/SideComponents/SideInfo.vue";
import SideTag from "@/components/base/SideComponents/SideTag.vue";
import SideCategory from "@/components/base/SideComponents/SideCategory.vue";
import {mapGetters, mapMutations} from 'vuex';

const userStore = "userStore"

export default{
    name:"SideBar",
    components: {
        SideInfo,
        SideTag,
        SideCategory
    },
    methods: {
        ...mapMutations(userStore, ['setLoginShow', 'logout']),
    },
    computed: {
        ...mapGetters(userStore,['isLogined', 'getUserName']),
    },
}
</script>
<style scoped>
</style>