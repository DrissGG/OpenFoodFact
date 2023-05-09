package dao;

import java.util.List;

import com.openfood.JPAUtils;
import com.openfood.model.Allergene;
import com.openfood.model.Categorie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AllergeneDAO implements IDAO<Allergene>{
	
	Allergene allergene = null;
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	
	public Allergene findByName(String name) {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Allergene a WHERE a.name = :name", Allergene.class);
		query.setParameter("name", name);
		/* getResultList() récupérer un unique résultat de la requête */
		try {
			allergene = query.getSingleResult();
		} 
		catch (NoResultException e) {
			// rien faire ici
		}
		return allergene;
	}
	
	@Override
	public List<Allergene> findAll() {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Categorie a", Allergene.class);
		return query.getResultList();
	}



	@Override
	public void create(Allergene allergene) {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		entityManager.persist(allergene);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Allergene entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Allergene entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Allergene findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
