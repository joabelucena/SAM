<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="resources/img/favicon.png">

    <title><spring:message code="title.home" /></title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"		rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-reset.css" />"	rel="stylesheet">
    <!--external css-->
    <link href="<c:url value="/resources/assets/font-awesome/css/font-awesome.css" />" 				rel="stylesheet" />
    <link href="<c:url value="/resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" />" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<c:url value="/resources/css/owl.carousel.css" />"									rel="stylesheet" type="text/css">

    <!--dynamic table-->
    <link href="<c:url value="/resources/assets/advanced-datatable/media/css/demo_page.css" />" 	rel="stylesheet" />
    <link href="<c:url value="/resources/assets/advanced-datatable/media/css/demo_table.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/assets/data-tables/DT_bootstrap.css" />"				rel="stylesheet"/>
    
    <!--right slidebar-->
    <link href="<c:url value="/resources/css/slidebars.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->

    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style-responsive.css" />" rel="stylesheet" />

	<!-- must be here because of Ajax-->
	<script src="<c:url value="/resources/js/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
	
	<!-- Auto Complete -->
	<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet" />
	
	
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
      <script src="resources/js/html5shiv.js"></script>
      <script src="resources/js/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <!-- Body Starts Here -->
  <body>
  	<section id="container">