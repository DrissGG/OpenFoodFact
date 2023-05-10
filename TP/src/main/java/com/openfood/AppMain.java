package com.openfood;

import java.io.IOException;

import jakarta.persistence.EntityManager;
import service.LectureFichier;

public class AppMain {

	public static void main(String[] args) throws IOException {
		LectureFichier lectureFichier = new LectureFichier();
		
		lectureFichier.parseFile();
		
		JPAUtils.getInstance().getEntityManager().close();
	}

}
