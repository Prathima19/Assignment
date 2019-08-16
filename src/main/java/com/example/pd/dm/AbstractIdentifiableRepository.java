package com.example.pd.dm;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.pd.AbstractIdentifiableEntity;


@NoRepositoryBean
public interface AbstractIdentifiableRepository<T extends AbstractIdentifiableEntity> extends JpaRepository<T, UUID>
{
}
