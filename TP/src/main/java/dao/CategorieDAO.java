package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.mysql.cj.Query;
import com.openfood.model.Categorie;

import jakarta.persistence.EntityManager;

public class CategorieDAO implements IDAO<Categorie> {

    private EntityManager entityManager;
    Statement st = null;
	PreparedStatement ps =null; 

    public CategorieDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


	public Categorie findById(int id) {
        return entityManager.find(Categorie.class, id);
    }

    @Override
    public List<Categorie> findAll() {
        Query query = (Query) entityManager.createQuery("SELECT c FROM Categorie c");
        return ((jakarta.persistence.Query) query).getResultList();
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
		// TODO Auto-generated method stub
		return null;
	}
}
    
