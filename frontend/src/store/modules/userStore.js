import router from "@/router";
import { unAuthorizedCheck } from "@/util/UserUtil";
import { getParamsWithAuth } from "@/util/UserUtil"
import { instance } from "@/util/AxiosConfig";

const jwtTokenName = "tk";
const userName = "userName";
const userNum = "userNo";

const userStore = {
    namespaced: true,
    state: {
        loginShow: false,
        isLogined: localStorage.getItem(jwtTokenName),
        userName: localStorage.getItem(userName),
        userNo: localStorage.getItem(userNum)
    },
    getters: {
        isLogined(state) {
            return state.isLogined;
        },
        getUserName(state) {
            return state.userName;
        },
        getUserNo(state) {
            return state.userNo;
        },
        getLoginShow(state) {
            return state.loginShow;
        },
    },
    mutations: {
        checkLoginWithModal(state){
            if(!state.isLogined){
                state.loginShow = true;
            }
        },
        login(state, { token }) {
            localStorage.setItem(jwtTokenName, token);
            state.isLogined = localStorage.getItem(jwtTokenName);
        },
        setUserName(state, { name }) {
            localStorage.setItem(userName, name);
            state.userName = localStorage.getItem(userName);
        },
        setUserNo(state, { userNo }) {
            localStorage.setItem(userNum, userNo);
            state.userNo = localStorage.getItem(userNum);
        },
        setLoginShow(state) {
            state.loginShow = !state.loginShow;
        },
        logout(state) {
            localStorage.clear();
            state.isLogined = localStorage.getItem(jwtTokenName);
            state.userName = localStorage.getItem(userName);
            state.userNo = localStorage.getItem(userNum);
            instance.defaults.headers.common['Authorization'] = getParamsWithAuth();
        },
    },
    actions: {
        //로그인 후 user 정보 가져오기
        getUserInfo({ commit }, { }) { // eslint-disable-line no-unused-vars, no-empty-pattern
            instance.defaults.headers.common['Authorization'] = getParamsWithAuth();
            return instance.get(`/user`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        const name = data.data.nickname;
                        const userNo = data.data.userNo;
                        commit("setUserName", { name });
                        commit("setUserNo", { userNo });
                        router.push('/');
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },
    },
}

export default userStore;