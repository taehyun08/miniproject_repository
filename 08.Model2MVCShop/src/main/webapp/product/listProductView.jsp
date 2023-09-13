<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>상품 목록조회</title>
	
	<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
		function fncGetProductList() {
			
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${menu}").submit();
		}
		
		function renderList(vo){
			
		}
		
		
		
		$(function() {
			var page = 1;
			//==> 검색 Event 연결처리부분
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 과 3 방법 조합 : $("tagName.className:filter함수") 사용함. 
			$( "td.ct_btn01:contains('검색')" ).on("click" , function() {
				fncGetProductList();
			});
			
			
			$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			$("h7").css("color" , "red");
			
			
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			
			$("#tags").on("click", function(){
				$.ajax( 
						{
							url : "/product/json/listProductName" ,
							method : "POST" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							success : function(JSONData , status) {
								$( "#tags" ).autocomplete({
								      source: JSONData
								});
							}
					});
				});
	
			
			$(".ct_list_pop").on("scroll", function(){
				
				
				$.ajax( 
						{
							url : "/product/json/listProductName" ,
							method : "POST" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							success : function(JSONData , status) {
								for()
								var displayValue = ""
				                $.each(JSONData.list, function(index, vo){
				                    renderList(vo);
				                })

							}
					});
				});
			
			
			
		});	

	
	
	
	
	
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm">

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
		<td align="right" id="order">
			<ul>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceDesc">가격높은순</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceAsce">가격낮은순</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=viewsDesc">가장많이본상품</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=nameAsce">이름순</a>
	    	</ul>
    	</td>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${search.searchCondition eq '0' ? "selected" : '' }>상품번호</option>
				<option value="1" ${search.searchCondition eq '1' ? "selected" : '' }>상품명</option>
				<option value="2" ${search.searchCondition eq '2' ? "selected" : '' }>상품가격</option>
			</select>
			<input type="text" id="tags" name="searchKeyword" value="${empty search.searchKeyword ? '' : search.searchKeyword}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						검색
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
		<td colspan="11" >전체  ${page.maxPage} 건수, 현재 ${search.currentPage} 페이지</td>
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
					판매중
			</c:when>
			<c:when test="${vo.stock == 0 && menu eq 'search'}">
					재고없음
			</c:when>
			<c:when test="${proTranCode eq '2'}">
					구매완료
					<c:if test="${menu eq 'manage'}">
						<a href="/product/updateTranCodeByProd?prodNo=${vo.prodNo}&tranCode=2">배송하기</a>
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
		<td align="center" id="page">
		
		<%-- 여기 바꿔야함 링크 뒤에 order부분 --%>
		<c:if test="${page.beginUnitPage != 1}">
			<a href="/product/listProduct?currentPage=${page.beginUnitPage-5}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">이전</a>
		</c:if>
		
		<c:forEach var="i" begin="${page.beginUnitPage}" end="${page.endUnitPage}">
			<a href="/product/listProduct?currentPage=${i}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">${i}</a>
		</c:forEach>
		
		<c:if test="${page.endUnitPage != page.maxPage}">
			<a href="/product/listProduct?currentPage=${page.beginUnitPage+5}&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=${search.orderBy}">다음</a>
		</c:if>
    	</td>
	</tr>
</table>

</form>

</div>
</body>
</html>

-->


<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head>
  
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.115.4">
    <title>Model2 MVC Shop</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/album/">

    
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    
   <script type="text/javascript">
   		var page = 1;
   		var itemId = 1;
   		var isLoading = false;
   		$(function(){
   			
   			
   			
   			
   			$('#tags').keypress(function(event) {
   			    // 눌린 키 코드 가져오기
   			    var keycode = (event.keyCode ? event.keyCode : event.which);
				console.log("키눌림");
   			    // 엔터 키 (키 코드 13) 눌렀을 때만 처리
   			    if (keycode == '13') {
   			      console.log("엔터키눌림");
   			      // 입력된 텍스트 가져오기
   			      var location = "/product/listProduct?menu=${menu}&searchCondition=1";
   			   	  $("form").attr("method" , "POST").attr("action" , location).submit();
   			    }
   			  });
   			
   			$(window).scroll(function() {
   			    // 스크롤 위치와 문서 높이 계산
   			    var scrollHeight = $(document).height();
   			    var scrollPosition = $(window).height() + $(window).scrollTop();
				//console.log((scrollPosition / scrollHeight) > 0.8);
				
				console.log(!isLoading);
   			    // 스크롤 위치가 문서 높이의 80%에 도달하면 추가 콘텐츠 로드
   			    if ((scrollPosition / scrollHeight) > 0.8 && !isLoading) {
   			      console.log("if문 들어옴");
   			      isLoading = true; // 데이터 로딩 중으로 플래그 설정
   			      loadMoreItems();
   			    }
   			});
   		    // 초기 아이템 로딩
   		    loadMoreItems();
   		    
   		    function loadMoreItems() {
	   			console.log("loadMoreItems 실행");
	   			var keyword = $('#tags').val();
			    var requestData = {
						currentPage : page,
						searchKeyword : keyword,
						orderBy : "${search.orderBy}"
				};
				$.ajax( 
						{
							url : "/product/json/listProduct?menu=${menu}" ,
							method : "POST" ,
							dataType : "json" ,
							data : JSON.stringify(requestData),
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							success : function(JSONData , status) {
								for (var i = 0; i < JSONData.list.length; i++) {
									var getProductButtonId = "getProductButton" + itemId;
									console.log(getProductButtonId);
									var fileName = ((JSONData.list[i].fileName).split(','))[0];
									console.log(fileName);
									var newItem = '<div class="col">' +
						               '<div class="card shadow-sm">' +
						               '<svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><image xlink:href="/images/uploadFiles/'+fileName+'" width="100%" height="100%" /></svg>' +
						               '<div class="card-body">' +
						               '<p class="card-text">' + JSONData.list[i].prodName + '</p>' +
						               '<div class="d-flex justify-content-between align-items-center">' +
						               '<div class="btn-group">' +
						               '<input type="hidden" name="hiddenValue" value=' + JSONData.list[i].prodNo + '>' +
						               '<button type="button" class="btn btn-sm btn-outline-secondary">View</button>' +
						               '<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>' +
						               '</div>' +
						               '<small class="text-body-secondary">' + JSONData.list[i].price + '원</small>' +
						               '</div>' +
						               '</div>' +
						               '</div>' +
						               '</div>';
									$('#itemList').append(newItem);
									itemId++;;
									if(${user.role != 'admin'}){
										$('button[type="button"]:contains("Edit")').remove();
									}
									$('button[type="button"]:contains("View")').on("click", function(){
										console.log("버튼 클릭시");
										var prodNo = $(this).prev('input[type="hidden"]').val();
										console.log(prodNo);
						   				window.location = "/product/getProduct?prodNo=" + prodNo +"&menu=" + JSONData.menu;
						   			});
									$('button[type="button"]:contains("Edit")').on("click", function(){
										console.log("버튼 클릭시");
										var parentDiv = $(this).closest('.btn-group');
										var prodNo = parentDiv.find('input[name="hiddenValue"]').val();
										console.log(prodNo);
						   				window.location = "/product/updateProduct?prodNo=" + prodNo +"&menu=manage";
						   			});
				   		        }
								page = page + 1;
								isLoading = false;
							}
				});
		        
		        
		    }
   			
   		});
   		
   		
   
	   
    
    </script>


    <!-- Favicons -->
