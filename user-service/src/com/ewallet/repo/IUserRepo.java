package com.ewallet.repo;

import com.ewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
}
