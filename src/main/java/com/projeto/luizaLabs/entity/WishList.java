package com.projeto.luizaLabs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "whishlist")
public class WishList  implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToMany
    @Column(name = "idProduto")
    private List<Produto> produto = new ArrayList<>();

    public boolean deletarProduto(Produto produtos){
        if(existeProduto(produtos)){
            produto.remove(produtos);
            return true;
        }
        return false;
    }

    public boolean existeProduto(Produto produtos){
        return produto.contains(produtos);
    }

    //Getter and Setter

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
}
