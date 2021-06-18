import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

import NotFound from "@/components/base/NotFound";


const requireAuth = () => (to, form, next) => {
    if(store.getters['userStore/isLogined']){
        return next();
    }
    window.alert("로그인이 필요합니다.")
    next('/');
}

function loadView(view) {
    return () => import(/* webpackChunkName: "view-[request]" */ `@/view/${view}.vue`)
  }

Vue.use(VueRouter);

const router = new VueRouter({
    mode : "history",
    routes: [
        {
            path : "/",
            name : "homeList",
            component : loadView("listView/HomeListView")
        },
        {
            path : "/loginCheck",
            name : "loginCheck",
            component : () => import("@/components/user/LoginCheck.vue")
        },
        {
            path : "/qna",
            name : "qnaList",
            component : loadView("listView/QnaListView")
        },
        {
            path : "/life",
            name : "lifeList",
            component : loadView("listView/LifeListView")
        },
        {
            path : "/trend",
            name : "trendList",
            component : loadView("listView/TrendListView")
        },
        {
            path : "/expert",
            name : "expertList",
            component : loadView("listView/ExpertListView")
        },
        {
            path : "/detail/:postNo",
            name : "Detail",
            component : loadView("detailView/DetailView")
        },
        {
            path : "/write",
            name : "writePost",
            component : loadView("writeView/WritePostView"),
            beforeEnter: requireAuth()
        },
        {
            path : "/update/:postNo",
            name : "updatePost",
            component : loadView("updateView/UpdatePostView"),
            beforeEnter: requireAuth()
        },
        {
            path: '*',
            name: 'notFound',
            component: NotFound
        }
    ]
});

export default router;