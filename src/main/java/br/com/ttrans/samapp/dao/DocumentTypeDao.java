package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.DocumentType;

public interface DocumentTypeDao {
	public void add(DocumentType type, Authentication authentication);
	public void edit(DocumentType type, Authentication authentication);
	public void delete(DocumentType type, Authentication authentication);
	public List<DocumentType> loadData();
}
