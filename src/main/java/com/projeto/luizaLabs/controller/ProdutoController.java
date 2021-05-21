package com.projeto.luizaLabs.controller;

import com.projeto.luizaLabs.entity.Produto;
import com.projeto.luizaLabs.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //Adicionar Produto
    @ApiOperation(value = "Adicionar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foi adicionado um novo produto", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @PostMapping("/produto")
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        try {
            Produto respostaProduto = produtoService.adicionarProduto(produto);
            return new ResponseEntity<>(produtoService.adicionarProduto(produto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar produto pelo ID
    @ApiOperation(value = "Retornar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto retornado com sucesso", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @GetMapping("/produto/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable long id) {
        try {
            return new ResponseEntity<>(produtoService.buscarProduto(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Atualizar o produto
    @ApiOperation(value = "Atualizar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado com sucesso", response = Response.class),
            @ApiResponse(code = 404, message = "Produto não encontrado", response = Response.class),
            @ApiResponse(code = 400, message = "Requisição inválida", response = Response.class)
    })
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produtoatualizado, @PathVariable(value = "id") long id) {
        try {
            Produto produto = produtoService.buscarProduto(id);
            if(produto != null){
                produtoatualizado.setID(produto.getID());
                return new ResponseEntity<>((Produto) produtoService.atualizaProduto(produtoatualizado), HttpStatus.OK);
            }
            return new
                    ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
