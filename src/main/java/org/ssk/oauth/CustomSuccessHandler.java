package org.ssk.oauth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.ssk.entity.MemberEntity;
import org.ssk.provider.JwtProvider;
import org.ssk.repository.MemberRepository;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * title        : Custom AuthenticationSuccessHandler
 * author       : sim
 * date         : 2023-07-02
 * description  : authorizationCode, accessToken, refreshToken 취득 및 accessToken을 통한 유저 정보 조회 후
 * 최종적으로 호출되는 Handler
 */

@AllArgsConstructor
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Map<String,Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if(memberEntity == null){
            memberEntity = joinMember(attributes);
        }

        Map<String, Object> claims = jwtProvider.generateClaims(memberEntity);

        String accessToken = jwtProvider.generateAccessToken(claims);

        AddAccessTokenToCookie(response, accessToken);
        responseRedirectUrl(request, response);
    }

    public void AddAccessTokenToCookie(HttpServletResponse response, String accessToken){
        accessToken = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("AccessToken", accessToken);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);
    }

    /**
     * Attributes To MemberEntity
     * @param oAuth2Attributes - oAuth2를 통해 조회한 Attributes
     * @return MemberEntity
     */
    public MemberEntity attributesToMemberEntity(Map<String,Object> oAuth2Attributes){

        return MemberEntity.builder()
                    .email(oAuth2Attributes.get("email").toString())
                    .nickname(oAuth2Attributes.get("nickname").toString())
                    .profileUrl(oAuth2Attributes.get("profileUrl").toString())
                    .build();
    }

    /**
     * 회원가입
     * @param oAuth2Attributes - oAuth2를 통해 조회한 Attributes
     * @return MemberEntity
     */
    public MemberEntity joinMember(Map<String,Object> oAuth2Attributes){
        MemberEntity memberEntity = attributesToMemberEntity(oAuth2Attributes);
        return memberRepository.save(memberEntity);
    }

    public void responseRedirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {

        getRedirectStrategy().sendRedirect(request, response, "/list");
    }

}
