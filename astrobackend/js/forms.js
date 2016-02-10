// Validations for page profile_settings


 $(document).ready(function(e) {
     $("#change_password").click(function(){
	 var old_password=$('input[name="old_password"]').val();
	 var new_password=$('input[name="new_password"]').val();
	 var confirm_password=$('input[name="confirm_password"]').val();
	 if(old_password==""){
		 	alert('Enter Old Password');
			$('input[name="old_password"]').focus();
			return false;
	 }
	 else if(new_password==""){
		 	alert('Enter New Password');
			$('input[name="new_password"]').focus();
			return false;
	 }
	 else if(confirm_password==""){
		 	alert('Enter Confirm Password');
			$('input[name="confirm_password"]').focus();
			return false;
	 }
	 if(new_password!=confirm_password){
		 alert("New Password and Confirm Password Doesn't Match");
		 $('input[name="new_password"]').focus;
		 return false;
	 }
		 $.ajax({
			 // alert("hii");
			  url: "change_password.php",
			  cache: false,
			  type: 'post',
			  data: $("#myform").serialize(),
			  success: function(data){
			  if(data!=1)
			  {
				  /*$("#empty").html(data);*/
				  //alert(data);
				  alert("Old Password Doesn't Match");
				  return false;
			  }
			  else
			  {
				  alert("Password Updated");
				  window.location.href='login.php';
			  }
			}
		 });
	 });
	 
	 
	 
	
// Validating insert category
	
	 var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		var checkin = $('#dpd1').datepicker({
			format: "dd/mm/yyyy",
	  onRender: function(date) {
		return date.valueOf() < now.valueOf() ? 'disabled' : '';
	  }
	}).on('changeDate', function(ev) {
	  if (ev.date.valueOf() > checkout.date.valueOf()) {
		var newDate = new Date(ev.date)
		newDate.setDate(newDate.getDate() + 1);
		checkout.setValue(newDate);
	  }
	  checkin.hide();
	  $('#dpd2')[0].focus();
	}).data('datepicker');
	var checkout = $('#dpd2').datepicker({
		format: "dd/mm/yyyy",
	  onRender: function(date) {
		return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
	  }
	}).on('changeDate', function(ev) {
	  checkout.hide();
	}).data('datepicker');

		
  });
  
  
//validating insert paper form

$("#insert_category").change(function(){
	//alert('hey');
    var insert_category =$(this).val();
    $.ajax({
        type:'post',
        cache: false,
        url:'get_volume.php',
        data : { insert_category : insert_category},
        success : function(result){
           // alert(result);
            if(result==1){
                $("#select_volume").html('<option>Select Volume</option>');
            }else{
                $("#select_volume").html(result);
            }
        }
    });
});

$("#select_volume").change(function(){
	//alert('hey');
    var insert_category =$('#insert_category').val();
    var select_volume =$(this).val();
    $.ajax({
        type:'post',
        cache: false,
        url:'get_issue.php',
        data : { select_volume : select_volume,insert_category:insert_category},
        success : function(result){
           // alert(result);
            if(result==1){
                $("#select_issue").html('<option>Select Issue</option>');
            }else{
                $("#select_issue").html(result);
            }
        }
    });
});









