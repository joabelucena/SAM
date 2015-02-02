<!-- inicio -->


<%@ include file="../../../includes/header.jsp"%>
<%@ include file="../../../includes/menu.jsp"%>
<%@ include file="../../../includes/sidebar.jsp"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<style>
.error {
	color: #f55;
}

</style>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-sm-12">
				<section class="panel">
					<header class="panel-heading">

						<spring:message code="${title}" />

						<span class="tools pull-right"> <a href="javascript:;"
							class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>



					</header>
					<div class="panel-body">

						<form:form action="siteType.do" method="POST"
							modelAttribute="siteType">
							<table>

								<tr ${hidden}>
									<td><spring:message code="model.id" /></td>
									<td><form:input readonly="true" path="sty_id" /></td>
								</tr>

								<tr>
									<td><spring:message code="model.sitestype.description" /></td>
									<td><form:input readonly="${disabled}" path="sty_description"  /></td>
									<td><form:errors path="sty_description" cssClass="error" /></td>
								</tr>
								
								<tr>
									<td colspan="2"><input type="submit" name="action" value=<spring:message code="view.form.submit" /> /></td>
								</tr>
							</table>
						</form:form>
					</div>
				</section>
			</div>
		</div>
		<!-- page end-->
	</section>
</section>

<%@ include file="../../../includes/footer.jsp"%>