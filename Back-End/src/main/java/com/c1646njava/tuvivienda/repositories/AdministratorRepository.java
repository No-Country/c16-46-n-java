package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
