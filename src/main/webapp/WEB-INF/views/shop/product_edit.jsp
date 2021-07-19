<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" 
rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="${path}/summernote/summernote.min.css" rel="stylesheet">
<script src="${path}/summernote/summernote.min.js"></script>
<script>
$(function(){
	//id가 description인 태그에 summernote 적용
	$("#description").summernote({
		height: 300, width: 800
	});
});
var _0x4962=['138kawtea','218980PrPwLb','1tdDuHk','form1','1299bxveUF','75839XaqQDJ','8KNlFWF','131869BwclYV','29886jeDDTO','13427wIcUSg','action','submit','108296WfWsBt','삭제하시겠습니까?'];function _0x4b20(_0xb7a4ba,_0x41d6ef){return _0x4b20=function(_0x49623d,_0x4b2037){_0x49623d=_0x49623d-0x6e;var _0x4d85f0=_0x4962[_0x49623d];return _0x4d85f0;},_0x4b20(_0xb7a4ba,_0x41d6ef);}(function(_0x399997,_0x5ea21d){var _0x5e3a58=_0x4b20;while(!![]){try{var _0x5a64f6=-parseInt(_0x5e3a58(0x76))+-parseInt(_0x5e3a58(0x70))*-parseInt(_0x5e3a58(0x73))+parseInt(_0x5e3a58(0x6f))*-parseInt(_0x5e3a58(0x7a))+parseInt(_0x5e3a58(0x79))+parseInt(_0x5e3a58(0x72))+-parseInt(_0x5e3a58(0x71))+-parseInt(_0x5e3a58(0x78))*-parseInt(_0x5e3a58(0x6e));if(_0x5a64f6===_0x5ea21d)break;else _0x399997['push'](_0x399997['shift']());}catch(_0x4485a2){_0x399997['push'](_0x399997['shift']());}}}(_0x4962,0x35994));function product_delete(){var _0x1af561=_0x4b20;confirm(_0x1af561(0x77))&&(document[_0x1af561(0x7b)][_0x1af561(0x74)]='/spring02/shop/product/delete.do',document['form1'][_0x1af561(0x75)]());}

/* function product_delete(){
	if(confirm("삭제하시겠습니까?")){
		document.form1.action="${path}/shop/product/delete.do";
		document.form1.submit();
	}
} */

function product_update(){
	var product_name=document.form1.product_name.value;
	var price=document.form1.price.value;
	var description=document.form1.description.value;
	if(product_name == ""){
		alert("상품명을 입력하세요.");
		document.form1.product_name.focus();
		return;
	}
	if(price == ""){
		alert("가격을 입력하세요.");
		document.form1.price.focus();
		return;
	}
/* 	if(description == ""){
		alert("상품설명을 입력하세요.");
		document.form1.description.focus();
		return;
	} */	
	document.form1.action="${path}/shop/product/update.do";
	document.form1.submit();
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 정보 수정/삭제</h2>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>상품명</td>
		<td><input name="product_name" value="${dto.product_name}"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price" value="${dto.price}"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="60" name="description" 
		id="description">${dto.description}</textarea>
		</td>
	</tr>	
	<tr>
		<td>상품이미지</td>
		<td>
			<img src="${path}/images/${dto.picture_url}" width="300px" 
			height="300px">
			<br>
			<input type="file" name="file1">
		</td>
	</tr>	
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="product_id" 
			value="${dto.product_id}">
			<input type="button" value="수정" onclick="product_update()">
			<input type="button" value="삭제" onclick="product_delete()">
			<input type="button" value="목록" 
			onclick="location.href='${path}/shop/product/list.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>