package il.ac.hit.model;

import java.util.List;

/**
 * every model for this program needs to implement these basic methods.
 */

public interface IProductsDAO {
	/**
	 * login function - matches username and password with the users table in the DB.
	 * @param userName
	 * @param password
	 * @return
	 * @throws ProductsManagementException
	 */
    public User login(String userName, String password) throws ProductsManagementException;
    /**
     * sign up function - adds new user to the users table in the DB.
     * @param u
     * @throws ProductsManagementException
     */
    public void signUp(User u) throws ProductsManagementException;
    /**
     * add product function - adds new product to the products table in the DB
     * @param product
     * @throws ProductsManagementException
     */
    public void addProduct(Product product) throws ProductsManagementException;
    /**
     * get product function - gets all products from the products table, and returns as list to the controller.
     * @return
     * @throws ProductsManagementException
     */
    public List<Product> getProducts() throws ProductsManagementException;
}
