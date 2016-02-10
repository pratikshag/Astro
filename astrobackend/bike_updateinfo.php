
<?php
include 'config.php';

    $bike_id = mysql_real_escape_string($_POST['id']);
	$bike_modal = mysql_real_escape_string($_POST['bike_modal']);
	$bike_reg = mysql_real_escape_string($_POST['bike_reg']);
	$bike_color = mysql_real_escape_string( $_POST['bike_color']);
	$bike_fare = mysql_real_escape_string($_POST['bike_fare']);
	$bike_image= addslashes(file_get_contents( $_FILES['bike_image']['tmp_name']));
		
	
	$sql= "UPDATE `bike_info` SET `bike_model`='$bike_modal',`bike_regno`='$bike_reg',`bike_color`='$bike_color',`bike_fare`='$bike_fare',`bike_image`='$bike_image' WHERE `bike_id`= '$bike_id'";
	
		
if(mysql_query($sql)){
	header('Location:show_bikeinfo.php');
} 




?>