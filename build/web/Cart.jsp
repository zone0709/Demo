<%-- 
    Document   : Cart
    Created on : Mar 10, 2018, 10:28:42 PM
    Author     : zone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="phat.cart.CartObj"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
    <c:set var="cart" value="${sessionScope.CART}"/>
    <c:if test="${not empty cart}">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="ProcessServlet">
                <tbody>
                <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                        <td>
                            <input type="checkbox" name="chkItem" value="${entry.key}" />
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3"><a href="ProcessServlet?btAction=Search&Min=${sessionScope.Min}&Max=${sessionScope.Max}">
                            Add more Books to Your Cart
                        </a>
                    </td> 
                    <td>
                        <input type="submit" value="Remove" name="btAction" />
                    </td>
                </tr>
                </tbody>
            </form>
        </table>

    </c:if>
    <c:if test="${empty cart}">
        <h2>No cart is existed </h2>
    </c:if>
</body>
</html>
