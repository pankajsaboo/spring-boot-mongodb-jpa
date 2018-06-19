package com.templateproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.templateproject.domain.Container;
import com.templateproject.repository.ContainerRepository;

@Service
public class ContainerService {
	
	private static final String CONTAINER_SEQ_KEY = "container";
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private ContainerRepository containerRepository;
	
	public Container save(Container container) throws Exception{
		container.setId(sequenceService.getNextSequenceId(CONTAINER_SEQ_KEY));
		return containerRepository.save(container);
	}
	
	public List<Container> findAll() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return containerRepository.findAll(sortByCreatedAtDesc);
	}
	
	public ResponseEntity<Optional<Container>> findById(long id) { 
		Optional<Container> container = containerRepository.findById(id);
        if(container == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(container, HttpStatus.OK);
        }
	}
	
	public void deleteById(long id) {
		containerRepository.deleteById(id);
	}
	
	public ResponseEntity<Container> update(long id, Container container) {
		Optional<Container> containerData = containerRepository.findById(id);
        if(containerData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Container data = containerData.get();
        data.setContainerName(container.getContainerName());
        data.setDescription(container.getDescription());
        return new ResponseEntity<>(containerRepository.save(data), HttpStatus.OK);
	}
	
}
