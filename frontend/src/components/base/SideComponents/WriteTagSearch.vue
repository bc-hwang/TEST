<template>
    <article>
        <div class="wri-tag-select">
            <div class="wri-tag-text">
            <input @keyup.down="moveKeyDown(0)" v-model="searchInput"
             ref="tag0" placeholder="태그를 검색하세요." type="search">
            </div>
            <ul class="tag-tech-wrap">
                <li class="tag-tech" @click="clickTag" :key="tag.tagNo" :id="tag.tagNo" v-for="tag in getTagSelected">{{tag.tagName}}</li>
            </ul>
        </div>
        <div @scroll="handleScroll" class="list-wrap list-border">
            <ul>
                <li @keyup.down="moveKeyDown(index+1)" @keyup.up="moveKeyUp(index+1)" 
                @keyup.enter="enterTag" v-bind:key="tag.tagNo" 
                :ref="'tag'+ (index+1)" :id="tag.tagNo" tabIndex="-1" class="list-tag" 
                v-for="(tag, index) in tagList" @click="clickTag">{{tag.tagName}}</li>
            </ul>
        </div>
    </article>
    
</template>
<script>
import { tagSearch } from "@/components/mixins/tagSearchMixin"
import debounce from "lodash/debounce";
import { mapActions, mapGetters, mapMutations } from 'vuex';

const tagCategoryStore = 'tagCategoryStore';
const userStore = "userStore";

export default{
    name:"WriteTagSearch",
    mixins: [tagSearch],
    created(){
        this.initTagSelected();
        let tags = JSON.parse(localStorage.getItem(`autoSaveTag_${this.getUserNo}`));
        if(tags !== null && tags !== ""){
                tags.forEach((tag) => {
                this.setTag({tag});
            });
        }
        this.initTag();
    },
    methods: {
        ...mapMutations(tagCategoryStore, ['setTag', 'initTagSelected']),
        ...mapActions(tagCategoryStore, ['getTagInAll']),
        initTag(){
            this.tagPage = 1;
            this.tagList = [];
        },
        getTagByScroll(val){
            const keyword = val;
            const page = this.tagPage;
            this.getTagInAll({keyword, page})
             .then((data) => {
                 this.endPageNo = data.endPageNo;
                 this.tagList = this.tagList.concat(data.tagList);
                 this.getSelectedTagInSide();
                 this.apiFinished = true;
             })
            .catch(({message}) => {
                console.log("error message : ", message);
             });
        },
        getTagBySearch(val){
            const keyword = val;
            const page = this.tagPage;
            this.getTagInAll({keyword, page})
             .then((data) => {
                 this.endPageNo = data.endPageNo;
                 this.tagList = data.tagList;
                 this.getSelectedTagInSide();
             })
            .catch(({message}) => {
                console.log("error message : ", message);
             });
        },
    },
    computed: {
        ...mapGetters(tagCategoryStore, ['getTagSelected']),
        ...mapGetters(userStore, ['getUserNo'])
    },
    watch: {
        searchInput: debounce(function(val){
            this.initTag();
            if(val !== ""){
                this.getTagBySearch(this.searchInput);
            }
        }, 500)
    }
}
</script>
<style scoped>
</style>