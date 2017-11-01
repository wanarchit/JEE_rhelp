package converters;

import NoyauFonctionnel.Employes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Paul
 */
@FacesConverter(value = "convertEmpNumss")
public class employeConverter implements Converter{


    @Override
    public Object getAsObject(FacesContext fc, UIComponent ui, String value) {
        NoyauFonctionnel.Employes emp = new Employes(value);
        return emp;
        
        
    }

    public String getAsString(FacesContext fc, UIComponent ui, Object value) {
        return null;
    }

 }