<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3><spring:message code="label.title"/></h3>
<p>You are logged in as Administrator</p>
<a href="<c:url value="j_spring_security_logout" />" > Logout</a>

