<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<title>Contact List</title>
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
		<script>
			const ServletURL = "${pageContext.request.contextPath}/Contactmaker";
			$(document).on("click", "#submitButton", function(event) {
				event.preventDefault();
				if($.trim($("#first_name").val()) === ""){
					alert("First name required!");
				} 
				else if($.trim($("#last_name").val()) === ""){
					alert("Last name required!");
				}
				else if($.trim($("#phone_number").val()) === ""){
					alert("Phone number required!");
				}
				else if($.trim($("#email").val()) === ""){
					alert("Email required!");
				}
				else if($.trim($("#address").val()) === ""){
					alert("Address required!");
				}
				else{
				$.post(ServletURL,
							$("#inputcontact").serialize(),
							function(responseText) {
        			document.getElementById("inputcontact").reset();
						$("#leftdiv").html(responseText);
    			})};
			});
		</script>
		<script>
			const ServletURL2 = "${pageContext.request.contextPath}/Contactmaker";
			$(document).ready(function() { 
    			$.get(ServletURL2, function(responseText) {
        			$("#leftdiv").html(responseText);           
    			});
			});
		</script>

	</head>

<body>
	<div style="float:right; width: 25%;" id=rightdiv>
		<form id="inputcontact" name="inputcontact">
		<h2>Input Contact:</h2><br>
		<label for="first_name">First name:</label><br>
		<input type="text" id="first_name" name="first_name"><br>
		<label for="last_name">Last name:</label><br>
		<input type="text" id="last_name" name="last_name"><br>
		<label for="phone_number">Phone number:</label><br>
		<input type="text" id="phone_number" name="phone_number"><br>
		<label for="email">Email:</label><br>
		<input type="text" id="email" name="email"><br>
		<label for="address">Address:</label><br>
		<input type="address" id="address" name="address"><br><br>
		<input type="submit" value="Submit" id="submitButton">
		</form>
	</div>
	<div style="float:left; width: 70%;" id="leftdivhead">
			<b><font size=6>Contacts</font></b><br>
				<div id="leftdiv">
				
				</div>


		</div>
</body>
</html>