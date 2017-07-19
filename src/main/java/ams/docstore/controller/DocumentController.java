package ams.docstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ams.docstore.domain.Document;
import ams.docstore.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {
// TODO: Java documentation
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/all")
	public List<Document> allDocuments() {
		return documentService.getAllDocuments();
	}
	
	@RequestMapping("/{id}")
	public Document getDocumentById(@PathVariable String id) {
		return documentService.getDocumentById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addDocument(@RequestBody Document document) {
		documentService.addDocument(document);
	}
	
	// TODO : add addDocumentFile method

	
	@RequestMapping("/{id}/file")
	public ResponseEntity<ByteArrayResource> getDocumentFile(@PathVariable String id) {
		Document doc = documentService.getDocumentById(id);
		byte[] docFile = doc.getDocumentFile();
		MediaType docMediaType = MediaType.parseMediaType(doc.getType());
		
		return ResponseEntity.ok()
				.contentLength(docFile.length)
				.contentType(docMediaType)
				.body(new ByteArrayResource(docFile));
	}
}
