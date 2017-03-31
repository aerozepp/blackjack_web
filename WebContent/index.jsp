<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

<meta charset="utf-8">
<meta name="blackjack_web">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/responsive.css">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

</head>

<body>

	<section id="menuBar">

	<div class="container">
		<div class="logo">
			<ul>
				<li><a><img src="img/my_logo.png" width="60" height="auto">
						<h2>BlackJack</h2></a></li>
			</ul>

		</div>
		<div class="menuList">
			<ul>
				<li><a>Play</a>
					<ul>
						<li id="alone"><a>Play Alone</a></li>
						<li id="together"><a>Play Together</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</section>
	
	<section id="gameRoom">
	<div class="container">
		<div class="layout">

			<div class="status">
				<ul>
					<li><h3>chip :</h3></li>
					<li><p class="size" id="chip_status"></p></li>
				</ul>
				<ul>
					<li><h3>bet :</h3></li>
					<li><p class="size" id="bet_status"></p></li>
				</ul>			
			</div>

			<div class="btn_group">
				<ul>
					<li><p id="deal">deal</p></li>
					<li><p id="hit">hit</p></li>
					<li><p id="stay">stay</p></li>
					<li><p id="reset">reset</p></li>
				</ul>
			</div>


			<div class="dealer_cards_panel">
				<ul>
					<li>
						<div class="dealer_cards"></div>
					</li>
					<li>
						<div class="dealer_hit_cards"></div>
					</li>
				</ul>
				
				<div id="dealer_balloon">
					<img class="flip balloon" src="img/balloon.png">
					<p id="dealer_value_status"></p>
				</div>
			</div>	
			
			<div class="user_cards_panel">
				<ul>
					<li>
						<div class="user_cards"></div>
					</li>
					<li>
						<div class="user_hit_cards"></div>
					</li>
					
				</ul>
				
				<div id="value_balloon">
					<img class="balloon" src="img/balloon.png">
					<p id="value_status"></p>
				</div>
			</div>

			<div class="chips">
				<ul>
					<li id="chip100"><img src="img/chipRed.png" width="70" height="auto"><p>100</p></li>
					<li id="chip50"><img src="img/chipBlue.png" width="70"
							height="auto"><p>50</p></li>
					<li id="chip10"><img src="img/chipYellow.png" width="70"
							height="auto"><p>10</p></li>
				</ul>
			</div>
		</div>
	</div>
	</section>

	<section id="modals"> 
		<div id="insurance" class="modal">
			<div class="modal-content">
  				<div class="modal-header">
    				<span class="close">&times;</span>
   					<h2>Insurance</h2>
  				</div>
 			 	<div class="modal-body">
    				<p>Dealer has an ace, so you can make a insurance bet up to half the original bet. If dealer had blackjack, you will be paid double the insurance bet. How many chips would you bet?</p>
    				
  				</div>
 		 		<div class="modal-footer">
    				<ul>
    					<li id="insuranceHalf"></li>
    					<li id="insuranceNo">No!</li>
    				</ul>
  				</div>
			</div>	
		</div>
		
		<div id="ace" class="modal">
			<div class="modal-content">
  				<div class="modal-header">
    				<span class="close">&times;</span>
   					<h2>Ace</h2>
  				</div>
 			 	<div class="modal-body">
  					<div id="yourAce"></div>
    				<p></p>
    				
  				</div>
 		 		<div class="modal-footer">
    				<ul>
    					<li id="ace1">1</li>
    					<li id="ace11">11</li>
    				</ul>
  				</div>
			</div>	
		</div>
		
		<div id="notify" class="modal">
			<div class="modal-content">
  				<div class="modal-header">
    				<span class="close">&times;</span>
   					<h2></h2>
  				</div>
 			 	<div class="modal-body">
    				<p></p>
  				</div>
			</div>	
		</div>
	
	</section>
	
	<section id="footer"></section>

