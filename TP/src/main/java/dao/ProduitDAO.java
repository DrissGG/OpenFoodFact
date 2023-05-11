package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Produit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ProduitDAO implements IDAO<Produit> {

    private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
    

    public Produit findById(int id) {
        return entityManager.find(Produit.class, id);
    }

    
    public List<Produit> findAll() {
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p", Produit.class);
        return query.getResultList();
    }

    @Override
    public void create(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.persist(produit);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
    }

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
}


