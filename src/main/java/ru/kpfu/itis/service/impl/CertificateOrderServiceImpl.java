package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.CertificateOrder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.NotificationDto;
import ru.kpfu.itis.repository.CertificateOrderRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.CertificateOrderService;
import ru.kpfu.itis.service.NotificationService;
import ru.kpfu.itis.service.root.AbstractCrudService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Shchepetov on 14.05.2017.
 */
@Service
public class CertificateOrderServiceImpl extends AbstractCrudService<CertificateOrder> implements CertificateOrderService {

    public static String DEFAULT_WORKER = "worker@mail.ru";

    private final UserRepository userRepository;
    private final CertificateOrderRepository certificateOrderRepository;
    private final NotificationService notificationService;


    @Autowired
    public CertificateOrderServiceImpl(UserRepository userRepository, CertificateOrderRepository certificateOrderRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.certificateOrderRepository = certificateOrderRepository;
        this.notificationService = notificationService;
    }

    @Override
    public List<CertificateOrder> getAll() {
        return certificateOrderRepository.findAll();
    }

    @Override
    public void done(long userId) {
        User user = userRepository.findOne(userId);
        certificateOrderRepository.delete(certificateOrderRepository.findByUser(user));
        NotificationDto dto = new NotificationDto();
        dto.setTitle("Справки");
        dto.setBody("Ваши справки готовы. Вы можете забрать их в деканате");
        dto.setUserId(userRepository.findByEmail(DEFAULT_WORKER).getId());
        dto.setDeadline(false);
        List<Long> users = new ArrayList<>();
        users.add(userId);
        dto.setUsers(users);
        notificationService.save(dto);
    }

    @Override
    public String orderCertificate(int count, long id) {
        if (count > 5) {
            return "Вами заказано слишком много справок. За один раз не более 5";
        }
        if (count < 1) {
            return "Число справок не может быть меньше 1";
        }
        CertificateOrder certificateOrder = certificateOrderRepository.findByUser(userRepository.findOne(id));
        if (certificateOrder != null) {
            int existingCount = certificateOrder.getCount();
            if (existingCount + count > 5) {
                return "Вами заказано слишком много справок. За один раз не более 5. Вами уже заказано " + existingCount + " штук";
            }
            certificateOrder.setCount(certificateOrder.getCount() + count);
            this.update(certificateOrder);
            return "Справки в количестве " + certificateOrder.getCount() + " штук заказаны";
        }
        certificateOrder = new CertificateOrder();
        certificateOrder.setCount(count);
        //TODO: добавить сотрудника, ответственного за справки
        certificateOrder.setUser(userRepository.findOne(id));

        certificateOrderRepository.save(certificateOrder);
        return "Справки в количестве " + count + " штук заказаны";

    }

    @Override
    protected void setRepository() {
        this.repository = certificateOrderRepository;
    }
}
