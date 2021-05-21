package com.projeto.luizaLabs.repository;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findById(Long id);

    List<WishList> findByCliente(Cliente existeCliente);

    WishList findByClienteID(Long id);
}
