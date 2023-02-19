function getInfo(obj) {
    var par = obj.parent();
    let dName = par.find('li:eq(1)').text().replace("이름 : ", "");
    dName = dName.trim();
    let dShop = par.find('input:eq(0)').val();
    $.ajax({
        url: "/appointment",
        type: "post",
        data: {dName: dName, dShop: dShop},
        success: function (req) {
            if (req.status == "success") {
                location.href = "/appointSign";
            } else {
                alert(req.data);
                location.href = "/main";
            }
        },
        errors: function () {
            alert("에러");
        }
    })
}

/**
 * 샵 정보 가져옴, 디자이너 정보도 가져옴
 */
function getShopInfo(text) {
    var shopName = text;
    $.ajax({
        url: "/getShopInfo",
        type: "post",
        data: {shopName: shopName},
        success: function (data) {
            var shopInfo = data[0]
            var designerInfo = data[1]

            var divShop = $("#div-shopInfo");
            var divDesigner = $("#div-designerInfo");

            // 샵 정보 추가
            // 이미 있을경우 비움
            $(divShop).empty();
            // 요소 추가
            $(divShop).append("<h2 class=\"major\">주소</h2>");
            $(divShop).append("<ul>");
            $(divShop).append("<li> 우편번호 : " + shopInfo.shopAddrNum + "</li>");
            $(divShop).append("<li> 도로명 : " + shopInfo.shopAddrRoad + "</li>");
            $(divShop).append("<li> 지번 : " + shopInfo.shopAddrJibun + "</li>");
            $(divShop).append("<li> 상세주소 : " + shopInfo.shopAddrDetail + "</li>");
            $(divShop).append("</ul>");

            // 디자이너 정보 추가
            // 이미 있으면 비워줌
            let divDesignerChild = '';
            $(divDesigner).empty();

            for (let i = 0; i < designerInfo.length; i++) {
                if (i == 0) {
                    divDesignerChild += "<h2 class=\"major\">디자이너</h2>";
                }
                divDesignerChild += "<ul>";
                divDesignerChild += "<li> 직급 : " + designerInfo[i].designerPosition + "</li>";
                divDesignerChild += "<li> 이름 : " + designerInfo[i].designerName + "</li>";
                divDesignerChild += "<input hidden value='" + designerInfo[i].designerShop + "'/>";
                divDesignerChild += "<li> 예약금 : " + designerInfo[i].apointPrice + "</li>";
                divDesignerChild += "<li> 한줄 소개 : " + designerInfo[i].designerIntroduce + "</li>";
                divDesignerChild += "<li value='테스트' onclick= 'getInfo($(this))'><button type=\"button\" class=\"btn btn-sm\" id=\"btn-apoint\">디자이너 선택(예약)</button></li>";
                divDesignerChild += "</ul>";
                divDesigner.html(divDesignerChild);
            }
        },
        errors: function () {
            alert('통신 실패');
        }
    })
}

function getReview(text) {
    var shopName = text;
    $.ajax({
        url: "/getReview",
        type: "post",
        data: {shopName: shopName},
        success: function (data) {
            var reviewList = data;
            var review = $("#div-review");

            let divReview = '';
            $(review).empty();
            $(review).append("<h2 class=\"major\">리뷰</h2>");
            for (let i = 0; i < reviewList.length; i++) {
                if (i == 0) {
                    divReview += "<h2 class=\"major\">리뷰</h2>";
                }
                divReview += "<ul>";
                divReview += "<li> 작성자 : " + reviewList[i].reWriter + "</li>";
                divReview += "<li> 작성일자 : " + reviewList[i].reCreate + "</li>";
                divReview += "<li> 디자이너 : " + reviewList[i].reDesigner + "</li>";
                divReview += "<li> 내용 : " + reviewList[i].reContent + "</li>";
                divReview += "</ul>";
                review.html(divReview);
            }
        },
        errors: function () {
            alert('통신 실패');
        }
    })

}