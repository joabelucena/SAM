
<!-- inicio -->


<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/menu.jsp"%>
<%@ include file="../../includes/sidebar.jsp"%>

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

						<form:form action="parameter.do" method="POST"
							modelAttribute="parameter">
							<table>


								<tr ${hidden}>
									<td >Id</td>
									<td><form:input readonly="true" path="par_id" /></td>
								</tr>

								<tr>
									<td>Name</td>
									<td><form:input readonly="${disabled}" path="par_name" /></td>
								</tr>
								<tr>
									<td>Type</td>
									<td><form:input readonly="${disabled}" path="par_type" /></td>
									<td><form:errors path="par_type" cssClass="error" /></td>
								</tr>
								<tr>
									<td>Value</td>
									<td><form:input readonly="${disabled}" path="par_value" /></td>
								</tr>
								<tr>
									<td>Description</td>
									<td><form:input readonly="${disabled}" path="par_desc" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="action" value="Confirmar" /></td>
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



<%@ include file="../../includes/footer.jsp"%>

