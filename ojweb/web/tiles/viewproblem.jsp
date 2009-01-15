<%-- 
    Document   : viewproblem
    Created on : Jan 13, 2009, 2:34:24 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="viewproblem">
    <h:form>
        <h:outputText value="#{ViewProblemBean.problem.problemPath}"></h:outputText>
        <c:import url="${ViewProblemBean.problem.problemPath}"/>
    </h:form>
</f:subview>