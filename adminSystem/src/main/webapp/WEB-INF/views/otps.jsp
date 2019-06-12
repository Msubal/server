
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="sub-bnr sub-schedule" data-stellar-background-ratio="0.3">
    <div class="overlay-gr">
      <div class="container">
        <h2>OTP_Inventory</h2>
        <p>otp 관리 페이지입니다.</p>
      </div>
    </div>
</section>
  
<!-- Breadcrumb -->
<ol class="breadcrumb">
  <li> <a href="<c:url value="/"/>" > Home </a> </li>
  <li class="active">OTP_Inventory</li>
</ol>

<!-- CONTENT START -->
<div class="content">
  <div class="container">
     <!-- student-page -->
   <div class="student-page text-center">
      <h2>OTP_Log_Inventory</h2>
      <p>Log</p>
      <table class="table table-striped text-center">
         <thead>
            <tr class="bg-success text-center">
               <th class="text-center">num</th>
               <th class="text-center">Student_id</th>
               <th class="text-center">Building_num</th>
               <th class="text-center">Day</th>
               <th class="text-center">Start_Time</th>
               <th class="text-center">End_Time</th>
               <th class="text-center">OTP_PW</th>
               <th class="text-center">Current</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="otp" items="${otps}">
               <tr>
                  <td>${otp.num}</td>
                  <td>${otp.student_id}</td>
                  <td>${otp.building_num}</td>
                  <td>${otp.day}</td>
                  <td>${otp.start_time}</td>
                  <td>${otp.end_time}</td>
                  <td>${otp.otp_pw}</td>
                  <td>${otp.current}</td>
            </c:forEach>
         </tbody>
      </table>
   </div>
   <br />
   <br />
</div>
</div>