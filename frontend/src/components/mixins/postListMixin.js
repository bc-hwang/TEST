import { dateChange } from "@/util/DateUtil";
import { clickTag } from "@/util/MouseUtil";
import { mapActions, mapGetters, mapMutations } from 'vuex';
const postStore = 'postStore';
const tagCategoryStore = 'tagCategoryStore';

const postList ={
    data(){
        return {
            apiFinished: true,
        }
    },
    created(){
        window.scrollTo(0, 0);
        window.addEventListener("scroll", this.handleScroll);
        this.initPage()
        
    },
    destroyed(){
        window.removeEventListener("scroll", this.handleScroll);
    },
    methods: {
        ...mapMutations(postStore, ['setPageIndex', 'clearPostList']),
        ...mapMutations(tagCategoryStore, ['setCategory', 'setTag']),
        ...mapActions(postStore, ['getPost']),
        handleScroll(){
            if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 1) {
                let index = this.getPageIndex;
                if(this.apiFinished && index < this.getPostEndPageNo){
                    index++;
                    this.apiFinished = false;
                    this.setPageIndex({index});
                    this.callGetPost();
                }
            }      
        },
        clickTag(e){
            let tag = clickTag(e);
            this.setTag({tag});
        },
        dateConvert(date){
            return dateChange(date);
        },
        initPage(){
            const category = this.category;
            const index = 1;
            this.setCategory({category});
            this.setPageIndex({index});
            this.clearPostList({});
            this.callGetPost();
        },
        callGetPost(){
            this.getPost({})
            .then(() =>{
                this.apiFinished = true;
            })
            .catch(({message}) => {
                console.log("error message : ", message);
            });
        }
    },
    computed: {
        ...mapGetters(tagCategoryStore,['getTagSelected', 'getCategorySelected']),
        ...mapGetters(postStore,['getPostList', 'getPostEndPageNo', 'getPageIndex']),
    },
} 
export { postList }