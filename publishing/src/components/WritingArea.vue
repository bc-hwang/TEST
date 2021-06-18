<template>
  <section id="wri-sec">
    <article id="wri-header">
    <select name="category">
      <option value="trend">TREND</option>
      <option value="qna">Q&A</option>
      <option value="life">LIFE</option>
    </select>
      <div class="header-input-wrap">
        <b-form-input v-model="text" placeholder="제목을 입력해주세요"></b-form-input>
      </div>
    </article>

    <!------------------------- 태그 입력 ------------------------->

    <article id="wri-body">
      <h1 class="tag-num--round"><p>STEP 01</p>태그 입력하기</h1>
      <div class="tag-show-area">
        <!-- 태그 검색하기 -->
        <div class="wri-tag-select">
          <div class="wri-tag-text">
            <input type="text" placeholder="태그를 검색하세요">
            <!-- 태그 5개가 되면 나타남 --><p class="nav-hidden">태그가 5개를 넘었습니다</p>
            <!-- 태그 선택 없이 보내기 누르면 나타남 --><p>* 태그를 입력해 주세요</p>
          </div>
          <ul class="tag-tech-wrap">
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
        <!-- 모든 태그 노출 -->
        <div class="list-wrap list-border">
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

    <!------------------------- 글자 입력 ------------------------->

    <article id="wri-text">
      <h1 class="tag-num--round"><p>STEP 02</p>글 입력하기</h1>
      <b-form-textarea
        id="textarea-auto-height"
        placeholder="텍스트 입력해주세요"
        rows="3"
        max-rows="8"
      ></b-form-textarea>
    </article>

    <!------------------------- 버튼 입력 ------------------------->

    <article id="wri-btn"> 
      <div>
        <button> 저장하기 </button>
        <button> 보내기 </button>
      </div>
    </article>

  </section>
</template>




<script>
  export default {
    data() {
      return {
        selected: 'a',
        category: [
          { value: 'a', text: 'TREND' },
          { value: 'b', text: 'Q&A' },
          { value: 'c', text: 'LIFE' }
        ]
      }
    }
  }
    
    </script>