
package com.mycompany.Empresa.Controlador;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;
import static spark.debug.DebugScreen.enableDebugScreen;
import util.Path;


public class Principal {
   
   public static void main(String[] args) { 
  
    Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    enableDebugScreen();
      
    get(Path.Web.AREAS, EmpresaControlador.getAreas ); 
    get(Path.Web.AREASDIRECTOR, EmpresaControlador.getAreasConDirector ); 
    get(Path.Web.DEDICACION,EmpresaControlador.getEmpleadosDedicacion ); 
    get(Path.Web.AREASCONEMPLEADOS, EmpresaControlador.getAreasConEmpleados);  
    get(Path.Web.EMPLEADOSAREACODIGO, EmpresaControlador.getEmpleadosPorCodigoArea );  
    get(Path.Web.DIRECTORYEMPLEADO, EmpresaControlador.getDirectorEmpleado );  
}
}