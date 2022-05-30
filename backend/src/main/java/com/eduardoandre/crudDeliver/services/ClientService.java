package com.eduardoandre.crudDeliver.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardoandre.crudDeliver.dto.ClientDTO;
import com.eduardoandre.crudDeliver.entities.Client;
import com.eduardoandre.crudDeliver.repositories.ClientRepository;
import com.eduardoandre.crudDeliver.services.exceptions.DataBaseException;
import com.eduardoandre.crudDeliver.services.exceptions.NotFoundEntityException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(c -> new ClientDTO(c));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new NotFoundEntityException("Entidade não encontrada"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new NotFoundEntityException("Id não encontrado!");
						
		}
	}

	public void delete(Long id) {
		try {
		    repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new NotFoundEntityException("Id não encontrado! " + id);			
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade violada!");
		}	
	}
	
	
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
