
<?php
include 'config.php';
	$event_name = mysql_real_escape_string($_POST['event_name']);
	$event_date = mysql_real_escape_string($_POST['event_date']);
	$event_address = mysql_real_escape_string( $_POST['event_address']);
	$event_detail = mysql_real_escape_string($_POST['event_detail']);
	$event_image= addslashes(file_get_contents( $_FILES['event_image']['tmp_name']));
		
	
	$sql= "INSERT INTO `event_info`(`event_name`, `event_date`, `event_address`, `event_detail`, `event_image`) VALUES ('$event_name', '$event_date', '$event_address', '$event_detail', '$event_image')";
	
		
if(mysql_query($sql)){
	header('Location:show_bikeinfo.php');
} 
else{
	header('Location: bike_info.php');
}



?>