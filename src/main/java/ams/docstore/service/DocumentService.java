package ams.docstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ams.docstore.domain.Document;

public interface DocumentService {

	List<Document> getAllDocuments();
	Document getDocumentById(String docId);
	
	void addDocument(Document document);
	void addDocumentFile(String id, MultipartFile file) throws IOException;
	
	boolean checkDocumentById(String docId);
}
