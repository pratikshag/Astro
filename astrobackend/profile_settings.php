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
                           Profile Settings
                        
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.php">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> Profile Settings
                            </li>
                        </ol>
                        
                        <form class="form-horizontal" method="post" id="myform" onSubmit="return false">
                          <div class="row">
                              <label  class="col-sm-6 control-label">Change Password</label>
                          </div>
                          <div class="col-sm-offset-2 col-sm-8">
                              <div class="form-group">
                                <label  class="col-sm-3 control-label">Old Password</label>
                                <div class="col-sm-9">
                                  <input type="password" class="form-control" name="old_password" placeholder="Old Password">
                                </div>
                              </div>
                    
                              <div class="form-group">
                                <label  class="col-sm-3 control-label">New Password</label>
                                <div class="col-sm-9">
                                  <input type="password" class="form-control" name="new_password" placeholder="New Password">
                                </div>
                              </div>
                              
                      
                              <div class="form-group">
                                <label  class="col-sm-3 control-label">Confirm Password</label>
                                <div class="col-sm-9">
                                  <input type="password" class="form-control" name="confirm_password" placeholder="Confirm Password">
                                </div>
                              </div>
                  
                               <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-2">
                                  <input type="submit" class="btn btn-info" value="Submit" id="change_password">
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
