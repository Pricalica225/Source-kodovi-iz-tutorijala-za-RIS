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

	<form action="/SpringWeb/predstavaController/getIzvodjenja" method = "get">

		Unesite naziv predstave: <input type="text" name="nazPredstave">
		<br> <br> <input type="submit"
			value="Prikazi izvodjenja za predstavu">

	</form>
	
	<c:if test="${!empty izvodjenja}">
	
		<table border="1">
		
			<tr>
				<th>Datum izvodjenja</th>
				<th>Predstava</th>
				<th>Scena</th>
				<th>Rezervacija></th>
			</tr>
			
			<c:forEach var="i" items="${izvodjenja}">
				
				<tr>
					<td>${i.datum}</td>
					<td>${i.predstava.naziv}</td>
					<td>${i.scena.naziv}</td>
					<td><a href="/SpringWeb/predstavaController/vratiMesta?id=${i.idIzvodjenje}">Rezervisi</a></td>
				</tr>
			
			</c:forEach>
		
		</table>
	
	</c:if>


</body>
</html>