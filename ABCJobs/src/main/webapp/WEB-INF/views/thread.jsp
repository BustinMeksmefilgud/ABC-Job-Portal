<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ABCJobs.bean.User"%>
<jsp:include page="../header.jsp">
	<jsp:param value="Dashboard" name="HTMLtitle" />
</jsp:include>


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
          	
                  <hr>
                 
                    <div class="container bg-info bg-gradient rounded-5">
                      <div class="d-flex">
                         <a class="navbar-brand col-1 my-4 ms-3 py-4 border-none rounded-circle align-self-stretch text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
			               href="/abcjobs/viewProfile?Fname=${threadFname}"
			               style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
			                <span>${tf}</span>
			                <span>${tl}</span>
			            </a>
			            <a class="navbar-brand my-4 ms-3 pt-2 text-dark"
			               href="/abcjobs/viewProfile?Fname=${threadFname}">
			                ${temail}
			            </a>
			 	       </div>
			 	       <h4 class="pt-2 px-4">${tcontent}</h4>
			 	       <form action="saveComment" method="post" class="ms-auto row">  
					 	     <div class="col-8 p-3 pb-0">
					 	       	<input type="text" class="form-control" name="comment" placeholder="Make a comment" value="">
					 	       	<input type="hidden" name="tid" value="${tid}">
					 	       	<input type="hidden" name="uid" value="${uid}">
		                     </div>
		                     <div class="col-4 p-3 pb-0"> 
		                        <button type="submit" class="btn btn-primary" id="post">Post</button>
		                     </div>
	 					</form>		
		 						 <c:forEach var="comments" items="${comments}">
			 						 <div class="comments cyan rounded-5 mx-3 mt-3">
					                      <div class="d-flex">
								            <a class="navbar-brand col-1 mt-4 ms-3 py-4 border-none rounded-circle text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
								                href="/abcjobs/viewProfile?Fname=${comments.getUser().getUserprofile().getFname()}"
								                style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
								                <span>${comments.getUser().getUserprofile().getFname().charAt(0)}</span>
								                <span>${comments.getUser().getUserprofile().getLname().charAt(0)}</span>
								            </a>
								            <div class="col-11 pt-4"> 
								                <a class="navbar-brand mt-4 ms-3 pt-4 text-dark-muted"
								                    href="/abcjobs/viewProfile?Fname=${comments.getUser().getUserprofile().getFname()}">
								                    ${comments.getUser().getEmail()}
								                </a>
								                <h5 class="pt-2 pb-3 px-4 text-dark">${comments.getContent()}</h5>
								            </div>
								             
								        </div>
								         <button class="show-replies-btn">Reply</button>
								         
								          <div class="replies hidden">
								         <form action="saveReply" method="post" class="ms-auto row ">  
									 	       	<div class="col-8 p-3">
									 	       	  	<input type="text" class="form-control" name="reply" id="reply" placeholder="Make a reply" value="">
									 	       	  	<input type="hidden" name="tid" value="${tid}">
									 	       	  	<input type="hidden" name="uid" value="${uid}">
									 	       	  	<input type="hidden" name="cid" value="${comments.getId()}">
						                        </div>
						                        <div class="col-4 p-3 pb-0"> 
						                       		<button type="submit" class="btn btn-primary" id="reply">Reply</button>
						                       	</div>
			 								</form>	
			 								
			 								
			 								 <c:forEach var="replies" items="${replies}">
									        	<c:if test="${comments.getId() == replies.getCid()}">
									        	
									        		<div class="d-flex ms-lg-5">
										            <a class="navbar-brand col-1 ms-3 py-4 border-none rounded-circle text-center d-flex align-items-center justify-content-center bg-success bg-gradient text-white"
										                href="/abcjobs/viewProfile?Fname=${replies.getUser().getUserprofile().getFname()}"
										                style="width: 60px; height: 60px; line-height: 40px; border-radius: 50%;">
										                <span>${replies.getUser().getUserprofile().getFname().charAt(0)}</span>
										                <span>${replies.getUser().getUserprofile().getLname().charAt(0)}</span>
										            </a>
										            <div class="col-11"> 
										                <a class="navbar-brand mt-4 ms-3 pt-4 text-dark-muted"
										                    href="/abcjobs/viewProfile?Fname=${replies.getUser().getUserprofile().getFname()}">
										                    ${replies.getUser().getEmail()}
										                </a>
										                <h5 class="pt-2 pb-3 px-4 text-dark">${replies.getContent()}</h5>
										            </div>
										             
										       	 </div>
									        	</c:if>
							        
				         
					 	   					 </c:forEach>	
					 	   					</div>
			 							</div>	
							       
					 	   		 </c:forEach>	
					 	    <div class="container-fluid bg-info text-center text-info rounded-5 mb-5 pb-3">
									Nothing to see here
							</div>
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
          <div class="align-items-center m-3 rounded-5 p-3 shadow-sm cyan">
	           <div>
	           	<h2>${job.getName()}</h2>
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

<script>
    document.addEventListener("DOMContentLoaded", function () {
    	const commentsDiv = document.querySelector('.comments');
        const showRepliesButtons = document.querySelectorAll(".show-replies-btn");

        showRepliesButtons.forEach((button) => {
            button.addEventListener("click", function () {
                const repliesSection = button.parentElement.querySelector(".replies");
                repliesSection.classList.toggle("hidden");
                commentsDiv.classList.toggle('expand');
            });
        });
    });
</script>
<jsp:include page="../footer.jsp"></jsp:include>