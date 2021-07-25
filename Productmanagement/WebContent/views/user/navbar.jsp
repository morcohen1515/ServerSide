<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Navbar</title>
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
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/addcostitem">Add Item</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/getcostitems">Items</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/getcostpermonth">Item per month</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8888/Productmanagement/router/user/logout">LogOut</a>
                </li>
            </ul>
            </div>
        </div>
    </nav>
</body>
</html>