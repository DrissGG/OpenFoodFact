package com.openfood;

import java.io.IOException;

import dao.AllergeneDAO;
import dao.CategorieDAO;
import dao.IngredientDAO;
import dao.MarqueDAO;
import dao.ProduitDAO;
import service.LectureFichier;
import service.menuUser;

public class AppMain {

	public static void main(String[] args) throws IOException {
		
		// Appel de la méthode parseFile pour importer les données du fichier après la fermeture de l'EntityManager
		LectureFichier lectureFichier = new LectureFichier();		
		lectureFichier.parseFile();
		
		ProduitDAO produitDAO = new ProduitDAO();
        CategorieDAO categorieDAO = new CategorieDAO();
        MarqueDAO marqueDao = new MarqueDAO();
        IngredientDAO ingredientDAO = new IngredientDAO();
        AllergeneDAO allegeneDao = new AllergeneDAO();
        
        // Créez une instance de menuUser en lui passant les DAOs en tant que dépendances
        menuUser menu = new menuUser(produitDAO, categorieDAO,marqueDao,ingredientDAO,allegeneDao);

        // Exécutez le menu
        menu.run();
		
		JPAUtils.getInstance().getEntityManager().close();
		
		
        
	}

}
