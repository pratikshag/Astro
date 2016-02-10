<?php

$id= $_GET['id'];

require 'config.php';

$sql="delete from `bike_info` where bike_id='$id'";

$result= mysql_query($sql);

if(isset($result))
{
	header('location: show_bikeinfo.php');
}
?>