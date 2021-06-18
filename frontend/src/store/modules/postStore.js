import { unAuthorizedCheck } from "@/util/UserUtil";
import { makeTagParams } from "@/util/UserUtil";
import { instance } from "@/util/AxiosConfig";


const postStore = {
    namespaced: true,
    state: {
        PageIndex: 1,
        postSearchKeyword: "",
        postList: [],
        postEndPageNo: 0,
    },
    getters: {
        getPageIndex(state) {
            return state.PageIndex;
        },
        getPostSearchKeyword(state) {
            return state.postSearchKeyword;
        },
        getPostList(state) {
            return state.postList;
        },
        getPostEndPageNo(state) {
            return state.postEndPageNo;
        }
    },
    mutations: {
        setPageIndex(state, { index }) {
            state.PageIndex = index;
        },
        setPostSearchKeyword(state, { keyword }) {
            state.postSearchKeyword = keyword;
        },
        setPostList(state, { postList }) {
            state.postList = state.postList.concat(postList);
        },
        clearPostList(state, { }) { //eslint-disable-line no-empty-pattern
            while (state.postList.length > 0) {
                state.postList.pop();
            }
        },
        setPostEndPageNo(state, { postEndPageNo }) {
            state.postEndPageNo = postEndPageNo;
        }
    },
    actions: {
        //게시글 리스트 불러오기
        getPost({ state, commit, rootGetters }, { }) { //eslint-disable-line no-empty-pattern
            const tags = makeTagParams(rootGetters['tagCategoryStore/getTagSelected']);
            const category = rootGetters['tagCategoryStore/getCategorySelected'];
            const page = state.PageIndex;
            const content = "N";
            let keyword = state.postSearchKeyword;
            return instance.get("/api/post", {
                params: {
                    category: category,
                    page: page,
                    size: 10,
                    content: content,
                    tags: tags,
                    keyword: keyword
                }
            })
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        const result = data.data;
                        const postList = result.postList;
                        const postEndPageNo = result.endPageNo;
                        commit("setPostList", { postList });
                        commit("setPostEndPageNo", { postEndPageNo });
                        return result;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        //게시글 상세보기
        getPostDetail({ commit }, { postNo }) { // eslint-disable-line no-unused-vars
            return instance.get(`/api/post/${postNo}`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                }).catch(error => {
                    console.log(error);
                });
        },

        //게시글 삭제하기
        deletePost({ commit }, { postNo }) { // eslint-disable-line no-unused-vars
            return instance.delete(`/api/post/${postNo}`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                })
                .catch(error => {
                    unAuthorizedCheck(error, commit);
                })
        },

        //게시글 생성하기
        createPost({ commit, rootGetters }, { categoryName, postTitle, postContent }) { // eslint-disable-line no-unused-vars
            let form = {
                categoryName: categoryName,
                postTitle: postTitle,
                postContent: postContent,
                tags: rootGetters['tagCategoryStore/getTagSelected'],
            }
            return instance.post("/api/post", form)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        //게시글 수정하기
        updatePost({ commit, rootGetters }, { postNo, categoryName, postTitle, postContent }) { // eslint-disable-line no-unused-vars
            let form = {
                categoryName: categoryName,
                postTitle: postTitle,
                postContent: postContent,
                tags: rootGetters['tagCategoryStore/getTagSelected']
            }
            return instance.put(`/api/post/${postNo}`, form)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        //게시글 추천하기
        recommendPost({ commit }, { postNo }) { // eslint-disable-line no-unused-vars
            return instance.post(`/api/post/${postNo}/recommend`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        //댓글 삭제하기
        deleteComment({ commit }, { postNo, commentNo }) { // eslint-disable-line no-unused-vars
            return instance.delete(`/api/post/${postNo}/comment/${commentNo}`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                })
                .catch(error => {
                    unAuthorizedCheck(error, commit);
                })
        },

        //댓글 생성하기
        createComment({ commit }, { postNo, commentContent }) { // eslint-disable-line no-unused-vars
            let form = {
                commentContent: commentContent
            }
            return instance.post(`/api/post/${postNo}/comment`, form)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                })
                .catch(error => {
                    unAuthorizedCheck(error, commit);
                })
        },

        //댓글 수정하기
        updateComment({ commit }, { postNo, commentNo, commentContent }) { // eslint-disable-line no-unused-vars
            let form = {
                commentContent: commentContent
            }
            return instance.put(`/api/post/${postNo}/comment/${commentNo}`, form)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                })
                .catch(error => {
                    unAuthorizedCheck(error, commit);
                })
        },

        //댓글 추천하기
        recommendComment({ commit }, { postNo, commentNo }) { // eslint-disable-line no-unused-vars
            return instance.post(`/api/post/${postNo}/comment/${commentNo}/recommend`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        //게시글에 해당하는 댓글 가져오기
        getComment({ commit }, { postNo }) { // eslint-disable-line no-unused-vars
            return instance.get(`/api/post/${postNo}/comment`)
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        return data.data;
                    }
                })
                .catch(error => {
                    unAuthorizedCheck(error, commit);
                })
        },
    },
}

export default postStore