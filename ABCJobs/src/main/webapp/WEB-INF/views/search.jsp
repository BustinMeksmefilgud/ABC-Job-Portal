<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp">
    <jsp:param value="Search" name="HTMLtitle" />
</jsp:include>
<div class="container">
	<h3>Search Users</h3>
    <form action="" method="get" class="mb-4 pb-5 d-flex">
    	<input type="text" class="form-control w-25" name="q" placeholder="Enter a name or country" value="<%= request.getParameter("q") != null ? request.getParameter("q") : "" %>">
    	<button type="submit" class="btn btn-outline-light text-light ms-2">Search</button>
    </form>

    <div>
    	<h1>${notFound == true ? "Not Found" : ""}</h1>
	   	<c:forEach var="s" items="${selected}">
	       <div class="d-flex align-items-center mb-3 rounded-5 p-5 shadow-sm bg-info bg-gradient">
	           <div>
	           	<h2>${s.getFname()} ${s.getLname()}</h2>
	           	<p>${s.getCountry()}</p>
	           </div>
	           <form action="result" method="post" class="ms-auto">
	           		<input type="hidden" name="Pid" value="${s.getId()}">
		           	<button type="submit" class="btn btn-outline-light ms-auto">View Profile</button>
			   </form>
	       </div>
     	</c:forEach>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>