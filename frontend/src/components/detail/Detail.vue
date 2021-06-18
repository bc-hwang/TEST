<template>
  <div>
    <section>
      <article class="con-header">
        <div class="con-header-wrap">
          <div class="con-head-box">
            <p> {{post.categoryName}} </p> 
            <div class="con-head-btn-wrap">
              <button @click="recommendPostCall" type="button" class="con-heart-i" 
              :class="{'checked-hart' : (post.recommend && isLogined)}">
                <b-icon icon="heart-fill"></b-icon>
                <span>{{post.recommendCount}}</span>
              </button>
              <b-dropdown v-if="getUserNo == post.userNo" toggle-class="drop-btn">
                <template #button-content >
                  <b-icon icon="three-dots-vertical"></b-icon>
                </template>
                <router-link :to="{name: 'updatePost', params: { postNo: post.postNo}}">
                  <b-dropdown-item-button>
                    <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
                    Edit
                  </b-dropdown-item-button>
                </router-link>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item-button @click="deletePostCall" variant="danger">
                  <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
                  Delete
                </b-dropdown-item-button>
              </b-dropdown>
            </div>
          </div>
          <h1>{{post.postTitle}}</h1>
          <div class="header-pro-wrap">
            <div class="profile-wrap">
              <b-avatar class="mr-3"></b-avatar>
              <div class="profile-text">
                <p class="profile-name">{{post.userNickName}}</p>
                <p class="profile-date">{{dateConvert(post.postRegistDate)}}</p>
              </div>
            </div>
            <ul class="content-tag-wrap">
              <li class="tag-r--w" :key="tag.tagNo" :id="tag.tagNo" v-for="(tag) in post.tags">{{tag.tagName}}</li>
            </ul>
          </div>
        </div>
      </article>
      <article class="user-contents">
        <div class="user-text">
          <vue-markdown class="markdown-body" :source="post.postContent"></vue-markdown>
        </div>
      </article>
    </section>
    <commentList></commentList>
  </div>
</template>
<script>
import { postDetail } from "@/components/mixins/postDetailMixin"
export default {
  name: "Detail",
  mixins: [postDetail],
}
</script>

<style scoped>
@import '~github-markdown-css';
</style>

