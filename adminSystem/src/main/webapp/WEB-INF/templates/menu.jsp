<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Header -->
  <header> 
    <!-- Top Bar -->
    <div class="container">
      <div class="top-bar">
        <div class="open-time">
          <p><i class="ion-ios-clock-outline"></i> CapstoneDesign opening hours: 1PM to 5PM. Open only Friday</p>
        </div>
        <div class="call">
          <p><i class="ion-headphone"></i> 010 8370 7314</p>
        </div>
      </div>
    </div>
    
    <!-- Logo -->
    <div class="container">
      <div class="logo"> <a href="<c:url value="/"/>"><img src="<c:url value="/resources/images/logo.png"/>" alt=""></a> </div>
      
      <!-- Nav -->
      <nav>
        <ul id="ownmenu" class="ownmenu">
          <li class="active"><a href="<c:url value="/"/>">HOME</a></li>
          <li><a href="<c:url value="/"/>">PAGES</a>
            <ul class="dropdown">
              <li><a href="<c:url value="/"/>">HOME</a></li>
              <li><a href="<c:url value="/admin/students"/>"> Student </a></li>
              <li><a href="<c:url value="/admin/buildings"/>"> Building </a></li>
              <li><a href="<c:url value="/admin/buildings/schedules"/>"> Schedule </a></li>
              <li><a href="single-event.html">single EVENT </a></li>
              <li><a href="sponsors.html"> sponsors </a></li>
              <li><a href="contact.html"> Contact</a></li>
              <li><a href="<c:url value="error"/>"> 404 Page</a></li>
            </ul>
          </li>
          <li><a href="<c:url value="/"/>"> history </a></li>
          <li><a href="<c:url value="/"/>"> Gallery </a></li>
          <li><a href="<c:url value="/"/>"> EVENTs </a></li>
          <li><a href="<c:url value="/"/>"> sponsors </a></li>
          <li><a href="<c:url value="/"/>"> Contact</a></li>
        </ul>
      </nav>
    </div>
  </header>
  <!-- Header End --> 