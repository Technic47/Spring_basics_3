package ru.gb.springdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gb.springdemo.model.BaseEntity;

@NoRepositoryBean
public interface AbstractJpaRepository<E extends BaseEntity> extends CrudRepository<E, Long> {
}
