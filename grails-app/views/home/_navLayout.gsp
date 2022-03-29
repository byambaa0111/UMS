<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 10/27/2020
  Time: 2:25 PM
--%>
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
        <li class="slide">
            <a class="side-menu__item"  data-toggle="slide" href="#">
                <i class="side-menu__icon fa fa-laptop"></i>
                <span class="side-menu__label">Dashboard</span><span class="badge badge-orange nav-badge">5</span>
            </a>
            <ul class="slide-menu">
                <li><a class="slide-item"  href="index.html"><span>Sales Dashboard </span></a></li>
                <li><a class="slide-item" href="index2.html"><span>Social Dashboard</span></a></li>
                <li><a class="slide-item" href="index3.html"><span>Marketing Dashboard</span></a></li>
                <li><a class="slide-item" href="index4.html"><span>IT Dashboard</span></a></li>
                <li><a class="slide-item" href="index5.html"><span>Crypto Currency </span></a></li>
            </ul>
        </li>
        <li>
            <a class="side-menu__item" href="widgets.html"><i class="side-menu__icon fe fe-grid"></i><span class="side-menu__label">Widgets</span></a>
        </li>
    </ul>
</aside>
<!--aside close-->