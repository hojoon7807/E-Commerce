package com.example.project.domain.user.repository;

import com.example.project.domain.user.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

  @Query("select a from Address a where a.user.userId = :userId")
  List<Address> findAllByUserId(Long userId);

  @Query("select a from Address a join fetch a.user where a.user.userId = :userId and a.isDefault = true")
  Address findDefaultAddressWithUserByUserId(Long userId);

  @Modifying
  @Query("update Address a set a.isDefault = false where a.user.userId = :userId and a.isDefault = true")
  int updateDefaultAddress(Long userId);
}
