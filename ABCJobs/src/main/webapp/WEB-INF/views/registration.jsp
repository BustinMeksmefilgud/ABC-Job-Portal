<jsp:include page="../header.jsp">
	<jsp:param value="Registration" name="HTMLtitle" />
</jsp:include>
<div class="container text-center bg-info bg-gradient text-body py-5 rounded-4">
	<h3>Registration</h3>
	
	<div class="alert alert-success alert-dismissible fade show my-3 ${ errMsg == null ? "d-none" : "d-block" }" role="alert">
  		${ errMsg }
  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	
	<form action="registration" method="post">
	<div class="row justify-content-center">
		<div class="col-3 mb-3">
			<label for="firstName" class="form-label">First Name</label>
			<input type="text" class="form-control" id="firstName" name="firstName" required>
			<div class="invalid-feedback">
	      		First Name is required
    		</div>
		</div>
		
		<div class="col-3 mb-3">
			<label for="lastName" class="form-label">Last Name</label>
			<input type="text" class="form-control" id="lastName" name="lastName" required>
			<div class="invalid-feedback">
	      		Last Name is required
    		</div>
		</div>
	    </div>
		<div class="row mb-3 justify-content-center">
			<label for="emailAddresss" class="form-label">Email address</label>
			<input type="email" class="form-control w-50" id="emailAddress" name="emailAddress" required>
			<div class="invalid-feedback">
	      		Email address invalid
    		</div>
		</div>

		<div class="row mb-3 justify-content-center">
			<label for="password" class="form-label">Password</label>
			<input type="password" class="form-control w-50" id="password" name="password" required>
			<div class="invalid-feedback">
	      		Password required & must match
    		</div>
		</div>
		
		<div class="row mb-3 justify-content-center">
			<label for="passwordConfirmation" class="form-label">Password Confirmation</label>
			<input type="password" class="form-control w-50" id="passwordConfirmation" name="passwordConfirmation" required>
			<div class="invalid-feedback">
	      		Password required & must match
    		</div>
		</div>
		<button type="submit" class="btn btn-primary">Register</button>
		<p class="mb-5">Have an account? <a href="login" class="mb-3 text-light">Login</a></p>
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>