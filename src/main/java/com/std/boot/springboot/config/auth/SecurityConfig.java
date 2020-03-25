package com.std.boot.springboot.config.auth;

import com.std.boot.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()     // h2-console 화면을 사용하기 위해 해당옵션들을 disable 합니다.
                .and()
                    .authorizeRequests()    // URL 별 권한 관리를 설정하는 옵션의 시작점, 본 라인이 선언되어야 antMatchers 옵션을 사용할 수 있음.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()  //권한관리 대상을 지정하는 옵션, URL, HTTP 메소드별로 관리가 가능.
                    .antMatchers("/api/v1/**").hasAnyRole(Role.USER.name(), Role.GUEST.name())            // /api/v1/** 주소를 가진 API는 USER 권한을 가진사람만 접근 가능.
                    .anyRequest().authenticated()       // 설정된 값들 이외 나머지 URL들은 모두 인증된 사용자들에게만 허용
                .and()
                    .logout().logoutSuccessUrl("/")     // 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공 시 / 주소로 이동
                .and()
                    .oauth2Login().userInfoEndpoint()       // oauth2Login: OAuth2 로그인 기능에 대한 여러 설정의 진입점, userInfoEndPoint: OAuth 로그인 성공 이후 사용자 정보를 가져올떄의 설정들을 담당.
                        .userService(customOAuth2UserService);  // 소셜로그인 성공 시 후속조치를 진행할 UserService 인터페이스의 구현체로 등록
    }
}