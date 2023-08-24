<jsp:include page="../header.jsp">
    <jsp:param value="Thank You !" name="HTMLtitle" />
</jsp:include>
<div class="container text-center bg-info bg-gradient text-body py-5 rounded-4 h-100">
    <h1>Thank You !</h1>
    <p class="text-dark">Nice! you have been successfully registered under ${email}  . <br> Check your email for the activation link</p>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ver">
	  Get Confirmation Link
	</button>

</div>
<!-- Modal -->
<div class="modal fade text-center" id="ver" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
      	<i class='bx bxs-envelope fs-2'></i>
      	<h2>You've got mail!</h2>
      	<p>Click here to activate your account</p>
       	<a href="verified" class="btn btn-secondary">Continue</a>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>