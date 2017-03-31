$(document).ready(function() {

	$("#alone").click(function() {
		$.get("start.do");
		$("#gameRoom .container").css("display", "block");
		$(".menuList li > ul").css("opacity", "0");
		$('#bet_status').text(0);
		$('#value_status').text(0);
		$('#chip_status').text(100);
	});

	$("#exit").click(function() {
		$("#gameRoom .container").css("display", "none");
		$(".menuList li > ul").css("opacity", "1");
	});

	$('#deal').click(function() {

		var value = $('#value_status').text();
		var bet = $('#bet_status').text();

		if (value > 0) {
			alert("Already dealt!")
		} else if (bet <= 0) {
			alert("bet!");
		} else {
			$.get('deal.do',{bet : bet}, function(json) {
				$("#value_status").text(json.value);
				$(".dealer_cards").html("<img src=\""+ json.dCard+ "\">&nbsp"+ "<img src=\"img/cards/back.jpg\">");
				$(".user_cards").html("<img src=\""+ json.card1+ "\">&nbsp"+ "<img src=\""+ json.card2+ "\">");
			});
		}

	});

	$('#hit').click(function() {

		var value = $('#value_status').text();

		if (value <= 0) {
			alert("deal cards!");
		} else {
			$.get('hit.do', function(responseHtml) {
			$('.user_hit_card').html(responseHtml);
			});
		}

	});

	$('#stay').click(function() {

		var value = $('#value_status').text();
		if (value <= 0) {
			alert("deal cards!");
		}
	});

	$('#chip100').click(function(){
		
		var value = $('#value_status').text();
		var bet = $('#bet_status').text();
		var chip = $('#chip_status').text();
		
		if(value == 0){
			
			if(chip >= 100){
				
				$('#bet_status').text(bet + 100);
				$('#chip_status').text(chip - 100);
			}
		}
						
	});
					
	$('#chip50').click(function(){
		var value = $('#value_status').text();
		var bet = $('#bet_status').text();
		var chip = $('#chip_status').text();
		
		if(value == 0){
			
			if(chip >= 50){
				
				$('#bet_status').text(bet + 50);
				$('#chip_status').text(chip - 50);
			}
		}
						
	});
					
	$('#chip10').click(function(){
		var value = $('#value_status').text();
		var bet = $('#bet_status').text();
		var chip = $('#chip_status').text();
		
		if(value == 0){
			
			if(chip >= 10){
				
				$('#bet_status').text(bet + 10);
				$('#chip_status').text(chip - 10);
			}
		}
						
						
	});
});
					