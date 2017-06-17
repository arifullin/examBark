package ru.kpfu.itis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.UserSearchDto;
import ru.kpfu.itis.model.dto.UserSearchResultDto;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.Sex;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.enums.UserRole;
import ru.kpfu.itis.repository.StudentGroupRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserSearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Safin Ramil on 14.05.17
 */
@Service
public class UserSearchServiceImpl implements UserSearchService {

    private static final Logger logger = LoggerFactory.getLogger(UserSearchServiceImpl.class);

    private final UserRepository userRepository;
    private final StudentGroupRepository groupRepository;

    @Autowired
    public UserSearchServiceImpl(UserRepository userRepository, StudentGroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<StudentGroup> findGroupByCourse(Integer course) {
        return groupRepository.findByCourse(course);
    }

    @Override
    public List<Integer> findAllCoursesValue() {
        return new ArrayList<>(groupRepository.findAll().stream()
                .map(StudentGroup::getCourse)
                .collect(Collectors.toSet()));
    }

    @Override
    public List<UserSearchResultDto> searchUsers(UserSearchDto dto) {

        try {

            List<User> users = userRepository.findAllByUserRole(UserRole.ROLE_STUDENT);

            users = users.stream()
                    .filter(user -> {
                        StudentGroup group = user.getGroup();
                        if (dto.getCourse() != -1) {
                            if (dto.getGroup() != -1) {
                                return group != null && group.getCourse() == dto.getCourse()
                                        && group.getNum() == dto.getGroup();
                            }
                            return group != null && group.getCourse() == dto.getCourse();
                        }
                        return group != null;
                    })
                    .filter(user -> dto.getName().isEmpty() || dto.getName().equals(user.getName()))
                    .filter(user -> dto.getSurname().isEmpty() || dto.getSurname().equals(user.getSurname()))
                    .filter(user -> dto.getPatronymic().isEmpty() || dto.getPatronymic().equals(user.getPatronymic()))
                    .filter(user -> {
                        Sex sex = resolveSex(dto);
                        return sex == Sex.ALL || sex.equals(user.getSex());
                    })
                    .filter(user -> {
                        EducationType education = resolveEducation(dto);
                        return education == EducationType.ALL || education.equals(user.getEducation());
                    }).filter(user -> resolveSocialStatus(dto, user.getSocialStatus()))
                    .collect(toList());

            if (users != null) {
                return users.stream().map(u -> {
                    UserSearchResultDto res = new UserSearchResultDto();

                    res.setId(u.getId());
                    res.setName(u.getName());
                    res.setSurname(u.getSurname());
                    res.setPatronymic(u.getPatronymic());
                    res.setRole(u.getUserRole());
                    res.setEmail(u.getEmail());
                    res.setAvatarUrl(u.getAvatarUrl());
                    res.setPhone(u.getPhone());
                    res.setSex(u.getSex());
                    res.setSocialStatus(u.getSocialStatus());
                    res.setGroup(u.getGroup());
                    res.setEducation(u.getEducation());
                    res.setDocuments(new ArrayList<>(u.getDocuments()));
                    return res;
                }).collect(toList());
            }

        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    private EducationType resolveEducation(UserSearchDto dto) {
        if (!dto.getBudget() && !dto.getContract() && !dto.getGrant()) // all false
            return EducationType.ALL; // all users

        if (dto.getBudget() && dto.getContract()) return EducationType.ALL; // all BUDGET and CONTRACT

        return dto.getContract() ? EducationType.CONTRACT : EducationType.BUDGET;
    }

    private Sex resolveSex(UserSearchDto dto) {
        if (dto.getMan().equals(dto.getWoman())) return Sex.ALL;
        return dto.getMan() ? Sex.MALE : Sex.FEMALE;
    }


    private boolean resolveSocialStatus(UserSearchDto dto, SocialStatus socialStatus) {
        if (!dto.getStarosta() && !dto.getProfOrg() && !dto.getSportOrg() && !dto.getCultureOrg() && !dto.getSocialOrg()) {
            return true; //all false
        }

        if (socialStatus == null) return false;

        if (dto.getStarosta() && dto.getProfOrg() && dto.getSportOrg() && dto.getCultureOrg() && dto.getSocialOrg()) {
            return true; //all true
        }
//check for status matching to dto
        if (dto.getStarosta() && socialStatus.equals(SocialStatus.STAROSTA)) return true;
        if (dto.getSportOrg() && socialStatus.equals(SocialStatus.SPORTORG)) return true;
        if (dto.getSocialOrg() && socialStatus.equals(SocialStatus.SOCORG)) return true;
        if (dto.getProfOrg() && socialStatus.equals(SocialStatus.PROFORG)) return true;
        return dto.getCultureOrg() && socialStatus.equals(SocialStatus.KULTORG);


    }


}
