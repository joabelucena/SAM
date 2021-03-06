<%--
SpagoBI - The Business Intelligence Free Platform

Copyright (C) 2005-2008 Engineering Ingegneria Informatica S.p.A.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
--%>

<%@tag language="java" pageEncoding="UTF-8" %>
<%@tag import="java.net.URLEncoder"%>
<%@tag import="java.util.Set"%>
<%@tag import="java.util.Iterator"%>
<%@tag import="it.eng.spagobi.services.common.SsoServiceInterface"%>
<%@tag import="it.eng.spagobi.commons.constants.SpagoBIConstants"%>
<%@tag import="it.eng.spago.security.DefaultCipher"%>

<%@attribute name="spagobiContext" required="true" type="java.lang.String"%>
<%@attribute name="userId" required="true" type="java.lang.String"%>
<%@attribute name="password" required="true" type="java.lang.String"%>
<%@attribute name="documentId" required="false" type="java.lang.String"%>
<%@attribute name="documentLabel" required="false" type="java.lang.String"%>
<%@attribute name="executionRole" required="false" type="java.lang.String"%>
<%@attribute name="parametersStr" required="false" type="java.lang.String"%>
<%@attribute name="parametersMap" required="false" type="java.util.Map"%>
<%@attribute name="displayToolbar" required="false" type="java.lang.Boolean"%>
<%@attribute name="displaySliders" required="false" type="java.lang.Boolean"%>
<%@attribute name="iframeStyle" required="false" type="java.lang.String"%>
<%@attribute name="theme" required="false" type="java.lang.String"%>
<%@attribute name="authenticationTicket" required="false" type="java.lang.String"%>

<%

StringBuffer mainUrl = new StringBuffer();
mainUrl.append(spagobiContext + "/servlet/AdapterHTTP?NEW_SESSION=true");
mainUrl.append("&ACTION_NAME=EXECUTE_DOCUMENT_ACTION");

if (documentId == null && documentLabel == null) {
	throw new Exception("Neither document id nor document label are specified!!");
}
if (documentId != null) {
	mainUrl.append("&OBJECT_ID=" + documentId);
} else {
	mainUrl.append("&OBJECT_LABEL=" + documentLabel);
}
if (parametersStr != null) mainUrl.append("&PARAMETERS=" + URLEncoder.encode(parametersStr,java.nio.charset.StandardCharsets.UTF_8.toString()));
if (parametersMap != null && !parametersMap.isEmpty()) {
	
	@SuppressWarnings("unchecked")
	Set<String> keys = parametersMap.keySet();
	Iterator<String> keysIt = keys.iterator();
	while (keysIt.hasNext()) {
		String urlName = keysIt.next();
		Object valueObj = parametersMap.get(urlName);
		if (valueObj != null) {
			mainUrl.append("&" + URLEncoder.encode(urlName, java.nio.charset.StandardCharsets.UTF_8.toString())
					+ "=" + URLEncoder.encode(valueObj.toString(),java.nio.charset.StandardCharsets.UTF_8.toString()));
		}
	}
}
if (executionRole != null) mainUrl.append("&ROLE=" + URLEncoder.encode(executionRole,java.nio.charset.StandardCharsets.UTF_8.toString()));
if (displayToolbar != null) mainUrl.append("&TOOLBAR_VISIBLE=" + displayToolbar.toString());
if (displaySliders != null) mainUrl.append("&SLIDERS_VISIBLE=" + displaySliders.toString());
if (theme != null)	mainUrl.append("&theme=" + theme);
if (authenticationTicket != null) mainUrl.append("&auth_ticket=" + URLEncoder.encode(authenticationTicket,java.nio.charset.StandardCharsets.UTF_8.toString()));

String encryptedPassword = null;
DefaultCipher chiper = new DefaultCipher();
encryptedPassword = chiper.encrypt(this.password);

%>

<form method="post" target="myiframe"
    action="<%= mainUrl.toString() %>" id="myform" name="myform">
    <input type="hidden" name="<%= SsoServiceInterface.USER_NAME_REQUEST_PARAMETER %>" value="<%= this.userId %>">
    <input type="hidden" name="<%= SsoServiceInterface.PASSWORD_REQUEST_PARAMETER %>" value="<%= encryptedPassword %>">
    <input type="hidden" name="<%= SsoServiceInterface.PASSWORD_MODE_REQUEST_PARAMETER %>" value="<%= SsoServiceInterface.PASSWORD_MODE_ENCRYPTED %>">
</form>

<iframe name="myiframe" id="myiframe" src="about:blank" style="<%= iframeStyle != null ? iframeStyle : "" %>"></iframe>

<script type="text/javascript">
document.getElementById("myform").submit();
</script>