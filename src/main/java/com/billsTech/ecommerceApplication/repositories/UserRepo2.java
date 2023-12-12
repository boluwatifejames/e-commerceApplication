package com.billsTech.ecommerceApplication.repositories;

import com.billsTech.ecommerceApplication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo2 extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

}
