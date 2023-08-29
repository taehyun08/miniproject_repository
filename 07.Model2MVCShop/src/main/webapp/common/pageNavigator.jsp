<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${pageVO.beginUnitPage != 1}">
			<a href="javascript:fncGetList('${pageVO.beginUnitPage-5}')">이전</a>
	</c:if>
	
	<c:forEach var="i" begin="${pageVO.beginUnitPage}" end="${pageVO.endUnitPage}">
		<a href="javascript:fncGetList('${ i }');">${ i }</a>
	</c:forEach>
	<c:if test="${pageVO.endUnitPage != pageVO.maxPage}">
			<a href="javascript:fncGetList('${pageVO.beginUnitPage+5}')">다음</a>
	</c:if>