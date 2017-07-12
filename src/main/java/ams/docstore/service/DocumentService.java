package ams.docstore.service;

import java.util.List;

import ams.docstore.domain.Document;

public interface DocumentService {

	List<Document> getAllDocuments();
	Document getDocumentById(String docId);
	
	void addDocument(Document document);
}
