      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              	  
			<!-- Inicio -->
                  <li>
                      <a class="active" href="<c:url value='/index' />">
                          <i class="fa fa-dashboard"></i>
                          <span><spring:message code="menu.start" /></span>
                      </a>
                  </li>
			<!-- Fim Inicio -->
			
			<!-- Cadastros -->
				  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-laptop"></i>
                          <span><spring:message code="menu.entries" /></span>
                      </a>
                      <ul class="sub">
                      	  
                      	  <!-- Sub-Menu: Locais  -->
                          <li class="sub-menu">
                              <a  href="javascript:;">
                              <span><spring:message code="menu.entries.sites" /></span>
                              </a>
                              <ul class="sub">
                                  <li><a  href="<c:url value='/sites/sites/' />">
                                  	  <span><spring:message code="menu.entries.sites.sites" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/sites/types/' />">
                                  	  <span><spring:message code="menu.entries.sites.type" /></span>
                                  	  </a>
                                  </li>
                              </ul>
                          </li>
                          
                          <!-- Sub-Menu: Equipamentos -->
                           <li class="sub-menu">
                              <a  href="javascript:;">
                              <span><spring:message code="menu.entries.equipments" /></span>
                              </a>
                              <ul class="sub">
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.equipments.equipments" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.equipments.model" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.equipments.type" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.equipments.manufacturer" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.equipments.counter" /></span>
                                  	  </a>
                                  </li>
                              </ul>
                          </li>
                          
                          <!-- Sub-Menu: Alarmes -->
                           <li class="sub-menu">
                              <a  href="javascript:;">
                              <span><spring:message code="menu.entries.alarms" /></span>
                              </a>
                              <ul class="sub">
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.alarms.alarms" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.alarms.group" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.alarms.filter" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.alarms.type" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.alarms.severity" /></span>
                                  	  </a>
                                  </li>
                              </ul>
                          </li>
                          
                          
                          <!-- Sub-Menu: Manutenção -->
                           <li class="sub-menu">
                              <a  href="javascript:;">
                              <span><spring:message code="menu.entries.maintenance" /></span>
                              </a>
                              <ul class="sub">
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.maintenance.technician" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.maintenance.subsystem" /></span>
                                  	  </a>
                                  </li>
                              </ul>
                          </li>
                          
                          
                          <!-- Sub-Menu: Ordem de Serviço -->
                           <li class="sub-menu">
                              <a  href="javascript:;">
                              <span><spring:message code="menu.entries.servicesorders" /></span>
                              </a>
                              <ul class="sub">
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.servicesorders.type" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.servicesorders.status" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.servicesorders.services" /></span>
                                  	  </a>
                                  </li>
                                  
                                  <li><a  href="<c:url value='/so/rules/' />">
                                  	  <span><spring:message code="menu.entries.servicesorders.rules" /></span>
                                  	  </a>
                                  </li>
                              </ul>
                          </li>
                          
                          
                          <!-- Sub-Menu: Usuarios -->
                           <li class="sub-menu">
                              <a href="javascript:;">
                              <span><spring:message code="menu.entries.users" /></span>
                              </a>
                              <ul class="sub">

                                  <li><a  href="<c:url value='/params/' />">
                                  	  <span><spring:message code="menu.entries.users.users" /></span>
                                  	  </a>
                                  </li>

                              </ul>
                          </li>                          
                          
                      </ul>
                  </li>
			<!-- Fim Cadastros -->

			<!-- Eventos -->
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-exclamation-triangle"></i>
                          <span><spring:message code="menu.events" /></span>
                      </a>
                      <ul class="sub">

						<li><a  href="<c:url value='/events/events/' />">
							<span><spring:message code="menu.events.alarms" /></span>
							</a>
						</li>
						
						<li><a  href="<c:url value='events/alarms/' />">
							<span><spring:message code="menu.events.events" /></span>
							</a>
						</li>
						
                      </ul>
                  </li>
			<!-- Fim Eventos -->

			<!-- Manutenções -->
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-wrench"></i>
                          <span><spring:message code="menu.maintenance" /></span>
                      </a>
                      <ul class="sub">
							
                          <li><a  href="<c:url value='/maintenance/so/' />">
							<span><spring:message code="menu.maintenance.serviceorder" /></span>
							</a></li>
                      	  <li></li>
                      </ul>
                  </li>
			<!-- Fim Manutenções -->

			<!-- Relatorios -->
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-bar-chart-o"></i>
                          <span><spring:message code="menu.reports" /></span>
                      </a>
                      <ul class="sub">
                      
                      </ul>
                  </li>
			<!-- Fim Relatorios -->
                  
			<!-- Configuracoes -->
                  <li>
                      <a href="<c:url value='/params/' />">
                          <i class="fa fa-gears"></i>
                          <span><spring:message code="menu.configurations" /></span>
                      </a>
                  </li>
              </ul>
			<!-- Fim Configurações -->
			
			<!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->