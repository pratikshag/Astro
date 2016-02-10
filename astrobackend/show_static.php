<?php
session_start();
if(!isset($_SESSION['user_id']))
{
	header('Location:login.php');
	die;
}
?>
<?php include 'header.php';?>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                           Static Pages
                        
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.php">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> Show Static Pages
                            </li>
                        </ol>
                        
                                            
            <?php
			
			
			
				$sql="select * from static_page order by id desc";
			
			
			 ?>
			
                        
                        
    <div class="row">
	<div class="col-sm-offset-1 col-sm-10 ">
    		<?php
				
				include 'config.php';
				$res=mysql_query($sql) or die(mysql_error());
				echo '<div class="row">';
					echo '<div class="col-sm-3"><strong>heading</strong></div>';
					echo '<div class="col-sm-6"><strong>Content</strong></div>';
					echo '<div class="col-sm-3"><strong>Opration</strong></div>';
				
				echo '</div>';
				echo '<hr>';
						while($th=@mysql_fetch_array($res, MYSQL_NUM))
						{
							//$name=explode('|',$th[1]);
												
							echo '<div class="row">';
								echo '<div class="col-sm-3">'.$th[1].'</div>';
								echo '<div class="col-sm-6">'.$th[2].'</div>';
								echo '<div class="col-sm-3">';
								echo '<div class="col-sm-3"><a href="delete_static.php?id='.$th[0].'"><button class="btn btn-danger btn-xs">Delete</button></a></div>';
												
								echo '</div>';
							echo '</div></br>';
							
						}
			
			?>
                    </div>
                   
            </div>   
           </div>
           
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->

<?php include 'footer.php';?>
