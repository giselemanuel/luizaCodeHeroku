package com.projeto.luizaLabs.repository;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto save(Produto produto);

    Produto findByNome(String nome);

    Produto findByID(long id);
}
