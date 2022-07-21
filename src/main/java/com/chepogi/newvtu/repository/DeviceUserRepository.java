package com.chepogi.newvtu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chepogi.newvtu.model.DeviceUser;

public interface DeviceUserRepository extends JpaRepository<DeviceUser, Long> {

    Optional<DeviceUser> findByLastNameIgnoreCase(String lastName);
}