<meta name="theme-color" content="#712cf9">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }
      .bd-mode-toggle {
        z-index: 1500;
      }
    </style>

    
  </head>
  <body>
  	<form>
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
      <symbol id="check2" viewBox="0 0 16 16">
        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
      </symbol>
      <symbol id="circle-half" viewBox="0 0 16 16">
        <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z"/>
      </symbol>
      <symbol id="moon-stars-fill" viewBox="0 0 16 16">
        <path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>
        <path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"/>
      </symbol>
      <symbol id="sun-fill" viewBox="0 0 16 16">
        <path d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"/>
      </symbol>
    </svg>

    <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
      <button class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
              id="bd-theme"
              type="button"
              aria-expanded="false"
              data-bs-toggle="dropdown"
              aria-label="Toggle theme (auto)">
        <svg class="bi my-1 theme-icon-active" width="1em" height="1em"><use href="#circle-half"></use></svg>
        <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
      </button>
      <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#sun-fill"></use></svg>
            Light
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#moon-stars-fill"></use></svg>
            Dark
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto" aria-pressed="true">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#circle-half"></use></svg>
            Auto
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
      </ul>
    </div>

    
<header data-bs-theme="dark">
  <div class="collapse text-bg-dark" id="navbarHeader">
    <div class="container">
      <div class="row">
        <div class="col-sm-8 col-md-7 py-4">
          <h4>About</h4>
          <p class="text-body-secondary">Add some information about the album below, the author, or any other background context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off to some social networking sites or contact information.</p>
        </div>
        <div class="col-sm-4 offset-md-1 py-4">
          <h4>Contact</h4>
          <ul class="list-unstyled">
            <li><a href="#" class="text-white">Follow on Twitter</a></li>
            <li><a href="#" class="text-white">Like on Facebook</a></li>
            <li><a href="#" class="text-white">Email me</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container">
      <a href="/" class="navbar-brand d-flex align-items-center">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2" viewBox="0 0 24 24"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
        <strong>MVC SHOP</strong>
      </a>
      <div class="collapse" id="navbarToggleExternalContent" data-bs-theme="dark">
	  	  <div class="bg-dark p-4">
	    	<h5 class="text-body-emphasis h4">Coallapsed content</h5>
	    	<span class="text-body-secondary">Toggleable via the navbar brand.</span>
	 	  </div>
	  </div>
	  <nav class="navbar navbar-dark bg-dark">
	    <div class="container-fluid">
	      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div align="right">
			<input type="text" id="tags" name="searchKeyword" value="${empty search.searchKeyword ? '' : search.searchKeyword}" class="ct_input_g" style="width:200px; height:19px" />
		</div>
	    </div>
   	  </nav>
    </div>
    
  </div>
</header>

<main>

  <section class="py-5 text-center container">
  <ul>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceDesc">가격높은순</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=priceAsce">가격낮은순</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=viewsDesc">가장많이본상품</a>
		        <a href="/product/listProduct?currentPage=1&menu=${menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}&orderBy=nameAsce">이름순</a>
	    	</ul>
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Product List</h1>
        <p class="lead text-body-secondary">Something short and leading about the collection below?its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>
        <p>
          <a href="#" class="btn btn-primary my-2">Main call to action</a>
          <a href="#" class="btn btn-secondary my-2">Secondary action</a>
        </p>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-body-tertiary">
    <div class="container">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" id="itemList">
      
      

        
        
      </div>
    </div>
  </div>

</main>
</form>
<footer class="text-body-secondary py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#">Back to top</a>
    </p>
    <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
    <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a href="/docs/5.3/getting-started/introduction/">getting started guide</a>.</p>
  </div>
</footer>

    </body>
</html>

