package com.eduardoandre.crudDeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardoandre.crudDeliver.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
