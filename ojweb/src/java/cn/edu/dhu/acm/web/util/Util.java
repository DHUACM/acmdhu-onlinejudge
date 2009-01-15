package cn.edu.dhu.acm.web.util;

import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;

/**
 *
 * @author yhu
 */
public class Util {

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static Application getApplication() {
        return getFacesContext().getApplication();
    }

    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static Object getBean(String name) {
        return getApplication().getVariableResolver()
                .resolveVariable(getFacesContext(), name);
    }
}
