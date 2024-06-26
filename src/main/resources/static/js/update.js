// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault();
    let data = $("#profileUpdate").serialize();
    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("update 성공", res)
        location.href=`/user/${userId}`;
    }).fail(error => {
        if (error.data == null) { // message만 응답
            alert(error.responseJSON.message);
        } else { // errorMap이 포함된 응답
            alert("회원정보 수정에 실패하였습니다. 원인 : " +
                JSON.stringify((error.responseJSON.data)));
        }
    });
}