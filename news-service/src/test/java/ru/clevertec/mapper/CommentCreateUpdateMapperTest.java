package ru.clevertec.mapper;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.impl.CommentCreateUpdateMapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.util.UtilClass;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.clevertec.util.UtilClass.SUBJECT;
import static ru.clevertec.util.UtilClass.news1;
import static ru.clevertec.util.UtilClass.username1Admin;

@ExtendWith(MockitoExtension.class)
class CommentCreateUpdateMapperTest {

    private static final String USERNAME = "username1";
    private static final Long NEWS_ID = 1L;
    private static final Long USER_ID = 1L;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CommentCreateUpdateMapper commentCreateUpdateMapper;

    @Test
    void checkMap() {
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto(SUBJECT, USERNAME, NEWS_ID, USER_ID);
        when(userRepository.findByUsername(commentCreateUpdateDto.username()))
                .thenReturn(username1Admin);
        doReturn(Optional.of(news1))
                .when(newsRepository).findById(commentCreateUpdateDto.newsId());

        Comment actualResult = commentCreateUpdateMapper.map(commentCreateUpdateDto);

        assertAll(
                () -> assertThat(actualResult.getSubject()).isEqualTo(commentCreateUpdateDto.subject()),
                () -> assertThat(actualResult.getUser().getId()).isEqualTo(NEWS_ID)
        );
        verify(userRepository).findByUsername(USERNAME);
        verify(newsRepository).findById(NEWS_ID);
    }

    @Test
    void checkMapWithTwoParams() {
        String anotherSubject = "anotherSubject";
        CommentCreateUpdateDto from = new CommentCreateUpdateDto(anotherSubject, USERNAME, 1L, 1L);
        Comment to = UtilClass.comment1;
        String subjectBefore = to.getSubject();

        commentCreateUpdateMapper.map(from, to);
        String subjectAfter = to.getSubject();

        assertThat(subjectAfter).isNotEqualTo(subjectBefore);
    }
}