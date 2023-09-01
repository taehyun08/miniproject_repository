<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>���� �����ȸ</title>

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
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${pageVO.maxPage} �Ǽ�, ���� ${pageVO.currentPage} ������</td>
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

	<c:set var="no" value="${searchVO.pageUnit }"/>
	<c:forEach var = "purchaseVO" items="${map.list}" >
	<tr class="ct_list_pop">
		<td align="Left">
			<a href="/getPurchase.do?tranNo=${purchaseVO.tranNo}">${purchaseVO.purchaseProd.prodName}</a>
		</td>
		<td></td>
		<td align="left">
			${purchaseVO.paymentOption.replaceAll(' ', '') eq '1' ? '���ݰ���' : 'ī�����'}
		</td>
		<td></td>
		<td align="left">${purchaseVO.receiverName}</td>
		<td></td>
		<td align="left">${purchaseVO.divyAddr}</td>
		<td></td>
		<td align="left">����
		<c:set var="tranCode" value="${purchaseVO.tranCode.replaceAll(' ', '')}"/>
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
			${purchaseVO.divyRequest}
		</td>
		<td></td>
		<td align="left">
			${purchaseVO.orderDate}
		</td>
		<td></td>
		<td align="left">
			<c:if test="${tranCode eq '3'}">
				<a href="/updateTranCode.do?tranNo=${purchaseVO.tranNo}&tranCode=3">���ǵ���</a>
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
		<td align="center">
		<c:if test="${pageVO.beginUnitPage != 1}">
			<a href="/listPurchase.do?page=${pageVO.getBeginUnitPage()-pageVO.getPageUnit()}">����</a> 
		</c:if>
		<c:forEach var="i" begin="${pageVO.beginUnitPage}" end="${pageVO.endUnitPage}">
			<a href="/listPurchase.do?page=${i}">${i}</a> 
		</c:forEach>
		<c:if test="${pageVO.endUnitPage != pageVO.maxPage}">
			<a href="/listPurchase.do?page=${pageVO.getBeginUnitPage()+pageVO.getPageUnit()}">����</a> 
		</c:if>
		
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>