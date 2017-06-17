package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.CertificateOrder;

import java.util.List;

/**
 * Created by Daniel Shchepetov on 14.05.2017.
 */
@Service
public interface CertificateOrderService {

    String orderCertificate(int count, long id);

    List<CertificateOrder> getAll();

    void done(long userId);
}
