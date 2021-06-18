<template>
  <div>
    <section>
      <h1 class="contents-name"> Home</h1>
<!-- section, selected tag box start -->
        <article class="art-selec-tag">
          <div class="tag-box-wrap">
            <ul>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
              <li class="tag-tech">
                <span>태그</span>
                <b-icon icon="x"></b-icon>
              </li>
            </ul>
          </div>
        </article>
<!-- section, selected tag box end -->
<!-- section, contents box start -->
        <article>
          <div class="contents-area">
            <div class="content-wrap">
              <div class="content-body">
                <p class="content-cartegory">TREND</p>
                <h1>The Complete Guide to Full Stack Ethereum Development</h1>
              </div>
              <!--여기가 변경되었음/ wrap으로 줄 바꿈 유도-->
              <div class="content-belt">
                <div class="profile-wrap">
                  <b-avatar class="mr-3"></b-avatar>
                  <div class="profile-text">
                    <p class="profile-name">J. Circlehead</p>
                    <p class="profile-date">2021.04.13</p>
                  </div>
                </div>
                <ul>
                  <li class="tag-tech">Javascript</li>
                  <li class="tag-tech">Javascript</li>
                  <li class="tag-tech">Javascript</li>
                  <li class="tag-tech">Javascript</li>
                </ul>
              </div>
            <!--여기가 변경되었음/ wrap으로 줄 바꿈 유도-->
            </div>
            <div class="chat-reaction-wrap">
              <button class="reaction-wrap">
                <b-icon icon="heart"></b-icon>
                <span class="reaction-num">4</span>
                  reactions
              </button>
              <router-link to="/about" class="comments-wrap">
                <b-icon icon="chat-dots"></b-icon>
                <span class="comment-num">4</span>
                comments
              </router-link>
            </div>
          </div>
        </article>
<!-- section, contents end -->
        </section>
      <div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SectionArea'
}
</script>
