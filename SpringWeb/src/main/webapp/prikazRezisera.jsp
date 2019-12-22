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
	
	<c:if test="${!empty reziseri}">
	
		<form action="/SpringWeb/predstavaController/getPredstaveZaRezisera" method="get">
		
			<select name="reziser">
			
				<c:forEach var="r" items="${reziseri}">
					
					<option value="${r.idReziser}">${r.ime} | ${r.prezime}
					
				</c:forEach>
			
			</select>
			
			<input type="submit" value="Prikazi predstave za izabranog rezisera">
		
		</form>
	
	</c:if>
	
	<c:if test="${!empty predstaveR}">
	
		<form action="/SpringWeb/reports/predstave.pdf" method="get">
			
			<table border="1">
		
			<tr>
				<th>Naziv predstave</th>
				<th>Trajanje predstave</th>
				<th>Opis predstave</th>
			</tr>
			
			<c:forEach var="p" items="${predstaveR}">
				
				<tr>
					<td>${p.naziv}</td>
					<td>${p.trajanje}</td>
					<td>${p.opis}</td>
				</tr>
			
			</c:forEach>
			
			</table>
			
			<input type="submit" value="Generisi izvestaj">
		
		</form>
	
	
	</c:if>
</body>
</html>