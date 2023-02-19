package com.example.smp_1.service;

import com.example.smp_1.commons.FileUtils;
import com.example.smp_1.dto.*;
import com.example.smp_1.mapper.promiseHairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class promiseHairServiceImpl implements promiseHairService {
    @Autowired
    private promiseHairMapper phMapper;

    @Autowired
    private FileUtils fileUtils;


    //    유저 회원가입
    @Override
    public void signUser(userDto userDto) throws Exception {
        phMapper.signUser(userDto);
    }

    //    유저 아이디 중복체크
    @Override
    public int checkUserId(String userId) {
        int cnt = phMapper.checkUserId(userId);
//        System.out.println(cnt);
        return cnt;
    }

    //    유저 전화번호 중복체크
    @Override
    public int checkUserPh(String userPh) {
        int cnt = phMapper.checkUserPh(userPh);
//        System.out.println(cnt);
        return cnt;
    }

    //    유저 이메일 중복체크
    @Override
    public int checkUserMail(String userMail) {
        int cnt = phMapper.checkUserMail(userMail);
//        System.out.println(cnt);
        return cnt;
    }

    //    유저 로그인
    @Override
    public userDto checkUserLogin(String id, String pw) {
        userDto userdto = phMapper.checkUserLogin(id, pw);
        return userdto;
    }

    // 유저 마이페이지 수정
    public void updateUserInfo(userDto userDto) {
        phMapper.updateUserInfo(userDto);
        phMapper.chaneUserInfo(userDto);
    }

    //    세션 최신화
    @Override
    public userDto changeUserSession(userDto userDto) {
        return phMapper.changeUserSession(userDto);
    }

    //    Shop 마이페이지 수정
    @Override
    public void updateShopInfo(shopDto shopDto) {
        phMapper.updateShopInfo(shopDto);
        phMapper.changeShopInfo(shopDto);
        phMapper.changeShopInfo2(shopDto);
        phMapper.changeShopInfo3(shopDto);
    }

    @Override
    public shopDto changeShopSession(shopDto shopDto) {
        return phMapper.changeShopSession(shopDto);
    }

    //    Owner 회원가입
    @Override
    public void signOwner(shopDto shopDto) throws Exception {
        phMapper.signOwner(shopDto);
    }

    //    Shop 아이디 중복체크
    @Override
    public int checkShopId(String shopId) {
        int cnt = phMapper.checkShopId(shopId);
        return cnt;
    }

    //    가게번호 중복체크
    @Override
    public int checkShopTel(String shopTel) {
        int cnt = phMapper.checkShopTel(shopTel);
        return cnt;
    }

    //    OwnerPh 중복체크
    @Override
    public int checkOwnerPh(String ownerPh) {
        int cnt = phMapper.checkOwnerPh(ownerPh);
        return cnt;
    }

    //    Owner 이메일 중복체크
    @Override
    public int checkOwnerMail(String ownerMail) {
        int cnt = phMapper.checkOwnerMail(ownerMail);
        return cnt;
    }

    //    Shop 로그인
    @Override
    public shopDto checkShopLogin(String userId, String userPw) {
        shopDto shopdto = phMapper.checkShopLogin(userId, userPw);
        return shopdto;
    }

    @Override
    public void insertAppointment(apointDto apointdto) {
        phMapper.insertAppointment(apointdto);
    }

    //    shopName 뿌리기
    @Override
    public String[] selectShopName() {
        String[] shopName = phMapper.selectShopName();
        return shopName;
    }

    //    예약 확인
    @Override
    public apointDto apointCheck(String apointUserId) {
        return phMapper.apointCheck(apointUserId);
    }


    //    유저 모든 예약 가져오기
    @Override
    public List<apointDto> getUserApoints(String apointUserId) {
        return phMapper.getUserApoints(apointUserId);
    }

    //    샵 모든 예약 가져오기
    @Override
    public List<apointDto> getShopApoints(String apointShop) {
        return phMapper.getShopApoints(apointShop);
    }

    //    디자이너 추가
    @Override
    public void insertDesigner(designerDto designerDto) throws Exception {
        phMapper.insertDesigner(designerDto);
    }

    @Override
    public shopDto getShopInfo(String shopName) {
        return phMapper.getShopInfo(shopName);
    }

    @Override
    public List<designerDto> getDesignerInfo(String designerShop) {
        return phMapper.getDesignerInfo(designerShop);
    }

    // 리뷰 진입 페이지
    @Override
    public List<reviewDto> selectReviewDto() throws Exception {
        return phMapper.selectReviewDto();
    }

    @Override
    public void insertReview(reviewDto reviewDto) throws Exception {
        phMapper.insertReview(reviewDto);
    }

    //    디자이너 정보 가져오기
    @Override
    public designerDto postDesignerInfo(String designerName, String designerShop) {
        return phMapper.postDesignerInfo(designerName, designerShop);
    }

    //    예약 바꼈을때 세션 최신화
    @Override
    public apointDto changeApointSession(apointDto apointdto) {
        return phMapper.changeApointSession(apointdto);
    }

    //    디자이너 목록 가져옴
    @Override
    public List<designerDto> designerList(String apointShop) {
        return phMapper.designerList(apointShop);
    }

    //    디자이너 정보 가져옴
    @Override
    public designerDto designerInfo(String designerName, String designerShop) {
        return phMapper.designerInfo(designerName, designerShop);
    }

    //    디자이너 정보 수정
    @Override
    public void designerUpdate(designerDto designerDto) throws Exception {
        phMapper.designerUpdate(designerDto);
        phMapper.changeDesignerInfo(designerDto);
    }

    //    예약 정보 가져옴
    @Override
    public List<apointDto> getDesignerApoint(String apointDesigner, String apointShop, String apointDate) {
        return phMapper.getDesignerApoint(apointDesigner, apointShop, apointDate);
    }

    //    예약 취소
    @Override
    public void apointCancel(String apointDesigner, String apointShop, String apointDate, String apointTime, String apointUserId) {
        phMapper.apointCancel(apointDesigner, apointShop, apointDate, apointTime, apointUserId);
    }

    @Override
    public apointDto getApoints(String apointDesigner, String apointShop, String apointDate, String apointTime) {
        return phMapper.getApoints(apointDesigner, apointShop, apointDate, apointTime);
    }

    @Override
    public List<reviewDto> getReview(String shopName) {
        return phMapper.getReview(shopName);
    }
}
