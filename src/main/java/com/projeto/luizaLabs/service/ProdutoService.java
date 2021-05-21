package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save((Produto) produto);
    }

    public Produto buscarProduto(long id) {
        return produtoRepository.findByID(id);
    }

    public Produto atualizaProduto(Produto produto) {
        return produtoRepository.save((Produto) produto);
    }

    public long quantidadeDeProdutos(){
        return produtoRepository.count();
    }

    public Produto findByNome(String  nome) {
        return produtoRepository.findByNome(nome);
    }
}