package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.repository.StudentGroupRepository;
import ru.kpfu.itis.service.StudentGroupService;
import ru.kpfu.itis.service.root.AbstractCrudService;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class StGroupServiceImpl extends AbstractCrudService<StudentGroup> implements StudentGroupService{
    @Autowired
    StudentGroupRepository studentGroupRepository;
    @Override
    protected void setRepository() {
        repository = studentGroupRepository;
    }
    public StudentGroup findByCourseAndGroup(int course,int num){
         return studentGroupRepository.findByCourseAndNum(course,num);
    }
}
