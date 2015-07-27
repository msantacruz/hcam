<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ec.gob.iess.cuartomaquinas.dto.AguaDTO"%>
<%@page import="ec.gob.iess.cuartomaquinas.dto.ConsumoAguaDTO"%>
<%@page import="java.util.List"%>
<%@page import="ec.gob.iess.cuartomaquinas.db.ManejadorAgua"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Estadistica Agua | HCAM</title>

	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/plugins/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/plugins/dataTables/dataTables.tableTools.min.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>

    <div id="wrapper">

   <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" img src="images/logo%20IESS.png" width="52" height="98"/>
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">HCAM</strong>
                                </span></span>
                              </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            
                            <li><a href="index.html">Salir</a></li>
                        
                        </ul>
                    </div>
                    <div class="logo-element">
                        HCAM
                    </div>
                </li>
                
                <li class="active">
                    <a href="agua.html"><i class="fa fa-tint"></i> <span class="nav-label">AGUA POTABLE</span></a>
                </li>
                <li>
                    <a href="diesel.html"><i class="fa fa-truck"></i> <span class="nav-label">DIESEL</span></a>
                </li>
                <li>
                    <a href="vapor.html"><i class="fa fa-fire"></i> <span class="nav-label">VAPOR</span></a>
                </li>
                
            </ul>

        </div>
    </nav>


        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
        </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <span class="m-r-sm text-muted welcome-message">Monitoreo Casa de M&aacute;quinas</span>
                </li>
                
                <li>
                    <a href="index.html">
                        <i class="fa fa-sign-out"></i> Salir
                    </a>
                </li>
            </ul>
        </nav>
        </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>Estadistica Sistema de Presi&oacute;n Constante</h2>
                </div>
               
            </div>
        <div class="wrapper wrapper-content animated fadeInRight">
        
        	<div class="form-group">
						<form>
                                    <div class="col-lg-2"><select class="form-control m-b" name="mes" id="mes">
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
                
                    
               

                                    <div class="col-lg-2"><select class="form-control m-b" name="anio" id="anio">
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
                        <h5>Registro del funcionamiento de bombas, alarmas, presi&oacute;n y flujo</h5>
                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" >
                    <thead>
                    <tr>
                        <th>Fecha y Hora</th>
                        <th>Presi&oacute;n</th>
                        <th>Flujo</th>
                        <th>Bomba 1</th>
                        <th>Bomba 2</th>
                        <th>Bomba 3</th>
                        <th>Alarma</th>
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
	            	
	                    ManejadorAgua manejadorAgua = new ManejadorAgua();
                    	List<AguaDTO> lista= manejadorAgua.buscarEstadistica(Integer.parseInt(mes), Integer.parseInt(anio));
                    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    	for(AguaDTO agua: lista) {
                    %> 
                    <tr>
                        <td><%= format.format(agua.getFecha()) %></td>
                        <td><%= agua.getPresion() %> PSI</td>
                        <td><%= agua.getFlujo() %> l/min</td>
                        <td><%= agua.getBomba_1() %></td>
                        <td><%= agua.getBomba_2() %></td>
                        <td><%= agua.getBomba_3() %></td>
                        <td><%= agua.getAlarma() %></td>
                        
                    </tr>
                    <%
                    	}
                    %>       
                    
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Fecha y Hora</th>
                        <th>Presi&oacute;n</th>
                        <th>Flujo</th>
                        <th>Bomba 1</th>
                        <th>Bomba 2</th>
                        <th>Bomba 3</th>
                        <th>Alarma</th>
                        
                        
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
                        <h5>Estadistica de Consumo</h5>   
             		</div>
                    <div class="ibox-content">
                           <div class="flot-chart">     
                                <div class="flot-chart-content" id="flot-bar-chart"></div>
                            </div>
                    </div>
                </div>
            </div>
            
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Registro del consumo de agua</h5>
                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" >
                    <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Consumo</th>              
                    </tr>
                    </thead>
                    <tbody>
                    <% 
                    	ManejadorAgua manejadorAguaConsumoTabla = new ManejadorAgua();
						List<ConsumoAguaDTO> listaConsumoTabla= manejadorAguaConsumoTabla.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
						SimpleDateFormat formatoTabla = new SimpleDateFormat("yyyy-MM-dd");
						for(ConsumoAguaDTO consumo_agua: listaConsumoTabla) {                   		
                    %> 
                    <tr>
                        <td><%= formatoTabla.format(consumo_agua.getFecha()) %></td>
                        <td><%= consumo_agua.getConsumo() %> Gal</td>
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
        <div class="footer">
            
            <div>
                <strong>Copyright</strong> Escuela Politecnica Nacional - FIEE &copy; 2014-2015
            
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
        $(document).ready(function() {        	

        	$("#mes").val(<%=mes%>).attr('selected', 'selected');
        	$("#anio").val(<%=anio%>).attr('selected', 'selected');
            
            $('.dataTables-example').dataTable({
                responsive: true,
                "dom": 'T<"clear">lfrtip',
                "tableTools": {
                    "sSwfPath": "js/plugins/dataTables/swf/copy_csv_xls_pdf.swf"
                }
            });

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable( '../example_ajax.php', {
                "callback": function( sValue, y ) {
                    var aPos = oTable.fnGetPosition( this );
                    oTable.fnUpdate( sValue, aPos[0], aPos[1] );
                },
                "submitdata": function ( value, settings ) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition( this )[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            } );

            $(function() {
                var barOptions = {
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            fill: true,
                            fillColor: {
                                colors: [{
                                    opacity: 0.8
                                }, {
                                    opacity: 0.8
                                }]
                            }
                        }
                    },
                    xaxis: {
                        tickDecimals: 0
                    },
                    colors: ["#1ab394"],
                    grid: {
                        color: "#999999",
                        hoverable: true,
                        clickable: true,
                        tickColor: "#D4D4D4",
                        borderWidth:0
                    },
                    legend: {
                        show: false
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "dia: %x, galones: %y"
                    }
                };
                var barData = {
                    label: "bar",
                    data: [
							<%
	
								ManejadorAgua manejadorAguaConsumo = new ManejadorAgua();
								List<ConsumoAguaDTO> listaConsumo= manejadorAguaConsumo.buscarEstadistica1(Integer.parseInt(mes), Integer.parseInt(anio));
								SimpleDateFormat formato = new SimpleDateFormat("dd");
								for(ConsumoAguaDTO consumo_agua: listaConsumo) {
							%> 
                        [<%= formato.format(consumo_agua.getFecha()) %>,<%=consumo_agua.getConsumo()%>],
                        	<%
		                    	}
		                    %>  
                        
                    ]
                };
                $.plot($("#flot-bar-chart"), [barData], barOptions);

            });
        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData( [
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row",
                "New row"] );

        }
    </script>
<style>
    body.DTTT_Print {
        background: #fff;

    }
    .DTTT_Print #page-wrapper {
        margin: 0;
        background:#fff;
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
