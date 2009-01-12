<%-- 
    Document   : problemlist
    Created on : Jan 11, 2009, 8:54:26 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<f:subview id="problemlist">
    <h:form>
    <h:dataTable headerClass="list-header" id="problemList" 
                 rowClasses="list-row-even,list-row-odd"
                 value="#{ProblemListBean.problems}"
                 var="problem" width="100%">
        <h:column>
            <h:outputText value="#{problem['problemId']}"/>
            <f:facet name="header">
                <h:outputText value="ID"/>
            </f:facet>
        </h:column>
        <h:column>
            <h:commandLink action="#{ProblemListBean.viewProblem}">
                <h:outputText value="#{problem['title']}"/>
            </h:commandLink>
            <h:outputText value=" -- #{problem['source']}"/>
            <f:facet name="header">
                <h:outputText value="Title"/>
            </f:facet>
        </h:column>
        <h:column>
            <h:outputText value="#{problem['accepted']} / #{problem['submit']}"/>
            <f:facet name="header">
                <h:outputText value="Ratio"/>
            </f:facet>
        </h:column>
    </h:dataTable>
    </h:form>
</f:subview>