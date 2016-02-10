
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HawaiiJaipur </title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="http://kylemitofsky.com/libraries/libraries/datepicker.css">
    <script src="//tinymce.cachefly.net/4.2/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    plugins: [
        "advlist autolink lists link image charmap print preview anchor",
        "searchreplace visualblocks code fullscreen",
        "insertdatetime media table contextmenu paste"
    ],
    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
});
</script>
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php">HawaiiJaipur</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <!--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <strong>Welcome Admin,</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li>-->
              
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Welcome Admin <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="profile_settings.php"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="logout.php"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="index.php"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    
                                   
                     

		     


                     


                   <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#page1"><i class="fa fa-fw fa-arrows-v"></i> Bike Information <i class="fa fa-fw fa-caret-down"></i></a>
                   
                        <ul id="page1" class="collapse">
                            <li>
                                <a href="bike_info.php">Add Bike Informations</a>
                            </li>
                            <li>
                                <a href="show_bikeinfo.php">View Bike Informations</a>
                            </li>
			    </ul>
                  
                  <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#page2"><i class="fa fa-fw fa-arrows-v"></i> Weekly Horoscope <i class="fa fa-fw fa-caret-down"></i></a>
                   
                        <ul id="page2" class="collapse">
                            <li>
                                <a href="week.php">Add Weekly Horoscope</a>
                            </li>
                            <li>
                                <a href="show_week.php">View Weekly Horoscope</a>
                            </li>
			    </ul>

			 
             <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#page3"><i class="fa fa-fw fa-arrows-v"></i> Monthly Horoscope <i class="fa fa-fw fa-caret-down"></i></a>
                   
                        <ul id="page3" class="collapse">
                            <li>
                                <a href="month.php">Add Monthly Horoscope</a>
                            </li>
                            <li>
                                <a href="show_month.php">View Monthly Horoscope</a>
                            </li>
			    </ul>
		
        
        <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#page4"><i class="fa fa-fw fa-arrows-v"></i> Static Pages <i class="fa fa-fw fa-caret-down"></i></a>
                   
                        <ul id="page4" class="collapse">
                            <li>
                                <a href="static.php">Add Static Pages</a>
                            </li>
                            <li>
                                <a href="show_static.php">View Static Pages</a>
                            </li>
			    </ul>

					
                    <li>
                        <a href="show_amc.php"><i class="fa fa-fw fa-bar-chart-o"></i>Show AMC Quries</a>
                    </li>
                    
                    <li>
                        <a href="show_afc.php"><i class="fa fa-fw fa-bar-chart-o"></i>Show AFC Quries</a>
                    </li>
                   
                    <li>
                        <a href="show_question.php"><i class="fa fa-fw fa-bar-chart-o"></i>Questions</a>
                    </li>
                    
                    <li>
                    
                        <a href="#"><i class="fa fa-fw fa-bar-chart-o"></i>Support Ticket</a>
                    </li>
                    
                     <li>
                        <a href="profile_settings.php"><i class="fa fa-fw fa-bar-chart-o"></i>Profile Settings</a>
                    </li>
                   
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
