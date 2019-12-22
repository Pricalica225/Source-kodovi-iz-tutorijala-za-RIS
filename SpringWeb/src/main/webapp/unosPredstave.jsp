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
	
	<form action="/SpringWeb/predstavaController/savePredstava" method="post">
	
		Unesite naziv predstave: <input type="text" name="naziv"> <br>
		Unesite opis predstave: <input type="text" name="opis"> <br>
		Unesite trajanje predstave: <input type="text" name="trajanje"> <br>
		
		<select name="zanr">
			
			<c:forEach var="z" items="${zanrovi}">
			
				<option value="${z.idZanr}">${z.naziv}
			
			</c:forEach>
		
		</select> <br>
		
		<select name="reziser">
			
			<c:forEach var="r" items="${reziseri}">
			
				<option value="${r.idReziser}">${r.ime} | ${r.prezime}
			
			</c:forEach>
		
		</select> <br>
		
		<input type="submit" value="Unesi predstavu">
		
	</form>
	
	<c:if test="${!empty predstava}">
		
		Uspesno ste uneli predstavu. ID predstave je ${predstava.idPredstava}
	
	</c:if>
</body>
</html>