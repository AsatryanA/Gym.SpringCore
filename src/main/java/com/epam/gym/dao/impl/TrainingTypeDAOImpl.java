package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.TrainingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingTypeDAOImpl implements TrainingTypeDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<TrainingType> getAll() {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("from TrainingType", TrainingType.class)
                .list();
    }
}
