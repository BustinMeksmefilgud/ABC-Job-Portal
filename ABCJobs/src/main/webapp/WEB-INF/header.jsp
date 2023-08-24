<%@ page import="ABCJobs.bean.User" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>ABC Jobs | <%= request.getParameter("HTMLtitle") !=null ? request.getParameter("HTMLtitle")
          : "Welcome!" %>
      </title>
      <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
      <link href="<%= request.getParameter("isNested") == null ? "css/zephyr.css" : request.getParameter("isNested") %>" rel="stylesheet">
      
      <style>
      body {
      		background-color: teal;
      		}
      .cyan {
      		background-color: darkturquoise;
      		transition: max-height 0.5s ease;
      		}
      .comments.expand {
		    max-height: 1000px; /* Adjust this value based on your content */
		    transition: max-height 0.5s ease-in-out;
		    overflow: hidden;
		}	
      .show-replies-btn {
		    background: none;
		    border: none;
		    padding-bottom: 20px;
		    margin-left: 30px;
		    font: inherit;
		    text-decoration: none;
		    cursor: pointer;
		    color: blue; /* Customize the color as needed */
		}
		
		.replies {
			max-height: auto;
		    transition: opacity 1s; /* Add transitions */
		}
		.hidden {
			overflow: hidden;
		    opacity: 0;
		    height: 0;
		}
      </style>
    </head>

    <body style="min-height: 100vh;">
      <nav class="navbar navbar-expand-lg bg-info mb-4 shadow-sm">
        <div class="container">
         <% if((Boolean) session.getAttribute("isLoggedIn") != null) { %>
          <a class="navbar-brand" href="dashboard"><img src="images/abcogol.png" alt="Logo" width="150" height="100" ></a>
          <% } else { %>
           <a class="navbar-brand" href="home"><img src="images/abcogol.png" alt="Logo" width="150" height="100" ></a>
            <% } %>
           
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 float-end">
             <% if((Boolean) session.getAttribute("isLoggedIn") != null) { %>
              <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/dashboard.jsp") ? "text-light"
                  : "text-black" %>" href="dashboard">Home</a>
              </li>
              <% } else { %>
              <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/index.jsp") ? "text-light"
                  : "text-black" %>" href="home">Home</a>
              </li>
              <% } %>
                <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/search.jsp") ? "text-light"
                  : "text-black" %>" href="search">Search</a>
              </li>
              <% if((Boolean) session.getAttribute("isLoggedIn") != null) { %>
                 <li class="nav-item">
                  <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/profile.jsp")
                    ? "text-light" : "text-black" %>" href="profile">Profile</a>
                </li>
                <% } else { %>
                  <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/registration.jsp") ? "text-light"
                  : "text-black" %>" href="registration">Register</a>
              </li>
                   <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/login.jsp") ? "text-light"
                  : "text-black" %>" href="login">Login</a>
              </li>
              <% } %>
              <%  if((Boolean) session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isAdmin") != null) { %>
                  <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/admin-dash.jsp") ? "text-light"
                  : "text-black" %>" href="admin-dash">User</a>
              </li>
              <li class="nav-item">
                <a class="nav-link <%= request.getServletPath().equals("/WEB-INF/views/admin-job.jsp") ? "text-light"
                  : "text-black" %>" href="admin-job">Jobs</a>
              </li>
              <% } %>
            </ul>
          </div>
        </div>
      </nav>