<%@ page import="ABCJobs.bean.User" %>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container d-flex justify-content-center align-items-center" style="height: 60vh;">
	<div class="row text-center align-items-center w-100">
		<div class="col-6 p-5 mb-3 bg-info bg-gradient rounded-4">
			<h1><span class="text-dark">ABC</span> Jobs</h1>
			<p class="text-light">Start your learning journey with us</p>
			<% if((Boolean) session.getAttribute("isLoggedIn") != null) { %>
			 <a href="logout" class="btn btn-danger">Logout</a>
			   <% } else { %>
			<a href="login" class="btn btn-primary">Login</a>
			<a href="registration" class="btn btn-primary">Sign Up</a>
			<% } %>
		</div>
	   <div class="col-6">
	   <div class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="images/Jobs.jpg" class="d-block w-100" alt="Apply for a job now">
    </div>
    <div class="carousel-item">
      <img src="images/carousek.jpg" class="d-block w-100" alt="Find Work!">
    </div>
    <div class="carousel-item">
      <img src="images/register bg.jpg" class="d-block w-100" alt="Register with us">
    </div>
    <div class="carousel-item">
      <img src="images/joob.jpg" class="d-block w-100" alt="It's not just a theory">
    </div>
  </div>
</div>
	
	   </div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>