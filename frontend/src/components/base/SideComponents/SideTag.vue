<template>
    <article class="art-search">
        <h1 class="tag-num--round"><p>STEP 03</p>태그 검색</h1>
            <div class="list-area">
                <div class="list-search">
                    <div class="search-wrap">
                        <input @keyup.down="moveKeyDown(0)" v-model="searchInput" 
                        class="search-input" type="search" ref="tag0"
                        placeholder="검색어를 입력해주세요.">
                    </div>
                </div>
                <div @scroll="handleScroll" class="list-wrap">
                    <span></span>
                    <ul>
                        <li @keyup.down="moveKeyDown(index+1)" @keyup.up="moveKeyUp(index+1)" 
                        @keyup.enter="enterTag" v-bind:key="tag.tagNo" 
                        :ref="'tag'+ (index+1)" :id="tag.tagNo" tabIndex="-1"
                        v-bind:class="{'list-tag-active' : tagActive[tag.tagNo] , 'list-tag' : true}" 
                        v-for="(tag, index) in tagList" @click="clickTag">{{tag.tagName}}</li>
                    </ul>
                </div>
            </div>
    </article>
</template>
<script>
import { tagSearch } from "@/components/mixins/tagSearchMixin"
export default{
    name:"SideTag",
    mixins: [tagSearch],
}
</script>
<style scoped>
</style>