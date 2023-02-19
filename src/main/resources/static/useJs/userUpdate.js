// 유효성 검사 true false 리스트
var checkList = [true, true, true, true, true];

// 입력 방지 펑션
// pwd
$(function () {
    $("#userPw").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[ \{\}\[\]\/?.,;:|\)*~`!^\-+┼<>@\#$%&\'\"\\\(\=]/g, ''));
    });
});
// 전화번호
$(function () {
    $("#userPh").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
});

// 이름 체크
function checkName() { // 간단한 체크
    var name = $('#userName').val();
    if (name == '') {
        checkList[0] = false;
        $('.checkName').css("display", "inline-block");
    } else {
        checkList[0] = true;
        $('.checkName').css("display", "none");
    }
}

//비밀 번호 수정 ===============
function upDatePw() {
    var pw = $("#userPw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    if (pw == '') { // 빈값일때
        checkList[1] = false;
        $('.pwd_already').css("display", "none");
        $('.checkPw').css("display", "inline-block");
        $('.pwd_not').css("display", "none");
        $('.pwd_ok').css("display", "none");
        $('.pwd_space').css("display", "none");
        $('#userPw').focus();
    } else if (pw.length < 8 || pw.length > 16 || (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) { // 8 ~ 16자리이고, 영문, 숫자, 특수문자 조합
        checkList[1] = false;
        $('.pwd_already').css("display", "none");
        $('.checkPw').css("display", "none");
        $('.pwd_not').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_space').css("display", "none");
        $('#userPw').focus();
    } else if (pw.search(/\s/) != -1) { // 공백이 있을때
        checkList[1] = false;
        $('.pwd_already').css("display", "none");
        $('.checkPw').css("display", "none");
        $('.pwd_space').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_not').css("display", "none");
        $('#userPw').focus();
    } else { // 사용 가능한 비밀번호
        checkList[1] = true;
        $('.pwd_already').css("display", "none");
        $('.checkPw').css("display", "none");
        $('.pwd_ok').css("display", "inline-block")
        $('.pwd_space').css("display", "none");
        $('.pwd_not').css("display", "none")
    }
}

//======================

// 전화번호 체크
function upDatePh() {
    var ph = $('#userPh').val(); // ph 저장
    var regExp = /^(010)[0-9]{4}[0-9]{4}$/;
    $.ajax({
        url: './userPhCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {userPh: ph},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (ph == '') { // 값이 비어있을때
                checkList[2] = false;
                $('.updatePn').css("display", "inline-block");
                $('.ph_not').css("display", "none");
                $('.ph_already').css("display", "none");
                $('.ph_ok').css("display", "none");
                $('#userPh').focus();
            } else if (!regExp.test(ph)) { // 유효성 체크
                checkList[2] = false;
                $('.updatePn').css("display", "none");
                $('.ph_not').css("display", "inline-block");
                $('.ph_already').css("display", "none");
                $('.ph_ok').css("display", "none");
                $('#userPh').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 전화번호
                checkList[2] = false;
                $('.updatePn').css("display", "none");
                $('.ph_already').css("display", "inline-block");
                $('.ph_ok').css("display", "none");
                $('.ph_not').css("display", "none");
                $('#userPh').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 전화번호
                checkList[2] = true;
                $('.updatePn').css("display", "none");
                $('.ph_ok').css("display", "inline-block");
                $('.ph_already').css("display", "none");
                $('.ph_not').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

//이메일 체크
function checkMail() {
    var mail = $('#userMail').val();
    var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    $.ajax({
        url: './userMailCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {userMail: mail},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (mail == '') { // 값이 비어있을때
                checkList[3] = false;
                $("#btn-sendMail").attr("disabled", true);
                $('.checkMail').css("display", "inline-block");
                $('.mail_not').css("display", "none");
                $('.mail_already').css("display", "none");
                $('.mail_ok').css("display", "none");
                $('#userMail').focus();
            } else if (regExp.test(mail) == false) { // 유효성 체크
                checkList[3] = false;
                $("#btn-sendMail").attr("disabled", true);
                $('.checkMail').css("display", "none");
                $('.mail_not').css("display", "inline-block");
                $('.mail_already').css("display", "none");
                $('.mail_ok').css("display", "none");
                $('#userMail').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 이메일
                checkList[3] = false;
                $("#btn-sendMail").attr("disabled", true);
                $('.checkMail').css("display", "none");
                $('.mail_already').css("display", "inline-block");
                $('.mail_ok').css("display", "none");
                $('.mail_not').css("display", "none");
                $('#userMail').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
                checkList[3] = true;
                $("#btn-sendMail").attr("disabled", false);
                $('.checkMail').css("display", "none");
                4
                $('.mail_ok').css("display", "inline-block");
                $('.mail_already').css("display", "none");
                $('.mail_not').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

// 이메일 인증번호 발송

var emailWord = 0;

function sendMail() {
    var email = $("#userMail").val();
    $.ajax({
        url: 'userEmailSend',
        type: 'post',
        data: {userMail: email},
        success: function (data) {
            emailWord = data;
            $('#userWord').attr("disabled", false);
            alert("인증번호를 보냈습니다 확인해주세요.");
            // console.log(emailWord);
        }, error: function () {
            alert("이메일이 올바르지 않습니다. 다시 입력해주세요.");
        }
    })
}

// 이메일 인증번호
function checkWord() {
    var word = $('#userWord').val();
    if (word == '') { // 값이 비어있을때
        checkList[4] = false;
        $(".checkWord").css("display", "inline-block");
        $(".word_not").css("display", "none");
        $(".word_ok").css("display", "none");
    } else if (word != emailWord) { // 인증번호가 다를때
        checkList[4] = false;
        $(".checkWord").css("display", "none");
        $(".word_not").css("display", "inline-block");
        $(".word_ok").css("display", "none");
    } else if (word == emailWord) { // 인증번호가 같을때
        checkList[4] = true;
        $(".checkWord").css("display", "none");
        $(".word_not").css("display", "none");
        $(".word_ok").css("display", "inline-block");
    }
};

// changeEmail
function changeEmail() {
    checkList[4] = false;
}


// 모두 유효성 검사를 통과했을때 로그인 허용 해주기 위함
function checkAll() {
    // 회원가입 버튼
    let update = $('#btn-update');
    // 값이 비었는지 확인하기 위한 리스트
    let checkVal = [$("#userPw").val(), $("#userName").val(), $("#userPh").val(), $("#userMail").val(), $("#userWord").val]
    // 값이 없을때를 알리기 위한 리스트
    let checkSpan = [$('.checkPw'), $('.checkName'), $('.checkPh'), $('.checkMail'), $('.checkWord')];

    let true_cnt = 0;

    for (let i = 0; i < checkList.length; i++) {
        if (i == 4) {
            if (checkList[i] == false) {
                checkSpan[i].css('display', 'inline-block')
            } else {
                true_cnt++
            }
        } else if (checkList[i] == false && checkVal[i] == '') {
            checkSpan[i].css('display', 'inline-block')
        } else if (checkList[i] == true) {
            true_cnt++
        }
    }
    console.log(true_cnt);
    if (true_cnt == 5) {
        update.attr("type", "submit");
        alert("수정완료");
        update.click(true);
    }

}