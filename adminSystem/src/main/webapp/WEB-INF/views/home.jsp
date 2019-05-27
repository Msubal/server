<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

  <!--======= HOME MAIN SLIDER =========-->
  <section class="home-slider">
    <div class="tp-banner-container">
      <div class="tp-banner" >
        <ul>
          
           <c:if test="${pageContext.request.userPrincipal.name != null}">
				<c:if test="${pageContext.request.userPrincipal.name == 'admin'}">
          <!-- Slider 1 -->
          <li data-transition="fade" data-slotamount="7"> <img src="<c:url value="/resources/images/slides/slide-1.png"/>" data-bgposition="center center" alt="" />
					<!-- Layer -->
            <div class="tp-caption sft font-montserrat tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="-100" 
                  data-speed="700" 
                  data-start="1000" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on"
                  style="color: #fff; font-size: 60px; text-transform: uppercase; font-weight:bolder; letter-spacing:3px;"> WellCome!! </div>
            
            <!-- Layer -->
            <div class="tp-caption sfb  font-montserrat text-center tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="-20" 
                  data-speed="700" 
                  data-start="1500" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on" 
                  style="color: #fff; font-size: 18px; font-weight:100; text-transform: uppercase;"> Hello Admin <br>
            	 관리자!! </div>
            
            <!-- Layer -->
            <div class="tp-caption sfb tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="80" 
                  data-speed="700" 
                  data-start="2000" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on"> &nbsp; &nbsp;
                  <a href="javascript:document.getElementById('logout').submit()" class="btn" >LogOut</a> &nbsp; &nbsp;
					<form id="logout" action="<c:url value="/logout"/>" method = "post">
						<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
					</form>
				</div>
			</li>
				
				<!-- Slider 2 -->
	          <li data-transition="fade" data-slotamount="7"> <img src="<c:url value="/resources/images/slides/slide-2.png"/>" data-bgposition="center center" alt="" /> 
	            
	            <!-- Layer -->
	            <div class="tp-caption sft font-montserrat tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="-100" 
	                  data-speed="700" 
	                  data-start="1000" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on"
	                  style="color: #fff; font-size: 60px; text-transform: uppercase; font-weight:bolder; letter-spacing:3px;"> Build_Schedule </div>
	            
	            <!-- Layer -->
	            <div class="tp-caption sfb  font-montserrat text-center tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="-20" 
	                  data-speed="700" 
	                  data-start="1500" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on" 
	                  style="color: #fff; font-size: 18px; font-weight:100; text-transform: uppercase;"> You can change Build_Name <br>
	              강의실 이름을 바꾸보세요! </div>
	            
	            <!-- Layer -->
	            <div class="tp-caption sfb tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="80" 
	                  data-speed="700" 
	                  data-start="2000" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on"> &nbsp; &nbsp; <a href="<c:url value="/admin/buildings"/>" class="btn">확인 및 변경</a> &nbsp; &nbsp; </div>
	          </li>
	          
	          <!-- Slider 3 -->
	          <li data-transition="fade" data-slotamount="7"> <img src="<c:url value="/resources/images/slides/slide-3.png"/>" data-bgposition="center center" alt="" /> 
	            
	            <!-- Layer -->
	            <div class="tp-caption sft font-montserrat tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="-100" 
	                  data-speed="700" 
	                  data-start="1000" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on"
	                  style="color: #fff; font-size: 60px; text-transform: uppercase; font-weight:bolder; letter-spacing:3px;"> Student </div>
	            
	            <!-- Layer -->
	            <div class="tp-caption sfb  font-montserrat text-center tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="-20" 
	                  data-speed="700" 
	                  data-start="1500" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on" 
	                  style="color: #fff; font-size: 18px; font-weight:100; text-transform: uppercase;"> You can change student <br>
	              학생명단을 추가, 삭제, 수정 할수 있습니다! </div>
	            
	            <!-- Layer -->
	            <div class="tp-caption sfb tp-resizeme" 
	                  data-x="center" data-hoffset="0"
	                  data-y="center" data-voffset="80" 
	                  data-speed="700" 
	                  data-start="2000" 
	                  data-easing="easeOutBack"
	                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
	                  data-splitin="none" 
	                  data-splitout="none" 
	                  data-elementdelay="0.1" 
	                  data-endelementdelay="0.1" 
	                  data-endspeed="300" 
	                  data-captionhidden="on"> &nbsp; &nbsp; <a href="<c:url value="/admin/students"/>" class="btn">학생관리자</a> &nbsp; &nbsp; </div>
	          </li>
	          
				</c:if>
			</c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
            
            <li data-transition="fade" data-slotamount="7"> <img src="<c:url value="/resources/images/slides/slide-1.png"/>" data-bgposition="center center" alt="" />
            
            <form action="<c:url value="/login"/>" method="post">
            <!-- Layer -->
            <div class="tp-caption sft font-montserrat tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="-150" 
                  data-speed="700" 
                  data-start="1000" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on"
                  style="color: #fff; font-size: 60px; text-transform: uppercase; font-weight:bolder; letter-spacing:3px;"> Admin Login Form
            </div>
            
            <!-- Layer -->
            <div class="tp-caption sfb  font-montserrat text-center tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="-20" 
                  data-speed="700" 
                  data-start="1500" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on" 
                  style="color: #fff; font-size: 18px; font-weight:100; text-transform: uppercase;">
                  <!-- Content Start -->
					<div class="login-page text-center">
						
						<c:if test="${not empty errorMsg}">
							<div style = "color:#ff0000;"> <h3> ${errorMsg} </h3> </div>
						</c:if>
						<c:if test="${not empty logoutMsg}">
							<div style = "color:#0000ff;"> <h3> ${logoutMsg} </h3> </div>
						</c:if>
						<ul>
							<li class="col">
								<label for="username"> Username </label>
							</li>
							<li class="col">
								<input type="text" class="form-control" id="username" style="width:100%;" placeholder="username" name="username" >
							</li>
							<li class="col">
								<label for="pwd"> Password </label>
							</li>
							<li class="col">
								<input type="password" class="form-control" id="password" style="width:100%;" placeholder="password" name="password" >
							</li>
						</ul>
					</div>
             	</div>
             	
             	<!-- Layer -->
     			<div class="tp-caption sfb tp-resizeme" 
                  data-x="center" data-hoffset="0"
                  data-y="center" data-voffset="120" 
                  data-speed="700" 
                  data-start="2000" 
                  data-easing="easeOutBack"
                  data-customin="x:0;y:0;z:0;rotationX:0;rotationY:0;rotationZ:0;scaleX:0;scaleY:1;skewX:0;skewY:0;opacity:0;transformPerspective:600;transformOrigin:50% 50%;" 
                  data-splitin="none" 
                  data-splitout="none" 
                  data-elementdelay="0.1" 
                  data-endelementdelay="0.1" 
                  data-endspeed="300" 
                  data-captionhidden="on">
		                  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button type="submit" class="btn btn-primary">Login</button>
				</div>
              </form>
          	</c:if>
        </ul>
      </div>
    </div>
  </section>