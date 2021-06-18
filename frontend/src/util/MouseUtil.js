const clickTag = function(e){
    const tagName = e.currentTarget.textContent;
    const tagNo = e.currentTarget.id;
    let tag = {
        "tagName" : tagName,
        "tagNo" : tagNo
    };
    return tag;
}

const toggleElement = function(e, id){
    let element = document.getElementById(id);
    if(element.style.display === 'none'){
        element.style.display = "block";
        e.target.innerText = "접기"
    }
    else{
        element.style.display = 'none';
        e.target.innerText = "펴기"
    }
}
export { clickTag, toggleElement }