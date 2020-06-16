<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="nl">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>Bikes - Nieuwsbrief</title>
	<link rel="stylesheet" media="all" href="css/reset.css">
	<link rel="stylesheet" media="all" href="css/project.css">
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
</head>

<body>

	<jsp:include page="header.jsp">
		<jsp:param name="actual" value="news"/>
	</jsp:include>

	<div class="container">
		<main>
		<section>
			<h2>Nieuwsbrief</h2>
		
				<form method="POST" action="Servlet?command=newsChange" novalidate>
					<p>
						<input type="checkbox" name="nieuwsbrief" value="Nieuwsbrief" ${news == "Nieuwsbrief" ? 'checked' : ''}>Ja, ik wil de nieuwsbrief ontvangen <br>
					</p>
					<p>
						<input type="submit" value="Submit">
					</p>
				</form>
		</section>
		</main>

	</div>
</body>
</html>
