
<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/menu.jsp"%>
<%@ include file="../../includes/sidebar.jsp"%>

<style>
	.td center {

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
						<spring:message code="entries.parameters.title" />
						<span class="tools pull-right"> <a href="javascript:;"
							class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>
						
						<!-- Incluir novo registro -->
						<a href="add/0"
							class="btn btn-success btn-xs"> + Incluir 
						</a>
						
					</header>
					<div class="panel-body">
						<div class="adv-table">
							<table class="display table table-bordered table-st"
								id="dynamic-table">
								<thead>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>Type</th>
										<th>Value</th>
										<th>Descrição</th>
										<th><center>Ações</center></th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${parameterList}" var="parameter">
										<tr>

											<td>${parameter.par_id}</td>
											<td>${parameter.par_name}</td>
											<td>${parameter.par_type}</td>
											<td>${parameter.par_value}</td>
											<td>${parameter.par_desc}</td>
											
											<td class="td center">
												
													<!-- edit button --> 
													<!-- /url?par_name=asd&par_type=s&par_value=das&par_desc=das&action=Confirmar -->
													<a href="edit/${parameter.par_id}"
														class="btn btn-info btn-xs"> <i class="fa fa-pencil"></i>
													</a>
													
													<!-- remove button -->
													<a href="delete/${parameter.par_id}"
														class="btn btn-danger btn-xs"> <i class="fa fa-trash"></i>
													</a>
												
											</td>
										</tr>
									</c:forEach>
								</tbody>
								
								<tfoot>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>Type</th>
										<th>Value</th>
										<th>Descrição</th>
										<th><center>Ações</center></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- page end-->
	</section>
</section>

<script>


function loadData() {
	
	var contextPath='<%=request.getContextPath()%>';
	var t = $('#dynamic-table').DataTable();
	var counter = 5;
	
    $.ajax({
        url : contextPath + '/params/load',
        success : function(result) {
						
            t.clear().draw();
			
            for (var i = 0 ; i < result.length; ++i) {
                
            	t.row.add([result[i].par_id
							,result[i].par_name
							,result[i].par_type
							,result[i].par_value
							,result[i].par_desc
							,'<a href="edit/'+result[i].par_id+'"'+
							'class="btn btn-info btn-xs"> <i class="fa fa-pencil"></i>'+
							'</a>'+
							
							'<a href="delete/'+result[i].par_id+'"'+
							 'class="btn btn-danger btn-xs"> <i class="fa fa-trash"></i>'+
						    '</a>',]).draw();
        	}
            
            t.$('td:nth-last-child(1)').addClass('center')
            
        },
        error:function (jqXHR, textStatus, errorThrown) {
            console.log("Erro " + textStatus + " " + errorThrown + " !");
        }
    });
}

$(document).ready(function () {
	setInterval(loadData, 3000);
});

</script>



<!--main content end-->

<%@ include file="../../includes/footer.jsp"%>
