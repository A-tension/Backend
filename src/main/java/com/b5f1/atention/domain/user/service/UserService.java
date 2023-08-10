package com.b5f1.atention.domain.user.service;

import com.b5f1.atention.domain.user.dto.UserProfileUpdateDto;
import com.b5f1.atention.domain.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {

    public UserResponseDto getMyUserProfile(UUID userId);

    public void deleteUser(UUID userId);

    public UserResponseDto updateUserProfile(UUID userId, UserProfileUpdateDto userProfileUpdateDto);

}
