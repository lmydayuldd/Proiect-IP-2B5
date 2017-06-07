function openNav() {
	document.getElementById("mySidenav").style.width = "300px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
}	

 function removeMe(){
     $('div .minibox').click(function(e){
     	var body = {
      "type": element.value,
      "room": room.value,
      "x1": x1.value,
      "y1": y1.value,
      "x2": x2.value,   
      "y2": y2.value,
      "floor": floor.value,
     
    };
     $(e.target).remove();
     var xhr = new XMLHttpRequest();
	var url = "http://localhost:4500/delete/";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
            console.log(json);
		}
	};
	xhr.send(JSON.stringify(body));
    });
}


function onSaveElementClick(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior){
	var x1 = parseInt($('#x1').val());
	var y1 = parseInt($('#y1').val());
	var x2 = parseInt($('#x2').val());
	var y2 = parseInt($('#y2').val());
	var element = String($('#element').val());
	var room;
    var floor = parseInt($('#floor').val());
    var isExterior = parseInt($('#isExterior').val());
    var isExitWay = parseInt($('#isExitWay').val());
    if(isExterior == 1){
        room = "-1";
    }
    else{
        room = String($('#room').val());
    }
	sendElement(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior);
}


function onTemporarySaveDataClick(){
    var s = "Operation success."
	/*var xhr = new XMLHttpRequest();
	var url = "http://localhost:4500/finalSave/";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    console.log("1"); 
	xhr.onreadystatechange = function () {
        console.log(xhr.status);
        if (this.readyState == 4 && this.status == 200 && s === JSON.parse(this.responseText).message ) {
			document.getElementById('finalMessage').innerHTML = "<p> Bravo, ai introdus toate datele corect </p>";
            console.log("da");
            }
        else
        {
			//document.getElementById('wrongInput').innerHTML = "<p> Nu ai introdus corect valorile </p>"
            console.log("nu");
        }
    console.log("2");
    xhr.send("");
};*/

    var xhr = new XMLHttpRequest();
	var url = "http://localhost:4500/finalSave/";
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-type', 'application/json');
    console.log("1");
	xhr.onreadystatechange = function () {
        console.log("aici");
	    if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Elementele au fost stocate cu succes");	    
        }
        else{
            alert("Elementele nu au fost stocate cu succces");
        }
    };
    alert("Elementele au fost stocate cu succes");
}

$(document).ready(function(){
		$('#saveElement').on('click', function(ev){
			ev.preventDefault();
			var x1 = $('#x1').val();
                if (x1 == "") {
                    alert("X1 value must be filled out");
                    return false;
                }
            var y1 = $('#y1').val();
                if (y1 == "") {
                    alert("Y1 value must be filled out");
                    return false;
                }
            var x2 = document.forms["myForm"]["x2"].value;
                if (x2 == "") {
                    alert("X2 value must be filled out");
                    return false;
                }
            var y2 = document.forms["myForm"]["y2"].value;
                if (y2 == "") {
                    alert("Y2 value must be filled out");
                    return false;
                }
            var floor = document.forms["myForm"]["floor"].value;
                if (floor == "-1") {
                    alert("You must choose a floor");
                    return false;
                }
			// var valoare = $('#room').val();
			// var x1 = $('#x1').val();
			// var y1 = $('#y1').val();
			 onSaveElementClick(element.value, x1.value, y1.value, x2.value, y2.value, floor.value, room.value, isExitWay.value, isExterior.value);
		})
})

function sendElement(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior){
	var body = {
      "type": element,
      "room": room,
      "x1": x1,
      "y1": y1,
      "x2": x2,
      "y2": y2,
      "floor": floor,
      "isExitWay": isExitWay,
      "isExterior": isExterior
    };
    var x1 = $('#x1').val();
	var y1 = $('#y1').val();
	var x2 = $('#x2').val();
	var y2 = $('#y2').val();
	var element = $('#element').val();
	var y1 = $('#y1').val();
    console.log(JSON.stringify(body));

    var xhr = new XMLHttpRequest();
	var url = "http://localhost:4500/add/";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText).message;
            console.log(json);
            var x = document.getElementById("snackbar")
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
            var element = "<div class='minibox glyphicon glyphicon-trash' onclick='removeMe()'> " + room+" X1:"+x1+" Y1:"+y1+"</div>";
	        $("#mySidenav").append(element);	    
        }
    };
	xhr.send(JSON.stringify(body));
}
