package il.ac.hit.controller;

import il.ac.hit.model.IProductsDAO;
import il.ac.hit.model.Product;
import il.ac.hit.model.ProductsDAO;
import il.ac.hit.model.ProductsManagementException;
import il.ac.hit.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * being accessed through the routerServlet
 * controller for all user actions */

public class UserController extends BasicController {

	/**
	 * constructor	
	 * @param dao
	 */
    public UserController(IProductsDAO dao) {
        super(dao);
    }
   
    /**
     * login function -checking for a matching user in the DB.
     * @param request
     * @param response
     * @return
     * @throws ServletException
     */
    public User login(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			
			//getting necessary  fields
			PrintWriter out = response.getWriter();
	        String userName = request.getParameter("name");
	        String userPassword = request.getParameter("password");
	        
	        //enter only if required fields are not empty.
        	if(userName != null) {
	        	//checking fields
		        if(userName!= "" && userPassword!= "") {
		            try {
		            	//creating dao object, using singleton pattern.
		        		ProductsDAO dao = ProductsDAO.getInstance();
		                try {
		                	User u = new User();
		                	//login func. from the ProductsDAO
		                	u = dao.login(userName, userPassword);
		                	//if user does not exist.
		                	if(u == null) {
		    					String message = "User Not Found.";
		    					//sends message throw the request.
		    					request.setAttribute("message", message);
		                	}
		                	//return user to RouterServlet.
		                	return u;
	
		                } catch (ProductsManagementException e) {
		                    e.getMessage();
		                }
		            } catch(NumberFormatException e) {
		                out.println("<br/>Problem with converting" + e.getMessage());
		            }
		        } else {
		        	//only if user pressed submit with empty fields.
					String message = "I Need All The Details.";
					request.setAttribute("message", message);					
		        }
			}	        
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
    }
    
    /**
     * logout function - close the session http request.
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) {
    	//closing session before logout.
    	HttpSession session = request.getSession(false);
    	session.invalidate();
    }
    
    /**
     * sign up function - enter new user to users table in the DB.
     * @param request
     * @param response
     * @throws ProductsManagementException
     */
	public void signup(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
		try {
			//getting necessary fields.
			PrintWriter out = response.getWriter();
	        String userName = request.getParameter("name");
	        String userPassword = request.getParameter("password");
	        
	        //enter only if required fields are not empty.
	        if(userName != null) {
		        //checking fields.
		        if(userName != "" && userPassword != "") {
		            try {
		                User u = new User(userName, userPassword);
		                //creating dao object, using singleton pattern.
		        		ProductsDAO dao = ProductsDAO.getInstance();
		                try {
		                	//calling sign up method from ProductsDAO.
		                    dao.signUp(u);
		                } catch (ProductsManagementException e) {
		                    e.getMessage();
		                }
		            } catch(NumberFormatException e) {
		                out.println("<br/>Problem with converting a string to double " + e.getMessage());
		            }
		        } else {
		        	//only if user pressed submit with empty fields.
					String message = "I Need All The Details.";
					request.setAttribute("message", message);
		        }
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
   
	/**
	 * add cost item function - adds producst to products table in the DB.
	 * @param request
	 * @param response
	 */
    public void addcostitem(HttpServletRequest request, HttpServletResponse response) {
		try {
			//getting necessary fields.
			PrintWriter out = response.getWriter();
	        String category = request.getParameter("category");
	        String title = request.getParameter("title");
	        String description = request.getParameter("description");
	        String priceString = request.getParameter("price");
	        
	      //enter only if required fields are not empty.
	        if(category != null) {		        
		        //checking fields.
		        if(category != "" && title != "" && description != "" && priceString != "") {
		            try {
		        		ProductsDAO dao = ProductsDAO.getInstance();
		                try {

		                	//getting http session.
		                	HttpSession session = request.getSession();
		                	String userName = (String) session.getAttribute("username");
		                	//converting price to int.
		                	int price = Integer.parseInt(priceString);
		                	Product p = new Product(category, title, description, price, userName);
		                	//calling add product func. from ProductsDAO.
		                	dao.addProduct(p);
		                	
		                } catch (ProductsManagementException e) {
		                    e.getMessage();
		                }
		            } catch(NumberFormatException e) {
		                out.println("<br/>Problem with converting" + e.getMessage());
		            }	        		
		        } else {
					String message = "I Need All The Details.";
					request.setAttribute("message", message);
		        }
	        }	        
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    /**
     * get cost item function - get all products from products table in the DB.
     * @param request
     * @param response
     */
    public void getcostitems(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			
            try {
        		ProductsDAO dao = ProductsDAO.getInstance();
                try {
                	//getting session, to match products that belong to the user.
                	HttpSession session = request.getSession();
                	String userName = null;
                	//getting the correct username.
                	userName = (String) session.getAttribute("username");

                	//enter if user exist.
                	if(userName != null) {                		
                    	//calling the get products method from ProductsDAO.
                        List<Product> products = dao.getProducts();
                        List<Product> filterProducts = new LinkedList();
                        
                         for(Product p : products) {
                        	 //getting only the product that match the username.
                        	if(p.getUserName().equals(userName)) {
                        		filterProducts.add(p);
                        	}
                        }
                        
                        //for displaying products in the getcostitems.jsp file
                         session.setAttribute("products", filterProducts);
                	} else {
                		throw new ProductsManagementException("no products in this user.");
                		//sand massage that no products in this user.
                	}
                } catch (ProductsManagementException e) {
                    e.getMessage();
                }
            } catch(NumberFormatException e) {
                out.println("<br/>Problem with converting" + e.getMessage());
            }

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
   
    /**
     * get cost per month function - get all products that were added in the same month, from the products table
     * @param request
     * @param response
     */
    public void getcostpermonth(HttpServletRequest request, HttpServletResponse response) {
		try {
			//getting necessary fields.
			PrintWriter out = response.getWriter();
	        String month = request.getParameter("month");
	        
	      //enter only if required fields are not empty.
	        if(month != null) {
		        if(month != "") {
		            try {
		            	//creating dao object , using singleton pattern.
		        		ProductsDAO dao = ProductsDAO.getInstance();
		                try {
		                	//getting session.
		                	HttpSession session = request.getSession();
		                	String userName = null;
		                	//getting the correct username.
		                	userName = (String) session.getAttribute("username");
		                	
		                	//if user exists.
		                	if(userName != null) {
		                		
		                    	List<Product> productsFilter = new LinkedList();
		                    	
		                    	//calling get products method from the ProductsDAO.
		                        List<Product> products = dao.getProducts();
		                        
//								//getting matching products and adding to productsFiters list, for displaying in getcostpermonth.jsp.
		            	        int monthItem =Integer.parseInt(month);
		            	        //getting all the product that match the username.
		                        for(Product p : products) {
		                        	if(p.getMonth() + 1 == monthItem && p.getUserName().equals(userName)) {
		                        		productsFilter.add(p);
		                        	}
		                        }
		                        //adding the product list to the session for display.
		                        session.setAttribute("productspermonth", productsFilter);
		                		
		                	} else {
		                		throw new ProductsManagementException("no products in this user.");
		                		//sand massage that no products in this user.
		                	}
		                } catch (ProductsManagementException e) {
		                    e.getMessage();
		                }
		            } catch(NumberFormatException e) {
		                out.println("<br/>Problem with converting" + e.getMessage());
		            }
		        	
		        } else {
					String message = "I Need a Number.";
					request.setAttribute("message", message);
		        }	        	
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}
