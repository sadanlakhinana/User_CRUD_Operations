package org.user.user_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.user_crud.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
