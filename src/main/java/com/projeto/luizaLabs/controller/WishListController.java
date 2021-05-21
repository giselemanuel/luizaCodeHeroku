package com.projeto.luizaLabs.controller;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.repository.WishListRepository;
import com.projeto.luizaLabs.service.ProdutoService;
import com.projeto.luizaLabs.service.WishListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.entity.WishList;
import com.projeto.luizaLabs.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WishListController {
    private static final int MAXSIZE = 20;

    @Autowired
    private WishListService wishlistService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    //Adicionar um produto na wishlist
    @ApiOperation(value = "Adicionar um produto na wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foi adicionado um novo produto na sua wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })

    @PutMapping("/wishlist/{id_cliente}/{id_produto}")
    public ResponseEntity<WishList> adicionarProdutosNaWishlist(@PathVariable long id_cliente, @PathVariable long id_produto) {

        try {
            //ver se o cliente existe
            Cliente cliente = clienteService.buscarCliente(id_cliente);

            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                //ver se o cliente tem wishlist
                WishList wishListDoCliente = wishlistService.procurarPeloIDCliente(id_cliente);

                Produto produtoAdicionado  = produtoService.buscarProduto(id_produto);

                //se não existir wishlist
                if (wishListDoCliente == null && produtoAdicionado != null)
                {
                    WishList wishListCriada = new WishList();
                    wishListCriada.setCliente(cliente);

                    List<Produto> produto = new ArrayList<>();
                    produto.add(produtoAdicionado);

                    wishListCriada.setProduto(produto);
                    wishlistService.criarWishList(wishListCriada);

                    return new ResponseEntity<>(HttpStatus.OK);
                }

                //pegar os produtos da wishlist
                List<Produto> listaDeProdutoDoCliente = wishListDoCliente.getProduto();

                if (produtoAdicionado == null)
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                //verifica se produto já está na wishlist
                for (int i = 0; i < listaDeProdutoDoCliente.size(); i++) {
                    if (listaDeProdutoDoCliente.get(i) == produtoAdicionado)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                //verifica se já existem 20 produtos na wishlist
                if(listaDeProdutoDoCliente.size() >= 20)
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                //adiciona o produto na lista do cliente
                listaDeProdutoDoCliente.add(produtoAdicionado);

                //atualiza a lista do cliente junto com o novo produto
                wishListDoCliente.setProduto(listaDeProdutoDoCliente);
                wishlistService.atualizarWishlist(wishListDoCliente);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar todas as wishLists cadastradas
    @ApiOperation(value = "Visualizar todas as wishlists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Wishlists retornadas com sucesso", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })

    @GetMapping("/wishlist")
      public List<WishList> visualizarWishList(){
        Iterable<WishList> iterable = wishlistService.visualizarWishList();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

   //Visualizar wishlist pelo Id do cliente
   @ApiOperation(value = "Visualizar wishlist do cliente")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Wishlist retornada com sucesso", response = Response.class),
           @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
   })

    @GetMapping("/wishlist/cliente/{id_cliente}")
    public ResponseEntity<WishList> visualizarWishListIdCliente(@PathVariable long id_cliente){
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            WishList wishList = wishlistService.findByClientId(id_cliente);

            if (existeCliente == null || wishList == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(wishList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deletar um produto na WishList
    @ApiOperation(value = "Deletar um produto na wishlist do cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto da wishlist deletado com sucesso", response = Response.class),
            @ApiResponse(code = 404, message = "Produto não encontrado na wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @DeleteMapping("/wishlist/cliente/{id_cliente}/produto/{id_produto}")
    public ResponseEntity<WishList> removerProdutoNaWishlist(@PathVariable long id_produto, @PathVariable long id_cliente) {
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            WishList wishList = wishlistService.findByClientId(id_cliente);
            Produto existeProduto = produtoService.buscarProduto(id_produto);

            if (existeCliente == null || wishList == null || existeProduto == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            if (!wishList.deletarProduto(existeProduto))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            wishList = wishlistService.criarWishList(wishList);
            return new ResponseEntity<>(wishList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Consultar se um determinado produto está na wishlist do cliente
    @ApiOperation(value = "Consultar se determinado produto está na wishlist do cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto está na wishlist", response = Response.class),
            @ApiResponse(code = 404, message = "Produto não está na wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @GetMapping("/wishlist/cliente/{id_cliente}/produto/{nome}")
    public ResponseEntity<WishList> buscarProdutoNaWishlistCliente(@PathVariable long id_cliente,@PathVariable(value = "nome") String nome) {
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            WishList wishList = wishlistService.findByClientId(id_cliente);
            Produto produto = produtoService.findByNome(nome);

            if (existeCliente == null || wishList == null || produto == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

