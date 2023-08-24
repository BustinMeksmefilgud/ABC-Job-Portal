<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="../header.jsp">
    <jsp:param value="admin-up" name="HTMLtitle" />
</jsp:include>

<div class="container">
    <form action="admin-up-process" method="post">
   	<input type="hidden" name="UPid" value="${id}"> 
        <div class="row border rounded-3 p-3 mb-4 bg-info">
                <div
                    class="col-2 border-none rounded-circle align-self-stretch text-center fs-1 d-flex align-items-center justify-content-center bg-primary bg-gradient text-white">
                    <span>${f}</span>
                    <span>${l}</span>
                </div>
                <div class="col-8 p-5">
                  <h2>Edit Profile</h2>
                  <p class="text-info">Let the bass kick!<br>O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee</p>
                </div>
                <div class="col-2 align-self-center">
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-9">
               <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="firstName" value="${firstName}" name="firstName" placeholder="First Name" required>
                        <label for="firstName">First Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="lastName" value="${lastName}" name="lastName" placeholder="Last Name" required>
                        <label for="lastName">Last Name</label>
                    </div>
                 <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${city}" name="city" placeholder="City" required>
                        <label for="city">City</label>
                    </div>
                    <div class="form-floating mb-3">
                          <input type="text" class="form-control" value="${country}" name="country" placeholder="Country" required>
                        <label for="country">Country</label>
                    </div>    
                   
               
                </div>
                <div class="col-12 col-lg-3">
                    <div>
                        <div class="d-flex align-items-center mb-3">
                            <i class='bx bx-building fs-2'></i>
                            <div class="form-floating ml-3">
                                <input type="text" class="form-control" id="company" name="company" value="${company}" required>
                                <label for="company">Company</label>
                            </div>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <i class='bx bx-envelope fs-2'></i>
                            <div class="form-floating ml-3">
                                <input type="text" class="form-control" id="email" name="email" value="${email}"
                                    disabled>
                                <label for="email">Email</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div> 
<script src="js/profile.js"></script>
<jsp:include page="../footer.jsp"></jsp:include>