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
		<link rel="icon" type="image/x-icon" href="img/dcHome_favico.ico">
		
		<title>Listing for ${listing.address}</title>
	</head>
	
	<body>
	 	<!-- Navbar (sit on top) -->
	 	<div class="w3-top">
            	<div class="w3-bar w3-white w3-card" id="myNavbar">
                	<a href="/" class="w3-bar-item w3-button w3-wide"><img alt="" src="/img/homeLogo.png"></a>
                	<!-- Right-sided navbar links -->
                	<div class="w3-right w3-hide-small">
                		<!-- if usre is admin -->
                		<c:if test="${user.isAdmin() == true}">
                    		<a href="/users/dashboard" class="w3-bar-item w3-button"> ADMIN</a>
                    	</c:if>
                    	<!-- if user is a buyer show favorites tab -->
                    	<c:if test="${user.isRealtor() == false}">
                    		<a href="/listings/favorites" class="w3-bar-item w3-button"> FAVORITES</a>
                    	</c:if>
                    	<!-- listings route -->
                    	<a href="/listings" onclick="w3_close()" class="w3-bar-item w3-button" > LISTINGS</a>
                    	<!-- if user is realtor show realtor tab -->
                    	<c:if test="${user.isRealtor() == true}">
                    		<a href="/listings/realtor" class="w3-bar-item w3-button"> REALTOR</a>
                    	</c:if>
                    	<!-- if there's no user in session, show login -->
                    	<c:if test="${user == null}">
	                    	<!-- Modal link for Log In-Reg -->
	                    	<a href="/users/log" class="w3-bar-item w3-button"> LOG IN</a>
	              	</c:if>
	              	<!-- if there's a user in session, show logout -->
	              	<c:if test="${user != null}">
	              		<a href="/users/logout" class="w3-bar-item w3-button"></i> LOG OUT</a>
	              	</c:if>
                	</div>
                
                	<!-- Hide right-floated links on small screens and replace them with a menu icon -->
                	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
                    	<i class="fa fa-bars"></i>
                	</a>
            	</div>
        	</div>
        
			<!-- Mobile Menu -->
        	<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
            	<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
            	<!-- if user is admin -->
           	<c:if test="${user.isAdmin() == true}">
            		<a href="/users/dashboard" class="w3-bar-item w3-button"></i> ADMIN</a>
          	</c:if>
            	<!-- if user is realtor show realtor tab -->
			<c:if test="${user.isRealtor() == true}">
         		<a href="/listings/realtor" class="w3-bar-item w3-button"></i> REALTOR</a>
            	</c:if>
            	<!-- if user is a buyer show favorites tab -->
            	<!-- listings -->
           	<a href="/listings" onclick="w3_close()" class="w3-bar-item w3-button">LISTINGS</a>
           	<c:if test="${user.isRealtor() == false}">
            		<a href="/listings/favorites" class="w3-bar-item w3-button"></i> FAVORITES</a>
            	</c:if>
            	<a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
       		<!-- if there's no user in session, show login -->
           	<c:if test="${user == null}">
	       		<a href="#" class="w3-bar-item w3-button" data-toggle="modal" data-target="#logInUser" ></i> LOG IN</a>
	        	</c:if>
	        	<!-- if there's a user in session, show logout -->
	       	<c:if test="${user != null}">
	        		<a href="/users/logout" class="w3-bar-item w3-button"></i> LOG OUT</a>
	      	</c:if>
        	</nav>

			<!-- Listing Section -->
        	<div class="w3-container" style="padding:100px 16px" id="listings">
            	<h3 class="w3-center">Listing at ${listing.address}</h3>

            	<div class="w3-row-padding " style="margin-top:64px">
            	
            		<!-- Listing Picture Section (Left) -->
                	<div class="w3-col l6 m6 w3-margin-bottom">
                    	<div class="w3-card">
                    	<c:if test="${listing.getPictures().size() > 0 }">
				 			<img class="img-gallery" src="/images/${listing.getPictures().get(0).image}" alt="${listing.getPictures().get(0)}" style="width:100%">
			 			</c:if>
						<c:if test="${listing.getPictures().size() > 1 }">
				 			<img class="img-gallery-1" src="/images/${listing.getPictures().get(1).image}" alt="${listing.getPictures().get(1)}" style="width:100%">
			 			</c:if>
			 			<c:if test="${listing.getPictures().size() > 2 }">
				 			<img class="img-gallery-2" src="/images/${listing.getPictures().get(2).image}" alt="${listing.getPictures().get(2)}" style="width:100%">
			 			</c:if>
                    	</div>
                	</div>

					<!-- Listing Info Section (Right) -->
                	<div class="w3-col l6 m6 w3-margin-bottom">
                    	<div class="w3-card">
                        	<div class="w3-container">
                            	<h3>${listing.type}</h3>
                            	<p class="w3-opacity">$${listing.cost}</p>
                            
                            	<p><span>Description:</span> ${listing.description}</p>
                            	<p><span>Rooms:</span> ${listing.numberofrooms}</p>
                            	<p><span>Bathrooms:</span> ${listing.numberofbathrooms}</p>
                            	<p><span>Year Built:</span> ${listing.yearbuilt}</p>
                            	<p><span>Property Size (Square Feet):</span> ${listing.size}</p>
                            	
                            	<!-- Add to favorites -->
                            	<c:if test="${user != null}">
	                            	<p>
	                            		<form action="/listings/${listing.id}/add" method="post">
		                            		<button class="w3-button w3-light-grey w3-block">
		                                    	<i class="fa fa-heart"></i> Add to favorites
		                            		</button>
	                            		</form>
	                           	</p>
	                			</c:if>
	                			
	                			<!-- Route you to the contact form -->
	                       	<p>
	                        		<a href="#contact"><button class="w3-button w3-light-grey w3-block">
	                           	<i class="fa fa-envelope"></i> Contact</button></a>
	                       	</p>
                            	
                        	</div>
                    	</div>
                	</div>
                	
                	<div class="w3-col l6 m6 w3-margin-bottom">
                    	<div class="w3-card">
                    	<c:if test="${listing.getPictures().size() > 3 }">
				 			<img class="img-gallery" src="/images/${listing.getPictures().get(3).image}" alt="${listing.getPictures().get(3)}" style="width:100%">
			 			</c:if>
						<c:if test="${listing.getPictures().size() > 4 }">
				 			<img class="img-gallery-1" src="/images/${listing.getPictures().get(4).image}" alt="${listing.getPictures().get(4)}" style="width:100%">
			 			</c:if>
                    	</div>
                	</div>
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
            	<a href="#listings" class="w3-button w3-light-grey">
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