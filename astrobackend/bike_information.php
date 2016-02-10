
<?php
include 'config.php';
	$bike_modal = mysql_real_escape_string($_POST['bike_modal']);
	$bike_reg = mysql_real_escape_string($_POST['bike_reg']);
	$bike_color = mysql_real_escape_string( $_POST['bike_color']);
	$bike_fare = mysql_real_escape_string($_POST['bike_fare']);
	$bike_image= addslashes(file_get_contents( $_FILES['bike_image']['tmp_name']));
		
	
	$sql= "INSERT INTO `bike_info`(`bike_model`, `bike_regno`, `bike_color`, `bike_fare`, `bike_image`) VALUES ('$bike_modal', '$bike_reg', '$bike_color', '$bike_fare', '$bike_image')";
	
		
if(mysql_query($sql)){
	header('Location:show_bikeinfo.php');
} 
else{
	header('Location: bike_info.php');
}



?>