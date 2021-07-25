package il.ac.hit.model;

import java.util.Date;
/**
 * Product class
 * also has a hibernate.cfg file for mapping to the DB
 * */
public class Product {
	
	private int id;
	private String categoryName;
	private String title;
    private String description;
	private double price;
    private int month;
    private String userName;

    /**
     * constructor with no parameters, one of the conditions for JavaBean.
     */
	public Product() {}
	
	/**
	 * constructor with pararmeters.
	 * @param categoryName
	 * @param title
	 * @param description
	 * @param price
	 * @param userName
	 * @throws ProductsManagementException
	 */
    public Product(String categoryName, String title, String description, double price, String userName) throws ProductsManagementException {
    	this.setCategoryName(categoryName);
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        Date d = new Date();
        this.setMonth(d.getMonth());
        this.setUserName(userName);
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
	 * get category.
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * set category.
	 * @param categoryName
	 * @throws ProductsManagementException
	 */
	public void setCategoryName(String categoryName) throws ProductsManagementException {
        // check if category Name is empty.
        if(categoryName.length() == 0){
            throw new ProductsManagementException("category Name cannot be empty");
        }
		this.categoryName = categoryName;
	}
	/**
	 * get title.
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * set title.
	 * @param title
	 * @throws ProductsManagementException
	 */
	public void setTitle(String title) throws ProductsManagementException {
        // check if title is empty.
        if(title.length() == 0){
            throw new ProductsManagementException("title cannot be empty");
        }
		this.title = title;
	}
	/**
	 * get description.
	 * @return
	 */
    public String getDescription() {
        return description;
    }
    /**
     * set description.
     * @param description
     * @throws ProductsManagementException
     */
    public void setDescription(String description) throws ProductsManagementException {
        // check if description is empty.
        if(description.length() == 0){
            throw new ProductsManagementException("description cannot be empty");
        }
        this.description = description;
    }  
    /**
     * get price.
     * @return
     */
    public double getPrice() {
        return price;
    }
    /**
     * set price.
     * @param price
     * @throws ProductsManagementException
     */
    public void setPrice(double price) throws ProductsManagementException {
        // check if price is empty.
        if(price == 0){
            throw new ProductsManagementException("Price cannot be zero");
        }
        this.price = price;
    }
    /**
     * get month, for get items by month func.
     * @return
     */
    public int getMonth() {
		return month;
	}
    /**
     * set month, for get items by month func.
     * @param month
     * @throws ProductsManagementException
     */
	public void setMonth(int month) throws ProductsManagementException {
        // check if month is empty.
        if(month == 0){
            throw new ProductsManagementException("month cannot be zero");
        }
		this.month = month;
	}
	/**
	 * get username.
	 * @return
	 */
    public String getUserName() {
		return userName;
	}
    /**
     * set username.
     * @param userName
     * @throws ProductsManagementException
     */
	public void setUserName(String userName) throws ProductsManagementException {
        // check if user Name Name is empty.
        if(userName.length() == 0){
            throw new ProductsManagementException("user Name cannot be empty");
        }
		this.userName = userName;
	}

}
