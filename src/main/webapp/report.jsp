<%-- SpagoBI, the Open Source Business Intelligence suite

 Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0, without the "Incompatible With Secondary Licenses" notice.  If a copy of the MPL was not distributed with this file,
 You can obtain one at http://mozilla.org/MPL/2.0/. --%>

<%
/**
This page use the SpagoBI execution tag, that displays an iframe pointing to SpagoBI context with all information about document execution 
(document identifier, role to be used, values for parameters).
*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spagobi" tagdir="/WEB-INF/tags/spagobi" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="it.eng.spagobi.sdk.documents.bo.SDKDocumentParameter"%>
<%@page import="it.eng.spagobi.sdk.documents.bo.SDKDocument"%>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Document execution</title>
	<style>
	body, p {height: 98%; font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	html { height: 98%; }
	</style>
</head>
<body>
<%
String user = (String) session.getAttribute("spagobi_user");
String password = (String) session.getAttribute("spagobi_pwd");
Integer documentId = (Integer) session.getAttribute("spagobi_documentId");
String role = (String) session.getAttribute("spagobi_role");
String message = (String) session.getAttribute("spagobi_userMessage");
String spagoUrl = (String) session.getAttribute("spagobi_url");
%>

<c:choose>
    <c:when test="<%= documentId != null %>">

	<spagobi:execution
 			spagobiContext="<%= spagoUrl %>"
			userId="<%= user %>"
			password="<%= password %>" 
	        documentId="<%= documentId.toString() %>"
	        iframeStyle="border: 0px; height:100% ; width:100% "
	        executionRole="<%= role %>"
			parametersStr=""
	        displayToolbar="<%= Boolean.TRUE %>"
 	        displaySliders="<%= Boolean.TRUE %>" />        
    </c:when>
    
    <c:when test="<%= message != null %>">

        <h1>${message}</h1> --%>
        
    </c:when>    
    <c:otherwise>
  	      <!-- TODO Tratar Excessão -->		
    </c:otherwise>
</c:choose>


	        

</body>
</html>