/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Map;
import org.apache.velocity.app.VelocityEngine;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;


/**
 *
 * @author Daniel
 */
public class ViewUtil {
 
    // Renders un template con un model y request dado
    // incorporar toda variable que sean necesario a todas las paginas web
    public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.put("template", templatePath);
        model.put("PathWeb", Path.Web.class); // Access application URLs from templates
        return strictVelocityEngine().render(new ModelAndView(model,  Path.Template.LAYOUT));
    }

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        
        // verifica que las variables referencias existan en el model. 
        // Si hay una variable que apunta a null da un error.
        //configuredEngine.setProperty("runtime.references.strict", true); 
        
        // da un error al intentar hacer una suma inválida, por ejemplo null + 10
        //configuredEngine.setProperty("runtime.strict.math", true);

        // Estos son básicos para que funciones Velocity adecuadamente. NO SACAR.
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
        
    }   
}