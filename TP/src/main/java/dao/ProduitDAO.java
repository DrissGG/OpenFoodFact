package dao;

import java.util.List;

import com.openfood.model.Produit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProduitDAO implements IDAO<Produit> {

    private EntityManager entityManager;

    public ProduitDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Produit findById(int id) {
        return entityManager.find(Produit.class, id);
    }

    
    public List<Produit> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Produit p");
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
		// TODO Auto-generated method stub
		return null;
	}
}


