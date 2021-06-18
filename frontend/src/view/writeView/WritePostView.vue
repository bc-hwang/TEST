<template>
    <section id="wri-sec">
        <article id="wri-header">
            <select name="category" v-model="category">
                <option value="QNA">질문</option>
                <option value="LIFE">일상</option>
                <option value="TREND">트렌드</option>
                <option value="EXPERT">지식공유</option>
            </select>
            <div class="header-input-wrap">
                <b-form-input v-model="title" placeholder="제목을 입력해주세요"></b-form-input>
            </div>
        </article>

        <article id="wri-body">
            <h1 class="tag-num--round"><p>STEP 01</p>태그 입력하기</h1>
            <div class="tag-show-area">
                <WriteTagSearch></WriteTagSearch>    
            </div>
        </article>
        <article id="wri-text">
            <h1 class="tag-num--round"><p>STEP 02</p>글 입력하기</h1>
            <vue-simplemde preview-class="markdown-body"
            id="textarea-auto-height" style="padding: 2rem"
            v-model="content" ref="markdownEditor"/>
        </article>
        <article id="wri-btn"> 
            <button @click="createPostCall"> 보내기 </button>
        </article>
        <div :key="index" v-for="(log, index) in validLog">{{log}}</div>
    </section>
</template>

<script>
import WriteTagSearch from "@/components/base/SideComponents/WriteTagSearch.vue";
import { validCheck } from "@/components/mixins/validCheckMixin";
import router from "@/router";
import VueSimplemde from 'vue-simplemde';
import { mapState, mapActions, mapGetters } from 'vuex';

const tagCategoryStore = 'tagCategoryStore';
const userStore = "userStore";
const postStore = "postStore";
export default {
    name: "WritePostView",
    mixins: [validCheck],
    components :{
        WriteTagSearch, VueSimplemde
    },
    data(){
        return{
            title : "",
            category : localStorage.getItem(`autoSaveCategory_${this. getUserNo}`),
            content : "",
        }
    },
    mounted(){
        this.title = localStorage.getItem(`autoSaveTitle_${this.getUserNo}`);
        this.category = localStorage.getItem(`autoSaveCategory_${this. getUserNo}`);
        this.content = localStorage.getItem(`autoSaveContent_${this.getUserNo}`);

    },
    methods: {
        ...mapActions(postStore, ['createPost']),
        createPostCall(){
            this.initValidLog();
            const categoryName = this.category;
            const postTitle = this.title;
            const postContent = this.content;
            this.emptyValid(postTitle, "title");
            this.emptyValid(categoryName, "category");
            this.emptyValid(postContent, "content");
            if(this.validLog.length === 0){
                this.createPost({categoryName, postTitle, postContent})
                .then((data) => {
                    if(data.success){
                        localStorage.removeItem(`autoSaveContent_${this.getUserNo}`);
                        localStorage.removeItem(`autoSaveTitle_${this.getUserNo}`);
                        localStorage.removeItem(`autoSaveCategory_${this.getUserNo}`);
                        localStorage.removeItem(`autoSaveTag_${this.getUserNo}`);
                        router.push({name : "Detail", params: {postNo : data.data.postNo}})
                    }
                    else{
                        this.validLog.push(data.message);
                    }
                })
            }
        }
    },
    watch: {
        content: function(val){
            localStorage.setItem(`autoSaveContent_${this.getUserNo}`, val);
        },
        title: function(val){
            localStorage.setItem(`autoSaveTitle_${this.getUserNo}`, val);
        },
        category: function(val){
            localStorage.setItem(`autoSaveCategory_${this.getUserNo}`, val);
        },
        tagSelected: function(){
            localStorage.setItem(`autoSaveTag_${this.getUserNo}`, JSON.stringify(
                this.getTagSelected));
        }
    },
    computed: {
        ...mapState(tagCategoryStore, ['tagSelected']),
        ...mapGetters(tagCategoryStore, ['getTagSelected']),
        ...mapGetters(userStore, ['getUserNo'])
    }
}
</script>

<style scoped>
@import '~simplemde/dist/simplemde.min.css';
@import '~github-markdown-css';
</style>

