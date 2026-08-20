var token = localStorage.getItem("token");
if (token == null) {
    location.href = "login.html"                // 没有token踢去登录页
} else {
    fetch("/getInfo", {headers: {"Authorization": token}})
        .then(function (response) {
            if (response.status === 401) {        //token无效401
                throw "登录失效"
            }
            return response.json();
        })
        .then(function (result) {
            if (result.code !== 200) {
                throw "登录失效"
            }
        })

        .catch(function () {
            localStorage.removeItem("token")  // 失败删除token
            location.href = "login.html"           // 失败返回登录页
        })
}





