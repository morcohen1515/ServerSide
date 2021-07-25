<!DOCTYPE html>
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
    <div class="row mt-3">
        <div class="col-md-6 mx-auto">
            <div class="card card-body">
                <h3 class="text-center">Add item</h3>
                <form action="http://localhost:8888/Productmanagement/router/user/addcostitem" method="get">
                    
                    <!-- fields -->
                    <div class="form-group mb-4">
                        <input placeholder="Category" type="text" class="form-control" name="category">
                    </div>
                    <div class="form-group mb-4">
                        <input placeholder="Title" type="text" class="form-control" name="title">
                    </div>
                    <div class="form-group mb-4">
                       <input placeholder="Price" type="number" class="form-control" name="price">
                    </div>
                    <div class="form-group mb-4">
                        <input placeholder="Description" type="text" class="form-control" name="description">
                    </div> 
                    
                    <!-- error message -->
                    <% String message = (String)request.getAttribute("message"); %>
                    <%
                    if(message != null){
                    	%>
                    	<h6 style="color:red;" class="mb-3 mt-3"><%out.println(message);%></h6>
                    	<%
                    }
                    %>    
                     
                     <!-- button -->                              
                    <div class="container">
                    <button class="btn btn-success" type="submit" style=" width: 150px; margin-top:10px; margin-left: auto; margin-right: auto;">Submit</button>
                	</div> 
                	
                </form>
            </div>
        </div>
    </div>
</body>
</html>