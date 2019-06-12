<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="sub-bnr sub-schedule" data-stellar-background-ratio="0.3">
    <div class="overlay-gr">
      <div class="container">
        <h2>Building_Inventory</h2>
        <p>강의실 관리 페이지입니다.</p>
      </div>
    </div>
</section>
  
<!-- Breadcrumb -->
<ol class="breadcrumb">
  <li> <a href="<c:url value="/"/>" > Home </a> </li>
  <li class="active">Building_Inventory</li>
</ol>

<!-- CONTENT START -->
<div class="content">
  <div class="container">
  	<!-- building-page -->
	<div class="buliding-page text-center">
		<h2>Building_Inventory</h2>
		<p>Now Building_Inventorys</p>
		<table class="table table-striped text-center">
			<thead>
				<tr class="bg-success text-center">
					<th class="text-center">name</th>
					<th class="text-center">number</th>
					<th class="text-center"> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="building" items="${buildings}">
					<tr>
						<td>${building.name}</td>
						<td>${building.number}</td>
						<td>
							<a  href="<c:url value="/admin/buildings/schedules/${building.num}" />"> 
							<i class="fa fa-align-justify" style="color:#00FF00;"></i></a>
							<a  href="<c:url value="/admin/buildings/updateBuilding/${building.num}" />"> 
							<i class="fa fa-edit" style="color:#00FF00;"></i></a>
							<a  href="<c:url value="/admin/buildings/deleteBuilding/${building.num}" />"> 
							<i class="glyphicon glyphicon-remove" style="color:#00FF00;"></i></a>
						</td>
				</c:forEach>
			</tbody>
		</table>
		<a href="<c:url value="/admin/buildings/addBuilding"/>" class="btn btn-primary">Add_Building</a>
	</div>
	<br />
	<br />
</div>
</div>