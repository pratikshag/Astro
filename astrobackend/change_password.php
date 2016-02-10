<?php
	
	//require_once('../abell_backend/Connections/invoice.php');
	include 'config.php'; 
	session_start();
	$old_password=$_POST['old_password'];
	$new_password=$_POST['new_password'];
	$confirm_password=$_POST['confirm_password'];
	$email=$_SESSION['user_id'];
	$sql="select `password` from `admin_login` where  `email` ='$email' and `password`='$old_password'";
	$res=mysql_query($sql);
	$th=@mysql_fetch_array($res, MYSQL_NUM);
	if($th[0]!="" || $th[0]!=NULL)
	{
		$sql="update `admin_login` set `password`='$new_password' where `email`='$email'";
		mysql_query($sql);
		echo 1;
	} 
	else
	{
		echo 0;
	}
?>
