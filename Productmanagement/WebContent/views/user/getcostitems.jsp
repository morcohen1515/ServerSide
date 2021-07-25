<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="il.ac.hit.model.Product" %>
<%@ page import="il.ac.hit.controller.UserController" %>
<%@ page import="il.ac.hit.model.ProductsDAO" %>
<%@ page import="il.ac.hit.model.User" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <title>Add cost item</title>
</head>
<body>
	<%@include file="navbar.jsp" %>
    <div class="col-md-11 mx-auto my-auto">     
    	<h3 class="text-center">Get items</h3>               
        <form action="http://localhost:8888/Productmanagement/router/user/getcostitems" method="get">	
			<table class="table">
		  		<thead>
				    <tr>
				      <th scope="col">Id</th>
				      <th scope="col">Category</th>
				      <th scope="col">Title</th>
				      <th scope="col">Price</th>
				      <th scope="col">Description</th>
				      <th scope="col">Month</th>
				    </tr>
		 		</thead>
	   			<tbody>
	   				<%
	                	List<Product> pl = (List<Product>)session.getAttribute("products");
	                 if(pl!=null){
	                     for(Product p : pl){ 
	             	%>
	               <tr>
					   <td><%out.println(p.getId()); %></td>
					   <td><%out.println(p.getCategoryName()); %></td>
				       <td><%out.println(p.getTitle());%></td>
				       <td><%out.println(p.getPrice());%></td>
				       <td><%out.println(p.getDescription());%></td>	
				       <td><%out.println(p.getMonth()+1);%></td>
			       </tr>						       
					<%
	                     } 
	                 %>
 				</tbody>
			</table>
	             <%
	             } else {
	            	 %>
	            	 <h4 style="color:red;">No products in this user.</h4>
	            	 <%
	             }
	       		%>					
       	</form>                    
     </div>
</body>
</html>
