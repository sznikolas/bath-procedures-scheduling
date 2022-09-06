
// Info tab panel
function togleInfoTab() {
			$("#infoTab").toggle("slow", function() {
			});
		}



// VALIDATE: New User Account Registration validation
function emailCheck(){
    if($("#email").val()==""){
        $("#email").addClass('is-invalid');
        return false;
    }else{
        var regMail     =   /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
        if(regMail.test($("#email").val()) == false){
            $("#email").addClass('is-invalid');
            return false;
        }else{
            $("#email").removeClass('is-invalid');
            $('#next-form').collapse('show');
        }

    }
}

//VALIDATE: New User Account Registration validation
function validation(){
    if($("#username, #phone, #password, #cpassword").val()==""){
        $("#username, #phone, #password, #cpassword").addClass('is-invalid');
        return false;
    }else{
        $("#username, #phone, #password, #cpassword").removeClass('is-invalid');
    }
     
    if($("#password").val()!=$("#cpassword").val()){
        $("#cpassword").addClass('is-invalid');
        $("#cp").html('<span class="text-danger">Password and confirm password not matched!</span>');
        return false;
    }
}
$(document).ready(function(e) {
    $("#username").on("keyup",function(){
        if($("#username").val()==""){
            $("#username").addClass('is-invalid');
            return false;
        }else{
            $("#username").removeClass('is-invalid');
        }
    });
    $("#phone").on("keyup",function(){
        if($("#phone").val()==""){
            $("#phone").addClass('is-invalid');
            return false;
        }else{
            $("#phone").removeClass('is-invalid');
        }
    });
    $("#password").on("keyup",function(){
        if($("#password").val()==""){
            $("#password").addClass('is-invalid');
            return false;
        }else{
            $("#password").removeClass('is-invalid');
        }
    });
    $("#cpassword").on("keyup",function(){
        if($("#cpassword").val()==""){
            $("#cpassword").addClass('is-invalid');
            return false;
        }else{
            $("#cpassword").removeClass('is-invalid');
        }
    });
});



//DatePicker function for BirthDate
$(function() {
	  $("#datepickerBirthDate").datepicker({
			changeMonth: true,
			changeYear: true,
			showButtonPanel: false,
			dateFormat:'yy-mm-dd',
			maxDate: '-1D'	
		},
		$.datepicker.regional["sk"]);
	});


//DatePicker function for Procedures
$(function() {
	  $("#datepickerProcedures").datepicker({
			changeMonth: true,
			changeYear: true,
			showButtonPanel: false,
			dateFormat:'yy-mm-dd',
			minDate: '+7D'	
		},
		$.datepicker.regional["sk"]);
	});

//Input type number or other max length check
function maxLengthCheck(object) {
	  if (object.value.length > object.maxLength)
	  object.value = object.value.slice(0, object.maxLength)
	}



