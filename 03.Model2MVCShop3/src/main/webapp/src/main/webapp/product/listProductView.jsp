<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>

<%
int viewPage = 5;
	
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	Search searchVO=(Search)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<Product> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<Product>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
	totalPage += 1;
	}
	User userVO = (User)session.getAttribute("userVO");
	String menu = request.getParameter("menu");
%>









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

<form name="detailForm" action="/listProduct.do?menu=<%=menu%>" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<%
						if(menu.equals("manage")){
						%>
							상품 관리
						<%
						}else{
						%>
							상품 목록 조회
						<%
						}
						%>
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
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
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
		<td colspan="11" >전체  <%=total%> 건수, 현재 <%=currentPage%> 페이지</td>
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
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%
	int no=list.size();
			for(int i=0; i<list.size(); i++) {
		Product vo = (Product)list.get(i);
		String proTranCode = vo.getProTranCode();
		proTranCode = proTranCode.trim();
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=no-- %></td>
		<td></td>
		<td align="left">
				<%if(proTranCode.equals("1")){%>
					<a href="/getProduct.do?prodNo=<%=vo.getProdNo() %>&menu=<%=menu %>"><%=vo.getProdName() %></a>
				<% }else{%>
				<%=vo.getProdName() %>
				<%} %>
		</td>
		<td></td>
		<td align="left"><%=vo.getPrice() %></td>
		<td></td>
		<td align="left"><%=vo.getManuDate() %></td>
		<td></td>
		<td align="left">
			<% if(proTranCode.equals("1")){ %>
					판매중
			<%}else if(userVO == null || userVO.getRole().equals("user")){ %>
					재고없음
			<%}else if(proTranCode.equals("2")){%>
					구매완료
					<%if(menu.equals("manage")){ %>
						<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo() %>&tranCode=2">배송하기</a>
					<%} %>
			<%}else if(proTranCode.equals("3")){%>
					배송중
			<%}else if(proTranCode.equals("4")){%>
					배송완료
			<%} %>
			<%-- 		<%if((vo.getProTranCode()).equals("구매완료")){ %>
						<a href="/updateTranCodeByProd.do?prodNo=10000&tranCode=2">배송하기</a>
					<%} %>    		나중에 확인 --%>
			
					
					
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<% } %>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%int startPage = currentPage - (currentPage%5) + 1; %>
		<%int endPage = startPage + viewPage - 1; %>
		<%if(endPage > totalPage){ %>
			<%endPage = totalPage; %>
		<%} %>
		
		
		<%if(startPage != 1){%>
			<a href="/listProduct.do?page=<%=startPage-5%>&menu=<%=menu%>&searchCondition=<%=searchVO.getSearchCondition()%>&searchKeyword=<%=searchVO.getSearchKeyword()%>">이전</a>
		<%} %>
		
		<%for(int i = startPage; i <= endPage; i++){%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>&searchCondition=<%=searchVO.getSearchCondition()%>&searchKeyword=<%=searchVO.getSearchKeyword()%>"><%= i%></a>
		<%}%>
		
		<%if(endPage != totalPage){%>
			<a href="/listProduct.do?page=<%=startPage+5%>&menu=<%=menu%>&searchCondition=<%=searchVO.getSearchCondition()%>&searchKeyword=<%=searchVO.getSearchKeyword()%>">다음</a>
		<%} %>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
