<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Estadistica Diesel | HCAM</title>

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
                
                <li>
                    <a href="agua.html"><i class="fa fa-tint"></i> <span class="nav-label">AGUA POTABLE</span></a>
                </li>
                <li class="active">
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
                    <span class="m-r-sm text-muted welcome-message">Monitoreo Casa de Maquinas</span>
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
                    <h2>Estadistica Sistema de Monitoreo de Diesel</h2>
                </div>
               
            </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            
            <div class="form-group">

                                    <div class="col-lg-2"><select class="form-control m-b" name="account">
                                        <option>Enero</option>
                                        <option>Febrero</option>
                                        <option>Marzo</option>
                                        <option>Abril</option>
                                        <option>Mayo</option>
                                        <option>Junio</option>
                                        <option>Julio</option>
                                        <option>Agosto</option>
                                        <option>Septiembre</option>
                                        <option>Octubre</option>
                                        <option>Noviembre</option>
                                        <option>Diciembre</option>
                                    </select>

                                        
                                    </div>
                
                    
               

                                    <div class="col-lg-2"><select class="form-control m-b" name="account">
                                        <option>2015</option>
                                        
                                    </select>

                                        
                                    </div>
                                    
                                    <button class="btn btn-white" type="submit">Buscar</button>
                </div>
           
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Registro de cantidad de Diesel en los Tanques, Cantidad Entregada y Estado de Sensores.</h5>
                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" >
                    <thead>
                    <tr>
                        <th>Fecha y Hora</th>
                        <th>Tanque 1</th>
                        <th>Tanque 2</th>
                        <th>Total</th>
                        <th>Descarga - Temperatura</th>
                        <th>Contador Salida</th>
                        <th>Alarma</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                    	ManejadorDiesel manejadorDiesel = new ManejadorDiesel();
                    	List<MovimientoDieselDTO> lista= manejadorDiesel.buscarEstadistica(7, 2015);
                    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    	for(MovimientoDieselDTO Diesel: lista) {
                    %>    
                    <tr>
                        <td><%= format.format(diesel.getFecha()) %></td>
                        <td><%= diesel.getAcumuladoTanque1() %> GALONES</td>
                        <td><%= diesel.getAcumuladoTanque2() %> GALONES</td>
                        <td><%= diesel.getTotal() %> GALONES</td>
                        <td><%= diesel.getDescarga() %> GALONES</td>
                        <td><%= diesel.getTemperatura() %> &#176; C</td>
                        <td><%= diesel.getSalida() %> GALONES</td>
                        <td><%= diesel.getAlarma() %></td>
                        
                    </tr>
                    <%
                    	}
                    %>    
                    <tr>
                        <td>10/07/2015 - 17.35</td>
                        <td>2000 Gal</td>
                        <td>200 Gal</td>
                        <td>2200 Gal</td>
                        <td>6000 Gal - 25°C</td>
                        <td>9000 Gal</td>
                        <td>Bajo Nivel Tanque 2</td>
                    </tr>
                    <tr>
                        <td>10/07/2015 - 17.35</td>
                        <td>2000 Gal</td>
                        <td>200 Gal</td>
                        <td>2200 Gal</td>
                        <td>6000 Gal</td>
                        <td>9000 Gal</td>
                        <td>Bajo Nivel Tanque 2</td>
                    </tr>
                    
                    </tbody>
                    <tfoot>
                    <tr>
                         <th>Fecha y Hora</th>
                        <th>Tanque 1</th>
                        <th>Tanque 2</th>
                        <th>Total</th>
                        <th>Descarga - Temperatura</th>
                        <th>Contador Salida</th>
                        <th>Alarma</th>
                        
                        
                        
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
