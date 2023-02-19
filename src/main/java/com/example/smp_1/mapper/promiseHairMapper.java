package com.example.smp_1.mapper;


import com.example.smp_1.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface promiseHairMapper {
    //    유저 회원가입
    public void signUser(userDto userDto) throws Exception;

    //    유저 아이디 중복체크
    public int checkUserId(String userId);

    //    유저 전화번호 중복체크
    public int checkUserPh(String userPh);

    //    유저 이메일 중복체크
    public int checkUserMail(String userMail);

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

    //    유저 로그인
    public userDto checkUserLogin(String userId, String userPw);

    // 유저 정보 업데이트
    public void updateUserInfo(userDto userDto);

    //    샵 로그인
    public shopDto checkShopLogin(String shopId, String shopPw);

    //    예약
    void insertAppointment(apointDto apointdto);

    //    Shop Name을 뿌리기 위함
    String[] selectShopName();

    //    유저 세션 최신화
    userDto changeUserSession(userDto userDto);

    //    예약 확인
    apointDto apointCheck(String id);

    //    유저 모든 예약 가져오기
    List<apointDto> getUserApoints(String apointUserId);

    //    샵 네임 모든 예약 갸져오기
    List<apointDto> getShopApoints(String apointShop);

    //    Shop 정보 업데이트
    public void updateShopInfo(shopDto shopDto);

    //    Shop 세션 최신화
    shopDto changeShopSession(shopDto shopDto);

    //    디자이너 추가
    void insertDesigner(designerDto designerDto);

    //    샵 정보
    shopDto getShopInfo(String shopName);

    //    디자이너 정보
    List<designerDto> getDesignerInfo(String designerShop);

    //    리뷰
    public List<reviewDto> selectReviewDto() throws Exception;

    void insertReview(reviewDto reviewDto) throws Exception;
    //    -----------------------------------------------

    //     유저 정보 바꿀때 다른 값도 바꾸기 위함
    void chaneUserInfo(userDto userDto);

    //    샵 정보 바꿀떄 다른 값도 바꾸기 위함
    void changeShopInfo(shopDto shopDto);

    void changeShopInfo2(shopDto shopDto);

    void changeShopInfo3(shopDto shopDto);

    //    -----------------------------------------------
//    디자이너 정보 가져오기
    designerDto postDesignerInfo(String designerName, String designerShop);

    // 예약 세션 바꿈
    apointDto changeApointSession(apointDto apointdto);

    List<designerDto> designerList(String apointShop);

    designerDto designerInfo(String designerName, String designerShop);

    void designerUpdate(designerDto designerDto);

    void changeDesignerInfo(designerDto designerDto);

    List<apointDto> getDesignerApoint(String apointDesigner, String apointShop, String apointDate);

//    예약 취소
    void apointCancel(String apointDesigner, String apointShop, String apointDate, String apointTime, String apointUserId);

    apointDto getApoints(String apointDesigner, String apointShop, String apointDate, String apointTime);

    List<reviewDto> getReview(String reShop);
}