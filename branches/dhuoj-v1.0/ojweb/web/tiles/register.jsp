<%-- 
    Document   : register
    Created on : Jan 15, 2009, 9:41:47 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="register">
    <div align="center">
    <h:form>
        <h:panelGroup>
            <h:panelGrid columns="3" width="50%">
                <h:outputText value="Username: " />
                <h:inputText id="username" required="true" value="#{RegisterBean.username}" />
                <h:message for="username" styleClass="error-message" />

                <h:outputText value="Password: " />
                <h:inputSecret id="password" required="true" value="#{RegisterBean.password}" />
                <h:message for="password" styleClass="error-message" />

                <h:outputText value="Confirm Password: " />
                <h:inputSecret id="repassword" required="true" value="#{RegisterBean.repassword}" />
                <h:message for="repassword" styleClass="error-message" />

                <h:outputText value="Email: " />
                <h:inputText id="email" required="true" value="#{RegisterBean.email}" />
                <h:message for="email" styleClass="error-message" />

                <h:outputText value="Nickname: " />
                <h:inputText id="nickname" value="#{RegisterBean.nickname}" />
                <h:message for="nickname" styleClass="error-message" />

                <h:outputText value="School: " />
                <h:inputText id="school" value="#{RegisterBean.school}" />
                <h:message for="school" styleClass="error-message" />
            </h:panelGrid>

            <h:commandButton id="submit" value="submit" action="#{RegisterBean.submit_action}"></h:commandButton>
        </h:panelGroup>
        
    </h:form>
    </div>
</f:subview>