<form id="searchForm">
	<h4>Sports</h4>
	<div id="sports" style="margin-left: 10px;">
		<div class="checkbox">
			<label> <input type="checkbox" name="football"/> Football
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="rugby"/> Rugby
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="soccer"/> Soccer
			</label>
		</div>
	</div>
	<h4>Venues</h4>
	<div id="venue" style="margin-left: 10px;">
		<div class="checkbox">
			<label> <input type="checkbox" name="stade telus"/> Stade Telus
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="montreal"/> Montr�al
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="sherbrooke"/> Sherbrooke
			</label>
		</div>
	</div>
</form>
<script type="text/javascript">

$("#searchForm").change(function(){
	debugger;
 	var $form = $(this);
	var $inputs = $form.find("input, select, button, textarea");
	var formArray = {};
	formArray['sports'] = $("#sports :input").serializeArray();
	formArray['venue'] = $("#venue :input").serializeArray();
	$inputs.prop("disabled", true);
	var serializedData = JSON.stringify(formArray);
	console.log(serializedData);
	$.ajax( {url: '/search',
			data: serializedData,
			type: 'POST',
			contentType:'application/json',
			processData: false,
			success: function(response){
				$("#matchList tbody tr").remove();
				displayMatches(response);
				$inputs.prop("disabled", false);
			}
        });
 });
 
 function displayMatches(matchList){
 	if(!$.isArray(matchList)){
 		$("#matchList").show();
 		$("#searchMessage").hide();
	 	for(index in matchList){
	 		$("#matchList tbody").append(
	 		'<tr>' +
				'<td style="text-align: center;"><a'+
						'href="match/' + matchList[index].matchID + '" class="btn btn-default btn-xs"><i'+
						'class="icon icon-search"></i></a></td>'+
				'<td>' + matchList[index].venue + '</td>'+
				'<td>' + matchList[index].date + '</td>'+
				'<td>' + matchList[index].sport + '</td>'+
				'<td>' + matchList[index].homeTeam + '</td>'+
				'<td>' + matchList[index].visitorTeam + '</td>'+
				'<td><strong>' + matchList[index].totalNumberOfAvailableTickets + '</strong>'+
					'(<a href="match/' + matchList[index].matchID + '">view by section</a>)</td>'+
			'</tr>');		
	 	}
 	}
 	else {
 		$("#searchMessage").show();
 		$("#matchList").hide();
 	}
 }

 </script>
