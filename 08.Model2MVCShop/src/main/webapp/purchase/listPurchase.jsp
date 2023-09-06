<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("td:contains('����')").on("click" , function() {
			self.location = "/purchase/listPurchase?currentPage=${page.beginUnitPage-page.pageUnit}";
		});
		 

		$("td:contains('����')").on("click" , function() {
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
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${page.maxPage} �Ǽ�, ���� ${page.currentPage} ������</td>
	</tr>

	<tr>
		<td class="ct_list_b" width="100">��ǰ�̸�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">���Ź��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">�������̸�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������ּ�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">���ſ�û����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�ֹ���</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
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
			${purchase.paymentOption.replaceAll(' ', '') eq '1' ? '���ݰ���' : 'ī�����'}
		</td>
		<td></td>
		<td align="left">${purchase.receiverName}</td>
		<td></td>
		<td align="left">${purchase.divyAddr}</td>
		<td></td>
		<td align="left">����
		<c:set var="tranCode" value="${purchase.tranCode.replaceAll(' ', '')}"/>
			<c:choose>
				<c:when test="${tranCode eq '2'}">
					���ſϷ�
				</c:when>
				<c:when test="${tranCode eq '3'}">
					�����
				</c:when>
				<c:when test="${tranCode eq '4'}">
					��ۿϷ�
				</c:when>
				<c:otherwise>
					�Ǹ���
				</c:otherwise>
			</c:choose>
				���� �Դϴ�.</td>
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
				<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3">���ǵ���</a>
			</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
		<%--   ����ó�� --%>	

	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center" id="page">
		<c:if test="${page.beginUnitPage != 1}">
			����
		</c:if>
		<c:forEach var="i" begin="${page.beginUnitPage}" end="${page.endUnitPage}">
			<a href="/purchase/listPurchase?currentPage=${i}">${i}</a>
		</c:forEach>
		<c:if test="${page.endUnitPage != page.maxPage}">
			����
		</c:if>
		
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>