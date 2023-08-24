<jsp:include page="../header.jsp">
	<jsp:param value="Reset" name="HTMLtitle" />
</jsp:include>

<div class="container text-center bg-info bg-gradient text-body py-5 rounded-4">
	<h1>Reset your password</h1>
	<form action="reset" method="post">
	  <div class="row mb-3 justify-content-center">
	    <label for="password" class="form-label">New Password</label>
	    <input type="password" class="form-control w-50" id="password" name="password" required>
	    <div class="invalid-feedback">
      		Password required & must be match
   		</div>
	  </div>
	  <div class="row mb-3 justify-content-center">
	    <label for="passwordConfirmation" class="form-label">New Password Confirmation</label>
	    <input type="password" class="form-control w-50" id="passwordConfirmation" required>
	    <div class="invalid-feedback">
      		Password required & must be match
   		</div>
	  </div>
	  <button type="submit" class="btn btn-primary">Update Password</button>
	</form>
</div>

<jsp:include page="../footer.jsp"></jsp:include>