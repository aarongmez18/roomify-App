package com.app.roomify.provider.exchange.repository;

import com.app.roomify.domain.Profile;
import com.app.roomify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}