</body>

 <script>
 $(document).ready(function() {

		var bet = $('#bet_status');
		var chip = $('#chip_status');	 
	 	var value = $('#value_status');
		var dValue = $('#dealer_value_status');
		
		$("#alone").click(function() {
			$.get("start.do");
			$("#gameRoom .container").css("display", "block");
			$(".menuList li > ul").css("opacity", "0");
			
			bet.text(0);
			value.text(0);
			dValue.text(0);
			chip.text(100);
		});

		$('#deal').click(function() {
			
			if (value.text() > 0) {
				alert("Cards dealt!")
			} else if (bet.text() <= 0) {
				alert("bet!");
			} else {
				$('#value_balloon').css('display', 'block');
				$.ajax({
					url : "deal.do",
					data : {bet : bet.text()}
				}).done(function(json){
					$("#value_status").text(json.value);
					$(".dealer_cards").html("<img src=\""+ json.dCard_path+ "\">&nbsp"+ "<img id=\"turn\" src=\"img/cards/back.jpg\">");
					$(".user_cards").html("<img src=\""+ json.card1_path+ "\">&nbsp"+ "<img src=\""+ json.card2_path+ "\">");
					
					if(json.value == 21){
						blackjack();
					}else if(json.dCard_face == "ace"){
						insurance();
					}	
				});
			}
		});

		
		$('#hit').click(function() {
			
			if (value.text() <= 0) {
				alert("deal cards!");
			} else {
				$.get('hit.do', function(responseHtml) {
					$('.user_hit_cards').html(responseHtml);
					
					$.get('ace.do', function(json){
						
						if(json.path1 != "false"){
							$('#yourAce').html("<img src=\""+ json.path1 + "\" width=\"50\">");
							$('#ace').css('display', 'block');	
						}
						else{
							bustCheck();
						}
					});	
				});
			}
		});
		
		$('#stay').click(function() {

			var value = $('#value_status').text();
			if (value <= 0) {
				alert("deal cards!");
			}else{
				$('#dealer_balloon').css('display', 'block');
				$.get("dealer_hit.do", function(responseHtml){
					$('.dealer_hit_cards').html(responseHtml);
				});
			
				$.get("stay.do", function(json){
					$('#dealer_value_status').text(json.value);
					var src = json.path;
					turn('#turn', src);
				
					var user = json.userValue;
					var dealer = json.value;
				
					if(dealer > 21){
						dealer_bust();
						return;
					}else{
						if(user > dealer){
							win();
						}else if (user == dealer){
							tie();
						}else{
							lost();
						}	
					}
				});
			}
		});

		$('#ace1').click(function(){
			$.get("aceChoose.do", function(json){
				
				$('#value_status').text(json.value);	
			});
		
			$('#ace').css('display', 'none');
			
			bustCheck();
		
		});
		
	
		$('#ace11').click(function(){
			
			$.get("ace11.do", function(json){
				
				$('#value_status').text(json.value);	
			});
		
			$('#ace').css('display', 'none');
			bustCheck();	
				
		});
		
		$('#reset').click(function(){
			
			$(this).css('display', 'none');
			if(chip.text() <= 0){
				makeDialog("game over", "chips run out");
				
			}
			empty();
		});
		
		function turn(front, src) {
			
		    $(front).animate({
		        width: 0,
		        marginLeft: 50,
		        marginRight: 50
		    }, function () {
		    	this.src = src
		        $(this).animate({
		            width: 100,
		            marginLeft: 0,
		            marginRight: 0,
		        })
		    })
		}
		
		function bustCheck(){
			
			$.get("bustCheck.do", function(json){
				$('#value_status').text(json.value);
				if(json.value > 21){
					
					bust();	
					disableBtns();
					
				}
			});
		}
		
		function isDealerBlackjack(){
			
			var value = 0;
			$.get("getDealerValue.do", function(json){
				
				value = json.value;
			});
			
			if(value == 21){
				
				$.get("dealerBlackjack.do", function(json){
					
					makeDialog("You get insurance", "Dealer has Balckjack!");
					
					var src = json.turn;
					var win = json.chip;
					turn('#turn', src);	
					chip.text(win);					
				});
				
				disableBtns();
			}
			
	
			
		}
		
		function empty(){
			
			$('.dealer_cards').empty();
			$('.dealer_hit_cards').empty();
			$('.user_cards').empty();
			$('.user_hit_cards').empty();
			
			$('#value_status').text(0);
			$('#dealer_value_status').text(0);
			$('#value_balloon').css('display', 'none');
			$('#dealer_balloon').css('display', 'none');
			$('#bet_status').text(0);
			
			$.get('reset.do');
			
			enableBtns();
		}
		
		function blackjack(){
			
			makeDialog("You win!", "Blackjack!");
			$.get("blackjack.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();
		}
		
		function bust(){
			
			makeDialog("You went bust!", "Dealer win");
			$.get("lost.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();
		}
		
		function dealer_bust(){
			
			makeDialog("Dealer went bust!", "You win");
			$.get("win.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();
		}
		
		function win(){
			
			makeDialog("You win", "You are closer to 21");
			$.get("win.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();
		}
		
		function lost(){
			
			makeDialog("You lost", "Dealer is closer to 21");
			$.get("lost.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();	
		}
		
		function tie(){
			
			makeDialog("Tie", "The both have same count");
			$.get("tie.do", function(json){
				chip.text(json.chip);
			});
			disableBtns();
		}
		
		function insurance(){
			
			var bet = $('#bet_status').text();
	
			$('#insuranceHalf').text(bet/2); 
			
			$('#insurance').css('display', 'block');
		}
		
		function disableBtns(){
			
			$('#reset').css('display', 'block');
			$('#deal').css('display', 'none');
			$('#hit').css('display', 'none');
			$('#stay').css('display', 'none');
		}
		
		function enableBtns(){
			
			$('#deal').css('display', 'block');
			$('#hit').css('display', 'block');
			$('#stay').css('display', 'block');
			
		}
		
		function makeDialog(title, content){
			
			$('#notify .modal-header h2').text(title);
			$('#notify .modal-body p').text(content);
			$('#notify').css('display', 'block');
		}
		
	
		$('#insuranceHalf').click(function(){
				
			var bet = $('#bet_status').text()/2;
		
			var insurance = 0;
			
			if(bet > chip.text()){
				insurance = chip.text(); 
			}else{
				insurance = bet;
			}
			
			$.get("insurance.do", {bet:insurance} , function(json){
				chip.text(json.chip);
			});
			
			isDealerBlackjack();
			
			$('#insurance').css('display', 'none');
		});
			
		
		$('#insuranceNo').click(function(){
	
			$('#insurance').css('display', 'none');
		});
		
		$('.close').click(function(){
			$('#insurance').css('display', 'none');
			$('#notify').css('display', 'none');
			$('#ace').css('display', 'none');
		
			
		});
		
		$('#chip100').click(function(){
			
			var value = $('#value_status').text();
			var bet = $('#bet_status').text();
			var chip = $('#chip_status').text();
			
			if(value == 0){
				
				if(chip >= 100){
					
					$('#bet_status').text(parseInt(bet) + 100);
					$('#chip_status').text(parseInt(chip) - 100);
				}else{
					$('#bet_status').text(parseInt(bet)+parseInt(chip));
					$('#chip_status').text(0);
				}
			}else {
				alert("Cards dealt!");
			}
							
		});
			
		$('#chip50').click(function(){
			var value = $('#value_status').text();
			var bet = $('#bet_status').text();
			var chip = $('#chip_status').text();
			
			if(value == 0){
				
				if(chip >= 50){
					
					$('#bet_status').text(parseInt(bet) + 50);
					$('#chip_status').text(parseInt(chip) - 50);
				}else{
					$('#bet_status').text(parseInt(bet)+parseInt(chip));
					$('#chip_status').text(0);
				}
			}else {
				alert("Cards dealt!");
			}
							
		});
				
		$('#chip10').click(function(){
			
			var value = $('#value_status').text();
			var bet = $('#bet_status').text();
			var chip = $('#chip_status').text();
			
			if(value == 0){
				
				if(chip >= 10){
					
					$('#bet_status').text(parseInt(bet) + 10);
					$('#chip_status').text(parseInt(chip) - 10);
				}else{
					$('#bet_status').text(parseInt(bet) + parseInt(chip));
					$('#chip_status').text(0);
				}
			}else {
				alert("Cards dealt!");
			}
							
							
		});
	});
						
 
 </script>
</html>
