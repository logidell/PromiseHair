package com.example.smp_1.controller;


import com.example.smp_1.dto.*;
import com.example.smp_1.service.emailService;
import com.example.smp_1.service.promiseHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class webController {
    @Autowired
    private promiseHairService phService;

    @Autowired
    private emailService emailService;


    //    유저 이메일 인증
    @PostMapping("/userEmailSend")
    @ResponseBody
//    ajax를 통해서 값을 받고 거기로 이메일을 보냄
    public String userEmailSend(@RequestParam("userMail") String email) throws Exception {

        String data = emailService.sendSimpleMessage(email);

//        ajax로 값을 보내줘서 비교하게 함
        return data;
    }

    //    Shop 이메일 인증
    @PostMapping("/ownerEmailSend")
    @ResponseBody
//    ajax를 통해서 값을 받고 거기로 이메일을 보냄
    public String ownerEmailSend(@RequestParam("ownerMail") String email) throws Exception {

        String data = emailService.sendSimpleMessage(email);

//        ajax로 값을 보내줘서 비교하게 함
        return data;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    //    메인화면
    @RequestMapping("/main")
    public String main(Model model) {
        String[] shopName = phService.selectShopName();
//        Shop 버튼 눌렀을때 화면에 ShopName 뿌리기 위함
        model.addAttribute("shopName", shopName);
//        System.out.println(shopName);
        return "main";
    }

//    ---------------- 유저 관련 ---------------------

    //    유저 회원가입 창으로 가기
    @RequestMapping("/signUpUser")
    public String signUpUser() {
        return "signUpUser";
    }

//    중복체크를 위한 ajax 컨트롤러

    //    유저 아이디 중복체크
    @PostMapping("/userIdCheck")
    @ResponseBody
    public int userIdCheck(@RequestParam("userId") String id) {
        int cnt = phService.checkUserId(id);
        return cnt;
    }

    //    유저 전화번호 중복체크
    @PostMapping("/userPhCheck")
    @ResponseBody
    public int userPhCheck(@RequestParam("userPh") String ph) {
        int cnt = phService.checkUserPh(ph);
        return cnt;
    }

    //    유저 이메일 중복체크
    @PostMapping("/userMailCheck")
    @ResponseBody
    public int userMailCheck(@RequestParam("userMail") String mail) {
        int cnt = phService.checkUserMail(mail);
        return cnt;
    }

    //    유저 회원가입
    @RequestMapping("/signUser")
    public String insertUser(userDto userDto) throws Exception {
        phService.signUser(userDto);
        return "redirect:main";
    }

    //    유저 로그인
    @PostMapping("/userLogin")
    @ResponseBody
    public Object userLogin(@RequestParam("userId") String id, @RequestParam("userPw") String pw, @RequestParam("apointUserId") String apointId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        userDto user = phService.checkUserLogin(id, pw);

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        session.setAttribute("user", user);

        if (user == null) {
            return 0;
        } else {
            apointDto apointDto = phService.apointCheck(apointId);
            session.setAttribute("apoint", apointDto);
//            System.out.println(session.getAttribute("apoint"));
            return user;
        }
    }

    //    유저 마이페이지
    @RequestMapping("/userMypage")
    public String userMypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("user") == null) {
            model.addAttribute("message", "접근 권한이 없습니다.");
            return "myPageUser";
        }
        return "myPageUser";
    }

    //    유저 수정페이지로 이동
    @RequestMapping(value = "/myPageUserUpdate")
    public String updateUserPage() throws Exception {

        return "myPageUserUpdate";
    }

    //    유저 정보 수정
    @RequestMapping(value = "/userUpdate")
    public String userUpdate(userDto userDto, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        phService.updateUserInfo(userDto);
        if (session.getAttribute("user") != null) {
            userDto user = phService.changeUserSession(userDto);
            session.setAttribute("user", user);
        }
        return "redirect:userMypage";
    }


//    ---------------- 사업자 관련 ---------------------

    //    사업자 회원가입 창으로 가기
    @RequestMapping("/signUpOwner")
    public String signUpOwner() {
        return "signUpOwner";
    }

