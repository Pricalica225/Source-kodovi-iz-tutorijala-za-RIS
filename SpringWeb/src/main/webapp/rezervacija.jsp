<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${!empty mesta}">
	
		<form action="/SpringWeb/predstavaController/sacuvajKartu" method="post">
			
			<select name="mesto">
				
				<c:forEach var="m" items="${mesta}">
					
					<option value="${m.idMesto}">${m.broj} | ${m.red} | ${m.sekcija}>
					
				</c:forEach>
			
			</select> <br><br>
			
			Unesite ID posetioca: <input type="text" name="idPosetilac"> <br><br>
			
			<input type="submit" value="Sacuvaj">
			
		</form>
	
	</c:if>
	
	<c:if test="${! empty karta}">
	
		Karta je uspesno sacuvana. ID karte je ${karta.idKarta}.
	
	</c:if>
</body>
</html>