<template>
    <section>
        <!-- wellcome 이미지 -->
        <article class="art-wellcome">
            <span></span>
            <div class="side-wellcome">
                <h1>Wellcome!</h1>
                <p>DEVerse X DataUs</p>
                <button class="wellcome-btn">바로가기</button>
            </div>
        </article>
        <article class="art-myprofile nav-hidden">
            <span></span>
            <div class="myprofile-wrap">
                <b-avatar></b-avatar>
                <p>user name</p>
                <button class="wellcome-btn">프로필 바로가기</button>
            </div>
        </article>
        <!-- 검색하기 -->
        <article class="art-allsearch">
            <h1 class="tag-num--round"><p>STEP 01</p>전체 검색하기</h1>
            <div class="search-wrap">
                <input class="search-input" placeholder="Search">
                <button class="search-button" type="submit">
                    <b-icon icon="search"></b-icon>
                </button>
            </div>
        </article>
        <!-- 카테고리 -->
        <article class="art-category">
            <h1 class="tag-num--round"><p>STEP 02</p>카테고리</h1>
            <div class="category-wrap">
                <ul>
                    <li class="category-active">Home</li>
                    <li>TREND</li>
                    <li>LIFE</li>
                    <li>Q&A</li>
                </ul>
            </div>
        </article>
        <!-- 태그 검색하기 -->
        <article class="art-search">
            <h1 class="tag-num--round"><p>STEP 03</p>태그 검색하기</h1>
            <div class="list-area">
                <div class="list-search">
                    <div class="search-wrap">
                        <input class="search-input" type="search" placeholder="검색어를 입력해주세요.">
                    </div>  
                </div>
                <div class="list-wrap">
                    <span></span>
                    <ul>
                        <li class="list-tag list-tag-active">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                        <li class="list-tag">#Javascript</li>
                    </ul>
                </div>
            </div>
        </article>
    </section>
</template>

<script>
export default {
  name: 'SideArea'
}
</script>

