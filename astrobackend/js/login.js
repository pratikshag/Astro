// Validations for login page
$(document).ready(function() {
 
	 $("#login_btn").click(function(){
	 var user=$('input[name="user"]').val();
	 var pass=$('input[name="pass"]').val();
	 if(user==""){
		 	alert('Enter Username');
			$('input[name="user"]').focus();
			return false;
	 }
	 else if(pass==""){
		 	alert('Enter Password');
			$('input[name="pass"]').focus();
			return false;
	 }
	 
	  $.ajax({
		  url: "login_user.php",
		  cache: false,
		  type: 'post',
		  data: $("#login_form").serialize(),
		  success: function(data){
		  if(data!=1)
		  {
			  /*$("#empty").html(data);*/
			  //alert(data);
			  alert("Incorrect Credentials");
			  return false;
		  }
		  else
		  {
			  /*alert("Login Successful");*/
			  window.location.href='index.php';
		  }
		}
	 });
    });
});
