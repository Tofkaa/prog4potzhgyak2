<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://potpotzh.prog4.mik.pte.hu/tags" prefix="cxml" %>
<t:page>
  <style type="text/css">
    td {
      border: 1px solid black;
    }
  </style>
  <div>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Név</th>
        <th>Alapítási év</th>
        <th>Ország</th>
        <th>Ismert termék</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${companies}" var="company">
        <tr>
          <td><c:out value="${company.id}"/></td>
          <td><c:out value="${company.companyName}"/></td>
          <td><c:out value="${company.estYear}"/></td>
          <td><c:out value="${company.country}"/></td>
          <td><c:out value="${company.knownProducts}"/></td>
          <td>
            <form method="GET" action="">
              <input type="hidden" name="xmlId" value="${company.id}">
              <button type="submit">XML Lekérés</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <br><hr>
    <c:if test="${not empty param.xmlId}">
      <h3>A kiválasztott cég XML adatai:</h3>
      <cxml:xmlData companyId="${param.xmlId}" />
    </c:if>

  </div>
</t:page>
