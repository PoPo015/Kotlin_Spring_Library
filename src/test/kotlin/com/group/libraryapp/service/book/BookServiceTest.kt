package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class BookServiceTest(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val bookService: BookService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    @AfterEach
    fun clear() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun saveBookTest() {
        //given
        val bookRequest = BookRequest("이상한 나라의 엘리스")
        //then
        bookService.saveBook(bookRequest)

        //then
        val findByName = bookRepository.findByName(bookRequest.name)
        assertThat(findByName.get().name).isEqualTo("이상한 나라의 엘리스")
    }


    @Test
    @DisplayName("책 대출이 정상 동작한다.")
    fun loanBookTest() {
        //given
        val book = Book("이상한 나라의 엘리스", null)
        bookRepository.save(book)
        val saveUser = userRepository.save(User("김승태", 31, Collections.emptyList()))
        val bookLoanRequest = BookLoanRequest("김승태", "이상한 나라의 엘리스")

        //when
        bookService.loanBook(bookLoanRequest)

        //then
        val findAll = userLoanHistoryRepository.findAll()
        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].bookName).isEqualTo(book.name)
        assertThat(findAll[0].user.id).isEqualTo(saveUser.id)
        assertThat(findAll[0].isReturn).isFalse
    }

    @Test
    @DisplayName("책 대출 중이라면, 신규 대출이 실패한다.")
    fun loanBookFailTest() {
        // given
        val book = Book("이상한 나라의 엘리스", null)
        bookRepository.save(book)
        val saveUser = userRepository.save(User("김승태", 31))
        val bookLoanRequest = BookLoanRequest("김승태", "이상한 나라의 엘리스")
        bookService.loanBook(bookLoanRequest)

        //when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(bookLoanRequest)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

}