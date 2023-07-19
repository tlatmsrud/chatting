package org.ssk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssk.dto.UserDto;
import org.ssk.entity.User;
import org.ssk.exception.DuplicationNicknameException;
import org.ssk.repository.LoginRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public void login(String nickname){
        if(loginRepository.findByNickname(nickname) != null){
            throw new DuplicationNicknameException("중복된 닉네임입니다. 다른 닉네임으로 로그인해주세요");
        }

        loginRepository.save(
                User.builder()
                    .nickname(nickname)
                     .build()
        );
    }
}
