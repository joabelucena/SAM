
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
						<spring:message code="title.events.events" />
						
						<span class="tools pull-right"> <a href="javascript:;"
							class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>

					</header>
					
					<div class="panel-body">
						<div class="adv-table">
							<table class="display table table-bordered table-st"
								id="event-table">
								<thead>
									<tr>
										<th><spring:message code="model.id"/></th>
										<th><spring:message code="model.events.equipment"/></th>
										<th><spring:message code="model.events.alarm"/></th>
										<th><spring:message code="model.events.equipment"/></th>
										<th><spring:message code="model.events.equipment"/></th>
										<th><spring:message code="model.events.datetime"/></th>
										<th><spring:message code="model.events.action"/></th>
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
	console.log(contextPath + '/events/load');
	
    //Tabela de Eventos: Ordenado em ordem decrescente
    var table = $('#event-table').dataTable( {
        "aaSorting": [[ 0, "desc" ]],
        "scrollX": false,
        "ajax": contextPath + '/events/load',
        "language": {
    		"url": "../../resources/i18n/pt_lang.txt"
    	},
    	
    	/*
    	"fnRowCallback": function( nRow, aData ) {
    		var id = aData.myID; // ID is returned by the server as part of the data
    		var $nRow = $(nRow); // cache the row wrapped up in jQuery

    		$nRow.css({"background-color":"#ffd6d6"});

    		return nRow
    	},
    	*/
    	
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
		var table = $('#event-table').dataTable();
		console.log("a");
		table.api().ajax.reload(null,false);
	},500);

});
	

</script>



<!--main content end-->

<%@ include file="../../includes/footer.jsp"%>