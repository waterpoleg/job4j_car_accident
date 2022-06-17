<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Edit accident:
            </div>
            <div class="card-body">
                <form  action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                    <table>
                        <tr>
                            <td>Name:</td>
                            <td><input type='text' name='name' value="${accident.name}" required></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><input type='text' name='text' value="${accident.text}" required></td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td><input type='text' name='address' value="${accident.address}" required></td>
                        </tr>
                        <tr>
                            <td>Type:</td>
                            <td>
                                <select name="type.id">
                                    <c:forEach var="type" items="${types}" >
                                        <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td>Rules:</td>
                            <td>
                                <select name="rIds" multiple>
                                    <c:forEach var="rule" items="${rules}" >
                                        <option value="${rule.id}">${rule.name}</option>
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td colspan='2'><input name="submit" type="submit" value="Save" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
