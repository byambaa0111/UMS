<%@ page import="com.ums.hr.Teacher" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <g:layoutHead/>
</head>

%{--
<div id="header">
    <g:applyLayout name="_headerLayout">
        <g:pageProperty name="page.header"/>
    </g:applyLayout>
</div>--}%

<body class="app">
        <!--Header Style -->
        <div class="wave -three"></div>
        <!--app open-->
        <div id="app" class="page">
            <div class="main-wrapper" >
                <!--nav open-->
                <nav class="navbar navbar-expand-lg main-navbar">
                    <a class="header-brand" href="index.html">
                        <asset:image rel="icon" src="brand/logo-white.png" class="header-brand-img" alt="Splite-Admin  logo" type="image/x-ico"/>
                    </a>
                    <form class="form-inline mr-auto">
                    <ul class="navbar-nav mr-2">
                        <li><a href="#" data-toggle="sidebar" class="nav-link nav-link toggle"><i class="fa fa-reorder"></i></a></li>
                        <li><a href="#" data-toggle="search" class="nav-link nav-link d-md-none navsearch"><i class="fa fa-search"></i></a></li>
                    </ul>
                    <div class="search-element mr-3">
                        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                        <span class="Search-icon"><i class="fa fa-search"></i></span>
                    </div>
                </form>
                    <ul class="navbar-nav navbar-right">

                    <li class="dropdown dropdown-list-toggle d-none d-lg-block "><a href="#" data-toggle="dropdown" class="nav-link  nav-link-lg "><i class=" fa fa-flag-o "></i></a>
                        <div class="dropdown-menu dropdown-menu-lg  dropdown-menu-right">
                            <navBar:localeDropdownListItems uri="${request.forwardURI}"/>
                        </div>
                    </li>

                    <li class="dropdown dropdown-list-toggle"><a href="#" data-toggle="dropdown" class="nav-link  nav-link-lg "><span class="badge badge-secondary nav-link-badge">6</span><i class="fa fa-bell-o"></i></a>
                        <div class="dropdown-menu dropdown-list dropdown-menu-right">
                            <div class="dropdown-header">Notifications
                                <div class="float-right">
                                    <a href="#" class="text-white">View All</a>
                                </div>
                            </div>
                            <div class="dropdown-list-content">
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-users text-primary"></i>
                                    <div class="dropdown-item-desc">
                                        <b>So many Users Visit your template</b>
                                    </div>
                                </a>
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-exclamation-triangle text-danger"></i>
                                    <div class="dropdown-item-desc">
                                        <b>Error message occurred....</b>
                                    </div>
                                </a>
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-users text-warning"></i>
                                    <div class="dropdown-item-desc">
                                        <b> Adding new people</b>
                                    </div>
                                </a>
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-shopping-cart text-success"></i>
                                    <div class="dropdown-item-desc">
                                        <b>Your items Arrived</b>
                                    </div>
                                </a>
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-comment text-primary"></i>
                                    <div class="dropdown-item-desc">
                                        <b>New Message received</b> <div class="float-right"><span class="badge badge-pill badge-danger badge-sm">67</span></div>
                                    </div>
                                </a>
                                <a href="#" class="dropdown-item">
                                    <i class="fa fa-users text-primary"></i>
                                    <div class="dropdown-item-desc">
                                        <b>So many Users Visit your template</b>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown dropdown-list-toggle d-none d-lg-block">
                        <a href="#" class="nav-link nav-link-lg full-screen-link">
                            <i class="fa fa-expand " id="fullscreen-button"></i>
                        </a>
                    </li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg d-flex">
                        <span class="mr-3 mt-2 d-none d-lg-block ">
                            <span class="text-white">Hello,<span class="ml-1">${session['sysUser']} </span></span>
                        </span>
                        <span> <asset:image rel="icon"  src="avatar/avatar-3.jpeg" alt="profile-user" class="rounded-circle w-32 mr-2"/></span>
                    </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <div class=" dropdown-header noti-title text-center border-bottom pb-3">
                                <h10 class="text-capitalize text-dark mb-1">Систем групп</h10><br>
                                <small class="text-overflow m-0">${session['sysUserGroup']}</small>
                            </div>
                            <a class="dropdown-item" href="<g:createLink controller="teacher" action="profile" params="[id:com.ums.hr.Teacher.findByTeacherCode(session['sysUser']?.userId)?.id]"/>"><i class="mdi mdi-account-outline mr-2"></i> <span>My profile</span></a>
                            <a class="dropdown-item" href="#"><i class="mdi mdi-settings mr-2"></i> <span>Settings</span></a>
                            %{--<a class="dropdown-item" href="#"><i class="fe fe-calendar mr-2"></i> <span>Activity</span></a>--}%
                            <a class="dropdown-item" href="#"><i class="mdi mdi-compass-outline mr-2"></i> <span>Support</span></a>
                            <div class="dropdown-divider"></div> <g:link controller="login" action="logout" class="dropdown-item" > <i class="mdi  mdi-logout-variant mr-2"></i> <span>Logout</span></g:link>
                        </div>
                    </li>
                </ul>
                </nav>
                <!--aside open-->
                <aside class="app-sidebar">
                    <div class="app-sidebar__user">
                        <div class="dropdown user-pro-body text-center">
                            <div class="nav-link pl-1 pr-1 leading-none ">
                                <asset:image src="avatar/avatar-3.jpeg" alt="user-img" class="avatar-xl rounded-circle mb-1"/>
                                <span class="pulse bg-success" aria-hidden="true"></span>
                            </div>
                            <div class="user-info">
                                <h6 class=" mb-1 text-dark">Byambajargal</h6>
                                <span class="text-muted app-sidebar__user-name text-sm"> Web-Developer</span>
                            </div>
                        </div>
                    </div>

                    <ul class="side-menu">
                        <g:each  in="${session.menuList}" status="i" var="topMenu">
                        <li class="slide">

                            <a class="side-menu__item"  data-toggle="slide" href="#"><i class="side-menu__icon fa fa-laptop"></i>
                                <span class="side-menu__label">${topMenu.menuName}</span><span class="badge badge-orange nav-badge">${topMenu.subMenuList.size()}</span>
                            </a>
                            <ul class="slide-menu">
                            <g:each in="${topMenu.subMenuList}" status="j" var="subMenu">
                                <g:if test="${!subMenu.isHidden}">
                                    <li><a class="slide-item"  href="${createLink(controller:subMenu.controlName, action:subMenu.actionName)}"><span>${subMenu.menuName}</span></a></li>
                                </g:if>
                            </g:each>
                            </ul>
                        </li>
                        </g:each>

                    </ul>
                </aside>

                <!--aside close-->
                <!--app-content open-->
                <div class="app-content">
                    <g:layoutBody/>
                </div>
                <!--app-content close-->
                <a href="#top" id="back-to-top" ><i class="fa fa-angle-up"></i></a>
                <!--Footer-->

                <footer class="main-footer"></footer>
                <!--Footer close-->
                <!-- Popupchat open-->
                <div class="popup-box chat-popup" id="qnimate"></div>
                <!-- Popupchat close-->

            </div>
        </div>


</body>
</html>
