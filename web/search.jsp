<%-- 
    Document   : search
    Created on : Mar 10, 2018, 9:47:50 AM
    Author     : zone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">Welcome,${sessionScope.User}</font>
        <c:set var="role" value="${sessionScope.role}"/>
        <c:if test="${role eq 2 }">
            <h1> Search Staff Page</h1>
            <form action="ProcessServlet">
                Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
                <input type="submit" value="Search" name="btAction" /><br>
                <a href="InsertMobie.html">Insert Product</a>
            </form><br/>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCHRESULT}"/>

                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>mobileId</th>
                                <th>description</th>
                                <th>price</th>
                                <th>mobileName</th>
                                <th>yearOfProduction</th>
                                <th>quantity</th>
                                <th>notSale</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="ProcessServlet">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.mobileId}
                                        <input type="hidden" name="mobileId" value="${dto.mobileId}" />
                                    </td>
                                    <td>

                                        <input type="text" name="description" value="${dto.description}" />

                                    </td>
                                    <td><input type="text" name="price" value="${dto.price}" /></td>
                                    <td>
                                        ${dto.mobileName}
                                    </td>
                                    <td>
                                        ${dto.yearOfProduction}
                                    </td>
                                    <td>
                                        <input type="text" name="quantity" value="${dto.quantity}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="notSale" value="ADMIN"
                                               <c:if test="${dto.notSale}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>

                                    <td>
                                        <c:url var="deleteLink" value="ProcessServlet" >
                                            <c:param name="btAction" value="Delete"/>
                                            <c:param name="pk" value="${dto.mobileId}"/>
                                            <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                        </c:url>
                                        <a href="${deleteLink}">delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" name="btAction" />
                                        <input type="hidden" name="lastSearchValue" 
                                               value="${searchValue}" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <font color="red">
                <h2>No record is matched!!!</h2>
                </font>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${role eq 1 }">
        <h1> Search User Page</h1>
        <form action="ProcessServlet">
            PriceValue<br>
            Min<input type="text" name="Min" value="" />
            Max<input type="text" name="Max" value="" />
            <c:set var="Min" value="${param.Min}"/>
            <c:set var="Max" value="${param.Max}"/>
            <input type="submit" value="Search" name="btAction" /><br>
            
        </form><br/>
        <c:if test="${(not empty Min) && (not empty Max)}">
            
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>mobileId</th>
                            <th>description</th>
                            <th>price</th>
                            <th>mobileName</th>
                            <th>yearOfProduction</th>
                            <th>quantity</th>
                            <th>notSale</th>
                            <th>Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="ProcessServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.mobileId}
                                </td>
                                <td>
                                    ${dto.description}
                                </td>
                                <td>${dto.price}</td>
                                <td>
                                    ${dto.mobileName}
                                    <input type="hidden" name="MobiName" value="${dto.mobileName}" />
                                </td>
                                <td>
                                    ${dto.yearOfProduction}
                                </td>
                                <td>
                                    ${dto.quantity}
                                </td>
                                <td>
                                    ${dto.notSale}
                                </td>
                                <td><input type="submit" value="Add Product" name="btAction" />
                                    <input type="hidden" name="Min" value="${Min}" />
                                    <input type="hidden" name="Max" value="${Max}" />
                                <input type="hidden" name="lastSearchValue" 
                                               value="${searchValue}" />
                                </td>
                                
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
                <a href="Cart.jsp">View Cart</a>
        </c:if>
        <c:if test="${empty result}">
            <font color="red">
            <h2>No record is matched!!!</h2>
            </font>
        </c:if>
        </c:if>
</c:if>


</body>
</html>
