import { instance } from "@/util/AxiosConfig";
import { unAuthorizedCheck } from "@/util/UserUtil"

const tagCategoryStore = {
    namespaced: true,
    state: {
        tagSelected: [],
        categorySelected: "",
    },
    getters: {
        getTagSelected(state) {
            return state.tagSelected;
        },
        getCategorySelected(state) {
            return state.categorySelected;
        },
    },
    mutations: {
        setCategory(state, { category }) {
            state.categorySelected = category;
        },
        setTag(state, { tag }) { //set selected Tag
            let index = 0;
            const selectedObject = state.tagSelected.find((element) => {
                if (element.tagNo == tag.tagNo) {
                    return element;
                }
                index++;
            });
            if (selectedObject) {
                state.tagSelected.splice(index, 1);
            }
            else {
                state.tagSelected.push(tag);
            }
        },
        initTagSelected(state) {
            state.tagSelected = [];
        },
    },
    actions: {
        /**
        태크리스트 가져오기/인기순으로
        **/
        getTag({ commit }, { keyword, page }) { 
            const category = this.getters.getCategorySelected;
            return instance.get(`/api/tag/popular`, {
                params: {
                    category: category,
                    keyword: keyword,
                    page: page,
                }
            })
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        const result = data.data;
                        return result;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },

        /**
        전체 태그중에서 검색어에 해당되는 태그 가져오기
        **/
        getTagInAll({ commit }, { keyword, page }) { 
            return instance.get(`/api/tag/search`, {
                params: {
                    keyword: keyword,
                    page: page,
                }
            })
                .then(data => {
                    if (200 <= data.status && data.status < 300) {
                        const result = data.data;
                        return result;
                    }
                }).catch(error => {
                    unAuthorizedCheck(error, commit);
                });
        },
    },
}

export default tagCategoryStore;