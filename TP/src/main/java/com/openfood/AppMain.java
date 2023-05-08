package com.openfood;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.openfood.model.*;

import jakarta.persistence.EntityManager;

public class AppMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		EntityManager em = JPAUtils.getInstance().getEntityManager();
		Produit p1 = new Produit();
		
		em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
		
        System.out.println("Parfait : " + em);
		em.close();
	}

}
