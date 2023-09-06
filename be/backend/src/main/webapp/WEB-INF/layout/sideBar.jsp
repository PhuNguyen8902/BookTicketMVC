<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--<div id="wrapper">-->
<!-- Sidebar -->
<ul class="navbar-nav bg-info text-white height" >

    <!-- Sidebar - Brand -->
    <a class=" d-flex align-items-center justify-content-center text-decoration-none text-white fw-bold fs-3 " >
        <div >
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="mx-3"  >Admin<sup>2</sup></div>
        <!-- Divider -->
        <hr class=" my-0">

        <!-- Nav Item - Dashboard -->
        <li >
            <a class="nav-link text-white fs-5" href="/backend/admin">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Infomation</span></a>
        </li>

        <!-- Divider -->
        <hr >

        <!-- Heading -->
        <div class="text-muted">
            Quick Menu
        </div>

        <!-- Nav Item - Pages Collapse Menu -->


        <!-- Nav Item - Utilities Collapse Menu -->
        <%
            org.springframework.security.core.Authentication auth
                    = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated()) {
                String username = auth.getName();
                String userRole = "";

                for (org.springframework.security.core.GrantedAuthority authority : auth.getAuthorities()) {
                    userRole = authority.getAuthority();
                    break; // Lấy quyền đầu tiên (có thể điều chỉnh theo nhu cầu)
                }

                if (userRole.equals("ROLE_ADMIN")) {
        %>
        <li >

            <a class="nav-link text-white fs-5" href="/backend/admin/employee">

                <i class="fas fa-fw fa-cog"></i>
                <span>Employee</span>
            </a>
            <!--        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Components:</h6>
                            <a class="collapse-item" href="buttons.html">Buttons</a>
                            <a class="collapse-item" href="cards.html">Cards</a>
                        </div>
                    </div>-->
        </li>
        <li >
            <a class="nav-link text-white fs-5" href="/backend/admin/customer">
                <i class="fas fa-fw fa-cog"></i>
                <span>Customer</span>
            </a>
        </li>
        <li >
            <a class="nav-link text-white fs-5" href="/backend/admin/driver">
                <i class="fas fa-fw fa-cog"></i>
                <span>Driver</span>
            </a>
        </li>
        <li >

            <a class="nav-link text-white fs-5" href="/backend/admin/route">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Route</span>  
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/trip">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Trip</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/onlTicket">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Onl Ticket</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/offTicket">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Off Ticket</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/vehicle">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Vehicle</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/station">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Station</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/address">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Address</span>  
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/increasedPrice">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Increased Price</span>  
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/admin/chart/">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Chart</span>
            </a>
        </li>

        <%
        } else if (userRole.equals("ROLE_EMPLOYEE")) {
        %>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/employee">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Employee</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/customer">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Customer</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/driver">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Driver</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/route">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Route</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/trip">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Trip</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/onlTicket">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Onl Ticket</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/offTicket">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Off Ticket</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/vehicle">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Vehicle</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/station">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Station</span>
            </a>
        </li>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/employee/address">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Address</span>
            </a>
        </li>
        <%
        } else if (userRole.equals("ROLE_DRIVER")) {
        %>
        <li>
            <a class="nav-link text-white fs-5" href="/backend/driver/trip">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Trip</span>
            </a>
        </li>
        <%
            }
        } else {
        %>
        <%
            }
        %>


        <!-- Divider -->
        <hr >

        <!-- Heading -->
        <div class="text-muted">

            Addons
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link text-white fs-5" href="/backend/login-user">
                <i class="fas fa-fw fa-folder"></i>
                <span>Login</span>
            </a>
        </li>
        <!--    <li class="nav-item">
                <a class="nav-link text-white fs-5 "  href="/backend/logout">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Logout</span>
                </a>
            </li>-->
        <form action="/backend/logout-user" method="post">
            <!--<input type="submit" class="nav-link text-white fs-5 " value="Logout">-->
            <button class="nav-link text-white fs-5 btn" type="submit">
                <i class="fas fa-fw fa-folder"></i>
                Logout</button>
        </form>



        <!-- Nav Item - Charts -->
        <li >
            <a class="nav-link text-white fs-5" href="/backend/admin/create-account">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Create Account</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li >
            <!--<a class="nav-link" href="tables.html">-->
            <a class="nav-link text-white fs-5">

                <i class="fas fa-fw fa-table"></i>
                <span>Tables</span></a>
        </li>

        <!-- Divider -->
        <hr class=" d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class=" border-0 rounded-circle " >
                <i class="fa-solid fa-arrow-left"></i>
            </button>
        </div>
</ul>