<template>
    <div>
        <nav class="nav-wrap">
            <div class="nav-inner">
                <h1 class="logo-wrap">
                    <router-link :to="{name : 'homeList'}">
                        <a><img class="nav-logo" src="@/assets/img/deverse-logo.png"></a>
                    </router-link>
                </h1>
                <div class="nav-contents-wrap">
                    <ul class="nav-logout" v-if="isLogined">
                        <li @click="logout">로그아웃</li>
                        <router-link :to="{name : 'writePost'}">
                            <li>글쓰기</li>
                        </router-link>
                        <li class="nav-profile-wrap">
                            <div class="d-flex align-items-center">
                                <b-avatar variant="primary" class="mr-3"></b-avatar>
                                <span class="mr-auto">{{getUserName}}</span>   
                            </div>
                        </li>
                    </ul>
                    <ul v-else class="nav-login">
                        <b-button id="show-btn" @click="setLoginShow">로그인</b-button>
                        <LogIn />
                    </ul>
                </div>
                <div class="nav-btn">
                    <router-link :to="{name : 'writePost'}" v-if="isLogined">
                        <button type="button" class="nav-wri-btn"> 글쓰기 </button>
                    </router-link>
                    <button v-b-toggle.sidebar class="nav-side-btn">
                        <b-icon class="nav-icon" icon="list"></b-icon>    
                    </button>
                </div>
            </div>

            <b-sidebar id="sidebar" no-header width="100%" hight="100%">
                <SideBar></SideBar>
            </b-sidebar>
        </nav>
        <article v-if="true" class="nav-mini">
            <button @click="$router.go(-1)">뒤로가기 버튼</button>
        </article>
    </div>
</template>
<script>
import LogIn from "@/components/user/LogIn.vue";
import SideBar from "@/components/base/SideBar.vue";
import { mapGetters, mapMutations } from 'vuex';

const userStore = 'userStore';

export default{
    name:"Header",
    components :{
        LogIn,
        SideBar
    },
    methods: {
        ...mapMutations(userStore, ['logout', 'setLoginShow']),
    },
    computed: {
        ...mapGetters(userStore, ['isLogined', 'getUserName']),
    },
};
</script>
<style scoped>

</style>