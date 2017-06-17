package ru.kpfu.itis.model.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Safin Ramil on 08.05.17
 */
public class NotificationDto implements Serializable {

    private Long userId;

    private String title;

    private String body;

    private String datetime;

    private Boolean deadline;

    private String time1;

    private String time2;

    private List<Long> users;

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotBlank
    @Size(min = 1, max = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String titile) {
        this.title = titile;
    }

    @Size(min = 5, max= 512)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Boolean getDeadline() {
        return deadline;
    }

    public void setDeadline(Boolean deadline) {
        this.deadline = deadline;
    }


    @NotBlank
    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    @NotBlank
    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    @NotEmpty
    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificationDto{");
        sb.append("userId=").append(userId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", datetime='").append(datetime).append('\'');
        sb.append(", deadline=").append(deadline);
        sb.append(", time1='").append(time1).append('\'');
        sb.append(", time2='").append(time2).append('\'');
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
