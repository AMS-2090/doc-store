package ams.docstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.docstore.domain.Document;
import ams.docstore.domain.repository.DocumentRepository;
import ams.docstore.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	public List<Document> getAllDocuments() {
		return documentRepository.getAllDocuments();
	}

	@Override
	public Document getDocumentById(String docId) {
		return documentRepository.getDocumentById(docId);
	}
	
}
