package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.DocumentTypeDao;
import br.com.ttrans.samapp.model.DocumentType;
import br.com.ttrans.samapp.service.DocumentTypeService;

@Repository
public class DocumentTypeServiceImpl implements DocumentTypeService {
	
	@Autowired
	private DocumentTypeDao dao;

	@Transactional
	public void add(DocumentType type, Authentication authentication) {
		dao.add(type, authentication);
	}

	@Transactional
	public void edit(DocumentType type, Authentication authentication) {
		dao.edit(type, authentication);
	}

	@Transactional
	public void delete(DocumentType type, Authentication authentication) {
		dao.delete(type, authentication);
	}

	@Transactional
	public List<DocumentType> loadData() {
		return dao.loadData();
	}

}
