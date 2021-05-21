package com.projeto.luizaLabs.repository;

import com.projeto.luizaLabs.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByID(long id) ;

    Cliente save(Cliente cliente);

    long count();
}
