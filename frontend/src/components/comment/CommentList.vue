<template>
    <section class="sec-comment">
        <div :key="index" v-for="(log, index) in validLog">{{log}}</div>
        <div id='comment-area' class="comment-txt-wrap">
            <h1>댓글</h1>
            <span>{{this.commentList.length}}</span>
        </div>
        <article class="art-comment">
                <div class="comment-text">
                    <b-avatar class="mr-3"></b-avatar>
                    <p class="profile-name">
                        {{getUserName ? getUserName : "GUEST"}}
                    </p>
                </div>
                <div class="comment-wri-wrap">
                    <textarea v-model="commentInput"></textarea>
                    <button @click="createCommentCall" type="button" class="comment-btn">Send</button>
                </div>
        </article>
        <article class="art-commented" v-bind:key="com.commentNo" v-for="(com) in commentList">
            <div class="commented-header">
                <div class="conted-hea-text">
                    <b-avatar class="mr-3"></b-avatar>
                    <span>{{com.userNickName}}</span>
                    <span class="conted-date">{{dateConvert(com.commentRegistDate)}}</span>
                </div>
                <div class="coted-hea-btn">
                    <button @click="recommendCommentCall(com.commentNo)" type="button" >
                        <b-icon 
                        icon="heart" :class="{'checked-hart' : (com.recommend && isLogined)}"
                        ></b-icon>
                        <span>{{com.recommendCount}}</span>
                    </button>
                    <b-dropdown v-if="getUserNo == com.userNo" toggle-class="drop-btn">
                        <template #button-content >
                            <b-icon icon="three-dots-vertical" style="color:black"></b-icon>
                        </template>
                        <b-dropdown-item-button @click="makeEditable(com)">
                            <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
                            Edit
                        </b-dropdown-item-button>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item-button @click="deleteCommentCall(com.commentNo)" variant="danger">
                            <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
                            Delete
                        </b-dropdown-item-button>
                    </b-dropdown>
                </div>
            </div>
            <div v-if="com.editable" class="coted-edit-wrap">
                <textarea type="text" v-model="com.commentContent" placeholder="여기를 수정해보세요"></textarea>
                <button @click="updateCommentCall(com)" 
                type="button" class="comment-btn">수정</button>
            </div>
            <pre v-else class="coted-body">{{com.commentContent}}</pre>
        </article>
    </section>
</template>
<script>
import { dateChange } from "@/util/DateUtil"
import { validCheck } from "@/components/mixins/validCheckMixin"
import { mapGetters, mapActions, mapMutations } from 'vuex';
const postStore = 'postStore';
const userStore = 'userStore';

export default{
    name : "commentList",
    mixins: [validCheck],
    data() {
        return {
            commentList : [],
            commentInput : "",
        }
    },
    created(){
        this.getCommentCall();
    },
    methods: {
        ...mapActions(postStore, ['deleteComment', 'createComment', 'updateComment', 'recommendComment', 'getComment']),
        ...mapMutations(userStore, ['checkLoginWithModal']),
        dateConvert(date){
            return dateChange(date);
        },
        makeEditable(comment){
            this.commentList.forEach((element, index) => {
                if(element.commentNo == comment.commentNo){
                    comment.editable = !comment.editable;
                    this.commentList.splice(index, 1, comment);
                }
            });
        },
        getCommentCall(){
            const postNo = this.$route.params.postNo
            this.getComment({postNo})
            .then((data) =>{
                this.commentList = data.commentList;
                this.commentList.forEach((e) => {
                    e.editable = false;
                })
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        },
        updateCommentCall(comment){
            this.initValidLog();
            const postNo = this.$route.params.postNo;
            const commentNo = comment.commentNo;
            const commentContent = comment.commentContent;
            this.emptyValid(commentContent, "content");
            if(this.validLog.length === 0){
                this.updateComment({postNo, commentNo, commentContent})
                .then((data) => {
                    if(data.success){
                        this.getCommentCall();
                    }
                })
                .catch(({message}) => {
                    console.log("error message : ", message);
                });
            }
        },
        createCommentCall(){
            this.checkLoginWithModal();
            if(this.isLogined){
                this.initValidLog();
                const postNo = this.$route.params.postNo;
                const commentContent = this.commentInput;
                this.emptyValid(commentContent, "content");
                if(this.validLog.length === 0){
                    this.createComment({postNo, commentContent})
                    .then(data => {
                        if(data.success){
                            this.commentInput = "";
                            this.getCommentCall();
                        }
                        else{
                            console.log(data.message);
                        }
                    })
                    .catch(error => {
                        console.log(error);
                    })
                }
            }
        },
        deleteCommentCall(commentNo){
            const postNo = this.$route.params.postNo;
            this.deleteComment({postNo, commentNo})
            .then(data => {
                if(data.success){
                    this.commentList.forEach((element, index) => {
                        if(element.commentNo == data.data.commentNo){
                            this.commentList.splice(index, 1);
                        }
                    });
                }
                else{
                    console.log(data.message);
                }
            })
            .catch(error => {
                console.log(error);
            })
        },
        recommendCommentCall(commentNo){
            this.checkLoginWithModal();
            if(this.isLogined){
                const postNo = this.$route.params.postNo;
                this.recommendComment({postNo, commentNo})
                .then(data => {
                    if(data.success){
                        if(data.data.process === "add"){
                            this.commentList.forEach((e) => {
                                if(e.commentNo == commentNo){
                                    e.recommend = true;
                                    e.recommendCount++;
                                }
                            });
                        } 
                        else{
                            this.commentList.forEach((e) => {
                                if(e.commentNo == commentNo){
                                    e.recommend = false;
                                    e.recommendCount--;
                                }
                            });
                        }
                    }
                    else{
                        console.log(data.message);
                    }
                })
                .catch(({message}) => {
                    console.log("error message : ", message);
                });
            }
        },
    },
    computed: {
        ...mapGetters(userStore, ['getUserName', 'isLogined', 'getUserNo'])
    }
}
</script>
<style scoped>
</style>