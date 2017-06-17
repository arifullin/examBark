package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.forms.StudentProfileForm;
import ru.kpfu.itis.model.forms.TeacherProfileForm;
import ru.kpfu.itis.model.forms.WorkerProfileForm;
import ru.kpfu.itis.service.root.CrudService;

import java.util.List;
import java.util.Optional;

/**
 * @author Matyunin Vladislav
 * This is the interface to work with user data
 */
@Service
public interface UserService extends CrudService<User> {
    /**
     * @param course - the number of course(1,2..4, master courses - 5, 6)
     *               @return all users(@see model.User) from this course
     */
    List<User> getByCourse(int course);
    /**
     * @param course - course number
     * @param num - the number of group (e.g. 11-403 - number of group is 3)
     * @return all students from this course
     */
    List<User> getAllFromGroup(int course, int num);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User updateProfile(User user, StudentProfileForm form);
    User updateProfile(User user, TeacherProfileForm form);
    User updateProfile(User user, WorkerProfileForm form);
    User updateProfileByWorker(long userId, String education, String socialStatus, int course, int group);
}
