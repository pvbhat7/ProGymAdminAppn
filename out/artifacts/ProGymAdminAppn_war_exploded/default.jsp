<%
if(session.getAttribute("loggedInUser") != null)
response.sendRedirect("index");
else
response.sendRedirect("login");
%>