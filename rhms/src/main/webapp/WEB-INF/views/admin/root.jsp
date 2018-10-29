<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="tomorrow"
	value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>" />

<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="tomorrow" value="${tomorrow}" pattern="yyyy-MM-dd" />

<section id="admin-body">
	<section id="section-right" class="card">
		<ul class="list-group">

			<li class="list-group-item"><a
				href="<c:url value="/patients"/>?from=${currentDate}&to=${tomorrow}">
					Patients</a></li>

			<li class="list-group-item"><h6 class="text-info py-2">PatientVisits</h6></li>
			<c:forEach items="${doctors}" var="item">
				<li class="list-group-item"><a
					href="<c:url value="/patientVisits/doctors/"/>${item.id}?from=${currentDate}&to=${tomorrow}">
						${item.fullName}</a></li>
			</c:forEach>



		</ul>
	</section>

	<section id="main-content">

		<tiles:insertAttribute name="adminBody" />

	</section>

</section>