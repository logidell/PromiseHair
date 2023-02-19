package com.example.smp_1.configuration;//package com.example.smp_1.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class fileConfig implements WebMvcConfigurer {
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
////        기본 문자셋 설정
//        commonsMultipartResolver.setDefaultEncoding("UTF-8");
////        업로드 파일 최대 크기 설정, byte 크기로 설정하기 때문에 5 * 1024 * 1024 = 5MB가 됨
//        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
//
//        return commonsMultipartResolver;
//    }
//}
