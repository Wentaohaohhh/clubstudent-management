package com.example.clubstudentmanagement;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ControllerAdvice
@SpringBootApplication
public class ClubStudentManagementApplication {

    private static Logger log = LoggerFactory.getLogger(ClubStudentManagementApplication.class);
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerException(AuthenticationException exception){
        log.debug("{} was thrown", exception.getClass(), exception);
    }
    @Bean
    public Realm realm(){
        //uses 'classpath:shiro-users.properties' by default
        PropertiesRealm realm = new PropertiesRealm();
        return realm;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/**","authBasic");
        return chainDefinition;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
    public static void main(String[] args) {
        SpringApplication.run(ClubStudentManagementApplication.class, args);
    }

}
