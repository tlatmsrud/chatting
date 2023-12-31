package org.ssk.factory;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-03
 * description  :
 */
public class AuthenticationFactory {

    public static Authentication getAuthentication(Long id){
        return new UsernamePasswordAuthenticationToken(
                id
                , ""
                , Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
