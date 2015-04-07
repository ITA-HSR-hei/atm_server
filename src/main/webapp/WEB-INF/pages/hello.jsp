<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<body>
	<h1>Du bist eingeloggt als: ${loggedInUser}</h1>
	<p>Von der Datenbank geladen wurde ein Test-Dummy mit dem namen ${loadedUser}
	<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
</body>
</html>