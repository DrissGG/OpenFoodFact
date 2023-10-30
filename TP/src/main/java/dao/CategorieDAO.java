package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Categorie;
import com.openfood.model.Produit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CategorieDAO implements IDAO<Categorie> {

    private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

//findby name catgorie par le nom 
    
	public Categorie findByName(String name) {
        Categorie categorie = null;
   
        TypedQuery<Categorie> query = entityManager.createQuery("SELECT c FROM Categorie c WHERE c.name = :name", Categorie.class);
        query.setParameter("name", name);
        /* getResultList() récupérer un unique résultat de la requête */
        try {
        	categorie = query.getSingleResult();
		} catch (NoResultException e) {
			// rien faire ici
		}       
        
        return categorie;
    }

    @Override
    public List<Categorie> findAll() {
        TypedQuery<Categorie> query = entityManager.createQuery("SELECT c FROM Categorie c", Categorie.class);
        return query.getResultList();
    }

    @Override
    public void create(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.persist(categorie);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.merge(categorie);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.remove(categorie);
        entityManager.getTransaction().commit();
    }

	@Override
	public Categorie findById(Long id) {
		return entityManager.find(Categorie.class, id);
	}
	
	public List<Produit> getTopRatedProductsInCategory(Categorie category) {
	    EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	    TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.category = :category ORDER BY p.rating DESC", Produit.class)
	        .setParameter("category", category)
	        .setMaxResults(10);
	    return query.getResultList();
	}

}
    
