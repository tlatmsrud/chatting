package org.ssk.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ssk.factory.AuthenticationFactory;
import org.ssk.provider.JwtProvider;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-03
 * description  :
 */

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getAccessTokenToCookie(request);

        if(accessToken != null && !accessToken.isBlank()){
            Long id = jwtProvider.parseJwtToken(accessToken).get("id",Long.class);
            Authentication authentication = AuthenticationFactory.getAuthentication(id);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);


    }

    public String getAccessTokenToCookie(HttpServletRequest request){
        String accessToken = null;

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            Cookie accessTokenCookie = Arrays.stream(cookies)
                    .filter(cookie -> "AccessToken".equals(cookie.getName()))
                    .findFirst().get();

            accessToken = accessTokenCookie.getValue();
        }

        return accessToken;
    }
}
