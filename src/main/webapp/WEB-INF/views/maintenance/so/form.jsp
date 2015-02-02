<!-- inicio -->

<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/menu.jsp"%>
<%@ include file="../../includes/sidebar.jsp"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.error {
	color: #f55;
}
#type-table tbody tr:hover { 
    background-color: gray; 
    cursor: pointer; 
} 

#parent-table tbody tr:hover { 
    background-color: gray; 
    cursor: pointer; 
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

						<form:form id="site-form" action="site.do" method="POST"
							modelAttribute="site">
							<table>

								<!-- Id -->
								<tr ${hidden}>
									<td><spring:message code="model.id" /></td>
									<td><form:input readonly="true" path="sit_id" /></td>
								</tr>

								<!-- Descricao -->
								<tr>
									<td><spring:message code="model.sites.description" /></td>
									<td><form:input readonly="${disabled}" path="sit_description" /></td>
									<td><form:errors path="sit_description" cssClass="error" /></td>
								</tr>

								<!-- Sigla -->
								<tr>
									<td><spring:message code="model.sites.shortname" /></td>
									<td><form:input readonly="${disabled}" path="sit_shortname" /></td>
									<td><form:errors path="sit_shortname" cssClass="error" /></td>
								</tr>

								<!-- Tipo -->
								<tr>
									<td><spring:message code="model.sites.type" /></td>

									<td hidden=true><form:input path="type.sty_id"			id="type.sty_id" 			readonly="true"/></td>
									<td><form:input	path="type.sty_description"	id="type.sty_description"	readonly="true"/></td>

									<td><button id="type-button" type="button">?</button></td>

									<td><form:errors path="type.sty_description" cssClass="error" /></td>

								</tr>

								<!-- Local Origem -->
								<tr>
									<td><spring:message code="model.sites.parent" /></td>

									<td hidden=true><form:input path="parent.sit_id"			id="parent.sit_id"			readonly="true"/>			</td>
									<td><form:input	path="parent.sit_description"	id="parent.sit_description" readonly="true" />	</td>

									<td><button id="parent-button" type="button">?</button></td>
									
									<td><form:errors path="parent.sit_description" cssClass="error" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="action"
										value=<spring:message code="view.form.submit" />></td>
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


<div id="type-dialog" title="Tipos">
	<table class="display table table-bordered table-st" id="type-table">
		<thead>
			<tr>
				<th><spring:message code="model.id" /></th>
				<th><spring:message code="model.sitestype.description" /></th>
			</tr>
		</thead>
	</table>
</div>

<div id="parent-dialog" title="Locais">
	<table class="display table table-bordered table-st" id="parent-table">
		<thead>
			<tr>
				<th><spring:message code="model.id" /></th>
				<th><spring:message code="model.sites.description" /></th>
				<th><spring:message code="model.sites.shortname" /></th>
				<th><spring:message code="model.sites.parent" /></th>
				<th><spring:message code="model.sites.type" /></th>
			</tr>
		</thead>

	</table>
</div>

<script>

//Type-Table
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';

		console.log(contextPath + '/sites/types/load');

		//Tabela de Eventos: Ordenado em ordem decrescente
		var table = $('#type-table').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ],
			"ajax" : contextPath + '/sites/types/load',
			"bLengthChange": false,
			"language" : {
				"url" : contextPath + "/resources/i18n/pt_lang.txt"
			}
		});
		

		$('#type-table tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
});

//Type-Table
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';

		console.log(contextPath + '/sites/types/load');

		//Tabela de Eventos: Ordenado em ordem decrescente
		var table = $('#parent-table').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ],
			"ajax" : contextPath + '/sites/sites/load',
			"bLengthChange": false,
			"language" : {
				"url" : contextPath + "/resources/i18n/pt_lang.txt"
			}
		});

		$('#parent-table tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
});


//Dialog Form: Type
$(function() {
	var dialog

	function submit() {
		var table = $('#type-table').dataTable();

		console.log(table.api().row('.selected').data()[0]);
		console.log(table.api().row('.selected').data()[1]);

		document.getElementById('type.sty_id').value = table.api().row(
				'.selected').data()[0];
		document.getElementById('type.sty_description').value = table.api()
				.row('.selected').data()[1];

		dialog.dialog("close");

		return true;
	}

	dialog = $("#type-dialog").dialog({
		autoOpen : false,
		height : 300,
		width : 500,
		modal : true,
		buttons : {
			"Ok" : submit,
			Cancel : function() {
				dialog.dialog("close");
			}
		},
		close : function() {
			//allFields.removeClass( "ui-state-error" );
		}
	});

	$("#type-button").button().on("click", function() {
		dialog.dialog("open");
	});
});

//Dialog Form: Parent
$(function() {
	var dialog

	function submit() {
		var table = $('#parent-table').dataTable();

		console.log(table.api().row('.selected').data()[0]);
		console.log(table.api().row('.selected').data()[1]);

		document.getElementById('parent.sit_id').value = table.api().row(
				'.selected').data()[0];
		document.getElementById('parent.sit_description').value = table.api()
				.row('.selected').data()[1];

		dialog.dialog("close");

		return true;
	}

	dialog = $("#parent-dialog").dialog({
		autoOpen : false,
		height : 500,
		width : 500,
		modal : true,
		buttons : {
			"Ok" : submit,
			Cancel : function() {
				dialog.dialog("close");
			}
		},
		close : function() {
			//allFields.removeClass( "ui-state-error" );
		}
	});

	$("#parent-button").button().on("click", function() {
		dialog.dialog("open");
	});
});


</script>



<%@ include file="../../includes/footer.jsp"%>