<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default navbar-static-top">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/">Tickets</a>
	</div>
	<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="/">Home</a></li>
			<li><a href="/matchList">Matches</a></li>
		</ul>
        <sec:authorize access="isAuthenticated()">

        </sec:authorize>
		<div class="navbar-form navbar-right">
			<sec:authorize access="isAnonymous()">
 				<a id="logIn" class="btn btn-info" href="/login">Log In</a>
				<a id="signUp" class="btn btn-default" href="/signup">Sign Up</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/cart">Shopping Cart</a></li>
                </ul>
				<label>Hello <sec:authentication property="principal.username"/></label>
				<a id="logout" class="btn btn-danger" href="/logout">Logout</a>
			</sec:authorize>
		</div>
	</div>
</div>