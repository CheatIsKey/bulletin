package dev.dev.service;

import dev.dev.domain.User;
import dev.dev.domain.UserCreateRequest;
import dev.dev.domain.UserDto;
import dev.dev.domain.UserRole;
import dev.dev.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserDto findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. id=" + id));
        return new UserDto(user.getId(), user.getName(), user.getNickname() , user.getPhone(), user.getLoginId());
    }

    @Transactional(readOnly = true)
    public String findLoginIdByNameAndPhone(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name.trim(), phone.trim())
                .orElseThrow(() -> new EntityNotFoundException("정보와 일치하는 사용자 아이디가 없습니다."));
        return maskLoginId(user.getLoginId());
    }

    private String maskLoginId(String loginId) {
        if (loginId == null || loginId.length() < 3) return loginId;

        return loginId.substring(0, 2) + "*".repeat(loginId.length() - 3) + loginId.charAt(loginId.length() - 1);
    }

    @Transactional(readOnly = true)
    public Page<String> findUserNamePageByUserName(String name, Pageable pageable) {
        if (!StringUtils.hasText(name)) return Page.empty(pageable);
        return userRepository.findUserNamePageByUserName(name.trim(), pageable);
    }

    @Transactional
    public UserDto create(UserCreateRequest request) {
        String encoded = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getNickname(),
                request.getPhone(),
                request.getLoginId(),
                encoded,
                UserRole.CLIENT
        );
        User saved = userRepository.save(user);

        return new UserDto(saved.getId(), saved.getName(), saved.getNickname(), saved.getPhone(), saved.getLoginId());
    }

    @Transactional
    public UserDto update(Long id, String name, String phone, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (StringUtils.hasText(name)) {
            user.changeName(name.trim());
        }
        if (StringUtils.hasText(phone)) {
            user.changePhone(phone.trim());
        }
        if (StringUtils.hasText(password)) {
            String encoded = passwordEncoder.encode(password);
            user.changePassword(encoded);
        }

        return new UserDto(user.getId(), user.getName(), user.getNickname(), user.getPhone(), user.getLoginId());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
