package com.example.smp_1.service;


import com.example.smp_1.dto.*;

import java.util.List;

public interface promiseHairService {

//    ---------------- 유저 관련 ---------------------

    //    유저 회원가입
    public void signUser(userDto userDto) throws Exception;

    //    유저 아이디 중복체크
    public int checkUserId(String userId);

    //    유저 전화번호 중복체크
    public int checkUserPh(String userPh);

    //    유저 이메일 중복체크
    public int checkUserMail(String userMail);

    //    유저 로그인
    public userDto checkUserLogin(String userId, String userPw);

    //    -------------------------------------------------
    //    유저 정보 수정
    public void updateUserInfo(userDto userDto);

    //    유저 세션 최신화
    public userDto changeUserSession(userDto userDto);

    //    Shop 정보 수정
    public void updateShopInfo(shopDto shopDto);

    //    Shop 세션 최신화
    shopDto changeShopSession(shopDto shopDto);

//    ---------------- 사업자 관련 ---------------------

    //    Owner 회원가입
    public void signOwner(shopDto shopDto) throws Exception;

    //    Shop 아이디 중복체크
    public int checkShopId(String shopId);

    //    가게번호 중복체크
    public int checkShopTel(String shopTel);

    //    OwnerPh 중복체크
    public int checkOwnerPh(String ownerPh);

    //    Owner 이메일 중복체크
    public int checkOwnerMail(String ownerMail);

    //    샵 로그인
    public shopDto checkShopLogin(String shopId, String shopPw);

    void insertAppointment(apointDto apointdto);

    //    Shop Name 뿌리기
    String[] selectShopName();

    //    예약 확인
    apointDto apointCheck(String apointUserId);

    //    유저 예약 목록 가져오기
    List<apointDto> getUserApoints(String apointUserId);

    //    샵 예약 목록 가져오기
    List<apointDto> getShopApoints(String apointShop);

    //    디자이너 추가
    void insertDesigner(designerDto designerDto) throws Exception;

    //    샵 정보
    shopDto getShopInfo(String shopName);

    //    디자이너 정보
    List<designerDto> getDesignerInfo(String designerShop);

    //  shop  -> review 첫 페이지, 테이블 페이지
    public List<reviewDto> selectReviewDto() throws Exception;

    // reveiew 글쓰기 페이지
    public void insertReview(reviewDto reviewDto) throws Exception;

    //    디자이너 정보 가져오기
    designerDto postDesignerInfo(String designerName, String designerShop);

    apointDto changeApointSession(apointDto apointdto);

    //    디자이너 목록 뿌리기 위함
    List<designerDto> designerList(String apointShop);

    designerDto designerInfo(String designerName, String designerShop);

    void designerUpdate(designerDto designerDto) throws Exception;

    List<apointDto> getDesignerApoint(String apointDesigner, String apointShop, String apointDate);

    //    예약 취소
    void apointCancel(String apointDesigner, String apointShop, String apointDate, String apointTime, String apointUserId);

    //    예약 정보 가져오기
    apointDto getApoints(String apointDesigner, String apointShop, String apointDate, String apointTime);

    List<reviewDto> getReview(String shopName);
}
