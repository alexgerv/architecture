<form id="searchForm">
	<h4>Sports</h4>
	<div id="SPORT" style="margin-left: 10px;">
		<div class="checkbox">
			<label> <input type="checkbox" name="Football"/> Football
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="Rugby"/> Rugby
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="Soccer"/> Soccer
			</label>
		</div>
	</div>
	<h4>Venues</h4>
	<div id="VENUE" style="margin-left: 10px;">
		<div class="checkbox">
			<label> <input type="checkbox" name="Stade Telus"/> Stade Telus
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="Montreal"/> Montréal
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="Sherbrooke"/> Sherbrooke
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
	formArray['SPORT'] = $("#SPORT :input").serializeArray();
	formArray['VENUE'] = $("#VENUE :input").serializeArray();
	$inputs.prop("disabled", true);
	var serializedData = JSON.stringify(formArray);
	$.ajax( {url: '/search',
			data: serializedData,
			type: 'POST',
			dataType: 'json',
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
 	if($.isArray(matchList) && !$.isEmptyObject(matchList)){
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
