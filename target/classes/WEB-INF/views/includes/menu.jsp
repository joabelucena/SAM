<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<!--header start-->
	<header class="header white-bg">
		<div class="sidebar-toggle-box">
			<div class="fa fa-bars tooltips" data-placement="right"
				data-original-title="Toggle Navigation"></div>
		</div>
		<!--logo start-->
		<div class="logottrans">
			<img src="<c:url value="/resources/img/logo-ttrans.png"/>">
		</div>
		<div class="logo"> I <strong>SAM</strong>APP</div>
		<!--logo end-->
		<div class="nav notify-row" id="top_menu">
			<!--  notification start -->

			<!-- settings end -->
			<!-- inbox dropdown start-->
			<!-- inbox dropdown end -->
			<!-- notification dropdown start-->
			<!-- notification dropdown end -->

			<!--  notification end -->
		</div>
		<div class="top-nav ">
			<!--search & user info start-->
			<ul class="nav pull-right top-menu">
				<li><input type="text" class="form-control search"
					placeholder="Search"></li>
				<!-- user login dropdown start-->
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> <img alt=""
						src="<c:url value="/resources/img/avatar1_small.jpg"/>" > <span
						class="username"><sec:authentication property="name"/></span> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu extended logout">
						<div class="log-arrow-up"></div>
						<li><a href="<c:url value="/#"/>"><i class="fa fa-suitcase"></i>Profile</a></li>
						<li><a href="<c:url value="/#"/>"><i class="fa fa-cog"></i> Settings</a></li>
						<li><a href="<c:url value="/#"/>"><i class="fa fa-bell-o"></i> Notification</a></li>
						<li><a href="<c:url value="/logout"/> "><i class="fa fa-key"></i> Log
								Out</a></li>
					</ul></li>
				<li class="sb-toggle-right"><i class="fa  fa-align-right"></i>
				</li>
				<!-- user login dropdown end -->
			</ul>
			<!--search & user info end-->
		</div>
	</header>
	<!--header end-->