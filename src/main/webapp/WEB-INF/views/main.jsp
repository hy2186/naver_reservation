<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/reservation/resources/img/favicon.ico" rel="shortcut icon">
    <link href="/reservation/resources/css/style.css" rel="stylesheet">
    <script src="/reservation/resources/js/mainpage.js" type="module"></script>
    <script src="/reservation/resources/js/lib/jquery-3.6.0.js"></script>
    <script src="/reservation/resources/js/util/common.js" type="text/javascript"></script>
    <script src="/reservation/resources/js/lib/handlebars-v4.7.7.js"></script>
</head>

<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./myreservation.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a></a>
                <c:choose>
                    <c:when test="${sessionScope.reservationEmail != null}">
                        <a href="/reservation/myreservation" class="btn_my"> 
                            <span class="viewReservation" title="내예약">${sessionScope.reservationEmail}</span> 
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="/reservation/login" class="btn_my"> 
                            <span class="viewReservation" title="예약확인">예약확인</span>
                        </a>
                    </c:otherwise>
                </c:choose>
                
            </header>
        </div>
        <hr>
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="prev_e" style="display:none;">
                            <div class="prev_inn">
                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e" style="display:none;">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual">
                                <ul id="main_slider" class="visual_img">
                                </ul>
                            </div>
                            <span class="nxt_fix" style="display:none;"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul id="category_tab" class="event_tab_lst tab_lst_min">
                    <li class="item" data-category="0">
                        <a class="anchor active"> <span>전체리스트</span> </a>
                    </li>
                </ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span id="category_item_count" class="pink">10</span>개 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <ul id="left_product_box" class="lst_event_box">
                    </ul>
                    <ul id="right_product_box" class="lst_event_box">
                    </ul>
                    <!-- 더보기 -->
                    <div class="more">
                        <button id="more_button" class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="gototop">
            <a href="javascript:window.scrollTo(0,0)" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>


    <script type="dt-template" id="promotion_item_template">
        {{#each items}}
        <li class="item" style="background-image: url(/reservation/download/image/{{fileId}});">
            <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                <div class="event_txt">
                    <h4 class="event_txt_tit">{{productDescription}}</h4>
                    <p class="event_txt_adr">{{placeName}}</p>
                    <p class="event_txt_dsc"></p>
                </div>
            </a>
        </li>
        {{/each}}
    </script>

    <script type="dt-template" id="product_item_template">
        <li class="item">
            <a href="detail?displayInfoId={{displayInfoId}}" class="item_book">
                <div class="item_preview">
                    <img alt="{{productDescription}}" class="img_thumb" src="/reservation/download/image/{{imageFileId}}">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>{{productDescription}}</span> <small class="sm">{{placeName}}</small> </h4>
                    <p class="event_txt_dsc">{{productContent}}</p>
                </div>
            </a>
        </li>
    </script>
    
    <script type="dt-template" id="category_item_template">
        {{#each items}}
        <li class="item" data-category="{{id}}" data-count="{{count}}">
			<a class="anchor"> <span>{{name}}</span> </a>
		</li>
        {{/each}}
    </script>
</body>

</html>
