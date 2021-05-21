package com.projeto.luizaLabs.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    @Column(name = "cpf")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Column(name = "nome")
    @Size(min = 2, max = 100)
    private String nome;

    //Getter and Setter

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
