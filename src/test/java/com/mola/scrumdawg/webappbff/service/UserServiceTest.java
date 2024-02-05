package com.mola.scrumdawg.webappbff.service;

import com.mola.scrumdawg.webappbff.BaseTest;
import com.mola.scrumdawg.webappbff.exception.ResourceNotFoundException;
import com.mola.scrumdawg.webappbff.model.User;
import com.mola.scrumdawg.webappbff.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest extends BaseTest {

    @InjectMocks
    private UserService unitUnderTest;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserById_UserExistForGivenId_UserRepositoryShouldInvoke(){
        //Preparation
        String userId = nextString();
        User expectedUser = mock(User.class);
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        //Execution
        User actualUser = unitUnderTest.getUserById(userId);

        //Assertion
        assertSame(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_UserDoesNotExistForGivenId_ResourceNotFoundExceptionShouldBeThrown(){
        //Preparation
        String userId = nextString();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //Execution
        assertThatThrownBy(() -> unitUnderTest.getUserById(userId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User not found.");

        //Assertion
        verify(userRepository, times(1)).findById(userId);
    }
}