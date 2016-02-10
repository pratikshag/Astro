<?php
session_start();
if(!isset($_SESSION['user_id']))
{
	header('Location:login.php');
	die;
}
$admin_username = $_SESSION['user_id']
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
                                <i class="fa fa-file"></i> Static pages
                            </li>
                        </ol>
                        
                        
                        
                        <form class="form-horizontal" action="static_info.php" method="post">
                              <div class="row">
                                  <label  class="col-sm-6 control-label">Static Pages</label>
                              </div>
                              <div class="col-sm-offset-2 col-sm-8">
                                                            
                                  <div class="form-group">
                                    <label  class="col-sm-2 control-label">Heading</label>
                                    <div class="col-sm-10">
                                      <input type="text" name="heading" placeholder="Heading" class="form-control" />
                                    </div>
                                  </div>
                                  
                                  <div class="form-group">
                                    <label  class="col-sm-2 control-label">Content</label>
                                    <div class="col-sm-10">
                                      <textarea name="content" placeholder="content"></textarea>
                                    </div>
                                  </div>
                                  
                                  
                                  
                                  <input type="hidden" value="<?php echo $admin_username ?>" name="admin" />
                                  

                                   <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                      <input type="submit" class="btn btn-info" value="Submit" >
                                    </div>
                                  </div>
                                </div>
                                <div class="col-sm-2"></div>
      						</form>
                      
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->
 <?php include 'footer.php';?>
   
