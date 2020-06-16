<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>
        <img src="images/bikesBanner.png" alt="banner My Bikes">
    </h1>

    <nav class="nav">
        <ul>
            <li ${param.actual == 'index'?'class="current"':""}><a href="Servlet?command=home">Home</a></li>
            <li ${param.actual == 'overview'?'class="current"':""}><a href="Servlet?command=overview">Overview</a></li>
            <li ${param.actual == 'add'?'class="current"':""}><a href="Servlet?command=add">Add a bike</a></li>
            <li ${param.actual == 'news'?'class="current"':""}><a href="Servlet?command=news">Newsletter</a></li>
            <c:if test="${news == 'Nieuwsbrief'}">
                <li><a href="Servlet?command=lattestNews">Laatste Nieuws</a></li>
            </c:if>
        </ul>
    </nav>
</header>
