package dev.dev.service;

import dev.dev.domain.User;
import dev.dev.domain.UserDto;
import dev.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Long saveUser(User user) {
        return userRepository.save(user).getId();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public UserDto changeUserInfo(Long id, User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserDto userDto = new UserDto(user.getName(), user.getPhone(), user.getUserId(), user.getPassword());
        return userDto;
    }

}
