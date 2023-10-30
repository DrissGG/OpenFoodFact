package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Allergene;
import com.openfood.model.Categorie;
import com.openfood.model.Ingredient;
import com.openfood.model.Marque;
import com.openfood.model.Produit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProduitDAO implements IDAO<Produit> {

    private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
    
    /**
     * Récupère un produit par son identifiant.
     *
     * @param id L'identifiant du produit à récupérer.
     * @return Le produit correspondant à l'identifiant, ou null s'il n'existe pas.
     */
    public Produit findById(int id) {
        return entityManager.find(Produit.class, id);
    }

    /**
     * Récupère tous les produits de la base de données.
     *
     * @return Une liste de tous les produits.
     */
    public List<Produit> findAll() {
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p", Produit.class);
        return query.getResultList();
    }

    /**
     * Crée un nouveau produit dans la base de données.
     *
     * @param produit Le produit à créer.
     */
    @Override
    public void create(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.persist(produit);
        entityManager.getTransaction().commit();
    }

    /**
     * Met à jour un produit dans la base de données.
     *
     * @param produit Le produit à mettre à jour.
     */
    @Override
    public void update(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
    }

    /**
     * Supprime un produit de la base de données.
     *
     * @param produit Le produit à supprimer.
     */
    @Override
    public void delete(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.remove(produit);
        entityManager.getTransaction().commit();
    }

	@Override
	public Produit findById(Long id) {
		return null;
	}
	
	
	/**
	 * Récupère les 10 produits les mieux notés dans une catégorie donnée.
	 *
	 * @param category La catégorie pour laquelle on souhaite obtenir les produits.
	 * @return Une liste contenant les 10 produits les mieux notés de la catégorie.
	 */
	public List<Produit> getTopRatedProductsInCategory(Categorie category) {
	    EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	    TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.category = :category ORDER BY p.id", Produit.class)
	        .setParameter("category", category)
	        .setMaxResults(10);
	    return query.getResultList();
	}
	
	/**
	 * Récupère les 10 produits les mieux notés d'une marque donnée.
	 *
	 * @param marque La marque pour laquelle on souhaite obtenir les produits les mieux notés.
	 * @return Une liste contenant les 10 produits les mieux notés de la marque.
	 */
	 public List<Produit> getTopRatedProductsInMarque(Marque marque) {
	        EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE :marque MEMBER OF p.marques ORDER BY p.nutriscore ASC", Produit.class)
	            .setParameter("marque", marque)
	            .setMaxResults(10);
	        return query.getResultList();
	    }
	 
	 
	 /**
	  * Récupère les 10 produits les mieux notés dans une catégorie donnée qui ne contiennent pas un ingrédient spécifié.
	  *
	  * @param category   La catégorie pour laquelle on souhaite obtenir les produits.
	  * @param ingredient L'ingrédient à exclure des produits.
	  * @return Une liste contenant les 10 produits les mieux notés de la catégorie sans l'ingrédient spécifié.
	  */
	public List<Produit> getTopRatedProductsInCategoryWithoutIngredient(Categorie category, Ingredient ingredient) {
	    EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	    TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.category = :category AND :ingredient NOT MEMBER OF p.ingredients ORDER BY p.id DESC", Produit.class)
	        .setParameter("category", category)
	        .setParameter("ingredient", ingredient)
	        .setMaxResults(10);
	    return query.getResultList();
	}
	
	/**
	 * Récupère les 10 produits les mieux notés d'une marque donnée qui ne contiennent pas un ingrédient spécifié.
	 *
	 * @param marque              La marque pour laquelle on souhaite obtenir les produits.
	 * @param ingredientToExclude L'ingrédient à exclure des produits.
	 * @return Une liste contenant les 10 produits les mieux notés de la marque sans l'ingrédient spécifié.
	 */
	public List<Produit> getTopRatedProductsByMarqueWithoutIngredient(Marque marque, Ingredient ingredientToExclude) {
        EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE :marque MEMBER OF p.marques AND :ingredient NOT MEMBER OF p.ingredients ORDER BY p.nutriscore ASC", Produit.class)
            .setParameter("marque", marque)
            .setParameter("ingredient", ingredientToExclude)
            .setMaxResults(10);
        return query.getResultList();
    }
	
	
	/**
	 * Récupère les 10 produits les mieux notés dans une catégorie donnée qui ne contiennent pas un allergène spécifié.
	 *
	 * @param categorie           La catégorie pour laquelle on souhaite obtenir les produits.
	 * @param allergeneToExclude  L'allergène à exclure des produits.
	 * @return Une liste contenant les 10 produits les mieux notés de la catégorie sans l'allergène spécifié.
	 */
	 public List<Produit> getTopRatedProductsByCategoryWithoutAllergene(Categorie categorie, Allergene allergeneToExclude) {
	        EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.category = :categorie AND :allergene NOT MEMBER OF p.allergenes ORDER BY p.nutriscore ASC", Produit.class)
	            .setParameter("categorie", categorie)
	            .setParameter("allergene", allergeneToExclude)
	            .setMaxResults(10);
	        return query.getResultList();
	    }

	 
	 /**
	  * Récupère les 10 produits les mieux notés d'une marque donnée qui ne contiennent pas un allergène spécifié.
	  *
	  * @param marque              La marque pour laquelle on souhaite obtenir les produits.
	  * @param allergeneToExclude  L'allergène à exclure des produits.
	  * @return Une liste contenant les 10 produits les mieux notés de la marque sans l'allergène spécifié.
	  */
	 public List<Produit> getTopRatedProductsByMarqueWithoutAllergene(Marque marque, Allergene allergeneToExclude) {
	        EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE :marque MEMBER OF p.marques AND :allergene NOT MEMBER OF p.allergenes ORDER BY p.nutriscore ASC", Produit.class)
	            .setParameter("marque", marque)
	            .setParameter("allergene", allergeneToExclude)
	            .setMaxResults(10);
	        return query.getResultList();
	    }

}


