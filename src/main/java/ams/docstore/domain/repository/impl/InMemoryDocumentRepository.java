package ams.docstore.domain.repository.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ams.docstore.domain.Document;
import ams.docstore.domain.repository.DocumentRepository;

/*
 * Temporary hard-coded docs repo for testing purposes
 */
@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

	private List<Document> listOfDocuments;
	

	public InMemoryDocumentRepository() {
		
		listOfDocuments = new ArrayList<Document>();
		
		Document firstDoc = new Document("001", "first", "PDF");
		firstDoc.setSource("Scanner_1");
		firstDoc.setDate(LocalDate.now());
		firstDoc.setTime(LocalTime.now());
		firstDoc.setDescription("This is the first document in this temporary repository.");
		
		listOfDocuments.add(firstDoc);
	}
	
	@Override
	public List<Document> getAllDocuments() {
		return listOfDocuments;
	}

	@Override
	public Document getDocumentById(String docId) {
		return listOfDocuments.stream().filter(doc -> doc.getId().equals(docId)).findFirst().get();
	}
}
