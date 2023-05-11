package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openfood.model.Additif;
import com.openfood.model.Allergene;
import com.openfood.model.Categorie;
import com.openfood.model.Ingredient;
import com.openfood.model.Marque;
import com.openfood.model.NutriScore;
import com.openfood.model.Produit;

import dao.AdditifDAO;
import dao.AllergeneDAO;
import dao.CategorieDAO;
import dao.DAOFactory;
import dao.IngredientDAO;
import dao.MarqueDAO;
import dao.ProduitDAO;

public class LectureFichier {
	
	private final CategorieDAO categorieDAO;
    private final MarqueDAO marqueDAO;
    private final IngredientDAO ingredientDAO;
    private final AdditifDAO additifDAO;
    private final AllergeneDAO allergeneDAO;
    private final ProduitDAO produitDAO;

    public LectureFichier() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
        this.marqueDAO = daoFactory.getMarqueDAO();
        this.ingredientDAO = daoFactory.getIngredientDAO();
        this.additifDAO = daoFactory.getAdditifDAO();
        this.allergeneDAO = daoFactory.getAllergeneDAO();
        this.produitDAO = daoFactory.getProduitDAO();
    }

	public void parseFile() throws IOException {
		Path pathFile = Paths.get("open-food-facts.csv"); // emplacement du fichier 
		List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
	
		int count= 0;
		
		long debut = System.currentTimeMillis();
		
		for(String line : lines) {
			if(count == 0) {
				count++;
				continue;
			}
			Produit produit = new Produit();
			
			String[] values = line.split("\\|") ;	
			if(values.length > 30) {
				count++;
				continue;			
			}
			
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
//				    CategorieDAO categorieDAO = DAOFactory.getInstance().getCategorieDAO();
	                Categorie categorie = categorieDAO.findByName(values[i].trim());
	                if (categorie == null) {
	                    categorie = new Categorie();
	                    categorie.setName(values[i].trim());
	                    categorieDAO.create(categorie);
	                }
	                produit.setCategory(categorie);						
					 
					break;
				case 1:
//	                MarqueDAO marqueDAO = DAOFactory.getInstance().getMarqueDAO();
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
				case 2:
					produit.setName(values[i].trim());
					break;
				case 3:
					produit.setNutriscore(NutriScore.valueOf(values[i].trim().toUpperCase()));
					break;
				case 4:
//					IngredientDAO ingredientDAO = DAOFactory.getInstance().getIngredientDAO();
	                //String[] ingredientsTab = values[i].trim().replaceAll("\\(.*?\\)", "").replaceAll("[()]", "").replaceAll("\\d+\\s+%", "").replaceAll("\\d\\.+", "").replaceAll("\\d+%", "").replaceAll("[_\\*]", "").split(",");
					String[] ingredientsTab = values[i].replaceAll("\\d+\\s*[d+%.]|\\(.*?\\)|\\*|_|\\(|\\)", "").replaceAll("\\s*,\\s*,\\s*", ", ").trim().split(",");
					for (String ing : ingredientsTab) {
	                	
	                	Ingredient ingredient = ingredientDAO.findByName(ing);
		                if (ingredient == null) {
		                	ingredient = new Ingredient();
		                	ingredient.setName(ing);
		                	ingredientDAO.create(ingredient);
		                }
		                produit.getIngredients().add(ingredient);
					}
					
					break;
				case 5:
					try {
						produit.setEnergy(Float.parseFloat(values[i].trim()));
					} catch (NumberFormatException e) {
					}
					
					break;
				case 6:
					produit.setGraisse(Float.valueOf(values[i].trim()));
					break;
				case 7:
					produit.setSucres(Float.valueOf(values[i].trim()));
					break;
				case 8:
					produit.setFibres(Float.valueOf(values[i].trim()));
					break;
				case 9:
					produit.setProteines(Float.valueOf(values[i].trim()));
					break;
				case 10:
					produit.setSel(Double.valueOf(values[i].trim()));
					break;
				case 11:
					produit.setVitA(Double.valueOf(values[i].trim()));
					break;
				case 12:
					produit.setVitD(Double.valueOf(values[i].trim()));
					break;
				case 13:
					produit.setVitE(Double.valueOf(values[i].trim()));
					break;
				case 14:
					produit.setVitK(Double.valueOf(values[i].trim()));
					break;
				case 15:
					produit.setVitC(Double.valueOf(values[i].trim()));
					break;
				case 16:
					produit.setVitB1(Double.valueOf(values[i].trim()));
					break;
				case 17:
					produit.setVitB2(Double.valueOf(values[i].trim()));
					break;
				case 18:
					produit.setVitPP(Double.valueOf(values[i].trim()));
					break;
				case 19:
					produit.setVitB6(Double.valueOf(values[i].trim()));
					break;
				case 20:
					produit.setVitB9(Double.valueOf(values[i].trim()));
					break;
				case 21:
					produit.setVitB12(Double.valueOf(values[i].trim()));
					break;
				case 22:
					produit.setCalcium(Double.valueOf(values[i].trim()));
					break;
				case 23:
					produit.setMagnesium(Double.valueOf(values[i].trim()));
					break;
				case 24:
					produit.setIron(Double.valueOf(values[i].trim()));
					break;
				case 25:
					produit.setFer(Double.valueOf(values[i].trim()));
					break;
				case 26:
					produit.setBetaCarotene(Double.valueOf(values[i].trim()));
					break;
				case 27:
					produit.setPresenceHuilePalme(Boolean.valueOf(values[i].trim()));
					break;
				case 28: //allergenes
//					AllergeneDAO allergeneDAO = DAOFactory.getInstance().getAllergeneDAO();
	                String[] allergeneTab = values[i].trim().split(",");
	                for (String all : allergeneTab) {
	                	
	                	Allergene allergene = allergeneDAO.findByName(all.trim());
		                if (allergene == null) {
		                	allergene = new Allergene();
		                	allergene.setName(all.trim());
		                	allergeneDAO.create(allergene);
		                }
		                produit.getAllergenes().add(allergene);
					}
					break;
				case 29: //additifs
//					AdditifDAO additifDAO = DAOFactory.getInstance().getAdditifDAO();
	                String[] additifTab = values[i].trim().split(",");
	                for (String addit : additifTab) {
	                	//rechercher espace indexof
	                	String code = addit.substring(0,addit.indexOf(" ")).trim();
	                	String name = addit.substring(addit.indexOf("-")+ 1).trim();
	                	
	                	Additif additif = additifDAO.findByCode(code);
	               
		                if (additif == null ) {
		                	additif = new Additif();
		                	additif.setNom(name);
		                	additif.setCode(code);
		                	additifDAO.create(additif);
		                }

		                produit.getAdditifs().add(additif);
					}
	                

					break;

				default:
					break;
				}
            	
            }
            produitDAO.create(produit);
        	count++; // incrémentation du compteur
            if (count == 1000) { // condition de sortie de la boucle
                break;
            }
            
            
		}
		long fin = System.currentTimeMillis();
		
        System.out.println("Temps écoulé en millisecondes :" + (fin - debut));
		System.out.println("Temps écoulé en minutes :" + (fin - debut)/60000);
		
		
	}

}
