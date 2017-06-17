package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Daniel Shchepetov on 06.05.2017.
 */
@Entity
@Table
public class CertificateOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int count;
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
