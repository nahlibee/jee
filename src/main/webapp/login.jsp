<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>E-Vacataire</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="description" content="Portal - Bootstrap 5 Admin Dashboard Template For Developers">
    <meta name="author" content="Xiaoying Riley at 3rd Wave Media">
    <link rel="shortcut icon" href="assets/images/img2.png">

    <!-- FontAwesome JS-->
    <script defer src="assets/plugins/fontawesome/js/all.min.js"></script>

    <!-- App CSS -->
    <link id="theme-style" rel="stylesheet" href="assets/css/portal.css">

</head>

<body class="app app-login p-0">
    <div class="row g-0 app-auth-wrapper">
        <div class="col-12 col-md-7 col-lg-6 auth-main-col text-center p-5">
            <div class="d-flex flex-column align-content-end">
                <div class="app-auth-body mx-auto">
                    <div class="app-auth-branding mb-4">
                        <a class="app-logo" href="index.jsp">
                            <img class="logo-icon me-2 w-16 h-16" src="assets/images/p.jpeg" alt="logo">
                        </a>
                    </div>
                    <h2 class="auth-heading text-center mb-5">Log in to E-Vacataire</h2>
                    <div class="auth-form-container text-start">

                       <form action="login" method="post">
    <input class="auth-form login-form" type="hidden" name="action" value="login">
    <div class="email mb-3">
        <label class="sr-only" for="login">Email</label>
        <input id="signin-email" name="login" type="text" class="form-control signin-email"
               placeholder="Email address" required="required">
    </div>
    <!--//form-group-->
    <div class="password mb-3">
        <label class="sr-only" for="password">Password</label>
        <input id="password" name="password" type="password" class="form-control signin-password"
               placeholder="Password" required="required">
        <div class="extra mt-3 row justify-content-between">
            <div class="col-6">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="RememberPassword">
                    <label class="form-check-label" for="RememberPassword">
                        Remember me
                    </label>
                </div>
            </div>
            <!--//col-6-->
            <div class="col-6">
                <div class="forgot-password text-end">
                    <a href="reset-password.html">Forgot password?</a>
                </div>
            </div>
            <!--//col-6-->
        </div>
        <!--//extra-->
    </div>
    <!--//form-group-->
    <div class="text-center">
        <button type="submit" class="btn w-100 theme-btn mx-auto" value="Se connecter" style="background: linear-gradient(to right, #1E90FF, #008000); color: white;">Log In</button>
   
    </div>
</form>



                        <div class="auth-option text-center pt-5">No Account? Sign up
                            <a class="text-link" href="signup.jsp">here</a>.
                        </div>
                    </div><!--//auth-form-container-->

                </div><!--//auth-body-->


            </div><!--//flex-column-->
        </div><!--//auth-main-col-->
      <div class="col-12 col-md-5 col-lg-6 h-100 auth-background-col auth-blue-background">
            <div class="auth-background-holder" style="background-image: url('assets/images/5.png');">
            </div>
            <div class="auth-background-mask"></div>
            <div class="auth-background-overlay p-3 p-lg-5">
                <div class="d-flex flex-column align-content-end h-100">
                    <div class="h-100"></div>
                    <div class="overlay-content p-3 p-lg-4 rounded"  style="background-color: #1E90FF;">
                        <h5 class="mb-3 overlay-title">Explore admin!</h5>
                        <div> E-Vacataire admin dashboard Application.Welcome back ! </div>
                    </div>
                </div>
            </div><!--//auth-background-overlay-->
        </div><!--//auth-background-col-->

    </div><!--//row-->
</body>
<style>
.auth-blue-background {
    background-color: blue;
    /* Vous pouvez ajuster d'autres propriétés CSS selon vos besoins */
}
</style>
</html>
