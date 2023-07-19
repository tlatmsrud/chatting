package org.ssk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.entity.User;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */
public interface LoginRepository extends JpaRepository<User, Long>, LoginRepositoryCustom{
}
