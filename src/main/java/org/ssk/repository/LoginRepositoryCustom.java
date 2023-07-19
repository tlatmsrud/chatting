package org.ssk.repository;

import org.ssk.dto.UserDto;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */
public interface LoginRepositoryCustom {

    public UserDto findByNickname(String nickname);
}
