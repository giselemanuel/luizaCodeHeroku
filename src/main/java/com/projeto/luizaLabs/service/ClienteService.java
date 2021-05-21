package com.projeto.luizaLabs.service;

import com.projeto.luizaLabs.entity.Cliente;
import com.projeto.luizaLabs.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente buscarCliente(long id)
    {
        return clienteRepository.findByID(id);
    }
 
    public Cliente atualizarCliente(Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public long quantidadeDeClientes(){
        return clienteRepository.count();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findByID(id);
    }
}
