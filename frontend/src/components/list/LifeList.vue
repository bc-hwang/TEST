<template>
    <div>
        <section>
            <h1 class="contents-name">개발자의 일상</h1>
            <article class="art-selec-tag">
                <div class="tag-box-wrap">
                    <ul>
                        <li class="tag-tech" @click="clickTag" :key="data.tagNo" :id="data.tagNo" v-for="(data) in getTagSelected">
                            <span>{{data.tagName}}</span>
                            <b-icon icon="x"></b-icon>
                        </li>
                    </ul>
                </div>
            </article>
            <article>
                <div class="contents-area" v-bind:key="data.postNo" v-for="(data) in getPostList">
                    <div class="content-wrap">
                        <div class="content-body">
                            <p class="content-cartegory">{{data.categoryName}}</p>
                            <h1>
                                <router-link :to="{name: 'Detail', params: { postNo: data.postNo}, query: {comment : false}}">
                                    {{data.postTitle}}
                                </router-link>
                            </h1>
                        </div>    
                        <div class="content-belt">
                            <div class="profile-wrap">
                                <b-avatar class="mr-3"></b-avatar>
                                <div class="profile-text">
                                    <p class="profile-name">{{data.userNickName}}</p>
                                    <p class="profile-date">{{dateConvert(data.postRegistDate)}}</p>
                                </div>
                            </div>
                            <ul>
                                <li  @click="clickTag" class="tag-tech" :key="tag.tagNo" :id="tag.tagNo" v-for="(tag) in data.tags">{{tag.tagName}}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="chat-reaction-wrap">
                        <router-link class="reaction-wrap" :to="{name: 'Detail', params: { postNo: data.postNo}, query: {comment : false}}">
                            <b-icon icon="heart">
                            </b-icon>
                            <span class="reaction-num">{{data.recommendCount}}</span>
                            reactions
                        </router-link>
                        <router-link :to="{name: 'Detail', params: { postNo: data.postNo}, query: {comment : true}}">
                            <b-icon icon="chat-dots"></b-icon>
                            <span class="comment-num">{{data.commentCount}}</span>
                            comments
                        </router-link>
                    </div>
                </div>
            </article>
        </section>
    </div>
</template>
<script>
import { postList } from "@/components/mixins/postListMixin"

export default {
    name: "LifeList",
    mixins: [postList],
    data(){
        return{
            category: "LIFE",
        }
    },
}
</script>

<style scoped>

</style>
