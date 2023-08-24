<jsp:include page="../header.jsp">
    <jsp:param value="Login" name="HTMLtitle" />
</jsp:include>
<div class="container text-center bg-info bg-gradient text-body py-5 rounded-4">
    <h3>Login</h3>
  
   	<div class="alert alert-danger alert-dismissible fade show my-3 ${ message == null ? "d-none" : "d-block" }" role="alert">
  		${ message }
  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	
	<div class="alert alert-success alert-dismissible fade show my-3 ${ scMessage == null ? "d-none" : "d-block" }" role="alert">
  		${ scMessage }
  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	
    <form action="login" method="post">
        <div class="row mb-3 justify-content-center">
            <label for="emailAddress" class="form-label">Email address</label>
            <input type="email" class="form-control w-50" id="emailAddress" name="emailAddress" required>
        </div>
        <div class="row mb-3 justify-content-center">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control w-50" id="password" name="password" required>
        </div>
        <a href="forgot-password" class="d-block mb-3 text-light">Forgot password?</a>
        <button type="submit" class="btn btn-primary mb-3">Log in</button>
         <p>Don't have an account yet? <a href="registration" class="mb-3 text-light">Register Now</a></p>
    </form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>