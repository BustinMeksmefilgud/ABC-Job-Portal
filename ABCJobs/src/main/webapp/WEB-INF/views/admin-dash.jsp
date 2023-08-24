<%@ page import="ABCJobs.bean.User" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp">
    <jsp:param value="admin" name="HTMLtitle" />
</jsp:include>

<div class="container">
  <h1>Welcome back, ${adminName}</h1>
<div class="container">
	<table class="table table-hover text-dark my-3">
	    <thead>
	      <tr class="table-primary">
	        <th scope="col">#</th>
	        <th scope="col">Full Name</th>
	        <th scope="col">Email</th>
	        <th scope="col">Location</th>
	        <th scope="col">Company</th>
	        <th scope="col">Title</th>
	        <th scope="col">Choose Their Fate</th>
	      </tr>
	      
	    </thead>
	    <tbody>
	    	<% int i = 1; %>
		   	<c:forEach var="user" items="${user}">
	   		  <tr class="table-info">
		        <th scope="row"><%= i++ %></th>
		        <td>${user.getUserprofile().getFname()} ${user.getUserprofile().getLname()}</td>
		        <td>${user.getEmail()}</td>
		        <td>${user.getUserprofile().getCity()}, ${user.getUserprofile().getCountry()}</td>
		        <td>${user.getUserprofile().getCompany()}</td>
		        <td>${user.getUserprofile().getJob().getName()}</td>
		         <td class="text-center">
		          <a href="" data-bs-toggle="modal" data-bs-target="#editModal${user.getId()}"><i class='bx bx-edit fs-2 text-success px-3'></i></a>
		          <a href="" data-bs-toggle="modal" data-bs-target="#deleteModal${user.getId()}"><i class='bx bx-trash fs-2 text-danger px-3'></i></a>
		        </td>
		      </tr>
		      
		        <!-- edit confirmation -->
				<div class="modal fade" id="editModal${user.getId()}" style="z-index: 2000;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">Edit ${user.getUserprofile().getFname()}</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        Do you want to change this user's identity??
				      </div>
				      <form action="admin-up" method="post" class="modal-footer">
				      <input type="hidden" name="Uid" value="${user.getId()}">
				       <button type="submit" class="btn btn-outline-success ms-auto mb-4 me-3">Yes</button>
				      </form>
				    </div>
				  </div>
				</div>
		      
		       <!-- delete confirmation -->
				<div class="modal fade" id="deleteModal${user.getId()}" style="z-index: 2000;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">Delete ${user.getUserprofile().getFname()}</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        You want to really remove this user from this existence??
				      </div>
				      <form action="deleteUser" method="get" class="modal-footer">
				      <input type="hidden" name="Uid" value="${user.getId()}">
				       <button type="submit" class="btn btn-outline-danger ms-auto mb-4 me-3">Delete</button>
				      </form>
				    </div>
				  </div>
				</div>
				
				
		   	</c:forEach>
	    </tbody>
	  </table>
</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>