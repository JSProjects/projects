<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap-home.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

<!--     Fav and touch icons
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../assets/ico/favicon.png"> -->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <!-- <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Project name</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn">Sign in</button>
            </form>
          </div>/.nav-collapse -->
          <div id="utility_block">
			<div class="user_utility">
			<span class="name">Welcome back,${sessionScope.reseller}</span><a class="account" href="logout.jsp">Log Out</a>
			</div>
			</div>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="hero-unit">
        <h1 style="text-align:center;">Welcome to the Partner Licensing and Entitlements Center</h1>
        <p></p>
        <!-- <p><a href="#" class="btn btn-primary btn-large">Click Here &raquo;</a></p> -->
      </div>

      <!-- Example row of columns -->
      <div class="row" style="align:center;">
        <div class="span4">
          <h2 style="margin:10px;text-align:center;">Aerohive Orders Reporting Tool</h2>
          <p style="margin:10px;">AeroPORT gives partners visibility to the Aerohive order and licensing Information to help with manage their Aerohive licensing and renewals business </p>
          <p style="margin:10px;float:right;"><a class="btn" href="summary.jsp">Click Here &raquo;</a></p>
        </div>
       <% String value =((String) (session.getAttribute("uname")!=null && !session.getAttribute("uname").equals("") ? session.getAttribute("uname") :""));
       request.setAttribute("uname",value);%>
        <div class="span4">
          <h2 style="margin:10px;text-align:center;">Not-for-Resale Self Service Portal</h2>
          <p style="margin:10px;">This is the Not-for-Resale (NFR) self-service portal that allows VARs/VADs to requests and manage Entitlement Keys for their valid NFR kits.</p>
          <p style="margin:10px;float:right;"><a class="btn" href="/NFR/index?uname=${sessionScope.uname}">Click Here &raquo;</a></p>
       </div>
      </div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
      <hr>

   <!--    <footer>
        <p>&copy; Aerohive NetWorks Company 2015</p>
      </footer>
 -->
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-home.js"></script>
    <script src="js/bootstrap-transition.js"></script>
    <script src="js/bootstrap-alert.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    <script src="js/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap-tab.js"></script>
    <script src="js/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap-popover.js"></script>
    <script src="js/bootstrap-button.js"></script>
    <script src="js/bootstrap-collapse.js"></script>
    <script src="js/bootstrap-carousel.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>

  </body>
</html>
