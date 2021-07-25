package il.ac.hit.model;
/**
 * *
 * User class
 * also has a hibernate.cfg file for mapping to the DB		
 */
public class User {
	private int id;
	private String name;
    private String password;
    
    /**
     * constructor with no parameters, one of the conditions for JavaBean.
     */
    public User() {}
    /**
     * constructor with parameters.
     * @param name
     * @param password
     * @throws ProductsManagementException
     */
    public User(String name, String password) throws ProductsManagementException{
    	setName(name);
    	setPassword(password);
    }
    /**
     * get id.
     * @return
     */
    public int getId() {
		return id;
	}
    /**
     * set id.
     * @param id
     * @throws ProductsManagementException
     */
	public void setId(int id) throws ProductsManagementException {
        if(id == 0){
            throw new ProductsManagementException("id cannot be zero");
        }
		this.id = id;
	}
	/**
	 * get password.
	 * @return
	 */
    public String getPassword() {
        return password;
    }
    /**
     * set password.
     * @param password
     * @throws ProductsManagementException
     */
    public void setPassword(String password) throws ProductsManagementException {
        // check if id is empty.
        if(password == null){
            throw new ProductsManagementException("password cannot be zero");
        }
        this.password = password;
    }
    /**
     * get name.
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * set name.
     * @param name
     * @throws ProductsManagementException
     */
    public void setName(String name) throws ProductsManagementException {
        // check if Product name is empty.
        if(name.length() == 0){
            throw new ProductsManagementException("User name cannot be empty");
        }
        //check if Product name include space
        for(int i=0; i<name.length(); i++){
            if(name.charAt(i) == ' '){
                throw new ProductsManagementException("User name not valid");
            }
        }
        this.name = name;
    }
}
