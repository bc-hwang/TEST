<template>
    <section class="sec-comment">
        <div class="comment-txt-wrap">
            <h1>댓글</h1>
            <span>(45)</span>
        </div>
        <article class="art-comment">
                <div class="comment-text">
                    <b-avatar class="mr-3"></b-avatar>
                    <p class="profile-name">J. Circlehead</p>
                </div>
                <div class="comment-wri-wrap">
                          <textarea></textarea>
                    <button type="button" class="comment-btn">Send</button>
                </div>
        </article>
            <article class="art-commented">
                    <div class="commented-header">
                    <div class="conted-hea-text">
                        <b-avatar class="mr-3"></b-avatar>
                        <span>J. Circlehead</span>
                        <span class="conted-date"> 2021.04.20</span>
                    </div>
                    <div class="coted-hea-btn">
                        <button type="button" >
                            <b-icon icon="heart"></b-icon>
                            <span>4</span>
                        </button>
                        <b-dropdown toggle-class="drop-btn">
                            <template #button-content >
                                <b-icon icon="three-dots-vertical" style="color:black"></b-icon>
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
                    <div class="coted-body">
                        adsfsdaf
                    </div>
            </article>
            <article class="art-commented">
                    <div class="commented-header">
                    <div class="conted-hea-text">
                        <b-avatar class="mr-3"></b-avatar>
                        <span>J. Circlehead</span>
                        <span class="conted-date"> 2021.04.20</span>
                    </div>
                    <div class="coted-hea-btn">
                        <button type="button" >
                            <b-icon icon="heart"></b-icon>
                            <span>4</span>
                        </button>
                        <b-dropdown toggle-class="drop-btn">
                            <template #button-content >
                                <b-icon icon="three-dots-vertical" style="color:black"></b-icon>
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
                    <div class="coted-edit-wrap">
                        <textarea type="text"></textarea>
                        <button class="comment-btn" type="button">버튼</button>
                    </div>
            </article>
        <article>

        </article>
    </section>
</template>

<script>
export default {
  name: 'SubCommentArea'
}
</script>

