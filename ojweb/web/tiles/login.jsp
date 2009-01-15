<%--
    Document   : login
    Created on : Jan 14, 2009, 9:36:24 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="login">
    <h:form>
        <h:panelGrid columns="1">
            <h:panelGroup>
                <h:outputLabel for="username" value="Username: " />
                <h:inputText id="username" binding="#{LoginBean.username}"></h:inputText>
            </h:panelGroup>
            <h:panelGroup>
                <h:outputLabel for="username" value="Password: " />
                <h:inputSecret id="passowrd" binding="#{LoginBean.password}"></h:inputSecret>
            </h:panelGroup>
            <h:panelGroup>
                <h:commandButton id="login" value="Login" action="#{LoginBean.login_action}"></h:commandButton>
            </h:panelGroup>
        </h:panelGrid>
    </h:form>
</f:subview>