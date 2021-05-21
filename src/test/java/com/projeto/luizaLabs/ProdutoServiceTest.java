package com.projeto.luizaLabs;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.service.ProdutoService;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
@Transactional
public class ProdutoServiceTest {

    @Autowired
    ProdutoService produtoServiceTest;

    @Test
    void salvarProdutoNoBanco() {
    Produto produto = new Produto();
    BigDecimal geladeira = new BigDecimal(1000);
    produto.setPreco(geladeira);
    produto.setNome("Geladeira");

    Produto produtoSalvo = produtoServiceTest.adicionarProduto(produto);
    assertThat(produtoSalvo).isNotNull();
    }

    @Test
    void verificarIdDoProdutoSalvo() {
    Produto produto = new Produto();
    BigDecimal geladeira = new BigDecimal(1000);
    produto.setPreco(geladeira);
    produto.setNome("Geladeira");
    Produto produtoSalvo = produtoServiceTest.adicionarProduto(produto);
    assertThat(produtoSalvo.getID()).isEqualTo(6);
    }

    @Test
    public void getProdutoByID() {
    Produto produto = new Produto();
    BigDecimal geladeira = new BigDecimal(1000);
    produto.setPreco(geladeira);
    produto.setNome("Geladeira");
    produtoServiceTest.adicionarProduto(produto);
    Produto found = produtoServiceTest.buscarProduto(produto.getID());
    assertThat(found.getNome()).isEqualTo(produto.getNome());
    }

    @Test
    public void contaQuantosProdutosNoBanco() {
        Produto produto = new Produto();
        BigDecimal geladeira = new BigDecimal(1000);
        produto.setPreco(geladeira);
        produto.setNome("Geladeira");
    produtoServiceTest.adicionarProduto(produto);
    long qtd = produtoServiceTest.quantidadeDeProdutos();
    assertThat(qtd).isEqualTo(3);
    }
}