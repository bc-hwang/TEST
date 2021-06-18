<template>
    <article class="art-allsearch">
        <h1 class="tag-num--round"><p>STEP 01</p>전체 검색하기</h1>
        <div class="search-wrap">
            <input v-model="search" class="search-input" placeholder="Search">
            <button @click="initPage" class="search-button" type="submit">
                <b-icon icon="search"></b-icon>
            </button>
        </div>
    </article>
</template>
<script>
import { mapActions, mapMutations } from 'vuex';
const postStore = 'postStore';

export default{
    name:"SideInfo",
    data(){
        return{
            search: "",
        }
    },
    methods: {
        ...mapMutations(postStore, ['setPageIndex', 'clearPostList', 'setPostSearchKeyword']),
        ...mapActions(postStore, ['getPost']),
        initPage(){
            const index = 1;
            this.search = "";
            this.setPageIndex({index});
            this.clearPostList({});
            this.getPost({})
            .then(() =>{
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        },
    },
    watch:{
        search: function(keyword){
            this.setPostSearchKeyword({keyword});
        }
    }
};
</script>
<style scoped>

</style>