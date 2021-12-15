function validate(login, password){
    if (login.length < 3) {alert("Minimal login length is 3"); return false;}
    if (password.length < 8) {alert("Minimal password length is 8"); return false;}
    return true;
}

function  perform_login (login, password, callback_suc, callback_err){
    let data = {
        'login': login,
        'password': password
    }
    let url = "api/auth/login"
    return $.ajax({
        url:url,
        type:"POST",
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: callback_suc,
        error: callback_err
    })
}

function  perform_register (login, password, callback_suc, callback_err){
    let data = {
        'login': login,
        'password': password
    }
    let url = "api/auth/register"
    return $.ajax({
        url:url,
        type:"POST",
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        success: callback_suc,
        error: callback_err
    })
}