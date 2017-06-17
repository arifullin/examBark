package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.CertificateOrder;
import ru.kpfu.itis.model.User;

/**
 * Created by Daniel Shchepetov on 14.05.2017.
 */
@Repository
public interface CertificateOrderRepository extends JpaRepository<CertificateOrder, Long> {

    CertificateOrder findByUser(User user);
}
