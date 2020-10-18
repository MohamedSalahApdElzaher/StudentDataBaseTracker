<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Student Web App Tracker</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	    
	<body>
		
		<div id="wrapper">
			<div id="header">
				<h2> University </h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
			
				<input type="button" value="Add Student"
				       onclick="window.location.href='add-student-form.jsp'; return false;"
				       class="add-student-button"
				/>
			
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					
					<c:forEach var="studentItem" items="${STUDENT_LIST}">
					
					<c:url var="templink" value="StudentControllerServlet">
						<c:param name="comand" value="LOAD"/>
						<c:param name="studentId" value="${studentItem.id}"/>
					</c:url>
					
					<c:url var="deletelink" value="StudentControllerServlet">
						<c:param name="comand" value="DELETE"/>
						<c:param name="studentId" value="${studentItem.id}"/>
					</c:url>
					
						<tr>
							<td>${studentItem.firstname}</td>
							<td>${studentItem.lastname}</td>
							<td>${studentItem.email}</td>
							<td><a href="${templink}">Update</a>
							 |
							 <a href="${deletelink}" onclick="if (!(confirm('Are you sure to delete this student ?'))) return false">Delete</a>
							</td>
						</tr> 
					 
					</c:forEach>
				</table>
			</div>
		</div>
		
	</body>

</html>	