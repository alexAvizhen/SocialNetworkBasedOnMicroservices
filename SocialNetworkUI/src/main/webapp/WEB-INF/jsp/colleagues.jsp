<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.10.2016
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta firstName="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><spring:message code="msg.colleagues"/></title>

    <spring:url var="home" value="/" scope="request"/>

    <spring:url value="/resources/css/main.css" var="coreCss"/>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
    <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss"/>
    <link href="${fontAwesomeCss}" rel="stylesheet"/>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/lib/bootstrap.js"></script>

    <spring:url value="/resources/js/lib/jquery.1.10.2.min.js"
                var="jqueryJs"/>
    <script src="${jqueryJs}"></script>

    <spring:url value="/resources/js/lib/jquery.i18n.properties-min-1.0.9.js" var="jqueryi18n"/>
    <script src="${jqueryi18n}"></script>

    <link rel="stylesheet" href="/resources/css/bootstrap-table.css"/>
    <script src="/resources/js/lib/bootstrap-table.js"></script>
    <script src="/resources/js/lib/lodash.min.js"></script>
    <script src="/resources/js/lib/bootstrap-table-filter-control.min.js"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/app/service/UserService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/ColleaguesController.js" />"></script>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <h1><spring:message code="msg.siteName"/></h1>
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Open navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/"><img src="/resources/images/companyLogo.png"></a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="/"><spring:message code="msg.main"/></a> </li>
                        <li><a href="/friends"><spring:message code="msg.friends"/></a></li>
                        <li><a href="/chats"><spring:message code="msg.chats"/><span class="badge">12</span></a></li>
                        <li><a href="/communities"><spring:message code="msg.communities"/></a></li>
                        <li><a href="/colleagues"><spring:message code="msg.colleagues"/></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <a href="?locale=en">
                            <spring:message code="msg.en"/>
                        </a>
                        |
                        <a href="?locale=ru">
                            <spring:message code="msg.ru"/>
                        </a>
                        <input type="hidden" id="locale" value="${pageContext.response.locale}"/>

                        <c:if test="${currentUser.id == 0}">
                            <li>
                                <a href="/login">
                                    <span class="glyphicon glyphicon-log-in"></span>
                                    <spring:message code="msg.login"/>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${currentUser.id != 0}">
                            <c:if test="${currentUser.login != null}">
                                <li id="user-name-label">
                                    <a href="/login">
                                        <span class="glyphicon glyphicon-user"></span>
                                            ${currentUser.login}
                                    </a>
                                </li>
                            </c:if>

                            <li>
                                <form action="/logout" method="post" class="navbar-form">
                                    <button type="submit" class="btn btn-link navbar-btn">
                                        <span class="glyphicon glyphicon-log-out"></span>
                                        <spring:message code="msg.logout"/>
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <h3><spring:message code="msg.colleagues"/></h3>
    </div>
</div>

<div class="container">
    <h3>
        ${param.msg}
        <c:if test="${currentUser.id == 0}">
            <h3><spring:message code="msg.haveNotPermission"/></h3>
        </c:if>
    </h3>
    <div class="row">
        <div class="col-md-10 col-lg-11" id="contentContainer">

            <c:if test="${currentUser.id != 0}">
                <table class="table table-no-bordered" id="table1" data-toggle="table"
                       data-classes="table table-no-bordered"
                       data-click-to-select="true"
                       data-url="/api/users/colleagues"
                       data-side-pagination="client"
                       data-pagination="true"
                       data-page-size="15"
                       data-page-list="[15, 25, 35]"
                       data-search="true"
                       data-show-columns="true"
                       data-show-refresh="true"
                       data-show-toggle="true"
                >
                    <thead>
                    <tr>
                        <th data-field="name" data-align="center"><spring:message code="msg.name"/></th>
                        <th data-field="surname" data-sortable="true" data-align="center"><spring:message code="msg.surname"/></th>
                        <th data-field="login"  data-align="center"><spring:message code="msg.username"/></th>
                        <th data-field="email" data-align="center"><spring:message code="msg.email"/></th>
                        <th data-field="position" data-align="center"><spring:message code="msg.position"/></th>
                        <th data-field="workStartDate" data-align="center"><spring:message code="msg.startDate"/></th>
                        <th data-field="id" data-formatter="LinkFormatter" data-align="center">Добавить в друзья</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div class="col-md-2 col-lg-1" id="rateContainer">

        </div>
    </div>
</div>


<script>
    function LinkFormatter(value, row, index) {
        return "<button type=\"button\" class=\"btn btn-primary addToFriend\" friend=\"" + value + "\">Добавить в друзья</button>";
    }
</script>

<div class="container" id="footer">
    <hr />
    <div class="text-center center-block">
        <p class="txt-railway"> <spring:message code="msg.siteUrl"/></p>
        <br />
        <a href="https://vk.com/alex_avizhen"><i class="fa fa fa-vk fa-3x social"></i></a>
        <a href="https://plus.google.com/116724968968879958223"><i class="fa fa-google-plus-square fa-3x social"></i></a>
        <a href="mailto:alex.avizhen97@gmail.com"><i class="fa fa-envelope-square fa-3x social"></i></a>
    </div>
    <hr />
</div>

</body>
</html>