<!-- inicio -->

<%@ include file="../../../includes/header.jsp"%>
<%@ include file="../../../includes/menu.jsp"%>
<%@ include file="../../../includes/sidebar.jsp"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.error {
	color: #f55;
}
#role-table tbody tr:hover { 
    background-color: gray; 
    cursor: pointer; 
} 

#cur-table tbody tr:hover { 
    background-color: gray; 
    cursor: pointer; 
}
#next-table tbody tr:hover { 
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

						<form:form id="form" action="action.do" method="POST"
							modelAttribute="rule">
							<table>

								<!-- Id -->
								<tr ${hidden}>
									<td><spring:message code="model.id" /></td>
									<td><form:input readonly="true" path="sru_id" /></td>
								</tr>

								<!-- Perfil -->
								<tr>
									<td><spring:message code="model.statusrules.role" /></td>

									<td hidden=true>
										<form:input path="role.id"			id="role.id" 		readonly="true"/>
									</td>
									<td>
										<form:input path="role.roleName"	id="role.roleName"	readonly="true"/>
									</td>

									<td><button id="role-button" type="button">?</button></td>

									<td><form:errors path="role.roleName" cssClass="error" /></td>

								</tr>

								<!-- Status Atual -->
								<tr>
									<td><spring:message code="model.statusrules.curstatus" /></td>

									<td hidden=true>
										<form:input path="curstatus.sos_id"				id="curstatus.sos_id"			readonly="true"/>
									</td>
									<td>
										<form:input path="curstatus.sos_description"	id="curstatus.sos_description" readonly="true" />
									</td>

									<td><button id="cur-button" type="button">?</button></td>
									
									<td><form:errors path="curstatus.sos_description" cssClass="error" /></td>
								</tr>
								
								<!-- Proximo Status -->
								<tr>
									<td><spring:message code="model.statusrules.nxtstatus" /></td>

									<td hidden=true>
										<form:input path="nxtstatus.sos_id"				id="nxtstatus.sos_id"			readonly="true"/>
									</td>
									<td>
										<form:input path="nxtstatus.sos_description"	id="nxtstatus.sos_description" readonly="true" />
									</td>

									<td><button id="next-button" type="button">?</button></td>
									
									<td><form:errors path="nxtstatus.sos_description" cssClass="error" /></td>
								</tr>
								
								<!-- Submit -->
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


<div id="role-dialog" title= <spring:message code="title.entries.users.roles"/> >
	<table class="display table table-bordered table-st" id="role-table">
		<thead>
			<tr>
				<th><spring:message code="model.id" /></th>
				<th><spring:message code="model.role.description" /></th>
			</tr>
		</thead>
	</table>
</div>


<div id="cur-dialog" title=<spring:message code="title.entries.so.status"/>>
	<table class="display table table-bordered table-st" id="cur-table">
		<thead>
			<tr>
				<th><spring:message code="model.id" /></th>
				<th><spring:message code="model.seviceorderstatus.description" /></th>
			</tr>
		</thead>
	</table>
</div>

<div id="next-dialog" title=<spring:message code="title.entries.so.status"/>>
	<table class="display table table-bordered table-st" id="next-table">
		<thead>
			<tr>
				<th><spring:message code="model.id" /></th>
				<th><spring:message code="model.seviceorderstatus.description" /></th>
			</tr>
		</thead>
	</table>
</div>

<script>

//Role-Table
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';

		//Tabela de Eventos: Ordenado em ordem decrescente
		var table = $('#role-table').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ],
			"ajax" : contextPath + '/so/status/load',
			"bLengthChange": false,
			"language" : {
				"url" : contextPath + "/resources/i18n/pt_lang.txt"
			}
		});
		

		$('#role-table tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
});

//Cur-Table
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';

		console.log(contextPath + '/so/status/load');

		//Tabela de Eventos: Ordenado em ordem decrescente
		var table = $('#cur-table').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ],
			"ajax" : contextPath + '/so/status/load',
			"bLengthChange": false,
			"language" : {
				"url" : contextPath + "/resources/i18n/pt_lang.txt"
			}
		});

		$('#cur-table tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
});

//Next-Table
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';

		//Tabela de Eventos: Ordenado em ordem decrescente
		var table = $('#next-table').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ],
			"ajax" : contextPath + '/so/status/load',
			"bLengthChange": false,
			"language" : {
				"url" : contextPath + "/resources/i18n/pt_lang.txt"
			}
		});

		$('#next-table tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
});

//********************************************************************


//Dialog Form: Type
$(function() {
	var dialog

	function submit() {
		var table = $('#role-table').dataTable();

		document.getElementById('type.sty_id').value = table.api().row(
				'.selected').data()[0];
		document.getElementById('type.sty_description').value = table.api()
				.row('.selected').data()[1];

		dialog.dialog("close");

		return true;
	}

	dialog = $("#role-dialog").dialog({
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

	$("#role-button").button().on("click", function() {
		dialog.dialog("open");
	});
});

$(function() {
	var dialog

	function submit() {
		var table = $('#cur-table').dataTable();

		document.getElementById('type.sty_id').value = table.api().row(
				'.selected').data()[0];
		document.getElementById('type.sty_description').value = table.api()
				.row('.selected').data()[1];

		dialog.dialog("close");

		return true;
	}

	dialog = $("#cur-dialog").dialog({
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

	$("#cur-button").button().on("click", function() {
		dialog.dialog("open");
	});
});

$(function() {
	var dialog

	function submit() {
		var table = $('#next-table').dataTable();

		document.getElementById('type.sty_id').value = table.api().row(
				'.selected').data()[0];
		document.getElementById('type.sty_description').value = table.api()
				.row('.selected').data()[1];

		dialog.dialog("close");

		return true;
	}

	dialog = $("#next-dialog").dialog({
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

	$("#next-button").button().on("click", function() {
		dialog.dialog("open");
	});
});



</script>



<%@ include file="../../../includes/footer.jsp"%>
