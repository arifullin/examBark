package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.dto.UserSearchDto;
import ru.kpfu.itis.model.dto.UserSearchResultDto;

import java.util.List;

/**
 * Created by Safin Ramil on 13.05.17
 */

@Service
public interface UserSearchService {

    List<StudentGroup> findGroupByCourse(Integer course);

    List<Integer> findAllCoursesValue();

    List<UserSearchResultDto> searchUsers(UserSearchDto dto);
}
