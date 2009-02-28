<%-- 
    Document   : index
    Created on : Jan 15, 2009, 8:03:01 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="login">
    <h:form>
        <h1>Welcome to DHU Online Judge!</h1>
        <h:outputLink value="submit.faces" >
            <h:outputText value="submit" />
        </h:outputLink>
        <br>
        <h:outputLink value="status.faces" >
            <h:outputText value="status" />
        </h:outputLink>
    </h:form>
</f:subview>