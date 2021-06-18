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
            <vue-simplemde preview-class="markdown-body" id="textarea-auto-height" 
            style="padding: 2rem" :configs="configSimpleMde" v-model="content" ref="markdownEditor" />
        </article>
        <article id="wri-btn"> 
            <button @click="updatePostCall"> 수정하기 </button>
        </article>
        <div :key="index" v-for="(log, index) in validLog">{{log}}</div>
    </section>
</template>

<script>
import WriteTagSearch from "@/components/base/SideComponents/WriteTagSearch.vue";
import { validCheck } from "@/components/mixins/validCheckMixin"
import router from "@/router"
import VueSimplemde from 'vue-simplemde'
import { mapMutations, mapActions } from 'vuex';

const tagCategoryStore = 'tagCategoryStore';
const postStore = "postStore";
export default {
    name: "UpdatePostView",
    mixins: [validCheck],
    components :{
        WriteTagSearch, VueSimplemde,
    },
    data(){
        return{
            configSimpleMde:{
                initialValue: this.content,
            },
            title : "",
            category : "",
            content : "",
        }
    },
    created(){
        this.getPostDetailCall();
    },
    methods: {
        ...mapMutations(tagCategoryStore, ['setTag']),
        ...mapActions(postStore, ['updatePost', 'getPostDetail']),
        updatePostCall(){
            this.initValidLog();
            const categoryName = this.category;
            const postTitle = this.title;
            const postContent = this.content;
            const postNo = this.$route.params.postNo;
            this.emptyValid(postTitle, "title");
            this.emptyValid(categoryName, "category");
            this.emptyValid(postContent, "content");
            if(this.validLog.length === 0){
                this.updatePost({postNo, categoryName, postTitle, postContent})
                .then((data) => {
                    if(data.success){
                        router.push({name : "Detail", params: {postNo : data.data.postNo}})
                    }
                    else{
                        this.validLog.push(data.message);
                    }
                })
                .catch(({message}) => {
                    console.log("error message : ", message);
                });
            }
        },
        getPostDetailCall(){
            const postNo = this.$route.params.postNo;
            this.getPostDetail({postNo})
            .then((data) =>{
                this.title = data.postTitle;
                this.content = data.postContent;
                this.category = data.categoryName;
                data.tags.forEach((tag) => {
                    this.setTag({tag});
                });
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        },
    },
}
</script>

<style scoped>
@import '~simplemde/dist/simplemde.min.css';
@import '~github-markdown-css';
</style>

