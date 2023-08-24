<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="ABCJobs.bean.User" %>
        <jsp:include page="../header.jsp">
            <jsp:param value="Profile" name="HTMLtitle" />
        </jsp:include>

        <div class="container">
            <div class="alert alert-success alert-dismissible fade show ${message == null ? " d-none" : "d-flex" }"
                role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <div class="row rounded-3 p-3 mb-4 bg-info bg-gradient">
                <div class="col-2 border-none rounded-circle align-self-stretch text-center fs-1 d-flex align-items-center justify-content-center bg-primary bg-gradient text-white"
                 style="width:200px; height:200px;">
                    <span>${f}</span>
                    <span>${l}</span>
                </div>
                <div class="col-8 p-5">
                    <h2>${fullName}</h2>
                    <p>${city}<br>${country}</p>
                    
                 </div>
                <div class="col-2 p-3 text-center">
                    <a href="update-profile" class="btn btn-primary mb-1">Edit Profile</a>
                    <a href="logout" class="btn btn-danger mb-1">Logout</a><br/>
                    <% if((Boolean) session.getAttribute("hasJob") != null && (Boolean) session.getAttribute("isAdmin") == null) { %>
                   <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#jobQuit">
	               	Resign
	           		</button>
	            	<% } %>
                </div>
            </div>
            
              <!-- Job Resign Modal -->
				<div class="modal fade" id="jobQuit" style="z-index: 2000;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">Resign</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				     		You wanna quit being a ${title}
				      </div>
				      <form action="jobResign" method="post" class="modal-footer">
		           		<button type="submit" class="btn btn-outline-light ms-auto">Resign</button>
				      </form>
				    </div>
				  </div>
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
                                <% if(User.isAdmin()) { %>
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
                        <h3>Your Posts</h3>
                        <hr>
                         <c:forEach var="post" items="${yourpost}">
		         
 					<div class="container bg-info bg-gradient rounded-5">
                      <div class="d-flex">
                      
                         <div class="navbar-brand col-1 my-4 ms-3 py-4 border-none rounded-circle align-self-stretch text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
			               style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
			                <span>${post.getUser().getUserprofile().getFname().charAt(0)}</span>
			                <span>${post.getUser().getUserprofile().getLname().charAt(0)}</span>
			            </div>
			            <div class="navbar-brand my-4 ms-3 pt-2 me-5 text-dark">
			                ${post.getUser().getEmail()}
			            </div>
			            <a href="" class="my-4 ms-auto" data-bs-toggle="modal" data-bs-target="#deleteModal${post.getId()}">
			            	<i class='bx bx-trash fs-2 text-danger px-3'></i>
			            </a>
			 	       	  </div>
			 	       	  <h4 class=" pt-2 px-4">
			 	       	    <a class="navbar-brand"
			               href="/abcjobs/thread?Tid=${post.getId()}">
			 	       	 		 ${post.getContent()}
			 	       	   </a>
			 	       	   </h4>
			 	       	   <div class="row mb-3">
				 	       	   <div class="col-6 text-center p-2 text-dark">
				 	       	  	 <i class='bx bx-like fs-2'></i>
				 	       	   </div>
				 	       	   <div class="col-6 text-center p-2 text-dark">
				 	       	   <a class="navbar-brand"
			               href="/abcjobs/thread?Tid=${post.getId()}">
				 	       	   	<i class='bx bx-chat fs-2'></i>
				 	       	   	</a>
				 	       	   </div>
			 	       	   </div>
                        </div>
				<!-- delete confirmation -->
								<div class="modal fade" id="deleteModal${post.getId()}" style="z-index: 2000;">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="modal-title fs-5" id="exampleModalLabel">Delete post</h1>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        You want to remove the existence of this post??
								      </div>
								      <form action="deleteThreadUser" method="get" class="modal-footer">
								      <input type="hidden" name="Tid" value="${post.getId()}">
								       <button type="submit" class="btn btn-outline-danger ms-auto mb-4 me-3">Delete</button>
								      </form>
								    </div>
								  </div>
								</div>
							</c:forEach>
                       </div>
                 
                </div>
                
            </div>

        </div>
        <jsp:include page="../footer.jsp"></jsp:include>