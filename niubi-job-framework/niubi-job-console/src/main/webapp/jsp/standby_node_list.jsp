<%--

    Copyright 2002-2015 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 
--%>
<%--
  User: Xiaolong Zuo
  Time: 1/15/2016 11:39
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="list_head.jsp"/>
</head>
<body>
<jsp:include page="dashboard_header.jsp"/>
<jsp:include page="dashboard_top.jsp"/>
<jsp:include page="standby_dashboard_sidebar.jsp">
    <jsp:param name="standbyNodes" value="active"/>
</jsp:include>
<jsp:include page="standby_node_list_container.jsp"/>
<jsp:include page="dashboard_footer.jsp"/>
<jsp:include page="list_bottom.jsp"/>
</body>
</html>
