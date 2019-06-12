<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<section class="sub-bnr sub-schedule"
	data-stellar-background-ratio="0.3">
	<div class="overlay-gr">
		<div class="container">
			<h2>Schedule_Inventory</h2>
			<p>시간표 관리 페이지입니다.</p>
		</div>
	</div>
</section>

<!-- Breadcrumb -->
<ol class="breadcrumb">
	<li><a href="<c:url value="/"/>"> Home </a></li>
	<li class="active">Schedule_Inventory</li>
</ol>

<!-- CONTENT START -->
<div class="content">
	<div class="container">
		<!-- student-page -->
		<div class="student-page text-center">
			<h2>Schedule_Inventory</h2>
			<p>Now Schedule_Inventorys</p>
			
			<h2>시간표 보기</h2>
			<br />
			<table class="table table-bordered text-center">
				<thead>
					<tr class="bg-success text-center">
						<th class="text-center">시간표</th>
						<c:forEach var="day" begin="2" end="6">
							<th class="text-center"><c:choose>
									<c:when test="${day==1}">일요일</c:when>
									<c:when test="${day==2}">월요일</c:when>
									<c:when test="${day==3}">화요일</c:when>
									<c:when test="${day==4}">수요일</c:when>
									<c:when test="${day==5}">목요일</c:when>
									<c:when test="${day==6}">금요일</c:when>
									<c:when test="${day==7}">토요일</c:when>
								</c:choose></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="times" begin="1" end="9">
						<tr class="text-center bg-primary">
							<td style="color: black" bgcolor="#FFFFFF">${times}교시</td>
							<c:forEach var="days" begin="2" end="6">
								<td
									<c:forEach var="schedule" items="${schedules}">
									<c:set var="day" value="${schedule.day}" />
									<c:set var="time" value="${schedule.time}" /> 
									<c:if test="${days==day}">
										<c:if test="${times==time}">
											bgcolor = "#FF0000"
										</c:if>
									</c:if>
								</c:forEach> />
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			
			<br />
			<table class="table table-striped text-center">
				<thead>
					<tr class="bg-success text-center">
						<th class="text-center">name</th>
						<th class="text-center">number</th>
						<th class="text-center">day</th>
						<th class="text-center">time</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${schedules}">
						<tr>
							<td>${schedule.name}</td>
							<td>${schedule.number}</td>
							<td><c:set var="day" value="${schedule.day}" /> <c:choose>
									<c:when test="${day==1}">일요일</c:when>
									<c:when test="${day==2}">월요일</c:when>
									<c:when test="${day==3}">화요일</c:when>
									<c:when test="${day==4}">수요일</c:when>
									<c:when test="${day==5}">목요일</c:when>
									<c:when test="${day==6}">금요일</c:when>
									<c:when test="${day==7}">토요일</c:when>
								</c:choose></td>
							<td>${schedule.time}교시</td>
							<td>
								<a href="<c:url value="/admin/buildings/schedules/updateSchedule/${schedule.num}" />">
									<i class="fa fa-edit" style="color:#00FF00;"></i>
								</a>
								<a href="<c:url value="/admin/buildings/schedules/deleteSchedule/${schedule.num}" />">
									<i class="glyphicon glyphicon-remove" style="color:#00FF00;"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<a href="<c:url value="/admin/buildings/schedules/addSchedule/${num}"/>" class="btn btn-primary">Add_Schedule</a>
			<a href="<c:url value="/admin/buildings"/>" class="btn btn-primary">Back</a>

		</div>
		<br /> <br />
	</div>
</div>