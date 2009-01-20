package cn.edu.dhu.acm.web.authorization;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import cn.edu.dhu.acm.web.backing.UserBean;
import cn.edu.dhu.acm.web.util.Util;

/**
 * Created on Jan 17, 2009
 * @author yhu
 */
public class UserLoginAuthorization implements PhaseListener {

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void beforePhase(PhaseEvent event) {
        FacesContext ctx = Util.getFacesContext();
        String viewId = ctx.getViewRoot().getViewId();
        if (viewId.endsWith("submit.jsp")) {
            UserBean ub = (UserBean) Util.getBean("UserBean");
            if (!ub.isLogonMode()) {
                try {
                    ExternalContext ectx = Util.getExternalContext();
                    String url = ectx.getRequestContextPath() + "/login.faces";
                    ectx.redirect(url);
                } catch(IOException ioe) {
                    
                }
            }
        }
    }

    public void afterPhase(PhaseEvent event) {

    }
}
