<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="${contextPath}/res/css/pretty.css">
</head>
<body background="${contextPath}/res/images/cubes.png">
  <div class="container text-center">
      <div class="col-md-12 col-sm-12 col-lg-12 page-header">
          <div class="animated bounce">
              <h2>Tuvastan hoonet...</h2>
          </div>
      </div>
  </div>
</body>
</html>
