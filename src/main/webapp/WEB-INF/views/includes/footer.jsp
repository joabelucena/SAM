
	<section class="wrapper">
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
          	  <img class="site-footer-img" src="<c:url value="/resources/img/logo-vlt-emtu.png"/>">
              <a href="#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
 	</section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.dcjqaccordion.2.7.js"/>" class="include" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.scrollTo.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.nicescroll.js"/>"	type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.sparkline.js"/>"		type="text/javascript"></script>
    <script src="<c:url value="/resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"/>"></script>
    <script src="<c:url value="/resources/js/owl.carousel.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.customSelect.min.js"/>"></script>
    <script src="<c:url value="/resources/js/respond.min.js"/>"></script>

	
    <!-- datatables -->
    <script type="text/javascript" src="<c:url value="/resources/assets/advanced-datatable/media/js/jquery.dataTables.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/assets/data-tables/DT_bootstrap.js"/>"></script>
    
    <!--dynamic table initialization -->
    <!--script src="<c:url value="/resources/js/dynamic_table_init.js"/>"></script -->
    <!-- script src="<c:url value="/resources/js/event_table_init.js"/>"></script-->

    <!--right slidebar-->
    <script src="<c:url value="/resources/js/slidebars.min.js"/>"></script>

    <!--common script for all pages-->
    <script src="<c:url value="/resources/js/common-scripts.js"/>"></script>

    <!--script for this page-->
    <script src="<c:url value="/resources/js/sparkline-chart.js"/>"></script>
    <script src="<c:url value="/resources/js/easy-pie-chart.js"/>"></script>
    <script src="<c:url value="/resources/js/count.js"/>"></script>

  <script>

      //owl carousel

      $(document).ready(function() {
          $("#owl-demo").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true,
			  autoPlay:true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>