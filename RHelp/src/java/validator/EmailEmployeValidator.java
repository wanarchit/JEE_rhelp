package validator;

import NoyauFonctionnel.Employes;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 *
 * @author Paul
 */
@FacesValidator("emailEmployeValidator")
public class EmailEmployeValidator implements Validator{

    private Pattern pattern;
    private static final String EMAIL_PATTERN = "^[a-zA-Z1-9\\.\\-\\_]+\\@[a-zA-Z]+\\.[a-zA-Z]+$";
            
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        if (value.equals("")) {
            return;
        }
        
        String mailSaisi = (String)value;
        pattern = Pattern.compile(EMAIL_PATTERN);
        if(!pattern.matcher(value.toString()).matches()) {
            FacesMessage message = new FacesMessage("Le format de l'adresse ne correspond pas au format attendu ('xxx@xx.xx')");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        
        
        
        
        List<Employes> listEmployeUI = (List<Employes>)component.getAttributes().get("employe");
        boolean valid = true;
        String nomEmpl = "";
        for (Employes emp : listEmployeUI) {
            if (emp.getMail().equals(mailSaisi)) {
                nomEmpl = emp.getPrenom()+" "+emp.getNom();
                valid = false;
            }
        }
        if(!valid){
            FacesMessage message = new FacesMessage("Un employé ("+nomEmpl+") à déjà cette adresse mail. Merci d'en choisir une autre.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }
}
