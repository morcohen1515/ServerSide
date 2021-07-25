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
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Product Management</a>
            <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" 
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/login" method="get">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/signup" method="get">SignUp</a>
                </li>
            </ul>
            </div>
        </div>
    </nav>
    <div class="row mt-3">
        <div class="col-md-6 mx-auto">
            <div class="card card-body">
                <h3 class="text-center">SignUp</h3>
                <form action="http://localhost:8888/Productmanagement/router/user/signup" method="post">
                    <div class="form-group mb-4 mt-4">
                        <input placeholder="Username" type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group mb-4">
                        <input placeholder="password" type="password" class="form-control" name="password">
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
                    
                    <button class="btn btn-success" type="submit" style="margin-top:10px; margin-left: auto; margin-right: auto;">Submit</button>
                </form>
            </div>
        </div>
    </div>    
</body>
</html>