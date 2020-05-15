package com.example.demo.repository;

import com.example.demo.model.EntityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AnyEntityObjectRepository <E extends EntityObject> extends JpaRepository<E, Long> {

}
