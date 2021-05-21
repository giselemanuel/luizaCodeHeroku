package com.projeto.luizaLabs;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.service.ClienteService;
import com.projeto.luizaLabs.service.ProdutoService;
import com.projeto.luizaLabs.service.WishListService;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
@Transactional
public class WishListServiceTest {

    @Autowired
    WishListService wishlistServiceTest;
    @Autowired
    ClienteService clienteServiceTest;
    @Autowired
    ProdutoService produtoServiceTest;

    @Test
    void salvarWishListNoBanco() {
        //criando uma lista
        List<Produto> produto = new ArrayList<Produto>();
        WishList wishlist = new WishList();

        //criando um cliente
        Cliente clienteCarol = new Cliente();
        clienteCarol.setCpf("12345678987");
        clienteCarol.setNome("Carol");
        clienteServiceTest.adicionarCliente(clienteCarol);

        //criando o produto
        Produto prod = new Produto();
        prod.setNome("Televisão");
        prod.setDescricao("televisão mais linda da nossa vida");
        BigDecimal valor = new BigDecimal(10);
        prod.setPreco(valor);
        Produto create = produtoServiceTest.adicionarProduto(prod);

        produto.add(create);
        wishlist.setCliente(clienteCarol);
        wishlist.setProduto(produto);


        WishList wishlistSalva = wishlistServiceTest.criarWishList(wishlist);
        assertThat(wishlistSalva).isNotNull();
    }

    @Test
    public void atualizarWishList() {
        List<Produto> produto = new ArrayList<Produto>();
        WishList wishlist = new WishList();

        //criando um cliente
        Cliente clienteCarol = new Cliente();
        clienteCarol.setCpf("12345678987");
        clienteCarol.setNome("Carol");
        clienteServiceTest.adicionarCliente(clienteCarol);

        //criando o produto
        Produto prod = new Produto();
        prod.setNome("Televisão");
        prod.setDescricao("televisão mais linda da nossa vida");
        BigDecimal valor = new BigDecimal(10);
        prod.setPreco(valor);
        Produto create = produtoServiceTest.adicionarProduto(prod);

        produto.add(create);
        wishlist.setCliente(clienteCarol);
        wishlist.setProduto(produto);


       WishList wishListSalva = wishlistServiceTest.atualizarWishlist(wishlist);
        assertThat(wishListSalva.getId()).isEqualTo(wishlist.getId());
    }

    @Test
    public void visualizarWishList() {
        List<Produto> produto = new ArrayList<Produto>();
        WishList wishlist = new WishList();

        //criando um cliente
        Cliente clienteCarol = new Cliente();
        clienteCarol.setCpf("12345678987");
        clienteCarol.setNome("Carol");
        clienteServiceTest.adicionarCliente(clienteCarol);

        //criando o produto
        Produto prod = new Produto();
        prod.setNome("Televisão");
        prod.setDescricao("televisão mais linda da nossa vida");
        BigDecimal valor = new BigDecimal(10);
        prod.setPreco(valor);
        Produto create = produtoServiceTest.adicionarProduto(prod);

        produto.add(create);
        wishlist.setCliente(clienteCarol);
        wishlist.setProduto(produto);


        List<WishList> wishListSalva = wishlistServiceTest.visualizarWishList();
        assertThat(wishListSalva).isNotNull();
    }

    @Test
    public void findByClientId() {
        List<Produto> produto = new ArrayList<Produto>();
        WishList wishlist = new WishList();

        //criando um cliente
        Cliente clienteCarol = new Cliente();
        clienteCarol.setCpf("12345678987");
        clienteCarol.setNome("Carol");
        clienteServiceTest.adicionarCliente(clienteCarol);

        //criando o produto
        Produto prod = new Produto();
        prod.setNome("Televisão");
        prod.setDescricao("televisão mais linda da nossa vida");
        BigDecimal valor = new BigDecimal(10);
        prod.setPreco(valor);
        Produto create = produtoServiceTest.adicionarProduto(prod);

        produto.add(create);
        wishlist.setCliente(clienteCarol);
        wishlist.setProduto(produto);

        WishList wishListSalva = wishlistServiceTest.findByClientId(clienteCarol.getID());
        assertThat(wishListSalva).isEqualTo(wishlist.getId());
    }
}
