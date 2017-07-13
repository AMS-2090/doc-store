package ams.docstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ams.docstore.domain.Document;
import ams.docstore.service.DocumentService;

@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/all")
	public List<Document> allDocuments() {
		return documentService.getAllDocuments();
	}
	
	@RequestMapping("/documents/{id}")
	public Document getDocumentById(@PathVariable String id) {
		return documentService.getDocumentById(id);
	}
	
	@RequestMapping(value = "/documents", method = RequestMethod.POST)
	public void addDocument(@RequestBody Document document) {
		documentService.addDocument(document);
	}
}
