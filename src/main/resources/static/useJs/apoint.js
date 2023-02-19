// 모든 값 확인 위함
$(document).ready(function () {
    $('#div-time').hide();
    $('#dp2').hide();
})
let checkList;
let frm;

$(document).ready(function () {
    checkList = [false, false, false, false, false];
    frm = $("#frm");
});

// Date 체크
function checkDate() {
    var date = $('#dp1').val();
    $('#dp2').val('');

    if (date == '') {
        checkList[0] = false;
        $('.date').css('display', 'inline-block');
        $('#div-time').hide();
        $('#dp2').hide();
    } else {
        checkList[0] = true;
        $('.date').css('display', 'none');
        $('#div-time').show();
        $('#dp2').show();
    }
}

// 중복 예약 방지
function doubleTimeCheck() {
    var date = $("#dp1").val();
    var dName = $("#apointDesigner").val();
    var dShop = $("#apointShop").val();
    $.ajax({
        url: "/doubleTimeCheck",
        type: "post",
        data: {dName: dName, dShop: dShop, date: date},
        success: function (time) {
            var timeList = [];
            for (let i = 0; i < time.length; i++) {
                timeList.push(time[i].apointTime);
            }

            // children은 자식태그까지만, find는 손자태그까지도 찾을 수 있음
            var times = $("#time").find("div");
            var str = "";
            var li_time;
            
            // 다시 보여줌
            for (let i = 0; i < times.length; i++){
                str = "li:eq(" + i + ")";
                li_time = $("#time").find(str);
                li_time.show();
            }
            
            // 중복값 안보여줌
            for (let i = 0; i < timeList.length; i++) {
                for (let j = 0; j < times.length; j++) {
                    if (timeList[i] == times[j].innerText) {
                        str = "li:eq(" + j + ")";
                        li_time = $("#time").find(str);
                        li_time.hide();
                        console.log(times[j]);
                        break;
                    }
                }
            }
        },
        errors: function () {
            alert("실패");
        }

    })
}

// Time 체크
function checkTime() {
    var time = $('#dp2').val();

    if (time == '') {
        checkList[1] = false;
        $('.time').css('display', 'inline-block');
    } else {
        checkList[1] = true;
        $('.time').css('display', 'none');
    }
}

// Cut 체크
function checkCut() {
    var cut = $('#apointCut').val();

    if (cut == ' ') {
        checkList[2] = false;
        $('.cut').css('display', 'inline-block');
    } else {
        checkList[2] = true;
        $('.cut').css('display', 'none');
    }
}

// Perm 체크
function checkPerm() {
    var perm = $('#apointPerm').val();

    if (perm == ' ') {
        checkList[3] = false;
        $('.perm').css('display', 'inline-block');
    } else {
        checkList[3] = true;
        $('.perm').css('display', 'none');
    }
}

// Color 체크
function checkColor() {
    var color = $('#apointColor').val();

    if (color == ' ') {
        checkList[4] = false;
        $('.color').css('display', 'inline-block');
    } else {
        checkList[4] = true;
        $('.color').css('display', 'none');
    }
}


// 모든 값이 체크 되면 실행
function checkAll() {

    // 값이 비었는지 확인하기 위한 리스트
    let checkVal = [$('#dp1').val(), $("#dp2").val(), $("#apointCut").val(), $("#apointPerm").val(), $("#apointColor").val()];
    // 값이 없을때를 알리기 위한 리스트
    let checkSpan = [$('.date'), $('.time'), $('.cut'), $('.perm'), $('.color')];

    let true_cnt = 0;
    let not_check = 0;

    for (let i = 0; i < checkList.length; i++) {
        if (checkList[i] == true && checkVal[i] == '해당 없음') {
            not_check++;
        }

        if (i >= 2) {
            if (checkList[i] == false && checkVal[i] == ' ') {
                checkSpan[i].css('display', 'inline-block')
            } else if (checkList[i] == true) {
                true_cnt++;
            }
        } else if (checkList[i] == false && checkVal[i] == '') {
            checkSpan[i].css('display', 'inline-block')
        } else if (checkList[i] == true) {
            true_cnt++
        }
    }
    if (not_check == 3) {
        return false;
    } else if (true_cnt == 5) {
        return true;
    } else {
        return false;
    }
}


//문서가 준비되면 제일 먼저 실행
// $(document).ready(function () {
//     $("#btn-apoint").click(function () {
//         payment(); //버튼 클릭하면 호출
//     });
// })

//버튼 클릭하면 실행
function payment() {
    var goPayment = checkAll();
    if (goPayment == true) {
        IMP.init('imp07475837');//아임포트 관리자 콘솔에서 확인한 '가맹점 식별코드' 입력
        IMP.request_pay({// param
            pg: "kakaopay.TC0ONETIME", //pg사명 or pg사명.CID (잘못 입력할 경우, 기본 PG사가 띄워짐)
            pay_method: "card", //지불 방법
            // merchant_uid: "iamport_test_id23642466433123", //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
            name: "Promise Hair", //결제창에 노출될 상품명
            amount: $('#apointPrice').val(), //금액
            buyer_email: $("#apointUserMail").val(),
            buyer_name: $("#apointUserName").val(),
            buyer_tel: $("#apointUserPh").val(),
        }, function (rsp) { // callback
            if (rsp.success) {
                alert("결제 완료");
                $('#dp2').disable = false;
                $('#apointTime').val($('#dp2').val());
                frm.submit();
            } else {
                alert("결제 취소");
            }
        });
    } else {
        alert("모두 선택해주세요");
    }
}
