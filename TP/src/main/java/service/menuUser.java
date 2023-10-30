/**
 * 
 */
package service;

import java.util.List;
import java.util.Scanner;

import com.openfood.model.Allergene;
import com.openfood.model.Categorie;
import com.openfood.model.Ingredient;
import com.openfood.model.Marque;
import com.openfood.model.Produit;

import dao.AllergeneDAO;
import dao.CategorieDAO;
import dao.IngredientDAO;
import dao.MarqueDAO;
import dao.ProduitDAO;

/**
 * Cette classe représente un menu pour les utilisateurs afin d'effectuer différentes opérations liées aux produits alimentaires.
 */
public class menuUser {
	
	 private final ProduitDAO produitDAO;
	    private final CategorieDAO categorieDAO;
	    private final MarqueDAO marqueDao;
	    private final IngredientDAO ingredientDAO;
	    private final AllergeneDAO allergeneDao;

	    public menuUser(ProduitDAO produitDAO, CategorieDAO categorieDAO,MarqueDAO marqueDAO,IngredientDAO ingredientDAO,AllergeneDAO allergeneDao) {
	        this.produitDAO = produitDAO;
	        this.categorieDAO = categorieDAO;
	        this.marqueDao = marqueDAO;
	        this.ingredientDAO = ingredientDAO;
	        this.allergeneDao=allergeneDao;
	    }


