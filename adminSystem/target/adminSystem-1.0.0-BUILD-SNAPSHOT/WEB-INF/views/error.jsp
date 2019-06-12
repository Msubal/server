<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="sub-bnr sub-404" data-stellar-background-ratio="0.3">
    <div class="overlay-gr">
      <div class="container">
        <h2>Error 404</h2>
        <p>아직 미구현 혹은 오류페이지입니다.</p>
      </div>
    </div>
</section>
  
<!-- Breadcrumb -->
<ol class="breadcrumb">
  <li> <a href="<c:url value="/"/>" > Home </a> </li>
  <li class="active">Error_Page</li>
</ol>

<!-- CONTENT START -->
<div class="content">
  <div class="container"> 
    <!--======= 404-page =========-->
    <div class="error-page text-center"><img src="<c:url value="/resources/images/404-page.jpg"/>" alt="" />
      <h1>404</h1>
      <h2>Sorry, this page isn’t available</h2>
      <h3>The link you followed is probably broken or the page has been removed.</h3>
    </div>
  </div>
</div>