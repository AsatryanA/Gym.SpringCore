package com.epam.gym.dao.impl;

import com.epam.gym.dao.TrainingTypeDAO;
import com.epam.gym.entity.TrainingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingTypeDAOImpl implements TrainingTypeDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<TrainingType> getAll(Pageable pageable) {
        var session = sessionFactory.getCurrentSession();
        Query<TrainingType> query = session.createQuery("FROM TrainingType", TrainingType.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @Override
    public Optional<TrainingType> getById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(TrainingType.class, id));
    }
}
