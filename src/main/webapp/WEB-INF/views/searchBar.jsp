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
        <div class="checkbox">
            <label> <input type="checkbox" name="Badminton"/> Badminton
            </label>
        </div>
        <div class="checkbox">
            <label> <input type="checkbox" name="Basketball"/> BasketBall
            </label>
        </div>
        <div class="checkbox">
            <label> <input type="checkbox" name="Cheerleading"/> Cheerleading
            </label>
        </div>
        <div class="checkbox">
            <label> <input type="checkbox" name="Cross-country"/> Cross-country
            </label>
        </div>  
        <div class="checkbox">
            <label> <input type="checkbox" name="Golf"/> Golf
            </label>
        </div> 
        <div class="checkbox">
            <label> <input type="checkbox" name="Swimming"/> Swimming
            </label>
        </div> 
        <div class="checkbox">
            <label> <input type="checkbox" name="Diving"/> Diving
            </label>
        </div> 
        <div class="checkbox">
            <label> <input type="checkbox" name="Ski"/> Ski
            </label>
        </div> 
        <div class="checkbox">
            <label> <input type="checkbox" name="Volleyball"/> Volleyball
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
			<label> <input type="checkbox" name="Montreal"/> Montreal
			</label>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" name="Sherbrooke"/> Sherbrooke
			</label>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	requestMatches();
});

$("#searchForm").change(function(){
 	requestMatches();
 });
 
function requestMatches(){
 	var $form = $("searchForm");
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
				if(response.length == 0){
					$("#searchMessage").show();
					$("#dataTable_wrapper").hide();
					$("#dataTable").hide();
				}else{
					$("#dataTable").dataTable({
						"bProcessing": true,
						"aaData": response,
						"bDestroy" : true,
						"aoColumns":[
							{"mData" : "matchIdentifier", 
								"mRender": function(data, type, full){
									return '<a '+
									'href="match/' + data + '" class="btn btn-default btn-xs"><i '+
									'class="icon icon-search"></i></a>';
								}
							},
							{"mData" : "venue"},
							{"mData" : "date"},
							{"mData" : "sport"},
							{"mData" : "homeTeam"},
							{"mData" : "visitorTeam"},
							{"mData" : "sex"},
							{"mData" : "totalNumberOfAvailableTickets",
								"bSortable" : false, 
								"mRender": function(data, type, full){
									return '<strong>' + data + '</strong>'+
									'(<a href="match/' + full.matchIdentifier + '">view by section</a>)</td>';
								},
							}
						]
					});
					$("#searchMessage").hide();
					$("#dataTable").show();
					$("#dataTable_wrapper").show();
				
				}
				$inputs.prop("disabled", false);
			}
        });
 }

 </script>
