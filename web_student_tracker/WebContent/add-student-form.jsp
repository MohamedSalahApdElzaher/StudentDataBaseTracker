<!DOCTYPE HTML>
<html>
	<head>
		<title>Add Student</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
		
	</head>
	    
	<body>
		
		<div id="wrapper">
			<div id="header">
				<h2> University </h2>
			</div>
		</div>
		
		<div id="container">
			<h3> Add New Student </h3>
			  
			    <form action="StudentControllerServlet" method="get">
			    	
			    	 <input type="hidden" name="comand" value="ADD">
			    	 
			    	 <table>
			    	 <body>
			    	 	<tr>
			    	 		<td><label>First Name :</label></td>
			    	 		<td><input type="text" name="firstname"/></td>
			    	 	</tr>
			    	 	<tr>
			    	 		<td><label>Last Name :</label></td>
			    	 		<td><input type="text" name="lastname"/></td>
			    	 	</tr>
			    	 	<tr>
			    	 		<td><label>Email :</label></td>
			    	 		<td><input type="text" name="email"/></td>
			    	 	</tr>
			    	 	<td><label></label></td>
			    	 	<td><input type="submit" value="save" class="save"/></td>
			    	 	</body>
			    	 </table>
			    	 
			    </form>
			    
			    <div style="clear: both;"></div>
			    <p><a href="StudentControllerServlet"/> Back To List</p>
			    
		</div>
		
	</body>

</html>	