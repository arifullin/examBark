package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.forms.StudentProfileForm;
import ru.kpfu.itis.model.forms.TeacherProfileForm;
import ru.kpfu.itis.model.forms.WorkerProfileForm;
import ru.kpfu.itis.repository.StudentGroupRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.root.AbstractCrudService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class UserServiceImpl extends AbstractCrudService<User> implements UserService {

    private final UserRepository userRepository;
    private final StudentGroupRepository stGroupRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StudentGroupRepository stGroupRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.stGroupRepository = stGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void setRepository() {
        repository = userRepository;
    }


    //TODO: optimize
    @Override
    public List<User> getByCourse(int course) {
        List<User> allUsers = new ArrayList<>();
        for (StudentGroup studentGroup : stGroupRepository.findByCourse(course)) {
            allUsers.addAll(studentGroup.getUsers());
        }
        return allUsers;
    }


    @Override
    public List<User> getAllFromGroup(int course, int num) {
        return new ArrayList<>(stGroupRepository.findByCourseAndNum(course, num).getUsers());
    }


    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmailIgnoreCase(email));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public User updateProfile(User user, StudentProfileForm form) {

        // TODO: check password length more normal
        if (form.getPassword()!= null && !form.getPassword().isEmpty() && form.getPassword().length() >= 6) {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        user.setAboutMe(form.getAboutMe());
        user.setPhone(form.getPhone());

        update(user);
        return user;
    }

    @Override
    public User updateProfile(User user, TeacherProfileForm form) {
        // TODO: reformat code^
        if (form.getPassword()!= null && !form.getPassword().isEmpty() && form.getPassword().length() >= 6) {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        user.setPhone(form.getPhone());
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        update(user);
        return user;
    }

    @Override
    public User updateProfile(User user, WorkerProfileForm form) {
        return updateProfile(user, (TeacherProfileForm) form);
    }

    @Override
    public User updateProfileByWorker(long userId, String education, String socialStatus, int course, int group) {
        User user = userRepository.findOne(userId);
        user.setGroup(stGroupRepository.findByCourseAndNum(course, group));
        switch (education) {
            case "Бюджет":
                user.setEducation(EducationType.BUDGET);
                break;
            case "Контракт":
                user.setEducation(EducationType.CONTRACT);
                break;
        }
        switch (socialStatus) {
            case "Староста":
                user.setSocialStatus(SocialStatus.STAROSTA);
                break;
            case "Соцорг":
                user.setSocialStatus(SocialStatus.SOCORG);
                break;
            case "Спорторг":
                user.setSocialStatus(SocialStatus.SPORTORG);
                break;
            case "Культорг":
                user.setSocialStatus(SocialStatus.KULTORG);
                break;
            case "Профорг":
                user.setSocialStatus(SocialStatus.PROFORG);
                break;
            default:
                user.setSocialStatus(SocialStatus.NONE);
        }
        this.update(user);
        return user;
    }


    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
