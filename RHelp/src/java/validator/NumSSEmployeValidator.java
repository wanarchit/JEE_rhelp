package validator;

import NoyauFonctionnel.Employes;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Paul
 */
@FacesValidator("numSSEmployeValidator")
public class NumSSEmployeValidator implements Validator{
           
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String numSaisi = (String)value;
        List<Employes> listEmployeUI = (List<Employes>)component.getAttributes().get("employe");
        boolean valid = true;
        String nomEmpl = "";
        for (Employes emp : listEmployeUI) {
            if (emp.getNumSS().equals(numSaisi)) {
                nomEmpl = emp.getPrenom()+" "+emp.getNom();
                valid = false;
            }
        }
        if(!valid){
            FacesMessage message = new FacesMessage("Un employé ("+nomEmpl+") à déjà ce numéro de sécurité social.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }
}
