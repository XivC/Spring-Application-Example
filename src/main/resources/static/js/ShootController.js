function bad_auth(){
    alert("Bad authentication")
    window.location.replace("/home")
    return false
}

function check_auth(){
    let token = localStorage.getItem("token")
    console.log(token)
    if (token === null) {
        return bad_auth()
    }
    return true
}

function check_y(value){

    if (value === null || isNaN(Number(value)) || value === "") return false;
    return !(value < -3 || value > 3);



}

function check_r(value){


    return value > 0;

}

function shoot(x, y, r, callback_suc, callback_err){
    console.log("shoot" + " " + x)
    let data = JSON.stringify({
        "x": parseFloat(x),
        "y": y,
        "r": r,
    })
    console.log(data)
    return $.ajax({
        url:"api/shot",
        type:"POST",
        headers: {"Authorization": "Bearer_"+localStorage.getItem("token")},
        data: data,
        contentType:"application/json; charset=utf-8",
        success: callback_suc,
        error: callback_err
    })
}

function get_hits(callback_suc, callback_err){
    return $.ajax({
        url:"api/points",
        type:"POST",
        headers: {"Authorization": "Bearer_"+localStorage.getItem("token")},
        contentType:"application/json; charset=utf-8",
        success: callback_suc,
        error: callback_err
    })
}