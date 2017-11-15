<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Gerrymandering Analysis</title>

		<spring:url value="/resources/css/style.css" var="style" />
		<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
		<link type="text/css" rel="stylesheet" href="${style}" />
		<link type="text/css" rel="stylesheet" href="${bootstrap}"/>

		<spring:url value= "/resources/js/jquery.min.js" var="jquery" />
    <spring:url value= "/resources/js/bootstrap.min.js" var="boot" />
    <spring:url value= "/resources/js/script.js" var="script" />
    <script src="${jquery}"></script>
    <script src="${boot}"></script>
    <script src="${script}"></script>

	<!-- can also just do this..
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/style.css" rel="stylesheet">
	-->
		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.2.0/dist/leaflet.css" integrity="sha512-M2wvCLH6DSRazYeZRIm1JnYyh22purTM+FDB5CsyxtQJYeKq83arPe5wgbNmcFXGqiSH2XR8dT/fJISVA1r/zQ==" crossorigin=""/>
		<script src="https://unpkg.com/leaflet@1.2.0/dist/leaflet.js" integrity="sha512-lInM/apFSqyy1o6s89K4iQUKg6ppXEgsVxT35HbzUupEVRh2Eu9Wdl4tHj7dZO0s1uvplcYGmt3498TtHq+log==" crossorigin=""></script>

		</head>
	<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<%@include file="/WEB-INF/views/include/navigation.jsp" %>
				<div class="window-mask">
