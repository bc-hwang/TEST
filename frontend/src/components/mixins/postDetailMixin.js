import { dateChange } from "@/util/DateUtil";
import commentList from "@/components/comment/CommentList";
import router from "@/router";
import VueMarkdown from 'vue-markdown';
import { mapGetters, mapActions, mapMutations } from 'vuex';
const postStore = 'postStore';
const userStore = 'userStore';

const postDetail ={
    components: { commentList, VueMarkdown },
    data(){
        return{
            post : {},
        }
    },
    created(){
        this.getPostDetailCall();
    },
    mounted(){
        if(this.$route.query.comment){
            document.getElementById('comment-area').scrollIntoView();
        }
        else{
            window.scrollTo(0, 0);
        }
    },
    methods: {
        ...mapActions(postStore, ['getPostDetail', 'recommendPost', 'deletePost']),
        ...mapMutations(userStore, ['checkLoginWithModal']),
        dateConvert(date){
            return dateChange(date);
        },
        getPostDetailCall(){
            const postNo = this.$route.params.postNo;
            this.getPostDetail({postNo})
            .then((data) =>{
                this.post = data;
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        },
        recommendPostCall(){
            this.checkLoginWithModal()
            if(this.isLogined){
                const postNo = this.$route.params.postNo;
                this.recommendPost({postNo})
                .then(data => {
                    if(data.success){
                        if(data.data.process === "add"){
                            this.post.recommend = true;
                            this.post.recommendCount++;
                        } 
                        else{
                            this.post.recommend = false;
                            this.post.recommendCount--;
                        }
                    }
                    else{
                        console.log(data.message);
                    }
                })
                .catch(({message}) => {
                    console.log("error message : ", message);
                });
            }
        },
        deletePostCall(){
            const postNo = this.$route.params.postNo;
            const category = this.post.categoryName.toLowerCase();
            this.deletePost({postNo})
            .then(data => {
                if(data.success){
                    router.push({ name: `${category}List`});
                }
                else{
                    console.log(data.message);
                }
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        }
    },
    computed: {
        ...mapGetters(userStore, ['isLogined', 'getUserNo'])
    }
} 
export { postDetail }