package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    //TODO KST
    //TIP 코틀린에서 Transactional 어노테이션을 사용할땐, open을 사용해 오버라이딩 되게만들어줘야한다.
    //귀찮으니 플러그인을 통해 해결한다
    @Transactional
    fun saveUser(request: UserCreateRequest)  {
        val user = User(request.name, request.age)
        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map { UserResponse(it) }
        // 위와 같은건 일반적으로 3가지로 사용할수 있음.
        // 1.람다와 같이 user -> UserResponse(user)
        // 2. 코틀린에 있는 키워드 it 사용 UserResponse(it)
        // 3. 생성자 사용법 ::UserResponse
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest){
        val user = userRepository.findById(request.id)
            .orElseThrow { IllegalAccessException("$request.id 는 비정상입니다") }
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String){
        val user = userRepository.findByName(name)
            .orElseThrow { IllegalAccessException("$name 는 비정상입니다") }

        userRepository.delete(user)

    }

}
