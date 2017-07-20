package ams.docstore.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Document {

	private String docId;
	private String name;
	/**
	 * Media Type notation, e.g. "image/png"
	 */
	private String type;
	private String source;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate date;
	@JsonFormat(pattern = "kk:mm")
	private LocalTime time;
	private String description;
	@JsonIgnore
	private byte[] documentFile;
	

	public Document() {
	}
	
	public Document(String id, String name, String type) {
		this.docId = id;
		this.name = name;
		this.type = type;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(byte[] documentFile) {
		this.documentFile = documentFile;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docId == null) ? 0 : docId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (docId == null) {
			if (other.docId != null)
				return false;
		} else if (!docId.equals(other.docId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Document [docId=" + docId + ", name=" + name + ", type=" + type + ", source=" + source + "]";
	}
	
	
}
