<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- bootstrap -->
		<link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap.min.css">
		<!-- w3 -->
		<link rel="stylesheet" type="text/css" href="/css/w3/w3.css">
		<!-- font -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
		<!-- font-awesome -->
		<link rel="stylesheet" type="text/css" href="/css/font-awesome/css/font-awesome.min.css">
		<!-- main stylesheet -->		
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<!-- favicon -->
		<link rel="icon" type="image/png" href="img/dcHome_favico.png"/>
		<title>Home</title>
	</head>
	
	<body>
	 	<!-- Navbar (sit on top) -->
	 	<div class="w3-top">
            	<div class="w3-bar w3-white w3-card" id="myNavbar">
                	<a href="/" class="w3-bar-item w3-button w3-wide"><img alt="" src="/img/homeLogo.png"></a>
                	<!-- Right-sided navbar links -->
                	<div class="w3-right w3-hide-small">
                    	<a href="/listings" onclick="w3_close()" class="w3-bar-item w3-button" > LISTINGS</a>
                    	<!-- Modal link for Log In-Reg -->
                    	<a href="users/log" class="w3-bar-item w3-button"> LOG IN</a>
                	</div>
                
                	<!-- Hide right-floated links on small screens and replace them with a menu icon -->
                	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
                    	<i class="fa fa-bars"></i>
                	</a>
            	</div>
        	</div>
        
        	<!-- Mobile  -->
        	<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
            	<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
            	<a href="/listings" onclick="w3_close()" class="w3-bar-item w3-button">LISTINGS</a>
            	<a href="/users/log" onclick="w3_close()" class="w3-bar-item w3-button">LOG IN</a>
            	<a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
        	</nav>

			<!-- Header with full-page image -->
        	<header class="bgimg-1 w3-display-container" id="home">
            <div class="w3-display-left w3-text-white" style="padding:48px">
                	<span class="w3-jumbo w3-hide-small">Your home a click away</span>
                	<br>
                	<span class="w3-xxlarge w3-hide-large w3-hide-medium">Your next home is a click away</span>
                	<br>
                	<p>
                    	<a href="#listings" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">View Listings</a>
                	</p>
            	</div>
				<!-- Social -->
            	<div class="w3-display-bottomleft w3-text-grey w3-large" style="padding:24px 48px">
                	<a href="https://www.facebook.com/Home-DC-689720904693989"><i class="fa fa-facebook-official w3-hover-opacity"></i></a>
                	<a href="https://www.instagram.com/DCHomeRealEstate"><i class="fa fa-instagram w3-hover-opacity"></i></a>
            	</div>
        	</header>

			<!-- Statistics -->
        	<div class="w3-container w3-row w3-center w3-dark-grey w3-padding-64">
            	<div class="w3-quarter">
                	<span class="w3-xxlarge">1300+</span>
                	<br>New Home Owners
            	</div>
            	<div class="w3-quarter">
                	<span class="w3-xxlarge">800+</span>
                	<br>Rental Properties
            	</div>
            	<div class="w3-quarter">
                	<span class="w3-xxlarge">100+</span>
                	<br>Listings
            	</div>
            	<div class="w3-quarter">
                	<span class="w3-xxlarge">50+</span>
                	<br>Realtors Available
            	</div>
        	</div>

			<!-- Listing Section -->
        	<div class="w3-container" style="padding:128px 16px" id="listings">
            	<h3 class="w3-center">LISTINGS</h3>
            	<p class="w3-center w3-large">Our most recent listings</p>

            	<div class="w3-row-padding" style="margin-top:64px">
            		<c:forEach items="${allListings }" var="x">
	                	<div class="w3-col l3 m6 w3-margin-bottom">
	                    	<div class="w3-card">
		                    	<c:if test="${x.getPictures().size() > 0 }">
				 				<img src="/images/${x.getPictures().get(0).image}" alt="${x.getPictures().get(0)}" style="width:100%">
			 				</c:if>
	                        	<div class="w3-container">
	                            	<h3>${x.address}</h3>
	                            	<p class="w3-opacity">${x.type}</p>
	                            	<p>${x.description }</p>
	                            	<p>
	                                	<a href="/listings/${x.id}"><button class="w3-button w3-light-grey w3-block">
	                                    	<i class="fa fa-search"></i>View Listing</button></a>
	                            	</p>
	                        	</div>
	                    	</div>
	                	</div>
                	</c:forEach>
			</div>
		</div>

			<!-- Contact Section -->
        	<div class="w3-container w3-light-grey" style="padding:128px 16px" id="contact">
            	<h3 class="w3-center">CONTACT</h3>
            	<p class="w3-center w3-large">Lets get in touch. Send us a message:</p>
            	<div class="w3-row-padding" style="margin-top:64px">
                	<div class="w3-half">
                    	<p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i> Washington DC, US</p>
                    	<p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i> Phone: 1-800-1HOMEDC</p>
                    	<p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> Email: info@dchome.com</p>
                    	<br>
                    	<form action="/message/new" method="POST">
                        	<p>
                            	<input class="w3-input w3-border" type="text" placeholder="Name" required name="name">
                        	</p>
                        	<p>
                            	<input class="w3-input w3-border" type="text" placeholder="Email" required name="email">
                        	</p>
                        	<p>
                            	<input class="w3-input w3-border" type="text" placeholder="Subject" required name="subject">
                        	</p>
                        	<p>
                            	<input class="w3-input w3-border" type="text" placeholder="Message" required name="content">
                        	</p>
                        	<p>
                            	<button class="w3-button w3-black" type="submit">
                                	<i class="fa fa-paper-plane"></i> SEND MESSAGE
                            	</button>
                        	</p>
                    	</form>
                	</div>
                	
                	<div class="w3-half">
                    	<!-- Add Google Maps -->
                    	<div id="googleMap" class="w3-greyscale-max" style="width:100%;height:510px;"></div>
                	</div>
            	</div>
        	</div>

        	<!-- Footer -->
        	<footer class="w3-center w3-black w3-padding-64">
            	<a href="#home" class="w3-button w3-light-grey">
                	<i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
            	<div class="w3-xlarge w3-section">
                	<a href="https://www.facebook.com/Home-DC-689720904693989"><i class="fa fa-facebook-official w3-hover-opacity"></i></a>
                	<a href="https://www.instagram.com/DCHomeRealEstate"><i class="fa fa-instagram w3-hover-opacity"></i></a>
            	</div>
            	<p>Powered by
                	<a href="https://ertorrez.com" title="W3.CSS" target="_blank" class="w3-hover-text-green">E. Ricardo Torrez</a>
            	</p>
        	</footer>
        	
		<!-- jquery 3.3.1-->
	    <script type="text/javascript" src="/js/jquery.min.js"></script>
	    <!-- bootstrap-->
    	<script type="text/javascript" src="/js/bootstrap/bootstrap.min.js"></script>
 		<!-- script js -->
 		<script type="text/javascript" src="/js/script.js"></script>
        <!-- google API -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBJ-UwG2QhQze8fgx2B4KPAplCRT670Leo&callback=myMap"></script>
	</body>
</html>