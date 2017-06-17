package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vladislav Matyunin
 *         StudentGroup made to:
 *         1) work with students from admin panel
 *         2) differ students from other type of users
 *         <p>
 *         course - number of course, e.g. 1,2.., master courses are 5 and 6
 *         num - number of group, e.g. 11-403 - num is 3
 */
@Entity
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int course;
    private int num;

    @OneToMany(mappedBy = "group")
    private Set<User> users = new LinkedHashSet<>(0);

    public StudentGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return course == that.course &&
                num == that.num &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, num);
    }
}
