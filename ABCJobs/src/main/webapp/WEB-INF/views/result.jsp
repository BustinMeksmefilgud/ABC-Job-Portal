<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="ABCJobs.bean.User"%>
<jsp:include page="../header.jsp">
    <jsp:param value="Result" name="HTMLtitle" />
</jsp:include>

<div class="container">
	<div class="alert alert-success alert-dismissible fade show ${message == null ? "d-none" : "d-flex"}" role="alert">
	  ${message}
	  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
    <div class="row rounded-3 p-3 mb-4 bg-info bg-gradient">
        <div class="col-2 border-none rounded-circle align-self-stretch text-center fs-1 d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
        style="width:200px; height:200px;">
            <span>${f}</span>
            <span>${l}</span>
        </div>
        <div class="col-8 p-5">
            <h2>${fullName}</h2>
            <p>${city}<br>${country}</p>
        </div>
        <% if((Boolean) session.getAttribute("isLoggedIn") != null) { %>
        <div class="col-2 p-3 text-center">
            <button class="btn btn-success" id="follow">Follow</button>
        </div>
        <% } %>
    </div>

            <div class="row">
	            <div class="col-12 col-lg-3">
	                    <div class="rounded-3 mb-5 pb-5 p-3 bg-info bg-gradient text-dark">
	                        <div class="d-flex align-items-center">
	                            <i class='bx bx-building fs-2'></i>
	                            <div class="p-2">
	                                <h4>Company</h4>
	                                <p>${company}</p>
	                            </div>
	                        </div>
	                        <div class="d-flex align-items-center">
	                            <i class='bx bx-laptop fs-2'></i>
	                            <div class="p-2">
	                                <h4>Title</h4>
	                                <% if((Boolean) session.getAttribute("isAdmin") != null) { %>
									    <p>Administrator</p>
								  	<% } else { %>
								    	<p>${title}</p>
								    <% } %>
	                            </div>
	                        </div>
	                        <div class="d-flex align-items-center">
	                            <i class='bx bx-envelope fs-2'></i>
	                            <div class="p-2">
	                                <h4>Email</h4>
	                                <p>${email}</p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-12 col-lg-9">
	                    <div class="mb-3">
	                        <h3>${firstName}'s Posts</h3>
	                        <hr>
			                    <c:forEach var="t" items="${post}">
			                    <div class="container bg-info bg-gradient rounded-5">
	                      <div class="d-flex">
	                      
	                         <a class="navbar-brand col-1 my-4 ms-3 py-4 border-none rounded-circle align-self-stretch text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
				               href="/abcjobs/viewProfile?Fname=${t.getUser().getUserprofile().getFname()}"
				               style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
				                <span>${t.getUser().getUserprofile().getFname().charAt(0)}</span>
				                <span>${t.getUser().getUserprofile().getLname().charAt(0)}</span>
				            </a>
				            <a class="navbar-brand my-4 ms-3 pt-2 text-dark"
				               href="/abcjobs/viewProfile?Fname=${t.getUser().getUserprofile().getFname()}">
				                ${t.getUser().getEmail()}
				            </a>
				 	       	  </div>
				 	       	  <h4 class=" pt-2 px-4">
				 	       	    <a class="navbar-brand"
				               href="/abcjobs/thread?Tid=${t.getId()}">
				 	       	 		 ${t.getContent()}
				 	       	   </a>
				 	       	   </h4>
				 	       	   <div class="row mb-3">
					 	       	   <div class="col-6 text-center p-2 text-dark">
					 	       	  	 <i class='bx bx-like fs-2'></i>
					 	       	   </div>
					 	       	   <div class="col-6 text-center p-2 text-dark">
					 	       	   <a class="navbar-brand"
				               href="/abcjobs/thread?Tid=${t.getId()}">
					 	       	   	<i class='bx bx-chat fs-2'></i>
					 	       	   	</a>
					 	       	   </div>
				 	       	   </div>
	                        </div>
								</c:forEach>
	
	                       </div>
	                 
	                </div>
                
            </div>

        </div>
<script>
	const followBtn = document.querySelector("#follow");
	followBtn.addEventListener("click", () => {
		if(followBtn.innerHTML == "Follow") {
			followBtn.classList.remove("btn-success");
			followBtn.classList.add("btn-danger");
			followBtn.innerHTML = "Unfollow";
		} else {
			followBtn.classList.add("btn-success");
			followBtn.classList.remove("btn-danger");
			followBtn.innerHTML = "Follow";
		}
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>