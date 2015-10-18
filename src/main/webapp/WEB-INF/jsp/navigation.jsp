<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="${contextPath}/res/css/pretty.css">
<body background="${contextPath}/res/images/cubes.png">
    <div class="container text-center">
        <c:choose>
            <c:when test="${not empty room}">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-lg-12 page-header">
                        <span id="destinationLabel" class="label label-info" style="font-size: 1.2em;">Sihtkoht: "<c:out value="${room.roomName}"/>"</span>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-lg-12">
                        <div id="navigation">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-lg-12">
                        <div id="searchmessage">
                            <div class="alert alert-warning" role="alert">
                                <img src="${contextPath}/res/images/ajax-loader.gif"
                                     class="img-responsive" style="width: 1.2em;height: 1.2em; display:inline;">
                                 Otsin majakaid...
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-lg-12">
                        <h1>Valitud sihtkohta ei eksisteeri.</h1>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>