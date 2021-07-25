package il.ac.hit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.LinkedList;
import java.util.List;

/**
 * implements the IProduct interface and handles the interaction with the DB.
 */
public class ProductsDAO implements IProductsDAO {
	
	//session for connection.
    private SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    //implements singleton pattern for ProductsDAO
    private static ProductsDAO dao;

    static {
    	ProductsDAO.dao = new ProductsDAO();
    }
    /**
     * func. for singleton pattern.
     * @return
     */
    public static ProductsDAO getInstance() {
        return ProductsDAO.dao;
    }

	/**
	 * login function - matches username and password with the users table in the DB.
	 * @param userName
	 * @param password
	 * @return
	 * @throws ProductsManagementException
	 */
    @Override
    public User login(String userName, String password) throws ProductsManagementException {
    	//open session
    	Session sess = factory.openSession();    	
    	Transaction tx = null;
    	
        try{
            tx = sess.beginTransaction();
            //get users table query.
            List<User> users = sess.createQuery("from User").list();
            //checking for a match.
            for(int i=0; i<users.size(); i++) {
                if(users.get(i).getName().equals(userName) && users.get(i).getPassword().equals(password)) {
                    return users.get(i);
                }
            }
            
            tx.commit();
            return null;
            
    	} catch(HibernateException e) {
    		if(tx != null) {
    			tx.rollback();
    		}
    		throw new ProductsManagementException("failed to login.");    		
    	}
        //close connection.
        finally {
        	if(sess != null) {
        		try {
                    sess.close();        			
        		} catch(HibernateException e) {
        			e.printStackTrace();
            		throw new ProductsManagementException("Problem with a close session.");
        		}
        	}
        }
    }

    /**
     * sign up function - adds new user to the users table in the DB.
     * @param u
     * @throws ProductsManagementException
     */
    @Override
    public void signUp(User u) throws ProductsManagementException{
    	//open seesion
    	Session sess = factory.openSession();    	
    	Transaction tx = null;
    	
    	try {
            tx = sess.beginTransaction();
            //checks that users does not exists.
            List<User> users = sess.createQuery("from User").list();
            for(int i=0; i<users.size(); i++) {
                if(users.get(i).getName().equals(u.getName())) {
                    throw new ProductsManagementException("This username already exists");
                }
            }
            //saves changes to the users table.
            sess.save(u);
            
            tx.commit();
    		
    	} catch(HibernateException e) {
    		if(tx != null) {
    			tx.rollback();
    		}
    		throw new ProductsManagementException("failed to signup.");    		
    	}
        //close connection.
        finally {
        	if(sess != null) {
        		try {
                    sess.close();        			
        		} catch(HibernateException e) {
        			e.printStackTrace();
            		throw new ProductsManagementException("Problem with a close session.");
        		}
        	}
        }
    }
    
    /**
     * add product function - adds new product to the products table in the DB
     * @param product
     * @throws ProductsManagementException
     */
    @Override
    public void addProduct(Product product) throws ProductsManagementException {
    	//open session
    	Session sess = factory.openSession();    	
    	Transaction tx = null;
    	
        try{
            tx = sess.beginTransaction();
            //saves product to the products table.
            sess.save(product);
            
            tx.commit();
            
        } catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new ProductsManagementException("failed to add a product.");    		
		}
        //close connection.
	    finally {
        	if(sess != null) {
        		try {
                    sess.close();        			
        		} catch(HibernateException e) {
        			e.printStackTrace();
            		throw new ProductsManagementException("Problem with a close session.");
        		}
        	}
	    }      
    }

    /**
     * get product function - gets all products from the products table, and returns as list to the controller.
     * @return
     * @throws ProductsManagementException
     */
    @Override
    public List<Product> getProducts() throws ProductsManagementException {
    	//open session    	
    	Session sess = factory.openSession();    	
    	Transaction tx = null;
    	
        try{
            tx = sess.beginTransaction();
          //get products table query.
            List products = sess.createQuery("from Product").list();
            
            tx.commit();
            return products;
            
        } catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new ProductsManagementException("failed to get products.");    		
		}
        //close connection
	    finally {
        	if(sess != null) {
        		try {
                    sess.close();        			
        		} catch(HibernateException e) {
        			e.printStackTrace();
            		throw new ProductsManagementException("Problem with a close session.");
        		}
        	}
	    }
       
    }
}

