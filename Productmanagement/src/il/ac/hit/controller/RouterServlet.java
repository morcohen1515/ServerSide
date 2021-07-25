package il.ac.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import il.ac.hit.model.ProductsDAO;
import il.ac.hit.model.User;


/**
 * Servlet implementation class RouterServlet
 * all actions pass through here.
 */
@WebServlet("/RouterServlet")
public class RouterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String pkg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
        pkg = getServletConfig().getInitParameter("package");
    }

	/**
	 * identifying the action through the URL and uses reflection for calling the correct method. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check what the controller is
         * Check what the action is
         * Activate a function with reflection*/
		
		String text = request.getRequestURI();
        PrintWriter writer = response.getWriter();
        
        String[]  arr = text.split("/");
        String controller = null;
        String action = null;
        
        //extracting the controller.
        if(arr.length>3) {
            controller = arr[3];
        }
        //extracting the action.
        if(arr.length>4) {
            action = arr[4].toLowerCase();
        }

        try {
        	//setting and activating the reflection.		
            String className = pkg + "." + controller.substring(0, 1).toUpperCase() + controller.substring(1).toLowerCase() + "Controller";
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(il.ac.hit.model.IProductsDAO.class);
            Object object = constructor.newInstance(il.ac.hit.model.ProductsDAO.getInstance());
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);       
            method.invoke(object, request,response);
            
            //using switch to identify the action.
            switch(action) {
            case "getcostitems":
	            RequestDispatcher dispatcher0 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher0.include(request,response);  
              break;
            case "getcostpermonth":
	            RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher1.include(request,response);  
              break;
            case "addcostitem":
	            RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher2.include(request,response);  
                break;
            case "signup":
	            RequestDispatcher dispatcher3 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher3.include(request,response);  
                break;
            case "login":
	            RequestDispatcher dispatcher4 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher4.include(request,response);  
                break;
            case "logout":
	            RequestDispatcher dispatcher5 = getServletContext().getRequestDispatcher("/views/user/login.jsp");
	            dispatcher5.include(request,response);
                break;
                
            default:
            	showErrorMessage(request,response,"OOPS something went wrong.");
          }            

          //exceptions
        } catch(ClassNotFoundException e) {
            showErrorMessage(request,response,"The requested controler doesnot exist");
        } catch(NoSuchMethodException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (InvocationTargetException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class or invoking the action");
        } catch (InstantiationException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (IllegalAccessException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        }
    }
	
	/**
	 * identifying the action through the URL and uses reflection for calling the correct method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check what the controller is
         * Check what the action is
         * Activate a function with reflection*/
		
		String text = request.getRequestURI();
        PrintWriter writer = response.getWriter();
        
        String[]  arr = text.split("/");
        String controller = null;
        String action = null;
        
        //extracting the controller.
        if(arr.length>3) {
            controller = arr[3];
        }
        //extracting the action.
        if(arr.length>4) {
            action = arr[4].toLowerCase();
        }

        try {
        	//setting and activating the reflection.		
            String className = pkg + "." + controller.substring(0, 1).toUpperCase() + controller.substring(1).toLowerCase() + "Controller";
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(il.ac.hit.model.IProductsDAO.class);
            Object object = constructor.newInstance(il.ac.hit.model.ProductsDAO.getInstance());
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);       
            Object u = method.invoke(object, request,response);
            
            // for login action, enter only if the user exists. 
    		if(u!=null) {
    			
    			String userName =  ((User) u).getName();
    			//starting session.
    			HttpSession session = request.getSession();
    			//setting the username attribute in the session.
                session.setAttribute("username", userName);

                //forward to addcostitem jsp.
    			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/user/addcostitem.jsp");
    			dispatcher.forward(request,response);
			} else {
				//if user does not exists or required fields are empty.
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
	            dispatcher.forward(request,response);        			
			}
    		
        
          //exceptions
        } catch(ClassNotFoundException e) {
            showErrorMessage(request,response,"The requested controler doesnot exist");
        } catch(NoSuchMethodException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (InvocationTargetException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class or invoking the action");
        } catch (InstantiationException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (IllegalAccessException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        }
	}
	
	/**
	 * display error function.
	 * @param request
	 * @param response
	 * @param text
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void showErrorMessage(HttpServletRequest request, HttpServletResponse response,String text) throws ServletException, IOException {
        //sending message through request.
    	request.setAttribute("errormessage",text);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        dispatcher.forward(request,response);
    }
}
