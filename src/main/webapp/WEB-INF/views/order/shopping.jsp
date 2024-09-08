<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장바구니</title>
<link href="${pageContext.request.contextPath}/assets/css/reset.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/imshopping.css" rel="stylesheet" type="text/css">
</head>
<body>
 <div id="wrap">
        <header id="header">
            <div class="header-container">
                <div class="logo">
                    <a href=""><img src="${pageContext.request.contextPath}/assets/images/Layer 1.png" alt="고양이 로고"></a>
                    <h1>야옹이 멍멍해봐</h1>
                </div>
            </div>
        </header>

        <h1>
            <a href=""> ◀ 장바구니 </a>
        </h1>

        <!-- 장바구니 내역-->
 <form action="${pageContext.request.contextPath}/order/payform" method="get">
    <table>
        <thead>
            <tr>
                <th><input type="checkbox" id="select-all-checkbox" onclick="toggleSelectAll(this)"> <!-- 모두 선택/해제 체크박스 --></th>
                <th colspan="2">상품명</th>
                <th>가격</th>
                <th>수량</th>
                <th>총액</th>
                <th>삭제</th>
            </tr>
        </thead>
        <c:forEach items="${shoppingList}" var="shoppingVo">
        <tbody>
            <tr>
                <td>
                        <input type="checkbox" name="selectedItems" value="${shoppingVo.goodsNo}">
                        <input type="hidden" name="shoppingNo" value="${shoppingVo.shoppingNo}">
                        <input type="hidden" name="optionsNo" value="${shoppingVo.optionsNo}">
                        <input type="hidden" name="userNo" value="${shoppingVo.userNo}">
                </td> <!-- 상품 번호를 전송 -->
                <td><img src="${pageContext.request.contextPath}/assets/images/download.jfif" alt="상품 A 이미지"></td>
                <td>${shoppingVo.goodsName} <br> 옵션 :  ${shoppingVo.taste} <br><br>장바구니 담은날 : ${shoppingVo.formattedPickDate} <br> ${shoppingVo.formattedEstimatedDeliveryDate} 배송 예정</td>
                <td class="item-price">${shoppingVo.price}원</td> <!-- 개별 상품 가격 -->
                <td><input type="number" value="${shoppingVo.count}" min="1" style="width: 50px;" onchange="updateItemTotal(this)"></td> <!-- 수량 변경 시 총액 업데이트 -->
                <td><span class="item-total">${shoppingVo.price * shoppingVo.count}원</span></td> <!-- 상품별 총액 표시 -->
                <td>          
                    <form action="${pageContext.request.contextPath}/shopping/delete" method="post">
                        <input type="hidden" name="shoppingNo" value="${shoppingVo.shoppingNo}">
                        <button type="submit" class="btn btn-remove">삭제</button>
                    </form>
                </td>
            </tr>
        </tbody>
        </c:forEach>
    </table>
    <br>

    <!-- 예상결제 금액-->
    <table>
        <thead>
            <tr>
                <th>주문금액</th>
                <th>할인</th>
                <th>배송비</th>
                <th>결제예정금액</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td id="orderAmount">0원</td> <!-- 주문 금액 -->
                <td>0원</td> <!-- 현재 할인을 0원으로 고정 -->
                <td>3000원</td> <!-- 고정된 배송비 -->
                <td id="finalAmount">3000원</td> <!-- 결제 예상 금액 -->
            </tr>
        </tbody>
    </table>
    <br>
    <div class="parent-container">
        <button class="btn" onclick="history.back()">뒤로가기</button>
        <button type="submit">결제하기</button>
    </div>
</form>
<script>
    // 전체 선택/해제 기능
    function toggleSelectAll(checkbox) {
        const checkboxes = document.querySelectorAll('input[type="checkbox"].item-checkbox');
        checkboxes.forEach(function(itemCheckbox) {
            itemCheckbox.checked = checkbox.checked;
        });
        updateTotal(); // 선택된 항목들의 합계를 업데이트
    }

    // 체크박스 선택 및 수량 변경에 따라 주문 금액을 동적으로 업데이트하는 함수
    function updateTotal() {
        let totalPrice = 0;
        let shippingCost = 3000;
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked.item-checkbox');
        
        checkboxes.forEach(function(checkbox) {
            const row = checkbox.closest('tr');
            const itemTotal = parseInt(row.querySelector('.item-total').innerText.replace(/[^0-9]/g, ''));
            totalPrice += itemTotal;
        });

        // 주문 금액과 결제 예정 금액 업데이트
        document.getElementById('orderAmount').innerText = totalPrice + "원";
        document.getElementById('finalAmount').innerText = (totalPrice + shippingCost) + "원";
    }

    // 수량 변경 시 해당 상품의 총액을 업데이트하고 전체 금액을 다시 계산하는 함수
    function updateItemTotal(inputElement) {
        const row = inputElement.closest('tr');
        const price = parseInt(row.querySelector('.item-price').innerText.replace(/[^0-9]/g, '')); // 개별 상품 가격
        const count = parseInt(inputElement.value);  // 수량
        const itemTotal = price * count;  // 총액 계산

        // 해당 항목의 총액을 업데이트
        row.querySelector('.item-total').innerText = itemTotal + "원";

        // 전체 주문 금액 업데이트
        updateTotal();
    }
</script>

</body>
</html>
