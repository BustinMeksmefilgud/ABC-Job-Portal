<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ABCJobs.bean.User"%>
<jsp:include page="../header.jsp">
	<jsp:param value="Dashboard" name="HTMLtitle" />
</jsp:include>

			<div class="container alert alert-success alert-dismissible fade show ${message == null ? " d-none" : "d-flex" }"
                role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
    <div class="row mt-4 mx-2">
      <!-- Left section - Profile Section -->
      <div class="col-lg-3">
        
         <div class="bg-info p-3 rounded flex text-center">
          <a class="navbar-brand" href="profile">
          <div class="border-none rounded-circle mx-auto mb-5 text-center fs-1 d-flex align-items-center justify-content-center bg-success bg-gradient text-white" style="width:125px; height:125px;">
              <span>${f}</span>
	          <span>${l}</span>
          </div>
            </a>
           <h3><a class="navbar-brand" href="profile">${fullName}</a></h3>
           <% if((Boolean) session.getAttribute("isAdmin") != null) { %>
	     <h6 class="text-secondary-emphasis">Administrator</h6>
  		   <% } else { %>
          <h6 class="text-secondary-emphasis">${title}</h6>
           <% } %>
          <h6 class="text-secondary-emphasis">${company}</h6>
        </div>
     
      </div>
      
      <!-- Middle section - User Posts -->
      <div class="col-lg-6">
        <div class="p-3 rounded">
          	<div class="mb-3">
               <h3>What's New</h3>
                  <hr>
                  <div class="container mb-3">
                      
                      	<form action="savePost" method="post" class="row d-flex bg-info bg-gradient rounded-2">  
				 	       	<div class="col-8 py-3">
				 	       	  	<input type="text" class="form-control" name="postContent" id="postContent" placeholder="Make a post" value="">
				 	       	  	<input type="hidden" name="uid" value="${uid}">
	                        </div>
	                        <div class="col-4 py-3"> 
	                       		<button type="submit" class="btn btn-primary" id="post">Post</button>
	                       	</div>
 						</form>		
                   
                   </div>
                   <c:forEach var="thread" items="${threads}">
                    
                    <div class="container bg-info bg-gradient rounded-5">
                      <div class="d-flex">
                      
                         <a class="navbar-brand col-1 my-4 ms-3 py-4 border-none rounded-circle align-self-stretch text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
			               href="/abcjobs/viewProfile?Fname=${thread.getUser().getUserprofile().getFname()}"
			               style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
			                <span>${thread.getUser().getUserprofile().getFname().charAt(0)}</span>
			                <span>${thread.getUser().getUserprofile().getLname().charAt(0)}</span>
			            </a>
			            <a class="navbar-brand my-4 ms-3 pt-2 text-dark"
			               href="/abcjobs/viewProfile?Fname=${thread.getUser().getUserprofile().getFname()}">
			                ${thread.getUser().getEmail()}
			            </a>
			            <% if((Boolean) session.getAttribute("isAdmin") != null) { %>
			 	       	  <a href="" class="my-4 ms-auto" data-bs-toggle="modal" data-bs-target="#deleteModal${thread.getId()}">
			            	<i class='bx bx-trash fs-2 text-danger px-3'></i>
			            </a>
			            <% } %>
			 	       	  </div>
			 	       	  <h4 class=" pt-2 px-4 text-truncate">
			 	       	    <a class="navbar-brand"
			               href="/abcjobs/thread?Tid=${thread.getId()}">
			 	       	 		 ${thread.getContent()}
			 	       	   </a>
			 	       	   </h4>
			 	       	   <div class="row mb-3">
				 	       	   <div class="col-6 text-center p-2 text-dark">
				 	       	  	 <i class='bx bx-like fs-2'></i>
				 	       	   </div>
				 	       	   <div class="col-6 text-center p-2 text-dark">
				 	       	   <a class="navbar-brand"
			               href="/abcjobs/thread?Tid=${thread.getId()}">
				 	       	   	<i class='bx bx-chat fs-2'></i>
				 	       	   	</a>
				 	       	   </div>
			 	       	   </div>
                        </div>
                       <!-- delete confirmation -->
								<div class="modal fade" id="deleteModal${thread.getId()}" style="z-index: 2000;">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h1 class="modal-title fs-5" id="exampleModalLabel">Delete post</h1>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        You want to remove the existence of this post??
								      </div>
								      <form action="deleteThreadAdmin" method="get" class="modal-footer">
								      <input type="hidden" name="Tid" value="${thread.getId()}">
								       <button type="submit" class="btn btn-outline-danger ms-auto mb-4 me-3">Delete</button>
								      </form>
								    </div>
								  </div>
								</div>
					</c:forEach>
                       </div>
        </div>
      </div>
      
      <!-- Right section - Job Offers -->
      <div class="col-lg-3">
        <div class="bg-info p-3 rounded">
          <h5>Job Offers</h5>
            
	       <c:forEach var="application" items="${application}">
		       <div class="d-flex align-items-center m-3 rounded-5 p-3 shadow-sm border">
		         ${application.getJob().getName()} application status: ${application.getStatus()}
		       </div>
	       </c:forEach>
	       
	       <% if((Boolean) session.getAttribute("hasJob") == null) { %>   
          	<c:forEach var="job" items="${jobs}">
          	<div class="d-flex flex-lg-nowrap align-items-center m-3 rounded-5 p-3 shadow-sm cyan">
	           <div>
	           	<h4>${job.getName()}</h4>
	           	<p>${job.getCompany()}</p>
	           </div>
	           <button type="button" class="btn btn-primary ms-auto" data-bs-toggle="modal" data-bs-target="#jobDetail${job.getId()}">
	               Details
	           </button>
	       </div>
	   
	         <!-- Job Details Modal -->
				<div class="modal fade" id="jobDetail${job.getId()}" style="z-index: 2000;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">Apply as ${job.getName()} for ${job.getCompany()}</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <p>${job.getDesc()}</p>
				        <p>Salary: ${job.getSalary()} </p>
				        <p>Hours: ${job.getHours()} </p>
				      </div>
				      <% if((Boolean) session.getAttribute("isAdmin") == null) { %>
				      <form action="saveApplicant" method="post" class="modal-footer">
				      	<input type="hidden" name="Jid" value="${job.getId()}">
		           		<button type="submit" class="btn btn-outline-light ms-auto">Apply</button>
				      </form>
				       <% } %>
				    </div>
				  </div>
				</div>
	       
	       
	       </c:forEach>
	       <% } %>
        </div>
      </div>
    </div>





<jsp:include page="../footer.jsp"></jsp:include>