package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Marque;
import com.openfood.model.Produit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class MarqueDAO implements IDAO<Marque> {

    private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

    @Override
    public List<Marque> findAll() {
        TypedQuery<Marque> query = entityManager.createQuery("SELECT m FROM Marque m", Marque.class);
        return query.getResultList();
    }

    @Override
    public Marque findById(Long id) {
        return entityManager.find(Marque.class, id);
    }
    
    public Marque findByName(String name) {
        Marque marque = null;
        
        TypedQuery<Marque> query = entityManager.createQuery("SELECT m FROM Marque m WHERE m.name = :name", Marque.class);
        query.setParameter("name", name);
        /* getResultList() récupérer un unique résultat de la requête */
        try {
        	marque = query.getSingleResult();
		} catch (NoResultException e) {
		}
        return marque;
        
    }

    @Override
    public void create(Marque marque) {
        entityManager.getTransaction().begin();
        entityManager.persist(marque);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Marque marque) {
        entityManager.getTransaction().begin();
        entityManager.merge(marque);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Marque marque) {
        entityManager.getTransaction().begin();
        entityManager.remove(marque);
        entityManager.getTransaction().commit();
    }
    
    public List<Produit> getTopRatedProductsByBrand(Marque brand) {
        EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p JOIN p.marques m WHERE m = :brand ORDER BY p.rating DESC", Produit.class)
            .setParameter("brand", brand)
            .setMaxResults(10);
        return query.getResultList();
    }

}
