// 아이디 적는 부분 한글 입력 방지
function noKr() {
    $("#id").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[ \{\}\[\]\/?.,;:|\)*~`!^\-+┼<>@\#$%&\'\"\\\(\=]/g, ''));
    });
};


// 사업자 체크 확인
var chk = false;

function chkShop() { // 사업자 체크 여부를 확인하기 위한 펑션
    if (chk == false) {
        chk = true;
    } else {
        chk = false;
    }
}

// 로그인 누르면 실행되는 펑션
function login() {
    if (chk == false) { // 사업자 체크가 안돼있을때 실행
        userLogin();
    } else { // 사업자 체크가 돼있을때 실행
        shopLogin();
    }
}


// 유저 로그인
function userLogin() {
    id = $('#id').val()
    pwd = $('#pwd').val()
    // 비밀번호를 날리기위한 pwd1
    pwd1 = $('#pwd');

    $.ajax({
        url: "./userLogin",
        type: "post",
        data: {userId: id, userPw: pwd, apointUserId: id},
        success: function (data) {
            if (data == 0) {
                // 기존 화면을 hide()
                // $('#Login').hide();
                // $('#Login').removeClass('active');
                //
                // $('#Sucess').show();
                // $('#Sucess').addClass('active');
                // $('#Sucess').focus();
                swal("로그인 실패", "다시 입력해주세요", 'warning');
                pwd1.val('');
            } else {
                swal('로그인 성공!', data.userName + "님 로그인되었습니다.", 'success').then(function () {
                    location.href = "/main";
                });
            }
        },
        error: function () {
            alert("통신 실패");
        }
    })
}

// 사업자 로그인
function shopLogin() {
    id = $('#id').val()
    pwd = $('#pwd').val()
    // 비밀번호를 날리기위한 pwd1
    pwd1 = $('#pwd');

    $.ajax({
        url: "./shopLogin",
        type: "post",
        data: {shopId: id, shopPw: pwd},
        success: function (data) {
            if (data == 0) {
                swal("로그인 실패", "다시 입력해주세요", 'warning');
                pwd1.val('');
            } else {
                swal('로그인 성공!', data.shopName + "님 로그인되었습니다.", 'success').then(function () {
                    location.href = "/main";
                });
            }
        },
        error: function () {
            alert("통신 실패");
        }
    })
}