package validator;

import NoyauFonctionnel.Reservations;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Paul
 */
@FacesValidator("dateRangeResaValidator")
public class DateRangeResaValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        UIInput dateDebut = (UIInput) component.getAttributes().get("dateD");
        Object dateDValue = dateDebut.getValue();
        if (dateDValue==null) {
            return;
        }
         
        Date dateDeb = (Date)dateDValue;
        Date endDate = (Date)value;
        if (endDate.before(dateDeb)) {
            FacesMessage message = new FacesMessage("Date de fin avant la date de début");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        Date currentDate = new Date();
        if (dateDeb.before(currentDate)) {
            FacesMessage message = new FacesMessage("Date de début est déjà passée");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        UIInput numSSUI = (UIInput) component.getAttributes().get("numSS");
        String numSSValue = (String) numSSUI.getSubmittedValue();
        List<Reservations> listResaUI = (List<Reservations>)component.getAttributes().get("resa");
        boolean valid = true;
        String nomEmpl = "";
        for (Reservations resa : listResaUI) {
            if (resa.getNumSS().getNumSS().equals(numSSValue)) {
                nomEmpl = resa.getNumSS().getPrenom()+" "+resa.getNumSS().getNom();
                if(resa.getDateD().before(dateDeb) && resa.getDateF().after(dateDeb)){
                    valid = false;
                }
                if(resa.getDateD().after(dateDeb) && resa.getDateF().before(endDate)){
                    valid = false;
                }
                if(resa.getDateD().before(endDate) && resa.getDateF().after(endDate)){
                    valid = false;
                }
            }
        }
        if(!valid){
            FacesMessage message = new FacesMessage("L'employé choisi ("+nomEmpl+") à déjà une réservation pour cette période");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        UIInput plaqueUI = (UIInput) component.getAttributes().get("plaque");
        String plaqueValue = (String) plaqueUI.getSubmittedValue();
        boolean valid2 = true;
        String nomEmp = "";
        String nomVoit = "";
        for (Reservations resa : listResaUI) {
            if (resa.getPlaque().getPlaque().equals(plaqueValue)) {
                nomVoit = resa.getPlaque().getMarque()+" "+resa.getPlaque().getModele();
                if(resa.getDateD().before(dateDeb) && resa.getDateF().after(dateDeb)){
                    nomEmp = resa.getNumSS().getPrenom()+" "+resa.getNumSS().getNom();
                    valid2 = false;
                }
                if(resa.getDateD().after(dateDeb) && resa.getDateF().before(endDate)){
                    nomEmp = resa.getNumSS().getPrenom()+" "+resa.getNumSS().getNom();
                    valid2 = false;
                }
                if(resa.getDateD().before(endDate) && resa.getDateF().after(endDate)){
                    nomEmp = resa.getNumSS().getPrenom()+" "+resa.getNumSS().getNom();
                    valid2 = false;
                }
            }
        }
        if(!valid2){
            FacesMessage message = new FacesMessage("La voiture choisie ("+nomVoit+") est déjà réservée pour cette période par "+nomEmp);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
