package validator;

import NoyauFonctionnel.Employes;
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
@FacesValidator("loginUserValidator")
public class LoginUserValidator implements Validator{
           
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            System.out.println("valeur null!");
            return;
        }
        
        if (value.equals("")) {
            System.out.println("valeur vide!");
            return;
        }
        
        String mdpSaisi = (String)value;
        System.out.println("mdp saisi = "+mdpSaisi);
        UIInput mdpConfUI = (UIInput) component.getAttributes().get("mdpConf");
        System.out.println("mdpconf ok : "+mdpConfUI.toString());
        Object mdpConfValue = mdpConfUI.getValue();
        System.out.println("mdpconfValue ok : "+mdpConfValue.toString());
        String mdpConf = (String) mdpConfValue;
        System.out.println("mdp conf saisi = "+mdpConf);
        boolean valid = true;
        
        if(!valid){
            FacesMessage message = new FacesMessage("Un employé à déjà ce numéro de sécurité social.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }else{
            FacesMessage message = new FacesMessage("OK");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }
}
