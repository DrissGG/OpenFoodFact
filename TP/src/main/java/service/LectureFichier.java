package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openfood.model.Categorie;
import com.openfood.model.Marque;
import com.openfood.model.Produit;

import dao.CategorieDAO;
import dao.DAOFactory;
import dao.MarqueDAO;
import dao.ProduitDAO;

public class LectureFichier {

	public void parseFile() throws IOException {
		// TODO Auto-generated method stub
		Path pathFile = Paths.get("open-food-facts.csv"); // emplacement du fichier 
		List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
	
		List<String> data = new ArrayList<>();
		int count= 0;
		
		for(String line : lines) {
			if(count == 0) {
				count++;
				continue;
			}
			Produit produit = new Produit();
			
			String[] values = line.split("\\|") ;
            for (int i = 0; i< values.length; i++) {
            	if(values[i].trim().length() == 0 ) {
            		continue;
            	}
            	switch (i) {
            	/*
            	 * Récupérer le nom de la catégorie dans la première colonne
            	 * Vérifier si la catégorie existe déjà dans la base de données en utilisant une instance de CategorieDAO
            	 * Si la catégorie n'existe pas, créer une nouvelle instance de Categorie et l'enregistrer dans la base de données en utilisant la méthode create de CategorieDAO
            	 * Récupérer l'objet Categorie correspondant (soit celui existant, soit celui que vous venez de créer) et l'utiliser pour remplir l'attribut categorie du produit.
            	 * */
				case 0: 
				    CategorieDAO categorieDAO = DAOFactory.getInstance().getCategorieDAO();
	                Categorie categorie = categorieDAO.findByName(values[i].trim());
	                if (categorie == null) {
	                    categorie = new Categorie();
	                    categorie.setName(values[i].trim());
	                    categorieDAO.create(categorie);
	                }
	                produit.setCategory(categorie);		
					
					 
					break;
				case 1:
	                MarqueDAO marqueDAO = DAOFactory.getInstance().getMarqueDAO();
	                String[] marquesTab = values[i].trim().split(",");
	                for (String m : marquesTab) {
	                	
	                	Marque marque = marqueDAO.findByName(m);
		                if (marque == null) {
		                    marque = new Marque();
		                    marque.setName(m);
		                    marqueDAO.create(marque);
		                }
		                produit.getMarques().add(marque);
					}
	                
	                break;

				default:
					break;
				}
            	
            }
                 
            DAOFactory.getInstance().getProduitDAO().create(produit);
        	count++; // incrémentation du compteur
            if (count == 500) { // condition de sortie de la boucle
                break;
            }
            
		}
		
	}

}
