<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${menu}" method="post">

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
						
							상품 관리
						</c:if>
						<c:if test ="${menu eq 'search'}">
							상품 목록 조회
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
		<td align="right">
			<ul>
	        <a href="/listProduct.do?currentPage=1&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=priceDesc">가격높은순</a>
	        <a href="/listProduct.do?currentPage=1&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=priceAsce">가격낮은순</a>
	        <a href="/listProduct.do?currentPage=1&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=viewsDesc">가장많이본상품</a>
	        <a href="/listProduct.do?currentPage=1&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=nameAsce">이름순</a>
	    	</ul>
    	</td>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${searchVO.searchCondition eq '0' ? "selected" : '' }>상품번호</option>
				<option value="1" ${searchVO.searchCondition eq '1' ? "selected" : '' }>상품명</option>
				<option value="2" ${searchVO.searchCondition eq '2' ? "selected" : '' }>상품가격</option>
			</select>
			<input type="text" name="searchKeyword" value="${empty searchVO.searchKeyword ? '' : searchVO.searchKeyword}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
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
		<td colspan="11" >전체  ${pageVO.maxPage} 건수, 현재 ${searchVO.page} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">조회수</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="no" value="${searchVO.pageUnit }"/>
	<c:forEach var="vo" items="${map.list}">
	
	<tr class="ct_list_pop">
		<td align="center">${no}</td>
		<c:set var="no" value="${no - 1}"/>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=${vo.prodNo}&menu=${menu}">${vo.prodName}</a>
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
					판매중
			</c:when>
			<c:when test="${vo.stock == 0 && menu eq 'search'}">
					재고없음
			</c:when>
			<c:when test="${proTranCode eq '2'}">
					구매완료
					<c:if test="${menu eq 'manage'}">
						<a href="/updateTranCodeByProd.do?prodNo=${vo.prodNo}&tranCode=2">배송하기</a>
					</c:if>
			</c:when>
			<c:when test="${proTranCode eq '3'}">
					배송중
			</c:when>
			<c:when test="${proTranCode eq '4'}">
					배송완료
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
		<td align="center">
		
		<%-- 여기 바꿔야함 링크 뒤에 order부분 --%>
		<c:if test="${pageVO.beginUnitPage != 1}">
			<a href="/listProduct.do?currentPage=${pageVO.beginUnitPage-5}&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=${searchVO.orderBy}">이전</a>
		</c:if>
		
		<c:forEach var="i" begin="${pageVO.beginUnitPage}" end="${pageVO.endUnitPage}">
			<a href="/listProduct.do?currentPage=${i}&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=${searchVO.orderBy}">${i}</a>
		</c:forEach>
		
		<c:if test="${pageVO.endUnitPage != pageVO.maxPage}">
			<a href="/listProduct.do?currentPage=${pageVO.beginUnitPage+5}&menu=${menu}&searchCondition=${searchVO.searchCondition}&searchKeyword=${searchVO.searchKeyword}&order=${searchVO.orderBy}">다음</a>
		</c:if>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
