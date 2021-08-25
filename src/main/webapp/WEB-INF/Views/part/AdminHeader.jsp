<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.navbar-light .navbar-brand, navbar-nav .show>.nav-link
{
color: rgb(255 248 248 / 90%);
}
.navbar-light .navbar-nav .active>.nav-link, .navbar-light .navbar-nav .nav-link.active, .navbar-light .navbar-nav .nav-link.show, .navbar-light .navbar-nav .show>.nav-link
{
color: rgb(255 248 248 / 90%);
}

</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-dark">
  <a class="navbar-brand" href="/ExpanseTracker/users">All Account</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <li class="nav-item active">
        <a class="nav-link" href="/ExpanseTracker/accountrole">Account Type<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/ExpanseTracker/category">Add Role<span class="sr-only">(current)</span></a>
      </li>
     <!--  <li class="nav-item active">
        <a class="nav-link textcolor" href="subcategory">Sub Category</a>
      </li> -->
     
    
    </ul>
    <div class="form-inline my-2 my-lg-0">
     <a href="/ExpanseTracker/logout">Logout</a>
     
    </div>
  </div>
</nav>

</body>
</html>