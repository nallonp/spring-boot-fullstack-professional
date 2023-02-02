package com.example.demo.student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void itShouldCheckIfStudentExistsByEmail() {
    //given
    String email = "jamila@gmail.com";
    Student student = new Student(
        "Jamila", email, Gender.FEMALE
    );
    underTest.save(student);
    //when
    Boolean existsEmail = underTest.selectExistsEmail(email);

    //then
    assertThat(existsEmail).isTrue();

  }

  @Test
  void itShouldCheckIfStudentDoesNotExistsByEmail() {
    //given
    String email = "jamila@gmail.com";
    //when
    Boolean existsEmail = underTest.selectExistsEmail(email);
    //then
    assertThat(existsEmail).isFalse();

  }
}
