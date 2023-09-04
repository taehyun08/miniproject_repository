<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>��ǰ �����ȸ</title>
	
	<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	
		function fncGetProductList() {
			
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${menu}").submit();
		}
		
		
		
		$(function() {
			//==> �˻� Event ����ó���κ�
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 �� 3 ��� ���� : $("tagName.className:filter�Լ�") �����. 
			 $( "td.ct_btn01:contains('�˻�')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('�˻�')" ).html() );
				fncGetProductList();
			});
			
			
			$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			$("h7").css("color" , "red");
			
			
			//==> �Ʒ��� ���� ������ ������ ??
			//==> �Ʒ��� �ּ��� �ϳ��� Ǯ�� ���� �����ϼ���.					
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			//console.log ( $(".ct_list_pop:nth-child(1)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(2)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(3)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(4)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(5)" ).html() ); 
			//console.log ( $(".ct_list_pop:nth-child(6)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(7)" ).html() ); 
			
		});	

	
	
	
	
	
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						
						<c:if test ="${menu eq 'manage'}">
						
							��ǰ ����
						</c:if>
						<c:if test ="${menu eq 'search'}">
							��ǰ ��� ��ȸ
						</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right" id="order">
			<ul>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceDesc">���ݳ�����</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceAsce">���ݳ�����</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=viewsDesc">���帹�̺���ǰ</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=nameAsce">�̸���</a>
	    	</ul>
    	</td>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${search.searchCondition eq '0' ? "selected" : '' }>��ǰ��ȣ</option>
				<option value="1" ${search.searchCondition eq '1' ? "selected" : '' }>��ǰ��</option>
				<option value="2" ${search.searchCondition eq '2' ? "selected" : '' }>��ǰ����</option>
			</select>
			<input type="text" name="searchKeyword" value="${empty search.searchKeyword ? '' : search.searchKeyword}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >��ü  ${page.maxPage} �Ǽ�, ���� ${search.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȸ��</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="no" value="${search.pageUnit }"/>
	<c:forEach var="vo" items="${map.list}">
	
	<tr class="ct_list_pop">
		<td align="center">${no}</td>
		<c:set var="no" value="${no - 1}"/>
		<td></td>
		<td align="left">
			<a href="/product/getProduct?prodNo=${vo.prodNo}&menu=${menu}">${vo.prodName}</a>
		</td>
		<td></td>
		<td align="left">${vo.price}</td>
		<td></td>
		<td align="left">${vo.manuDate}</td>
		<td></td>
		<td align="left">
		<c:set var="proTranCode" value="${vo.proTranCode.replaceAll(' ', '')}"/>
		<c:choose>
			<c:when test="${vo.stock > 0 && menu eq 'search'}">
					�Ǹ���
			</c:when>
			<c:when test="${vo.stock == 0 && menu eq 'search'}">
					������
			</c:when>
			<c:when test="${proTranCode eq '2'}">
					���ſϷ�
					<c:if test="${menu eq 'manage'}">
						<a href="/product/updateTranCodeByProd?prodNo=${vo.prodNo}&tranCode=2">����ϱ�</a>
					</c:if>
			</c:when>
			<c:when test="${proTranCode eq '3'}">
					�����
			</c:when>
			<c:when test="${proTranCode eq '4'}">
					��ۿϷ�
			</c:when>
		</c:choose>			
		</td>
		<td></td>	
		<td align="left">${vo.views}</td>
		<td></td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center" id="page">
		
		<%-- ���� �ٲ���� ��ũ �ڿ� order�κ� --%>
		<c:if test="${page.beginUnitPage != 1}">
			<a href="/product/listProduct?currentPage=${page.beginUnitPage-5}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">����</a>
		</c:if>
		
		<c:forEach var="i" begin="${page.beginUnitPage}" end="${page.endUnitPage}">
			<a href="/product/listProduct?currentPage=${i}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">${i}</a>
		</c:forEach>
		
		<c:if test="${page.endUnitPage != page.maxPage}">
			<a href="/product/listProduct?currentPage=${page.beginUnitPage+5}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">����</a>
		</c:if>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>
