<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="sub-bnr sub-schedule" data-stellar-background-ratio="0.3">
    <div class="overlay-gr">
      <div class="container">
        <h2>Student_Inventory</h2>
        <p>학생 관리 페이지입니다.</p>
      </div>
    </div>
</section>
  
<!-- Breadcrumb -->
<ol class="breadcrumb">
  <li> <a href="<c:url value="/"/>" > Home </a> </li>
  <li class="active">Student_Inventory</li>
</ol>

<!-- CONTENT START -->
<div class="content">
  <div class="container">
  	<!-- student-page -->
	<div class="student-page text-center">
		<h2>Student_Inventory</h2>
		<p>Now Student_Inventorys</p>
		<table class="table table-striped text-center">
			<thead>
				<tr class="bg-success text-center">
					<th class="text-center">name</th>
					<th class="text-center">id</th>
					<th class="text-center">password</th>
					<th class="text-center">check</th>
					<th class="text-center">otp_check</th>
					<th class="text-center"> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.name}</td>
						<td>${student.id}</td>
						<td>${student.password}</td>
						<td>${student.check}</td>
						<td>${student.otp_check}</td>
						<td>
							<a  href="<c:url value="/admin/students/updateStudent/${student.num}" />"> 
								<i class="fa fa-edit" style="color:#0000ff;"></i>
							</a>
							<a  href="<c:url value="/admin/students/deleteStudent/${student.num}" />"> 
								<i class="fa fa-user-times" style="color:#0000ff;"></i>
							</a>
						</td>
				</c:forEach>
			</tbody>
		</table>
		<a href="<c:url value="/admin/students/addStudent"/>" class="btn btn-primary">Add_Student</a>
	</div>
	<br />
	<br />
</div>
</div>