<?php
	
	//require_once('../abell_backend/Connections/invoice.php');
	include 'config.php'; 
	$email=$_POST['user'];
	$pass=$_POST['pass'];
	$sql="select `admin_name` from `admin_login` where  `admin_name` ='$email' and `admin_password`='$pass'";
	$res=mysql_query($sql);
	$th=@mysql_fetch_array($res, MYSQL_NUM);
	if($th[0]!="" || $th[0]!=NULL)
	{
		//echo "SUCCESS";
		session_start();
		$_SESSION['user_id']=$th[0];
		echo 1;
		
	} 
	else
	{
	 	//echo "failure";	
		echo 0;
	}
?>