var ryapi = {};
ryapi.basePath = "";
ryapi.token = "";

ryapi.lastActive = Date();

// 调用
ryapi.invock = function (url, data, callback, status) {
    url = ryapi.getUrl(url);
    // befor invock
    $.post(url, data, function (rult) {
        // after invock
        if (callback) {
            try {
                callback(new ryapi.invockValue(rult), status);
            } catch (ex) {
                console.log(ex.message);
                alert(ex.message);
            }
        }
    }, "json");
}

ryapi.invockValue = function (apiRult) {
    this.apiRult = apiRult;
}
ryapi.invockValue.prototype.apiRult = {};
ryapi.invockValue.prototype.endInvock = function () {
    if (this.apiRult.code != 0) {
        var ex = new Error();
        ex.message = this.apiRult.message;
        ex.name = this.apiRult.code;
        throw ex;
    }
    return this.apiRult.value;
}

// 生成 api 访问的 url
ryapi.getUrl = function (url) {
    url = ryapi.basePath + url;
    if (url.indexOf("?") < 0) {
        url += "?";
    }
    // var token =ryapi.getToken();
    //
    // if (token) {
    //     url += "&_token=" + token;
    // }

    return url;
}

// 获取最近的 token
// ryapi.getToken = function(){
// 	if(euc && euc.getTop().euc.config.debug){
// 		return euc.getTop().euc.config.debug_token;
// 	}
//
// 	if(euc && euc.getTop().ryapi.token){
// 		return euc.getTop().ryapi.token;
// 	}
//
// 	if(ryapi.token){
// 		return ryapi.token;
// 	}
//
// 	return "";
// }
//
// ryapi.getValue = function(data)
// {
//     return data.Value;
// }
//
// /* 结合 euc */
// if(typeof euc != 'undefined'){
// 	euc.ryapi = ryapi;
// }
