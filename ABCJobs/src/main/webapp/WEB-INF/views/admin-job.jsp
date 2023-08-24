<%@ page import="ABCJobs.bean.User" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp">
    <jsp:param value="Job" name="HTMLtitle" />
</jsp:include>
<div class="container alert alert-success alert-dismissible fade show ${message == null ? " d-none" : "d-flex" }"
                role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
     </div>
<div class="container">
	
 <h1>Welcome back, ${adminName}</h1>
   

	<div class="row">
		<div class="col-lg-8">
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#jobModal">
    		  Add Job
  		</button>
			<table class="table table-hover text-dark my-3">
		    	<thead>
		      	<tr class="table-primary">
				       <th scope="col">#</th>
				       <th scope="col">Job Name</th>
				       <th scope="col">Description</th>
				       <th scope="col">Salary</th>
				       <th scope="col">Hours</th>
				       <th scope="col">Company</th>
				       <th scope="col">Status</th>
		      			<th scope="col">Open/Close</th>
		     		</tr>
		      
		   		 </thead>
		    	<tbody>
			    <% int i = 1; %>
				   <c:forEach var="jobs" items="${jobs}">
			   		 <tr class="table-info">
				       <th scope="row"><%= i++ %></th>
				       <td>${jobs.getName()}</td>
				       <td>${jobs.getDesc()}</td>
				       <td>${jobs.getSalary()}</td>
				       <td>${jobs.getHours()}</td>
				       <td>${jobs.getCompany()}</td>
				       <td>${jobs.getStatus()}</td>
				       <td>
				       <form action="jobClose" method="get">
				       <input type="hidden" name="Jid" value="${jobs.getId()}">
				       <button type="submit" class="btn btn-outline-danger ms-auto mb-4 me-3">Close</button>
				      </form>
				       <form action="jobOpen" method="get">
				       <input type="hidden" name="Jid" value="${jobs.getId()}">
				       <button type="submit" class="btn btn-outline-danger ms-auto mb-4 me-3">Open</button>
				      </form>
				      </td>
				     </tr>
			   	</c:forEach>
		    	</tbody>
		  	</table>
		</div>
		<div class="col-lg-4">
			<div class="bg-info p-3 rounded">
			          <h5>Jobs for Approval</h5>
			          	<c:forEach var="j" items="${forApproval}">
			          		<div class="d-flex align-items-center m-3 rounded-5 p-3 shadow-sm bg-primary-subtle">
				           	<h6>${j.getUser().getUserprofile().getFname()}
				           	wants to apply as ${j.getJob().getName()} for ${j.getJob().getCompany()}
				           	</h6>
				           	   <a href="" data-bs-toggle="modal" data-bs-target="#acceptModal${j.getId()}"><i class='bx bx-check fs-2 text-success px-3'></i></a>
		          				<a href="" data-bs-toggle="modal" data-bs-target="#rejectModal${j.getId()}"><i class='bx bx-x fs-2 text-danger px-3'></i></a>
				           </div>
				           
				           <!-- Accept -->
							<div class="modal fade" id="acceptModal${j.getId()}" style="z-index: 2000;">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="exampleModalLabel">Accept ${j.getUser().getUserprofile().getFname()}</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							        Do you want to accept ${j.getUser().getUserprofile().getFname()}'s
				           	 application as ${j.getJob().getName()} for ${j.getJob().getCompany()}
							      </div>
							      <form action="jobAccept" method="post" class="modal-footer">
							      <input type="hidden" name="Aid" value="${j.getId()}">
							       <button type="submit" class="btn btn-outline-success ms-auto mb-4 me-3">Yes</button>
							      </form>
							    </div>
							  </div>
							</div>
							
							 <!-- Reject Humanity -->
							<div class="modal fade" id="rejectModal${j.getId()}" style="z-index: 2000;">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="exampleModalLabel">Reject ${j.getUser().getUserprofile().getFname()}</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							        Do you want to Reject ${j.getUser().getUserprofile().getFname()}'s
				           	 application as ${j.getJob().getName()} for ${j.getJob().getCompany()}
							      </div>
							      <form action="jobReject" method="post" class="modal-footer">
							      <input type="hidden" name="Aid" value="${j.getId()}">
							       <button type="submit" class="btn btn-outline-success ms-auto mb-4 me-3">Yes</button>
							      </form>
							    </div>
							  </div>
							</div>
				           
				           
						 </c:forEach>
			</div>
		</div>
	</div>
</div>



<!-- Add Job Modal -->
    <div class="modal fade" id="jobModal" tabindex="-1" aria-labelledby="jobModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
         <form action="saveJob" method="post">
          <div class="modal-header">
            <h5 class="modal-title" id="jobModalLabel">Add Job</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
         
            <!-- Job Name Input -->
            <div class="mb-3">
              <label for="jobName" class="form-label">Job Name</label>
              <input type="text" class="form-control" id="jobName" name="jobName" placeholder="Enter job name">
            </div>
            <!-- Job Description Input -->
            <div class="mb-3">
              <label for="jobDescription" class="form-label">Job Description</label>
              <textarea class="form-control" id="jobDescription" name="jobDescription" rows="4" placeholder="Enter job description"></textarea>
            </div>
             <!-- Company Input -->
            <div class="mb-3">
              <label for="company" class="form-label">Company</label>
              <input type="text" class="form-control" id="company" name="company" placeholder="Enter company">
            </div>
            <!-- Job Salary Input -->
            <div class="mb-3">
              <label for="salary" class="form-label">Salary</label>
              <input type="text" class="form-control" id="salary" name="salary" placeholder="Enter company">
            </div>
            <!-- Job Hours Input -->
            <div class="mb-3">
              <label for="hours" class="form-label">Hours</label>
              <input type="text" class="form-control" id="hours" name="hours" placeholder="Enter company">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Save Job</button>
            
          </div>
          </form>
        </div>
      </div>
    </div>

<jsp:include page="../footer.jsp"></jsp:include>