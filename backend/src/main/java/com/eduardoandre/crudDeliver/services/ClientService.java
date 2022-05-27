package com.eduardoandre.crudDeliver.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardoandre.crudDeliver.dto.ClientDTO;
import com.eduardoandre.crudDeliver.entities.Client;
import com.eduardoandre.crudDeliver.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findAll(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		return new ClientDTO(entity);
	}

}
