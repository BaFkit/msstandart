package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msstandart.dto.UserDto;
import ru.msstandart.dto.UserDtoForChangePass;
import ru.msstandart.entities.Role;
import ru.msstandart.entities.User;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        return user;
    }

    public List<UserDto> findAllUsers() {
       return userRepository.findAllExceptAdmin("admin").stream().map(EntityDtoMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void changePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id: '%d' not found", userId)));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
