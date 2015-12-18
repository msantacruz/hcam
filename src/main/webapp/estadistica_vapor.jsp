<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ec.gob.iess.cuartomaquinas.dto.VaporDTO"%>
<%@page import="ec.gob.iess.cuartomaquinas.dto.ConsumoVaporDTO"%>
<%@page import="java.util.List"%>
<%@page import="ec.gob.iess.cuartomaquinas.db.ManejadorVapor"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Estad&iacute;stica Vapor | HCAM</title>

<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="css/plugins/dataTables/dataTables.responsive.css"
	rel="stylesheet">
<link href="css/plugins/dataTables/dataTables.tableTools.min.css"
	rel="stylesheet">

<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <img alt="image" img src="images/logo%20IESS.png"
								width="52" height="98" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear"> <span class="block m-t-xs"> <strong
										class="font-bold">HCAM</strong>
								</span></span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">

								<li><a href="index.html">Salir</a></li>

							</ul>
						</div>
						<div class="logo-element">HCAM</div>
					</li>

					<li><a href="agua.html"><i class="fa fa-tint"></i> <span class="nav-label">AGUA POTABLE</span></a></li>
					<li><a href="diesel.html"><i class="fa fa-truck"></i> <span class="nav-label">DI&Eacute;SEL</span></a></li>
					<li class="active"><a href="vapor.html"><i class="fa fa-fire"></i> <span class="nav-label">VAPOR</span></a></li>
				</ul>

			</div>
		</nav>


		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">Monitoreo Casa de M&aacute;quinas</span></li>
						<li><a href="index.html"> <i class="fa fa-sign-out"></i>
								Salir
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>Estad&iacute;stica Sistema de Distribuci&oacute;n de Vapor</h2>
				</div>

			</div>
			<div class="wrapper wrapper-content animated fadeInRight">


				<div class="form-group">
					<form>
						<div class="col-lg-2">
							<select class="form-control m-b" name="mes" id="mes">
								<option value="1">Enero</option>
								<option value="2">Febrero</option>
								<option value="3">Marzo</option>
								<option value="4">Abril</option>
								<option value="5">Mayo</option>
								<option value="6">Junio</option>
								<option value="7">Julio</option>
								<option value="8">Agosto</option>
								<option value="9">Septiembre</option>
								<option value="10">Octubre</option>
								<option value="11">Noviembre</option>
								<option value="12">Diciembre</option>
							</select>


						</div>


						<div class="col-lg-2">
							<select class="form-control m-b" name="anio" id="anio">
								<%
									int anioActual = Calendar.getInstance().get(Calendar.YEAR);
																										                                    
											for (int i=2015; i<=anioActual; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
							</select>


						</div>

						<button class="btn btn-white" type="submit">Buscar</button>
					</form>
				</div>



				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>Registro del funcionamiento del Sistema de Distribuci&oacute;n de Vapor</h5>
							</div>
							<div class="ibox-content">

								<div class="table-responsive">
									<table id="records_table"
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Fecha y Hora</th>
												<th>V&aacute;lvula</th>
												<th>Estado</th>
												<th>Flujo</th>
												<th>Presi&oacute;n</th>
												<th>Temperatura</th>
											</tr>
										</thead>
										<tbody>
											<%
												String mes = request.getParameter("mes");
												if (mes == null) {
													int mesAnt  = Calendar.getInstance().get(Calendar.MONTH);
														mesAnt = mesAnt +1;
														mes = String.valueOf(mesAnt);
												}
																																								           		
												String anio = request.getParameter("anio");
												if (anio == null) {
													anio= String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
												}
																																							           		
												ManejadorVapor manejadorVapor = new ManejadorVapor();
													List<VaporDTO> lista= manejadorVapor.buscarEstadistica(Integer.parseInt(mes), Integer.parseInt(anio));
													SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
													for(VaporDTO vapor: lista) {
											%>
											<tr>
												<td><%=format.format(vapor.getFecha())%></td>
												<td><%=vapor.getValvula()%></td>
												<td><%=vapor.getEstado()%></td>
												<td><%=vapor.getFlujo()%> Kg/hora</td>
												<td><%=vapor.getPresion()%> PSI</td>
												<td><%=vapor.getTemperatura()%> &#176; C</td>

											</tr>
											<%
												}
											%>
										</tbody>
										<tfoot>
											<tr>
												<th>Fecha y Hora</th>
												<th>V&aacute;lvula</th>
												<th>Estado</th>
												<th>Flujo</th>
												<th>Presi&oacute;n</th>
												<th>Temperatura</th>


											</tr>
										</tfoot>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="row">

					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>Estad&iacute;stica de Consumo Total de Vapor</h5>
							</div>
							<div class="ibox-content">
								<div class="flot-chart">
									<div class="flot-chart-content" id="flot-bar-chart-total"></div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>Registro del Consumo Total de Vapor</h5>
							</div>
							<div class="ibox-content">

								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Fecha</th>
												<th>Consumo</th>
											</tr>
										</thead>
										<tbody>
											<%
												ManejadorVapor manejadorVaporConsumoTabla = new ManejadorVapor();
													List<ConsumoVaporDTO> listaConsumoTabla= manejadorVaporConsumoTabla.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
													SimpleDateFormat formatoTabla = new SimpleDateFormat("yyyy-MM-dd");
													for(ConsumoVaporDTO consumo_vapor: listaConsumoTabla) {
											%>
											<tr>
												<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
												<td><%=consumo_vapor.getConsumo_total()%> Kg</td>

											</tr>
											<%
												}
											%>

										</tbody>
										<tfoot>
											<tr>
												<th>Fecha</th>
												<th>Consumo</th>
											</tr>
										</tfoot>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>Estad&iacute;stica de Vapor Generado por el Caldero CB1</h5>
						</div>
						<div class="ibox-content">
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-bar-chart-cb1"></div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>Registro del Vapor Generado por el Caldero CB1</h5>
						</div>
						<div class="ibox-content">

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<th>Fecha</th>
											<th>Consumo</th>
										</tr>
									</thead>
									<tbody>
										<%
											ManejadorVapor manejadorVaporConsumoTablaCB1 = new ManejadorVapor();
												List<ConsumoVaporDTO> listaConsumoTablaCB1= manejadorVaporConsumoTablaCB1.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
												SimpleDateFormat formatoTablaCB1 = new SimpleDateFormat("yyyy-MM-dd");
												for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaCB1) {
										%>
										<tr>
											<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
											<td><%=consumo_vapor.getConsumo_cb1()%> Kg</td>

										</tr>
										<%
											}
										%>

									</tbody>
									<tfoot>
										<tr>
											<th>Fecha</th>
											<th>Consumo</th>
										</tr>
									</tfoot>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
		
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Estad&iacute;stica de Consumo de Vapor de los Tanques de Agua Caliente</h5>
					</div>
					<div class="ibox-content">
						<div class="flot-chart">
							<div class="flot-chart-content" id="flot-bar-chart-tanques-agua-caliente"></div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Registro del Consumo de Vapor de los Tanques de Agua Caliente</h5>
					</div>
					<div class="ibox-content">

						<div class="table-responsive">
							<table
								class="table table-striped table-bordered table-hover dataTables-example">
								<thead>
									<tr>
										<th>Fecha</th>
										<th>Consumo</th>
									</tr>
								</thead>
								<tbody>
									<%
										ManejadorVapor manejadorVaporConsumoTablaTANQUESAGUACALIENTE = new ManejadorVapor();
											List<ConsumoVaporDTO> listaConsumoTablaTANQUESAGUACALIENTE= manejadorVaporConsumoTablaTANQUESAGUACALIENTE.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
											SimpleDateFormat formatoTablaTANQUESAGUACALIENTE = new SimpleDateFormat("yyyy-MM-dd");
											for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaTANQUESAGUACALIENTE) {
									%>
									<tr>
										<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
										<td><%=consumo_vapor.getConsumo_tanques_agua_caliente()%>
											Kg</td>

									</tr>
									<%
										}
									%>

								</tbody>
								<tfoot>
									<tr>
										<th>Fecha</th>
										<th>Consumo</th>
									</tr>
								</tfoot>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Cocina</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-cocina"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Cocina</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaCOCINA = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaCOCINA= manejadorVaporConsumoTablaCOCINA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaCOCINA = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaCOCINA) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_cocina()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Patolog&iacute;a y Laboratorio Central</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-patologia-laboratorio-central"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Patolog&iacute;a y Laboratorio Central</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaPATOLOGIALABORATORIOCENTRAL = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaPATOLOGIALABORATORIOCENTRAL= manejadorVaporConsumoTablaPATOLOGIALABORATORIOCENTRAL.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaPATOLOGIALABORATORIOCENTRAL = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaPATOLOGIALABORATORIOCENTRAL) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_patologia_laboratorio_central()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Obstetricia</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-obstetricia"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Obstetricia</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaOBSTETRICIA = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaOBSTETRICIA = manejadorVaporConsumoTablaOBSTETRICIA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaOBSTETRICIA = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaOBSTETRICIA) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_obstetricia()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Biberones</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-biberones"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Biberones</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaBIBERONES = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaBIBERONES = manejadorVaporConsumoTablaBIBERONES.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaBIBERONES = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaBIBERONES) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_biberones()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Piscina</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-piscina"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Piscina</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaPISCINA = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaPISCINA = manejadorVaporConsumoTablaPISCINA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaPISCINA = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaPISCINA) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_piscina()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Quir&oacute;fanos y Salas de Partos</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-quirofano-sala-partos"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Quir&oacute;fanos y Salas de Partos</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaQUIROFANOSALAPARTOS = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaQUIROFANOSALAPARTOS = manejadorVaporConsumoTablaQUIROFANOSALAPARTOS.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaQUIROFANOSALAPARTOS = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaQUIROFANOSALAPARTOS) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_quirofano_sala_partos()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Lavander&iacute;a y Comedor</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-lavanderia-comedor"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Lavander&iacute;a y Comedor</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaLAVANDERIACOMEDOR = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaLAVANDERIACOMEDOR = manejadorVaporConsumoTablaLAVANDERIACOMEDOR.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaLAVANDERIACOMEDOR = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaLAVANDERIACOMEDOR) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_lavanderia_comedor()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Consumo de Vapor del &aacute;rea de Lavander&iacute;a</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-lavanderia"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor del &aacute;rea de Lavander&iacute;a</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaLAVANDERIA = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaLAVANDERIA = manejadorVaporConsumoTablaLAVANDERIA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaLAVANDERIA = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaLAVANDERIA) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_lavanderia()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Vapor Generado por el Caldero CB2 o por el Caldero CB4</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-cb2-cb4"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Vapor Generado por el Caldero CB2 o por el Caldero CB4</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaCB2CB4 = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaCB2CB4 = manejadorVaporConsumoTablaCB2CB4.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaCB2CB4 = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaCB2CB4) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_cb2_cb4()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Vapor Generado por el Caldero CB3 o por el Caldero CB5</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-cb3-cb5"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Vapor Generado por el Caldero CB3 o por el Caldero CB5</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaCB3CB5 = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaCB3CB5 = manejadorVaporConsumoTablaCB3CB5.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaCB3CB5 = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaCB3CB5) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_cb3_cb5()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Estad&iacute;stica de Vapor Consumido por la Unidad de Quemados</h5>
				</div>
				<div class="ibox-content">
					<div class="flot-chart">
						<div class="flot-chart-content" id="flot-bar-chart-quemados"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Registro del Consumo de Vapor Consumido por la Unidad de Quemados</h5>
				</div>
				<div class="ibox-content">

					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</thead>
							<tbody>
								<%
									ManejadorVapor manejadorVaporConsumoTablaQUEMADOS = new ManejadorVapor();
										List<ConsumoVaporDTO> listaConsumoTablaQUEMADOS = manejadorVaporConsumoTablaQUEMADOS.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
										SimpleDateFormat formatoTablaQUEMADOS = new SimpleDateFormat("yyyy-MM-dd");
										for(ConsumoVaporDTO consumo_vapor: listaConsumoTablaQUEMADOS) {
								%>
								<tr>
									<td><%=formatoTabla.format(consumo_vapor.getFecha())%></td>
									<td><%=consumo_vapor.getConsumo_quemados()%> Kg</td>

								</tr>
								<%
									}
								%>

							</tbody>
							<tfoot>
								<tr>
									<th>Fecha</th>
									<th>Consumo</th>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
		<div class="footer">
			<div>
				<strong>Copyright</strong> Escuela Polit&eacute;cnica Nacional - FIEE&copy; 2014-2015
			</div>
		</div>
	</div>
