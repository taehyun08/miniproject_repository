<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("td:contains('이전')").on("click" , function() {
			self.location = "/purchase/listPurchase?currentPage=${page.beginUnitPage-page.pageUnit}";
		});
		 

		$("td:contains('다음')").on("click" , function() {
			self.location = "/purchase/listPurchase?currentPage=${page.beginUnitPage+page.pageUnit}";
		});
	});	
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${page.maxPage} 건수, 현재 ${page.currentPage} 페이지</td>
	</tr>

	<tr>
		<td class="ct_list_b" width="100">물품이름</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">구매방법</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">구매자이름</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매자주소</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매요청사항</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">주문일</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
		<td class="ct_line02"></td>
	</tr>

	<tr>
		<td colspan="15" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var="no" value="${search.pageUnit }"/>
	<c:forEach var = "purchase" items="${map.list}" >
	<tr class="ct_list_pop">
		<td align="Left">
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">${purchase.purchaseProd.prodName}</a>
		</td>
		<td></td>
		<td align="left">
			${purchase.paymentOption.replaceAll(' ', '') eq '1' ? '현금결제' : '카드결제'}
		</td>
		<td></td>
		<td align="left">${purchase.receiverName}</td>
		<td></td>
		<td align="left">${purchase.divyAddr}</td>
		<td></td>
		<td align="left">현재
		<c:set var="tranCode" value="${purchase.tranCode.replaceAll(' ', '')}"/>
			<c:choose>
				<c:when test="${tranCode eq '2'}">
					구매완료
				</c:when>
				<c:when test="${tranCode eq '3'}">
					배송중
				</c:when>
				<c:when test="${tranCode eq '4'}">
					배송완료
				</c:when>
				<c:otherwise>
					판매중
				</c:otherwise>
			</c:choose>
				상태 입니다.</td>
		<td></td>
		<td align="left">
			${purchase.divyRequest}
		</td>
		<td></td>
		<td align="left">
			${purchase.orderDate}
		</td>
		<td></td>
		<td align="left">
			<c:if test="${tranCode eq '3'}">
				<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3">물건도착</a>
			</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
		<%--   예외처리 --%>	

	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center" id="page">
		<c:if test="${page.beginUnitPage != 1}">
			이전
		</c:if>
		<c:forEach var="i" begin="${page.beginUnitPage}" end="${page.endUnitPage}">
			<a href="/purchase/listPurchase?currentPage=${i}">${i}</a>
		</c:forEach>
		<c:if test="${page.endUnitPage != page.maxPage}">
			다음
		</c:if>
		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>