//    중복체크를 위한 ajax 컨트롤러

    //    Shop 아이디 중복체크
    @PostMapping("/shopIdCheck")
    @ResponseBody
    public int shopIdCheck(@RequestParam("shopId") String id) {
        int cnt = phService.checkShopId(id);
        return cnt;
    }

    //    Shop 번호 중복체크
    @PostMapping("/shopTelCheck")
    @ResponseBody
    public int shopTelCheck(@RequestParam("shopTel") String tel) {
        int cnt = phService.checkShopTel(tel);
        return cnt;
    }

    //    Owner 번호 중복체크
    @PostMapping("/ownerPhCheck")
    @ResponseBody
    public int ownerPhCheck(@RequestParam("ownerPh") String ph) {
        int cnt = phService.checkOwnerPh(ph);
        return cnt;
    }

    //    Owner 이메일 중복체크
    @PostMapping("/ownerMailCheck")
    @ResponseBody
    public int ownerMailCheck(@RequestParam("ownerMail") String mail) {
        int cnt = phService.checkOwnerMail(mail);
        return cnt;
    }

    //    Owner 회원가입
    @RequestMapping("/signOwner")
    public String insertOwner(shopDto shopDto) throws Exception {
        phService.signOwner(shopDto);
        return "redirect:/main";
    }

    //    Shop 로그인
    @PostMapping("/shopLogin")
    @ResponseBody
    public Object shopLogin(@RequestParam("shopId") String id, @RequestParam("shopPw") String pw, HttpServletRequest request) {

        HttpSession session = request.getSession();

        shopDto shop = phService.checkShopLogin(id, pw);

//        shop 세션이 이미 존재 할 경우 삭제하기 위함
        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        session.setAttribute("shop", shop);

        if (shop == null) {
            return 0;
        } else {
            return shop;
        }
    }

    //    Shop 마이페이지
    @RequestMapping("/shopMypage")
    public String shopMypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("shop") == null) {
            model.addAttribute("message", "접근 권한이 없습니다.");
            return "myPageShop";
        }
        return "myPageShop";
    }

    //    Shop 수정페이지로 이동
    @RequestMapping(value = "/myPageShopUpdate")
    public String updateShopPage() throws Exception {

        return "myPageShopUpdate";
    }

    //    Shop 정보 수정
    @RequestMapping(value = "/shopUpdate")
    public String shopUpdate(shopDto shopDto, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        phService.updateShopInfo(shopDto);
        if (session.getAttribute("shop") != null) {
            shopDto shop = phService.changeShopSession(shopDto);
            session.setAttribute("shop", shop);
        }
        return "redirect:shopMypage";
    }

    @RequestMapping("/signUpSelect")
    public String signUpSelect() {
        return "signUpSelect";
    }

    //    예약 화면
    @RequestMapping("/apoint")
    public String apoint(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("user") == null) {
            model.addAttribute("message", "로그인 후 이용해주세요.");
            return "main";
        }
        return "appointment";
    }

    //    ---------------- 예약 관련 ---------------------
    @PostMapping("/appointment")
    @ResponseBody
    public Object appointment(@RequestParam("dName") String dName, @RequestParam("dShop") String dShop, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Map<String, Object> map = new HashMap<>();
//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("user") == null) {
            map.put("status", "error");
            map.put("data", "로그인 후 이용해주세요");
        } else {
            designerDto dInfo = phService.postDesignerInfo(dName, dShop);
            map.put("status", "success");
            if (session.getAttribute("dInfo") != null) {
                session.removeAttribute("dInfo");
            }
            session.setAttribute("dInfo", dInfo);
        }
        return map;
    }

    @RequestMapping("/insertAppointment")
    public String insertAppointment(apointDto apointdto, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        phService.insertAppointment(apointdto);
        apointDto apointDto = phService.changeApointSession(apointdto);
        session.setAttribute("apoint", apointDto);
        return "redirect:main";
    }

    @RequestMapping("/appointSign")
    public String appointSign() {
        return "appointment";
    }

    //    로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:main";
    }

    //    유저 예약목록 가져오기
    @PostMapping("/getUserApoints")
    @ResponseBody
    public Object getUserApoints(@RequestParam("apointUserId") String id, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<apointDto> apointDto = phService.getUserApoints(id);
        if (session.getAttribute("apoints") != null) {
            session.removeAttribute("apoints");
        }
        session.setAttribute("apoints", apointDto);
        System.out.println(session.getAttribute("apoints"));

        return apointDto;
    }

    //   샵 예약목록 가져오기
    @PostMapping("/getShopApoints")
    @ResponseBody
    public Object getShopApoints(@RequestParam("apointShop") String name, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<apointDto> apointDto = phService.getShopApoints(name);
        List<designerDto> designerDto = phService.designerList(name);
        if (session.getAttribute("apoints") != null) {
            session.removeAttribute("apoints");
        }
        if (session.getAttribute("designers") != null) {
            session.removeAttribute("designers");
        }
        session.setAttribute("apoints", apointDto);
        session.setAttribute("designers", designerDto);

        return apointDto;
    }

    //    디자이너 추가 페이지로 이동
    @RequestMapping("/designerAdd")
    public String designerAdd() {
        return "signUpDesigner";
    }

    //    디자이너 추가
    @RequestMapping("/insertDesigner")
    public String insertDesigner(designerDto designerDto, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        phService.insertDesigner(designerDto);
        if (session.getAttribute("designers") != null) {
            session.removeAttribute("designers");
        }
        return "redirect:main";
    }

    //    샵 정보 가져옴, 디자이너 정보도 가져옴
    @PostMapping("/getShopInfo")
    @ResponseBody
    public List<Object> getShopInfo(@RequestParam("shopName") String shopName) {

        List<Object> infoList = new ArrayList<>();
        infoList.add(phService.getShopInfo(shopName));
        infoList.add(phService.getDesignerInfo(shopName));
//        System.out.println(infoList);

        return infoList;
    }

    // 리뷰 글 작성 페이지
    @PostMapping("/shopReviewWriter")
    @ResponseBody
    public Object shopReviewWriter(@RequestParam("apointDesigner") String apointDesigner, @RequestParam("apointShop") String apointShop, @RequestParam("apointDate") String apointDate, @RequestParam("apointTime") String apointTime, HttpServletRequest request) {
        HttpSession session = request.getSession();
        apointDto apointDto = phService.getApoints(apointDesigner, apointShop, apointDate, apointTime);

        if (session.getAttribute("apointInfo") != null) {
            session.removeAttribute("apointInfo");
        }
        session.setAttribute("apointInfo", apointDto);
        System.out.println(session.getAttribute("apointInfo"));

        return apointDto;
    }
    @RequestMapping("/reviewWrite")
    public String reviewWrite(){
        return "review/shopReviewWriter";
    }

    @RequestMapping("/shopReviewIn")
    public String insertReviewWrite(reviewDto reviewDto) throws Exception {
        phService.insertReview(reviewDto);
        return "redirect:userMypage";
    }

    @PostMapping("/designerUpdate")
    @ResponseBody
    public Object designerUpdate(@RequestParam("dName") String dName, @RequestParam("dShop") String dShop, HttpServletRequest request) {
        HttpSession session = request.getSession();

        designerDto designerDto1 = phService.designerInfo(dName, dShop);

        if (session.getAttribute("designerInfo") != null) {
            session.removeAttribute("designerInfo");
        }

        session.setAttribute("designerInfo", designerDto1);

        return designerDto1;
    }

    @RequestMapping("/designerUpdatePage")
    public String designerUpdatePage() {
        return "designerUpdate";
    }

    @RequestMapping("/designerInfoUpdate")
    public String designerInfoUpdate(designerDto designerDto, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("designers") != null) {
            session.removeAttribute("designers");
        }
        phService.designerUpdate(designerDto);
        return "redirect:main";
    }

    @PostMapping("/doubleTimeCheck")
    @ResponseBody
    public Object doubleTimeCheck(@RequestParam("dName") String apointDesigner, @RequestParam("dShop") String apointShop, @RequestParam("date") String apointDate) {

        List<apointDto> apointDto = phService.getDesignerApoint(apointDesigner, apointShop, apointDate);

        return apointDto;
    }

    //    예약 취소
    @PostMapping("/apointCancel")
    @ResponseBody
    public void apointCancel(@RequestParam("apointDesigner") String apointDesigner, @RequestParam("apointShop") String apointShop, @RequestParam("apointDate") String apointDate, @RequestParam("apointTime") String apointTime, @RequestParam("userId") String apointUserId) {
        phService.apointCancel(apointDesigner, apointShop, apointDate, apointTime, apointUserId);
    }

//    리뷰 뿌리기
    @PostMapping("/getReview")
    @ResponseBody
    public Object getReview(@RequestParam("shopName") String shopName){
        List<reviewDto> reviewDto = phService.getReview(shopName);

        return reviewDto;
    }
}
