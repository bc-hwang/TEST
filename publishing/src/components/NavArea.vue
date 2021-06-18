<template>
    <div>
        <nav class="nav-wrap">
            <!--navigation start point -->
                <div class="nav-inner">
                    <h1 class="logo-wrap">
                        <router-link to="/"><img class="nav-logo" src="../assets/img/deverse-logo.png"></router-link> 
                    </h1>
                        <div class="nav-contents-wrap ">
                            <ul class="nav-login ">
                                <li><!-- 모달 버튼 시작 -->
                                    <b-button id="show-btn" @click="$bvModal.show('bv-modal-example')">로그인</b-button>
                                    <!-- 모달 콘텐츠 레이아웃 시작-->
                                    <b-modal id="bv-modal-example" 
                                            centered
                                            hide-footer 
                                            >
                                        <img src="../assets/img/deverse-logo.png">
                                        <p>Deverse 로그인 하기</p>
                                        <div class="login-m-wrap">
                                            <button type="button">
                                                <span><b-icon icon="github"></b-icon></span> 깃허브 로그
                                            </button>
                                            <button type="button">
                                                <span><b-icon icon="google"></b-icon></span> 구글 로그인
                                            </button>
                                            <button type="button">
                                                <span><b-icon icon="facebook"></b-icon></span> 페이스북 로그인
                                            </button>
                                        </div>
                                    </b-modal>
                                    <!-- 모달 콘텐츠 레이아웃 끝-->
                                </li>
                                <li>회원가입</li>
                            </ul>
                            <ul class="nav-logout nav-hidden">
                                <li>로그아웃</li>
                                <li><button type="button" id="writing-change" class="nav-wri-btn">글쓰기</button></li>
                                <li class="nav-profile-wrap">
                                    <div class="d-flex align-items-center">
                                        <b-avatar variant="primary" class="mr-3"></b-avatar>
                                        <span class="mr-auto">BootstrapVue</span>   
                                    </div>
                                </li>
                            </ul>
                        </div>
                    <div class="nav-btn">
                        <button type="button" class="nav-wri-btn"> 글쓰기 </button>
                        <button v-b-toggle.sidebar class="nav-side-btn" >
                            <b-icon class="nav-icon" icon="list"></b-icon>    
                        </button>
                    </div>
                </div>
                <!--navigation end point -->
            <b-sidebar
            id="sidebar"
            no-header
            v-
            width="100%"
            hight="100%"
            >
                <section class="sidenav-area">
                    <div class="sidenav-header">
                        <img src="../assets/img/dataus-logo.png" style="width:6rem;">
                        <button v-b-toggle.sidebar ><b-icon class="sidenav-icon" icon="x"></b-icon></button>
                    </div>
                    <!-- 로그아웃 했을 때 wellcome
                         활성화하고 싶을 때는  nav-hidden를 지우세요 -->
                    <div class=" nav-hidden">
                        <div class="sidenav-well ">
                            <a href="#">
                                <span></span>
                                <div class="sidenav-well-inner">
                                    <div class="well-contents-wrap">
                                        <h1>Wellcome!</h1>
                                        <p>데이터스를 소개합니다.</p>
                                    </div>
                                    <div class="icon-box">
                                        <div class="right-icon"></div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="login-out-wrap">
                            <div class="sidenav-logout-status">
                                <p>로그인하기</p>
                                <p>회원가입하기</p>
                            </div>
                        </div>
                    </div>
                    <!-- 로그인 했을 때 wellcome -->
                        <div class="login-status-wrap">
                            <span></span>
                            <div class="login-status">
                                <div class="sidenav-myprofile">
                                    <b-avatar></b-avatar>
                                    <p>닉네임</p>
                                </div>
                            <div class="wellcome-wrap">
                                <button class="wellcome-btn">프로필 버튼</button>
                                <button class="wellcome-btn">로그아웃</button>
                            </div>
                        </div>
                    </div>
                    
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
                        <h1 class="tag-num--round"><p>STEP 03</p>태그 검색</h1>
                        <div class="list-area">
                            <div class="list-search">
                                <div class="search-wrap">
                                    <input class="search-input" type="search" placeholder="검색어를 입력해주세요.">
                                </div>  
                            </div>
                            <div class="list-wrap">
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
                    <article class="sidenav-footer">
                        <div class="sidenav-sns">
                            <div class="youtube-wrap">
                                <b-icon icon="youtube"></b-icon>
                            </div>
                            <div class="facebook-wrap">
                                <b-icon icon="facebook"></b-icon>
                            </div>
                        </div>
                    </article>
                </section>
            </b-sidebar>
        </nav>
        <!-- 글쓰기 페이지 일때만 발동 -->
        <article v-if="true" class="nav-mini">
            <button>뒤로가기 버튼</button>
        </article>
    </div>
</template>

<script>
export default {
  name: 'NavArea'
}


</script>

