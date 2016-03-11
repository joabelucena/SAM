<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Web Services</title>

<style>
table {
	border: 1px solid black;
	text-align: left;

}
td, th, tr {
	border: 1px solid black;

}
#nome{
	width: 50%;
}
</style>
</head>
<body>
	<h1>Web Services</h1>
	<table>
		<tr>
			<td id="nome">Nome / Descrição</td>
			<td id="url">URL</td>
		</tr>
		<c:if test="${not empty lists}">
			<c:forEach var="listValue" items="${lists}">
				<tr>

					<td>
						<ul>
							<li>${listValue[0]}</li>
							<ul>
								<li>${listValue[1]}</li>
							</ul>
						</ul>
					</td>

					<td><a href="${listValue[2]}">${listValue[2]}</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>