	    public void run() {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("Sélectionnez une opération :");
	            System.out.println("1. Affichez les 10 produits les mieux notés d’une catégorie");
	            System.out.println("2. Affichez les 10 produits les mieux notés d’une marque");
	            System.out.println("3. Affichez les 10 produits les mieux notés d’une catégorie sans un ingrédient donné");
	            System.out.println("4. Affichez les 10 produits les mieux notés d’une catégorie sans un allergène donné");
	            System.out.println("5. Affichez les 10 produits les mieux notés d’une marque sans un ingrédient donné");
	            System.out.println("6. Affichez les 10 produits les mieux notés d’une marque sans un allergène donné");
	            System.out.println("7. Quitter");
	            
	            System.out.print("Votre choix : ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                	// Afficher les 10 produits les mieux notés d’une catégorie
	                    scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                    System.out.print("Saisissez le nom de la catégorie : ");
	                    String categoryName = scanner.nextLine();
	                    Categorie category = categorieDAO.findByName(categoryName);

	                    if (category != null) {
	                        List<Produit> topProductsInCategory = produitDAO.getTopRatedProductsInCategory(category);
	                        System.out.println("Les 10 produits les mieux notés dans la catégorie " + categoryName + " sont :");
	                        for (Produit produit : topProductsInCategory) {
	                            System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                        }
	                    } else {
	                        System.out.println("La catégorie " + categoryName + " n'existe pas.");
	                    }
	                    break;
	                case 2:
	                    // Demandez à l'utilisateur de saisir la marque et affichez les produits
	                	scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                    System.out.print("Saisissez le nom de la marque : ");
	                    String marqueName = scanner.nextLine();
	                    Marque marque = marqueDao.findByName(marqueName);

	                    if (marque != null) {
	                        List<Produit> topProductsInMarque = produitDAO.getTopRatedProductsInMarque(marque);
	                        System.out.println("Les 10 produits les mieux notés de la marque " + marqueName + " sont :");
	                        for (Produit produit : topProductsInMarque) {
	                            System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                        }
	                    } else {
	                        System.out.println("La marque " + marqueName + " n'existe pas.");
	                    }
	                    break;
	                case 3:
	                    // Demandez à l'utilisateur de saisir la catégorie et un ingrédient, puis affichez les produits
	                	 scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                	    System.out.print("Saisissez le nom de la catégorie : ");
	                	    String categoryName1 = scanner.nextLine();
	                	    Categorie category1 = categorieDAO.findByName(categoryName1);

	                	    if (category1 != null) {
	                	        System.out.print("Saisissez le nom de l'ingrédient à exclure : ");
	                	        String ingredientName = scanner.nextLine();
	                	        Ingredient ingredientToExclude = ingredientDAO.findByName(ingredientName);

	                	        if (ingredientToExclude != null) {
	                	            List<Produit> topProductsInCategoryWithoutIngredient = produitDAO.getTopRatedProductsInCategoryWithoutIngredient(category1, ingredientToExclude);
	                	            System.out.println("Les 10 produits les mieux notés de la catégorie " + categoryName1 + " sans l'ingrédient " + ingredientName + " sont :");
	                	            for (Produit produit : topProductsInCategoryWithoutIngredient) {
	                	                System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                	            }
	                	        } else {
	                	            System.out.println("L'ingrédient " + ingredientName + " n'existe pas.");
	                	        }
	                	    } else {
	                	        System.out.println("La catégorie " + categoryName1 + " n'existe pas.");
	                	    }
	                	    break;
	   
	                case 4:
	                    scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                    System.out.print("Saisissez le nom de la marque : ");
	                    String marqueName1 = scanner.nextLine();
	                    Marque marque1 = marqueDao.findByName(marqueName1);

	                    if (marque1 != null) {
	                        System.out.print("Saisissez le nom de l'ingrédient à exclure : ");
	                        String ingredientName = scanner.nextLine();
	                        Ingredient ingredientToExclude = ingredientDAO.findByName(ingredientName);

	                        if (ingredientToExclude != null) {
	                            List<Produit> topProductsByMarqueWithoutIngredient = produitDAO.getTopRatedProductsByMarqueWithoutIngredient(marque1, ingredientToExclude);
	                            System.out.println("Les 10 produits les mieux notés de la marque " + marqueName1 + " sans l'ingrédient " + ingredientName + " sont :");
	                            for (Produit produit : topProductsByMarqueWithoutIngredient) {
	                                System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                            }
	                        } else {
	                            System.out.println("L'ingrédient " + ingredientName + " n'existe pas.");
	                        }
	                    } else {
	                        System.out.println("La marque " + marqueName1 + " n'existe pas.");
	                    }
	                    break;

	                case 5:
	                	scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                    System.out.print("Saisissez le nom de la catégorie : ");
	                    String categorieName = scanner.nextLine();
	                    Categorie categorie = categorieDAO.findByName(categorieName);

	                    if (categorie != null) {
	                        System.out.print("Saisissez le nom de l'allergène à exclure : ");
	                        String allergeneName = scanner.nextLine();
	                        Allergene allergeneToExclude = allergeneDao.findByName(allergeneName);

	                        if (allergeneToExclude != null) {
	                            List<Produit> topProductsByCategoryWithoutAllergene = produitDAO.getTopRatedProductsByCategoryWithoutAllergene(categorie, allergeneToExclude);
	                            System.out.println("Les 10 produits les mieux notés de la catégorie " + categorieName + " sans l'allergène " + allergeneName + " sont :");
	                            for (Produit produit : topProductsByCategoryWithoutAllergene) {
	                                System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                            }
	                        } else {
	                            System.out.println("L'allergène " + allergeneName + " n'existe pas.");
	                        }
	                    } else {
	                        System.out.println("La catégorie " + categorieName + " n'existe pas.");
	                    }
	                    break;
	                case 6:
	                	 scanner.nextLine(); // Consommer la nouvelle ligne en attente
	                	    System.out.print("Saisissez le nom de la marque : ");
	                	    String marqueName11 = scanner.nextLine();
	                	    Marque marque11 = marqueDao.findByName(marqueName11);

	                	    if (marque11 != null) {
	                	        System.out.print("Saisissez le nom de l'allergène à exclure : ");
	                	        String allergeneName = scanner.nextLine();
	                	        Allergene allergeneToExclude = allergeneDao.findByName(allergeneName);

	                	        if (allergeneToExclude != null) {
	                	            List<Produit> topProductsByMarqueWithoutAllergene = produitDAO.getTopRatedProductsByMarqueWithoutAllergene(marque11, allergeneToExclude);
	                	            System.out.println("Les 10 produits les mieux notés de la marque " + marqueName11 + " sans l'allergène " + allergeneName + " sont :");
	                	            for (Produit produit : topProductsByMarqueWithoutAllergene) {
	                	                System.out.println(produit.getName() + " - Nutriscore: " + produit.getNutriscore());
	                	            }
	                	        } else {
	                	            System.out.println("L'allergène " + allergeneName + " n'existe pas.");
	                	        }
	                	    } else {
	                	        System.out.println("La marque " + marqueName11 + " n'existe pas.");
	                	    }
	                	    break;
	                case 7:
	                    System.out.println("Au revoir !");
	                    break;
	                default:
	                    System.out.println("Choix non valide. Veuillez réessayer.");
	                    break;
	            }
	        } while (choice != 4);
	    }
}
