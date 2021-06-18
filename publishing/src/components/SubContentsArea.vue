<template>
    <section>
        <article class="con-header">
          <div class="con-header-wrap">
            <div class="con-head-box">
              <p> TREND </p> 
              <div class="con-head-btn-wrap">
                <button type="button" class="con-heart-i">
                  <b-icon icon="heart-fill"></b-icon>
                  <span>4</span>
                </button>
                <b-dropdown toggle-class="drop-btn">
                  <template #button-content >
                    <b-icon icon="three-dots-vertical"></b-icon>
                  </template>
                  <b-dropdown-item-button>
                    <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
                    Edit
                  </b-dropdown-item-button>
                  <b-dropdown-divider></b-dropdown-divider>
                  <b-dropdown-item-button variant="danger">
                    <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
                    Delete
                  </b-dropdown-item-button>
                </b-dropdown>
              </div>
            </div>
            <h1>Reach Your Goals with the CodeNewbie Challenge (#CNC2021)</h1>
            <div class="header-pro-wrap">
              <div class="profile-wrap">
                  <b-avatar class="mr-3"></b-avatar>
                  <div class="profile-text">
                    <p class="profile-name">J. Circlehead</p>
                    <p class="profile-date">2021.04.13</p>
                  </div>
                </div>
              <ul class="content-tag-wrap">
                  <li class="tag-r--w">#Javascript</li>
                  <li class="tag-r--w">#Javascript</li>
                  <li class="tag-r--w">#Javascript</li>
                  <li class="tag-r--w">#Javascript</li>
              </ul>
            </div>
          </div>
        </article>

        <acticle class="user-contents">
          <div class="user-text">
            It's an exciting day for early-career developers and people learning to code!
            The CodeNewbie Community has announced the relaunch of their beloved CodeNewbie Challenge with updated resources and a stronger focus on community support.
            If you ever find yourself needing help when it comes to Starting to Code, Coding More, Writing More, or Getting a Job, the CodeNewbie Challenge 2021 (#CNC2021) is for you.
            The five-week challenge is conducted through routine emails that package up manageable weekly "missions", suggested reading material from other community members, helpful worksheets and exercises, lots of support, and self-care tips. This potent combo will help you make real, satisfying progress towards your goals.
            Ready to challenge yourself to reach your biggest early-career coding goals? Sign up and view FAQs and additional info here.
            We hope you'll join us — you've got this! ❤️
          </div>
        </acticle>




    </section>
</template>

<script>
export default {
  name: 'SubContentsArea'
}
</script>

