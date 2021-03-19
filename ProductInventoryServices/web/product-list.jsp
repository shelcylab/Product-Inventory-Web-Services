<%-- 
    Document   : product-list
    Created on : 18 Feb, 2021, 3:23:38 PM
    Author     : shelc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
        <title>Product Inventory Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
                <div></div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Products Available</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Products</h3>
                <hr>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Serial Number</th>
                            <th>Product Name</th>
                            <th>Product Locations</th>
                            <th>Quantity</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="product" items="${listProducts}">
                            <tr>
                                <td>
                                    <c:out value="${product.id}" />
                                </td>
                                <td>
                                    <c:out value="${product.name}" />
                                </td>
                                <td>
                                    <c:out value="${product.location}" />
                                </td>
                                <td>
                                    <c:out value="${product.quantity}" />
                                </td>

                                <td><a href="edit?id=<c:out value='${product.id}' />">Update    </a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${product.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>

                </table>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Product</a>
                </div>
                <br>
            </div>
        </div>
    </body>
</html>