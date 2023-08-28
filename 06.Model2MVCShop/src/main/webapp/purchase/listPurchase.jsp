<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

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
		<td colspan="11">전체 ${pageVO.maxPage} 건수, 현재 ${pageVO.currentPage} 페이지</td>
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

	<c:set var="no" value="${searchVO.pageUnit }"/>
	<c:forEach var = "purchaseVO" items="${map.list}" >
	<tr class="ct_list_pop">
		<td align="Left">
			<a href="/getPurchase.do?tranNo=${purchaseVO.tranNo}">${purchaseVO.purchaseProd.prodName}</a>
		</td>
		<td></td>
		<td align="left">
			${purchaseVO.paymentOption.replaceAll(' ', '') eq '1' ? '현금결제' : '카드결제'}
		</td>
		<td></td>
		<td align="left">${purchaseVO.receiverName}</td>
		<td></td>
		<td align="left">${purchaseVO.divyAddr}</td>
		<td></td>
		<td align="left">현재
		<c:set var="tranCode" value="${purchaseVO.tranCode.replaceAll(' ', '')}"/>
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
			${purchaseVO.divyRequest}
		</td>
		<td></td>
		<td align="left">
			${purchaseVO.orderDate}
		</td>
		<td></td>
		<td align="left">
			<c:if test="${tranCode eq '3'}">
				<a href="/updateTranCode.do?tranNo=${purchaseVO.tranNo}&tranCode=3">물건도착</a>
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
		<td align="center">
		<c:if test="${pageVO.beginUnitPage != 1}">
			<a href="/listPurchase.do?page=${pageVO.getBeginUnitPage()-pageVO.getPageUnit()}">이전</a> 
		</c:if>
		<c:forEach var="i" begin="${pageVO.beginUnitPage}" end="${pageVO.endUnitPage}">
			<a href="/listPurchase.do?page=${i}">${i}</a> 
		</c:forEach>
		<c:if test="${pageVO.endUnitPage != pageVO.maxPage}">
			<a href="/listPurchase.do?page=${pageVO.getBeginUnitPage()+pageVO.getPageUnit()}">다음</a> 
		</c:if>
		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>