<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<jsp:useBean id="entity" type="com.example.module.model.Example" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="example-div">
    <div class="meta" ${markup.property(entity, "dateField")}>
        ${markup.formatDate(entity.dateField)}
    </div>
    <div style="float: right;" ${markup.property(entity, "imageField")}>
        <img src="${entity.imageField.url}"/>
    </div>
    <b><dxa:resource key="example.field1Label"/></b><p ${markup.property(entity, "field1")}>${entity.field1}</p>
    <b><dxa:resource key="example.field2Label"/></b><p ${markup.property(entity, "field2")}>${entity.field2}</p>
    <h4>Latin Quotes:</h4>
    <c:forEach var="quote" items="${entity.latinQuotes}" varStatus="status">
        <div ${markup.property(entity, "latinQuotes", status.index)}>
            <p>${quote.latinQuote} = ${quote.translation}</p>
        </div>
    </c:forEach>
    <br/>
    <%--
    <div>
        <b>Model Example2:</b> <p>${entity.example2.field1}</p>
    </div>
    --%>
    <c:if test="${entity.enrichedField != null}">
        <p>Enriched field (by controller): ${entity.enrichedField}</p>
    </c:if>
    <div ${markup.property(entity, "linkField")}>
        <a class="btn btn-primary checkout-button" href="${entity.linkField}">Component Link <i class="fa fa-chevron-right"></i></a>
    </div>
</div>


