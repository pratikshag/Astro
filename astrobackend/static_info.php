
<?php
include 'config.php';
	$heading =$_POST['heading'];
	$content = $_POST['content'];
	$admin_id= $_POST['admin'];
	
	
	$sql= "INSERT INTO `static_page`(`page_title`, `page_content`, `admin_id`) VALUES ('$heading', '$content', '$admin_id')";
	
	
	
if(mysql_query($sql)){
	header('Location:show_static.php');
} 
else{
	header('Location: static.php');
}



?>