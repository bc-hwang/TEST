const jwtTokenName = "tk";

function unAuthorizedCheck(error, commit){
    if (error.response.data.status === 401) {
      commit('logout');
      window.alert(error.response.data.message);
      return;
    }
    else{
      window.alert(error.response.data.message);
      return;
    }
  }
  
  function getParamsWithAuth() {
    return 'Bearer ' + localStorage.getItem(jwtTokenName);
  }

  function makeTagParams(tagSelected){
    const tagsTemp = tagSelected;
    let tags = "";
    tagsTemp.forEach((element) => {
      tags += (element.tagNo + ",");
    });
    return tags;
  }

export { unAuthorizedCheck, getParamsWithAuth, makeTagParams }