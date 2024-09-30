<%-- 
    Document   : updateProduct
    Created on : 6 thg 3, 2024, 18:16:43
    Author     : hii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
            .product-form {
    display: flex;
    flex-direction: column;
    max-width: 400px;
    margin: auto;
}

.form-group {
    margin-bottom: 10px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

        </style>
    </head>
    <body>
        
    <c:set var="c" value="${requestScope.product}"/>
    <form action="addnew" method="post" class="product-form">
        <h1 style="color: #45a049">Cập nhật sản phẩm</h1>

    <div class="form-group">
        <label for="id">Nhập ID:</label>
        <input type="text" id="id" name="id" value="${c.productID}"/>
    </div>
    <div class="form-group">
        <label for="name">Nhập tên sản phẩm:</label>
        <input type="text" id="name" name="name" value="${c.productName}"/>
    </div>
    <div class="form-group">
        <label for="describe">Nhập mô tả:</label>
        <input type="text" id="describe" name="describe" value="${c.description}"/>
    </div>
    <div class="form-group">
        <label for="image">Nhập link ảnh:</label>
        <input type="text" id="image" name="image" value="${c.image}"/>
    </div>
    <div class="form-group">
        <label for="price">Nhập số tiền</label>
        <input type="number" id="price" name="price" value="${c.price}"/>
    </div>
    <div class="form-group">
        <label for="quantity">Nhập số lượng:</label>
        <input type="number" id="quantity" name="quantity" value="${c.quantity}"/>
    </div>
    <div class="form-group">
        <label for="productCategoryID">Nhập mã sản phẩm:</label>
        <input type="number" id="productCategoryID" name="productCategoryID" value="${c.productCategoryID.productCategoryID}"/>
    </div>
    <input type="submit" value="Update"/>
</form>

</body>
</html>
