package com.example.project.domain.user.repository;

import com.example.project.domain.user.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
