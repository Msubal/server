<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" /></title>
<meta name="keywords" content="HTML5,CSS3,HTML,Template,Multi-Purpose,M_Adnan,Corporate Theme,Museum HTML5 Template Purpose,Museum - Premium HTML5 Template,Museum - Premium HTML5 Template">
<meta name="description" content="Museum - Premium HTML5 Template">
<meta name="author" content="M_Adnan">

<!-- AWESOME Tag -->
 <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- FONTS ONLINE -->
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:500,600,700,100,800,900,400,200,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,800,700italic' rel='stylesheet' type='text/css'>

<!--MAIN STYLE-->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/responsive.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/rs-plugin/css/settings.css"/>" media="screen" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<div>
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

	<script src="<c:url value="/resources/js/jquery-1.11.0.min.js"/>"></script> 
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script> 
<script src="<c:url value="/resources/js/own-menu.js"/>"></script> 
<script src="<c:url value="/resources/js/owl.carousel.min.js"/>"></script> 
<script src="<c:url value="/resources/js/jquery.stellar.min.js"/>"></script> 
<script src="<c:url value="/resources/js/smooth-scroll.js"/>"></script> 
<script src="<c:url value="/resources/js/jquery.prettyPhoto.js"/>"></script> 

<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 
<script type="text/javascript" src="<c:url value="/resources/rs-plugin/js/jquery.themepunch.tools.min.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/rs-plugin/js/jquery.themepunch.revolution.min.js"/>"></script> 
<script src="<c:url value="/resources/js/main.js"/>"></script>
<script type="text/javascript">
/*-----------------------------------------------------------------------------------*/
/*  SLIDER REVOLUTION
/*-----------------------------------------------------------------------------------*/
jQuery('.tp-banner').show().revolution({
  dottedOverlay:"none",
  delay:10000,
  startwidth:1170,
  startheight:630,
  navigationType:"bullet",
  navigationArrows:"solo",
  navigationStyle:"preview4",
  parallax:"mouse",
  parallaxBgFreeze:"on",
  parallaxLevels:[7,4,3,2,5,4,3,2,1,0],                       
  keyboardNavigation:"on",            
  shadow:0,
  fullWidth:"on",
  fullScreen:"off",
  shuffle:"off",            
  autoHeight:"off",           
  forceFullWidth:"off", 
  fullScreenOffsetContainer:""  
});
</script>
</body>
</html>