</div>
	<!-- Mainly scripts -->
	<script src="js/jquery-2.1.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Flot -->
	<script src="js/plugins/flot/jquery.flot.js"></script>
	<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script src="js/plugins/flot/jquery.flot.resize.js"></script>
	<script src="js/plugins/flot/jquery.flot.pie.js"></script>
	<script src="js/plugins/flot/jquery.flot.time.js"></script>


	<!-- Data Tables -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="js/plugins/dataTables/dataTables.responsive.js"></script>
	<script src="js/plugins/dataTables/dataTables.tableTools.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/inspinia.js"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- Page-Level Scripts -->
	<script>
		$(document)
				.ready(
						function() {

							$("#mes").val(
	<%=mes%>
		)
									.attr('selected', 'selected');
							$("#anio").val(
	<%=anio%>
		).attr('selected',
									'selected');

							$('.dataTables-example')
									.dataTable(
											{
												responsive : true,
												"dom" : 'T<"clear">lfrtip',
												"tableTools" : {
													"sSwfPath" : "js/plugins/dataTables/swf/copy_csv_xls_pdf.swf"
												}
											});

							/* Init DataTables */
							var oTable = $('#editable').dataTable();

							/* Apply the jEditable handlers to the table */
							oTable
									.$('td')
									.editable(
											'../example_ajax.php',
											{
												"callback" : function(sValue, y) {
													var aPos = oTable
															.fnGetPosition(this);
													oTable.fnUpdate(sValue,
															aPos[0], aPos[1]);
												},
												"submitdata" : function(value,
														settings) {
													return {
														"row_id" : this.parentNode
																.getAttribute('id'),
														"column" : oTable
																.fnGetPosition(this)[2]
													};
												},

												"width" : "90%",
												"height" : "100%"
											});

						});

		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumo = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumo= manejadorVaporConsumo.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formato = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_total()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-total"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoCB1 = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoCB1= manejadorVaporConsumoCB1.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoCB1 = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_cb1()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-cb1"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoTANQUESAGUACALIENTE = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoTANQUESAGUACALIENTE= manejadorVaporConsumoTANQUESAGUACALIENTE.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoTANQUESAGUACALIENTE = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_tanques_agua_caliente()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-tanques-agua-caliente"), [ barData ],
					barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoCOCINA = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoCOCINA= manejadorVaporConsumoCOCINA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoCOCINA = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_cocina()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-cocina"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoPATOLOGIALABORATORIOCENTRAL = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoPATOLOGIALABORATORIOCENTRAL = manejadorVaporConsumoPATOLOGIALABORATORIOCENTRAL.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoPATOLOGIALABORATORIOCENTRAL = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_patologia_laboratorio_central()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-patologia-laboratorio-central"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoOBSTETRICIA = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoOBSTETRICIA = manejadorVaporConsumoOBSTETRICIA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoOBSTETRICIA = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_obstetricia()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-obstetricia"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoBIBERONES = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoBIBERONES = manejadorVaporConsumoBIBERONES.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoBIBERONES = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_biberones()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-biberones"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoPISCINA = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoPISCINA = manejadorVaporConsumoPISCINA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoPISCINA = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_piscina()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-piscina"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoQUIROFANOSALAPARTOS = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoQUIROFANOSALAPARTOS = manejadorVaporConsumoQUIROFANOSALAPARTOS.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoQUIROFANOSALAPARTOS = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_quirofano_sala_partos()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-quirofano-sala-partos"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoLAVANDERIACOMEDOR = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoLAVANDERIACOMEDOR = manejadorVaporConsumoLAVANDERIACOMEDOR.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoLAVANDERIACOMEDOR = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_lavanderia_comedor()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-lavanderia-comedor"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoLAVANDERIA = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoLAVANDERIA = manejadorVaporConsumoLAVANDERIA.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoLAVANDERIA = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_lavanderia()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-lavanderia"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoCB2CB4 = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoCB2CB4 = manejadorVaporConsumoCB2CB4.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoCB2CB4 = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_cb2_cb4()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-cb2-cb4"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoCB3CB5 = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoCB3CB5 = manejadorVaporConsumoCB3CB5.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoCB3CB5 = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_cb3_cb5()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-cb3-cb5"), [ barData ], barOptions);

		});
		$(function() {
			var barOptions = {
				series : {
					bars : {
						show : true,
						barWidth : 0.6,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.8
							}, {
								opacity : 0.8
							} ]
						}
					}
				},
				xaxis : {
					tickDecimals : 0
				},
				colors : [ "#1ab394" ],
				grid : {
					color : "#999999",
					hoverable : true,
					clickable : true,
					tickColor : "#D4D4D4",
					borderWidth : 0
				},
				legend : {
					show : false
				},
				tooltip : true,
				tooltipOpts : {
					content : "dia: %x, galones: %y"
				}
			};
			var barData = {
				label : "bar",
				data : [
	<%ManejadorVapor manejadorVaporConsumoQUEMADOS = new ManejadorVapor();
							List<ConsumoVaporDTO> listaConsumoQUEMADOS = manejadorVaporConsumoQUEMADOS.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
							SimpleDateFormat formatoQUEMADOS = new SimpleDateFormat("dd");
							for(ConsumoVaporDTO consumo_vapor: listaConsumo) {%>
		[
	<%=formato.format(consumo_vapor.getFecha())%>
		,
	<%=consumo_vapor.getConsumo_quemados()%>
		],
	<%}%>
		]
			};
			$.plot($("#flot-bar-chart-quemados"), [ barData ], barOptions);

		});
		function fnClickAddRow() {
			$('#editable').dataTable().fnAddData(
					[ "Custom row", "New row", "New row", "New row", "New row",
							"New row" ]);

		}
	</script>

	<style>
body.DTTT_Print {
	background: #fff;
}

.DTTT_Print #page-wrapper {
	margin: 0;
	background: #fff;
}

button.DTTT_button, div.DTTT_button, a.DTTT_button {
	border: 1px solid #e7eaec;
	background: #fff;
	color: #676a6c;
	box-shadow: none;
	padding: 6px 8px;
}

button.DTTT_button:hover, div.DTTT_button:hover, a.DTTT_button:hover {
	border: 1px solid #d2d2d2;
	background: #fff;
	color: #676a6c;
	box-shadow: none;
	padding: 6px 8px;
}

.dataTables_filter label {
	margin-right: 5px;
}
</style>
</body>
</html>
