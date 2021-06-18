const validCheck = {
    data(){
        return{
            validLog : [],
        }
    },
    methods: {
        emptyValid(value, valueName){
            if(value === "" || value === null){
                let str = `${valueName}이/가 비어있습니다.`
                this.validLog.push(str);
            }
        },
        initValidLog(){
            this.validLog = [];
        }
    },
} 
export { validCheck }