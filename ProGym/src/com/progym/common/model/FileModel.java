package com.progym.common.model;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
   private MultipartFile file;
   private String reference;
   
   

   public String getReference() {
	return reference;
}

public void setReference(String reference) {
	this.reference = reference;
}

public MultipartFile getFile() {
      return file;
   }

   public void setFile(MultipartFile file) {
      this.file = file;
   }
}