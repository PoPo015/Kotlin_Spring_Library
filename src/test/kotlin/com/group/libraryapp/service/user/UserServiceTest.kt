package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }


    @Test
    @DisplayName("유저 저장")
    fun saveUserTest() {
        // given
        val userCreateRequest = UserCreateRequest("김승태", null);
        // when
        userService.saveUser(userCreateRequest)
        //then
        val findAll = userRepository.findAll()
        assertThat(findAll).hasSize(1);
        assertThat(findAll[0].name).isEqualTo("김승태")
        assertThat(findAll[0].age).isNull()
    }

    @Test
    @DisplayName("유저 목록 조회")
    fun getUsersTest() {
        //given
        userRepository.saveAll(
            listOf(
                User("김승태", 31),
                User("최태현", null),
            )
        );

        //when
        val users = userService.getUsers()

        //then
        assertThat(users).hasSize(2)
        assertThat(users).extracting("name").containsExactlyInAnyOrder("김승태", "최태현")
        assertThat(users).extracting("age").containsExactlyInAnyOrder(31, null)
    }


    @Test
    @DisplayName("유저 정보 업데이트")
    fun updateUserTest() {
        //given
        val savedUser = userRepository.save(User("A", 0))
        val userUpdateRequest = UserUpdateRequest(savedUser.id!!, "B")

        //when
        userService.updateUserName(userUpdateRequest)

        //then
        val findById = userRepository.findById(savedUser.id)
        assertThat(findById.get().name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 정보 삭제")
    fun deleteUserTest() {
        //given
        val savedUser = userRepository.save(User("A", 0))

        //when
        userService.deleteUser(savedUser.name)

        //then
        val findAll = userRepository.findAll()
        assertThat(findAll).isEmpty()
    }


}