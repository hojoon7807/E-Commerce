package com.example.project.application.user;

import com.example.project.application.user.exception.UserNotFoundException;
import com.example.project.application.user.usecase.GetMyPageUseCase;
import com.example.project.application.user.usecase.MyPageInfo;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.AddressRepository;
import com.example.project.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMyPageService implements GetMyPageUseCase {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;

  @Override
  public MyPageInfo apply(Long userId) {
    User user = findUser(userId);
    List<Address> addresses = findAddresses(user.getUserId());
    return MyPageInfo.from(user, addresses);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
                         .orElseThrow(UserNotFoundException::new);
  }

  private List<Address> findAddresses(Long userId) {
    return addressRepository.findAllByUserId(userId);
  }
}
