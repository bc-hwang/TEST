import { clickTag } from "@/util/MouseUtil"
import debounce from "lodash/debounce";
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex';
const tagCategoryStore = 'tagCategoryStore';
const tagSearch = {
    data(){
        return{
            tagList : [],
            tagActive : [],
            searchInput : '',
            tagPage : 1,
            apiFinished : true,
            endPageNo : 0,
        }
    },
    created(){
        this.initTagSelected({});
        this.initTag();
    },
    methods: {
        ...mapMutations(tagCategoryStore, ['setTag', 'initTagSelected']),
        ...mapActions(tagCategoryStore, ['getTag']),
        enterTag(e){
            this.clickTag(e);
        },
        moveKeyDown(index){
            let target = index + 1;
            if(target <= this.tagList.length){
                this.$refs["tag"+target][0].focus();
            }
        },
        moveKeyUp(index){
            let target = index - 1;
            if(target > 0){
                this.$refs["tag"+target][0].focus();
            }
            if(target === 0){
                this.$refs["tag"+target].focus();
            }
        },
        initTag(){
            this.tagPage = 1;
            this.tagList = [];
            this.getTagBySearch(this.searchInput);
        },
        handleScroll(e){
            if (e.target.scrollHeight - e.target.offsetHeight - e.target.scrollTop <= 1) {
                if(this.apiFinished && this.tagPage < this.endPageNo){
                    this.tagPage++;
                    this.apiFinished = false;
                    this.getTagByScroll(this.searchInput);
                }
            }
        },  
        clickTag(e){
            let tag = clickTag(e);
            this.setTag({tag});
        },
        getTagByScroll(val){
            const keyword = val;
            const page = this.tagPage;
            this.getTag({keyword, page})
             .then((data) => {
                 this.endPageNo = data.endPageNo;
                 this.tagList = this.tagList.concat(data.tagList);
                 this.getSelectedTagInSide();
                 this.apiFinished = true;
             })
            .catch(({message}) => {
                console.log("error message : ", message);
             });
        },
        getTagBySearch(val){
            const keyword = val;
            const page = this.tagPage;
            this.getTag({keyword, page})
             .then((data) => {
                 this.endPageNo = data.endPageNo;
                 this.tagList = data.tagList;
                 this.getSelectedTagInSide();
             })
            .catch(({message}) => {
                console.log("error message : ", message);
             });
        },
        getSelectedTagInSide(){
            const tagSelected = this.getTagSelected;
            this.tagActive = [];
            this.tagList.forEach(element => {
                this.tagActive[element.tagNo] = false;
            });
            this.tagList.forEach(tagInMenu => {
                tagSelected.forEach(tagSelec => {
                    if(tagInMenu.tagNo == tagSelec.tagNo){
                       this.tagActive[tagInMenu.tagNo] = true;
                    }
                });
            });
        },
    },
    computed: {
        ...mapState(tagCategoryStore, ['tagSelected', 'categorySelected']),
        ...mapGetters(tagCategoryStore,['getTagSelected']),
    },
    watch: {
        searchInput: debounce(function(){
            this.initTag();
        }, 500),
        categorySelected: function(){
            this.initTag();
        },
        tagSelected: function(){
            this.getSelectedTagInSide();
        }
    }
} 
export { tagSearch }