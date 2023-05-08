package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.openfood.model.Categorie;
import com.openfood.model.Marque;
import com.openfood.model.Produit;

import dao.CategorieDAO;
import dao.DAOFactory;
import dao.MarqueDAO;

public class LectureFichier {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path pathFile = Paths.get("open-food-facts.csv"); // emplacement du fichier 
		List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
	
		List<String> data = new ArrayList<>();
		
		for(String line : lines) {
			Produit produit = new Produit();
			
			String[] values = line.split("\\|") ;
            for (int i = 0; i< values.length; i++) {
            	switch (i) {
            	/*
            	 * Récupérer le nom de la catégorie dans la première colonne
            	 * Vérifier si la catégorie existe déjà dans la base de données en utilisant une instance de CategorieDAO
            	 * Si la catégorie n'existe pas, créer une nouvelle instance de Categorie et l'enregistrer dans la base de données en utilisant la méthode create de CategorieDAO
            	 * Récupérer l'objet Categorie correspondant (soit celui existant, soit celui que vous venez de créer) et l'utiliser pour remplir l'attribut categorie du produit.
            	 * */
				case 0: //categories
				    CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	                Categorie categorie = categorieDAO.findByName(values[i].trim());
	                if (categorie == null) {
	                    categorie = new Categorie();
	                    categorie.setName(values[i].trim());
	                    categorieDAO.create(categorie);
	                }
	                produit.setCategorie(categorie);
				
					
					
					//appel dao categorieDAO.creat()  ou creat.update() 
					break;
				case 2:
	                MarqueDAO marqueDAO = DAOFactory.getMarqueDAO();
	                Marque marque = marqueDAO.findByName(values[i].trim());
	                if (marque == null) {
	                    marque = new Marque();
	                    marque.setName(values[i].trim());
	                    marqueDAO.create(marque);
	                }
	                produit.setMarque(marque);
	                break;

				default:
					break;
				}
            	
            	
            	
            }
            
            for(String value : values) {
            	System.out.print(value + " ");
            	
            }
            System.out.print("\n");
            // appel dao produit.CREAT() pour 
		}
		
//	
		
		

	}

}
