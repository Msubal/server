<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.5">


<title>Admin System</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value ="resources/css/bootstrap.min.css"/>"
	rel="stylesheet">

<link href="<c:url value ="resources/css/carousel.css"/>"
	rel="stylesheet">



<link href="<c:url value ="resources/css/main.css"/>" rel="stylesheet">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="#">Carousel</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="<c:url value="/"/>">Students<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/schedule"/>">Schedules</a></li>
					<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
					</li>
				</ul>
				<form class="form-inline mt-2 mt-md-0">
					<input class="form-control mr-sm-2" type="text"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>



	<div class="container-wrapper">
		<div class="container">
			<h2>각호실의 수업 정보</h2>
			<p>수업 정보를 확인하는곳입니다.</p>
			<table class="table table-striped">
				<thead>
					<tr class="bg-success">
						<th>Num</th>
						<th>Name</th>
						<th>Day</th>
						<th>Time</th>
						<th></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${Schedule}">
						<tr>
							<td>${schedule.num}</td>
							<td>${schedule.name}</td>
							<td>
							<c:set var="day" value="${schedule.day}" /> 
							<c:choose>
									<c:when test="${day==1}">일요일</c:when>
									<c:when test="${day==2}">월요일</c:when>
									<c:when test="${day==3}">화요일</c:when>
									<c:when test="${day==4}">수요일</c:when>
									<c:when test="${day==5}">목요일</c:when>
									<c:when test="${day==6}">금요일</c:when>
									<c:when test="${day==7}">토요일</c:when>
								</c:choose></td>
							<td>${schedule.time}교시</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>



	<!-- FOOTER -->
	<footer class="container">
		<p class="float-right">
			<a href="#">Back to top</a>
		</p>
		<p>
			&copy; 2017-2019 Company, Inc. &middot; <a href="#">Privacy</a>
			&middot; <a href="#">Terms</a>
		</p>
	</footer>
	</main>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>

</body>
</html>