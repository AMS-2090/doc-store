package ams.docstore.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public void addDocument(Document document) {
		documentRepository.addDocument(document);
	}

	@Override
	public void addDocumentFile(String docId, MultipartFile file) throws IOException {
		
		byte[] byteArrayFile = file.getBytes();
		documentRepository.addDocumentFile(docId, byteArrayFile);
	}
	
}
