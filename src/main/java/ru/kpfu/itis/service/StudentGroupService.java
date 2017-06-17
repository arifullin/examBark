package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.service.root.CrudService;

/**
 * @author Matyunin Vladislav
 * @see StudentGroup
 * this interface and entity helps to cope with the problem to differ student from
 * teacher or worker
 */
@Service
public interface StudentGroupService extends CrudService<StudentGroup> {
}
