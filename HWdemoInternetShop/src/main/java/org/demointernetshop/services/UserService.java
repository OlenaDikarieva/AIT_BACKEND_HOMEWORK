package org.demointernetshop.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.user.LoginRequestDto;
import org.demointernetshop.dto.user.UserDto;
import org.demointernetshop.dto.user.UserRegistrationDto;
import org.demointernetshop.dto.user.UserUpdateDto;
import org.demointernetshop.entity.Role;
import org.demointernetshop.entity.User;
import org.demointernetshop.repository.UserRepository;
import org.demointernetshop.services.utils.Converters;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Assuming you have a PasswordEncoder bean


    public UserDto createUser(UserRegistrationDto request) {
        // Convert UserRegistrationDto to User entity
        User user = Converters.registrationDtoToEntity(request);

        // Set default role (you may need to adjust this according to your logic)
        Role role = new Role();
        role.setId(1); // Assuming 1 is the ID for the default role
        user.setRole(role);

        // Save the user
        User savedUser = userRepository.save(user);

        // Convert User entity to UserDto and return
        return Converters.entityToDto(savedUser);
    }

    public UserDto updateUser(Integer userId, UserUpdateDto request) {
        // Find the user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("User not found with id: " + userId);
                });

        // Update the existing user with the new data
        existingUser.setUsername(request.getUsername());
        existingUser.setPassword(request.getPassword());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());

        // Save the updated user
        User updatedUser = userRepository.save(existingUser);

        // Convert User entity to UserDto and return
        return Converters.entityToDto(updatedUser);
    }

    public UserDto loginUser(LoginRequestDto request) {
        // Find the user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        // Check if the provided password matches the stored password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            try {
                throw new BadCredentialsException("Invalid password");
            } catch (BadCredentialsException e) {
                throw new RuntimeException(e);
            }

        // Update last visit date
        user.setLastVisitData(LocalDateTime.now());
        userRepository.save(user);

        // Convert User entity to UserDto and return
        return Converters.entityToDto(user);
    }
    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for user id: " + id));
    }


}
