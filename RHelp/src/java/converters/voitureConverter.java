package converters;

import NoyauFonctionnel.Voiture;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Paul
 */
@FacesConverter(value = "convertVoitPlaque")
public class voitureConverter implements Converter{


    @Override
    public Object getAsObject(FacesContext fc, UIComponent ui, String value) {
        NoyauFonctionnel.Voiture voit = new Voiture(value);
        return voit;
        
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent ui, Object value) {
        return value.toString();
    }

 }