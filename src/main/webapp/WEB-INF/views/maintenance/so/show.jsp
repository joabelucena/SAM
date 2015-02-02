<!-- inicio -->

<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/menu.jsp"%>
<%@ include file="../../includes/sidebar.jsp"%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-sm-12">
				<section class="panel">
					<header class="panel-heading">
						<spring:message code="title.entries.sites.sites" />
						<span class="tools pull-right"> <a href="javascript:;"
							class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>
						
						<!-- Incluir novo registro -->
						<a href="add/0"
							class="btn btn-success btn-xs"><spring:message code="view.show.add" />
						</a>
						
					</header>
					<div class="panel-body">
						<div class="adv-table">
							<table class="display table table-bordered table-st"
								id="dynamic-table">
								<thead>
									<tr>
										<th><spring:message code="model.id" />					</th>
										<th><spring:message code="model.sites.description" />	</th>
										<th><spring:message code="model.sites.shortname" />		</th>
										<th><spring:message code="model.sites.parent" />		</th>
										<th><spring:message code="model.sites.type" />			</th>
										
										<th class="center" ><spring:message code="model.action"/></th>
									</tr>
								</thead>

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
$(document).ready(function () {
	
	var contextPath='<%=request.getContextPath()%>';
	
	console.log( contextPath + '/sites/load');
	
	
    //Tabela de Eventos: Ordenado em ordem decrescente
    var table = $('#dynamic-table').dataTable( {
        "aaSorting": [[ 0, "asc" ]],
        "ajax": contextPath + '/sites/sites/load',
        "language": {
    		"url":contextPath + "/resources/i18n/pt_lang.txt"
    	},
    	"columnDefs": [ {
            "targets": -1,
    	    "data": function ( row, type, val, meta ) {

    	    	var actions = 	'<a href="edit/'+row[0]+'"'
								+'class="btn btn-info btn-xs"><i class="fa fa-pencil"></i>'
								+'</a> '
								
								+'<a href="delete/'+row[0]+'"'
								+'class="btn btn-danger btn-xs"> <i class="fa fa-trash"></i>'
								'</a>';
			
      	      return actions;
      	    }
        } ]
    });
	
	setInterval(function() {
		var table = $('#dynamic-table').dataTable();
		table.api().ajax.reload(null,false);},10000);
});

</script>



<!--main content end-->

<%@ include file="../../includes/footer.jsp"%>
