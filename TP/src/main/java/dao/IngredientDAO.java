package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Categorie;
import com.openfood.model.Ingredient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class IngredientDAO {
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	
	public Ingredient findByName(String name) {
        Ingredient ingredient = null;
   
        TypedQuery<Ingredient> query = entityManager.createQuery("SELECT i FROM Ingredient i WHERE i.name = :name", Ingredient.class);
        query.setParameter("name", name);
        /* getResultList() récupérer un unique résultat de la requête */
        try {
        	ingredient = query.getSingleResult();
		} catch (NoResultException e) {
			// rien faire ici
		}       
        
        return ingredient;
    }

    
    public List<Ingredient> findAll() {
        TypedQuery<Ingredient> query = entityManager.createQuery("SELECT i FROM Ingredient i", Ingredient.class);
        return query.getResultList();
    }
    
    public Categorie findById(Long id) {
		return entityManager.find(Categorie.class, id);
	}

    
    public void create(Ingredient ingredient) {
        entityManager.getTransaction().begin();
        entityManager.persist(ingredient);
        entityManager.getTransaction().commit();
    }
    
    
	
}
