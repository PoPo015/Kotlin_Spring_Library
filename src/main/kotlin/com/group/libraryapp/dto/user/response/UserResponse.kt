package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?
) {

    //TODO KST
    // 자바에서 정적팩토리 메소드와 같이 of형태로 반환시켜준다
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age
            )
        }

    }

}