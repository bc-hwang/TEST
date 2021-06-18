<template>
    <article class="art-category">
        <h1 class="tag-num--round"><p>STEP 02</p>카테고리</h1>
            <div class="category-wrap">
                <ul>
                    <router-link :to="{name : 'homeList'}">
                        <li @mouseover="homeHovering = true" @mouseout="homeHovering=false" :class="{'category-active': homeHovering }">홈</li>
                    </router-link>
                    <router-link :to="{name : 'qnaList'}">
                        <li @mouseover="qnaHovering = true" @mouseout="qnaHovering=false" :class="{'category-active': qnaHovering }">질문방</li>
                    </router-link>
                    <router-link :to="{name : 'lifeList'}">
                        <li @mouseover="lifeHovering = true" @mouseout="lifeHovering=false" :class="{'category-active': lifeHovering }">개발자의 일상</li>
                    </router-link>
                    <router-link :to="{name : 'trendList'}">
                        <li @mouseover="trendHovering = true" @mouseout="trendHovering=false" :class="{'category-active': trendHovering }">트렌드</li>
                    </router-link>
                    <router-link :to="{name : 'expertList'}">
                        <li @mouseover="expertHovering = true" @mouseout="expertHovering=false" :class="{'category-active': expertHovering }">지식공유</li>
                    </router-link>
                </ul>
            </div>
    </article>
</template>
<script>
export default{
        name:"SideCategory",
        data(){
            return{
                homeHovering: false,
                qnaHovering: false,
                lifeHovering: false,
                trendHovering: false,
                expertHovering: false,
            }
        }
};
</script>
<style scoped>
</style>