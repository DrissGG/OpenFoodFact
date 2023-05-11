package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Additif;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AdditifDAO implements IDAO<Additif>{
	
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	Additif additif = null;
	
	
	@Override
	public void create(Additif additif) {
		entityManager.getTransaction().begin();
		entityManager.persist(additif);
		entityManager.getTransaction().commit();
		
	}
	public Additif findByName(String name) {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a WHERE a.nom = :nom", Additif.class);
		query.setParameter("nom", name);
		/* getResultList() récupérer un unique résultat de la requête */
		try {
			additif = query.getSingleResult();
		} 
		catch (NoResultException e) {
			// rien faire ici
		}
		return additif;
	}
	public Additif findByCode(String code) {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a WHERE a.code = :code", Additif.class);
		query.setParameter("code", code);
		/* getResultList() récupérer un unique résultat de la requête */
		try {
			additif = query.getSingleResult();
		} 
		catch (NoResultException e) {
			// rien faire ici
		}
		return additif;
	}
	
	
	public List<Additif> findAll() {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Categorie a", Additif.class);
		return query.getResultList();
	}
	
	@Override
	public void update(Additif additif) {}

	@Override
	public void delete(Additif additif) {}

	@Override
	public Additif findById(Long id) {
		return null;
	}
	
	

}
