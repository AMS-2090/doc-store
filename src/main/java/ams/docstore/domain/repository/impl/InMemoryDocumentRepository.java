package ams.docstore.domain.repository.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
		
		Document firstDoc = new Document("001", "first", "image/png");
		firstDoc.setSource("Scanner_1");
		firstDoc.setDate(LocalDate.now());
		firstDoc.setTime(LocalTime.now());
		firstDoc.setDescription("This is the first document in this temporary repository.");
		
		/*
		 * Load image from static resource and convert it into byte array
		 */
		File docFile = new File("src/main/resources/static/EKG.png");
		byte[] imageByteArray = new byte[0];
		
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();){
			
			BufferedImage buffImage = ImageIO.read(docFile);
			ImageIO.write(buffImage, "png", baos);
			baos.flush();
			imageByteArray = baos.toByteArray();
		} catch (IOException e) {
			System.out.println("Could not read or write an image.");
			e.printStackTrace();
		}
		
		// Set a loaded byte array image to Document field
		firstDoc.setDocumentFile(imageByteArray);
		
		listOfDocuments.add(firstDoc);
	}
	
	@Override
	public List<Document> getAllDocuments() {
		return listOfDocuments;
	}

	@Override
	public Document getDocumentById(String docId) {
		return listOfDocuments.stream().filter(doc -> doc.getDocId().equals(docId)).findFirst().get();
	}

	@Override
	public void addDocument(Document document) {
		listOfDocuments.add(document);
	}
}
