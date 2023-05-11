package dao;

import com.openfood.JPAUtils;

import jakarta.persistence.EntityManager;

public class DAOFactory {
	
	private static DAOFactory INSTANCE = new DAOFactory();
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();
	
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() 
	{
		return INSTANCE ;
	}
	
	public ProduitDAO getProduitDAO() 
	{
		return new ProduitDAO();
	}
	
	public CategorieDAO getCategorieDAO() 
	{
		return new CategorieDAO();
	}
	
	public MarqueDAO getMarqueDAO() 
	{
		return new MarqueDAO();
	}
	
	public IngredientDAO getIngredientDAO() 
	{
		return new IngredientDAO();
	}
	public AllergeneDAO getAllergeneDAO() 
	{
		return new AllergeneDAO();
	}
	public AdditifDAO getAdditifDAO() 
	{
		return new AdditifDAO();
	}
	
	public void close() 
	{
		entityManager.close();
	}

	
	
}



