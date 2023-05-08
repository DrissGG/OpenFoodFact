package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAOFactory {

	    private EntityManager entityManager;
	    private static DAOFactory instance;
	    
	    private DAOFactory() {
	        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OpenFood");
	        entityManager = entityManagerFactory.createEntityManager();
	    }
	    
	    public static DAOFactory getInstance() {
	        if(instance == null) {
	            instance = new DAOFactory();
	        }
	        return instance;
	    }
	    
	    public ProduitDAO getProduitDAO() {
	        return new ProduitDAO(entityManager);
	    }
	    
	    public  CategorieDAO getCategorieDAO() {
	        return new CategorieDAO(entityManager);
	    }
	    
	    // autres méthodes pour d'autres entités si nécessaire
	    
	    public void close() {
	        entityManager.close();
	    }
}



