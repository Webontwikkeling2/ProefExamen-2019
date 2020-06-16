<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>Overview Bike History</title>
    <link rel="stylesheet" media="all" href="css/reset.css">
    <link rel="stylesheet" media="all" href="css/project.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
</head>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="history"/>
</jsp:include>

<div class="container">
    <main>
        <section>
            <h2>Bike History</h2>
            <table id="overview">
                <thead>
                <tr>
                    <th>Brand</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${bikes}" var="bike">
                    <tr>
                        <td>${bike.brand}</td>
                        <td>${bike.category}</td>
                        <td><a href="Servlet?command=detail&id=${bike.itemId}">Details</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </section>
    </main>
</div>
</body>
</html>