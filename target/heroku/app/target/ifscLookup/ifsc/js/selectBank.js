var templ='';
var serverURL = "http://localhost:8080";/* "http://ifsclookup.herokuapp.com" */
$.get("tmp/branch_details_tmp.html").done(function(tmp){
	 templ= tmp;
 });
$.getJSON(serverURL + "/banksApi/banksList").done(function(data){
	var bankNames = data;
	if(data){
		var selectBox = $('#bankSelectBox');
//		selectBox.selectmenu();
		for(var i=0; i < bankNames.length; i++ ){
			$('<option>'+ bankNames[i] +'</option>').appendTo(selectBox);
		}
	}
	
	// on changing item in select box
	$( "#bankSelectBox" ).on( "change", function() {
	var selectedBank = selectBox.val();
	$.getJSON(serverURL + "/banksApi/banksList/" + selectedBank).done(function(data){
		
		if(data && data.length){
		
			$('#citySearchBox').autocomplete({
			      source: data
			    });
			$( "#citySearchBox" ).off("autocompleteselect");
			$( "#citySearchBox" ).on( "autocompleteselect", function( event, ui ) {
				if(!$("#branchList").is(':empty')){
					$( "#branchList" ).empty();
				}
				var selectedCity= $( "#citySearchBox" ).val();
				$.getJSON(serverURL+ "/banksApi/banksList/bankname/" + selectedBank + "/cityname/" + selectedCity).done(function(branchList){
					
				if(branchList && branchList.length ){
						
					for(var i=0; i<branchList.length; i++){
					  var templateData = branchList[i];
					
					  $.tmpl(templ,templateData).appendTo("#branchList");
						
						}
					}
				});
				
			} );
		}
	})
	} );
	$('#bankSelectBox').trigger("change");
	
	
	
});
