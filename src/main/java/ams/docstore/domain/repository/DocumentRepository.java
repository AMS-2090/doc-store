package ams.docstore.domain.repository;

import java.util.List;

import ams.docstore.domain.Document;

public interface DocumentRepository {

	List<Document> getAllDocuments();
	
	Document getDocumentById(String docId);
}
