<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@attribute name="menu" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<!DOCTYPE HTML>
<html>

<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<jsp:invoke fragment="head"/>
</head>

<body>

	<jsp:include page="/WEB-INF/template/navigation.jsp" />

	<div class="container-fluid">
		<div class="row-fluid">
			
			<jsp:invoke fragment="menu"/>
			
			
			<jsp:doBody />
			

		</div><!-- row-fluid -->

		<hr>

		

	</div><!-- container-fluid -->
<jsp:include page="/WEB-INF/template/footer.jsp" />
</body>
</html>