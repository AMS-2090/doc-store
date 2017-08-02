package ams.docstore.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ams.docstore.domain.Document;
import ams.docstore.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	/** 
	 * @return list of all stored documents
	 */
	@RequestMapping("/all")
	public List<Document> allDocuments() {
		return documentService.getAllDocuments();
	}
	
	/**
	 * Get a document by given id
	 * 
	 * @param id  document id from request's path
	 * @return document
	 */
	@RequestMapping("/{id}")
	public Document getDocumentById(@PathVariable String id) {
		return documentService.getDocumentById(id);
	}
	
	/**
	 * Store a given document
	 * 
	 * @param document  given document from a request's body
	 * @return ResponseEntity containing Http Status 201 and
	 * plain text message with URI location of a newly created resource
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addDocument(@RequestBody Document document) {
		documentService.addDocument(document);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(document.getDocId()).toUri();
		
		return ResponseEntity.created(location)
				.contentType(MediaType.TEXT_PLAIN)
				.body("Document has been created at location: " + location);
	}
	
	/**
	 * Upload document file to the existing document of a given id
	 * 
	 * @param id  given document id from request's path
	 * @param file  Multipart file from request's body
	 * @return ResponseEntity containing Http Status (200 if success, 400 otherwise) and
	 * plain text message about request result
	 * @throws IOException when internal file adding procedure fails
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@PathVariable String id
											,@RequestParam("file") MultipartFile file) throws IOException {
		if (documentService.checkDocumentById(id)) {
			try {
				documentService.addDocumentFile(id, file);
			} catch (IOException e) {
				throw new IOException("Document file [id = " + id + "] upload has failed.\nError message: " + e.getMessage());
			}
			return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
					.body("Document file for id = " + id + " has been successfully uploaded.");
		} else {
			return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN)
					.body("Document for id = " + id + " does not exist.");
		}
	}
	
	/**
	 * Get document file of a given id
	 * 
	 * @param id  document id from request's path
	 * @return ResponseEntity containing requested file 
	 */
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
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException e) {
		return e.getMessage();
	}
	
	
	
	// TODO: handle Exceptions : NoSuchElement, NullPointer
}
