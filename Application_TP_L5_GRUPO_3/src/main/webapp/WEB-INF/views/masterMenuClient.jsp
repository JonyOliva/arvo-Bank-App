<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="Include.html"></jsp:include>
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<title>ARVO Bank</title>
<%
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
  String date = sdf.format(new Date());
%>

</head>

<body>

<nav class="navbar navbar-light bg-light">
  <a>
  <img alt="logo" src="Resources/images/logo3.png">
  </a>
  
   
  <div class="dropdown align-middle">
  <div id="userMenu">
     

  <button class="btn dropdown-toggle" type="button" id="btnUser" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <i class="material-icons">account_circle</i>
   <span id="userName"> Nombre Usuario</span>
    </button>
   
  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
    <form><button class="dropdown-item" type="submit" name="btnLogout"><span class="material-icons">
exit_to_app</span> Cerrar Sesi�n</button></form>
    </div>
</div>
  </div>
 
</nav>



<nav class="navbar navbar-expand-lg navbar-dark font-weight-bold" style="background-color: #1761a0;">
 
    <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mx-auto">
           <li class="nav-item">
        <a class="nav-link text-light ml-4" href="clAccounts.do">Cuentas  </a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light ml-3" href="clTransfers.do">Transferencias </a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light ml-3" href="clLoans.do" <%-- href="loans.jsp?view=pageMyLoans" --%> >Pr�stamos</a>
      </li>
    </ul>
  </div>
  <div class="text-light font-weight-normal"> <%=date%></div>
</nav>


</body>
</html>