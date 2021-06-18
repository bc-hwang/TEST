const hour = 1000 * 60 * 60;
const day = hour * 24;
const month = day * 30;
const year = month *12;
const dateChange = function (date){
    let now = new Date();
    let postDate = new Date(date);
    let diff = now - postDate;
    if(diff > 0 && diff < hour){
        return `${parseInt(diff/1000/60)}분전`;
    }
    if(diff >= hour && diff < day){
        return `${parseInt(diff/1000/60/60)}시간전`
    }
    if(diff >= day && diff < month){
        return `${parseInt(diff/1000/60/60/24)}일전`
    }
    if(diff >= month && diff < year){
        return `${parseInt(diff/1000/60/60/24/30)}달전`
    }
    if(diff >= year){
        return `${parseInt(diff/1000/60/60/24/30/12)}년전`
    }
}

export { dateChange }