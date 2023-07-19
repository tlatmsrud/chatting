function login() {
    var nickname = $("#nickname").val();
    $.ajax({
        type : 'post',           // 타입 (get, post, put 등등)
        url : '/api/login',           // 요청할 서버url
        async : true,            // 비동기화 여부 (default : true)
        contentType : "application/json",
        data : nickname,
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
        },
        error : function(response) { // 결과 에러 콜백함수
            alert(JSON.stringify(response.responseJSON))
        }
    })
}