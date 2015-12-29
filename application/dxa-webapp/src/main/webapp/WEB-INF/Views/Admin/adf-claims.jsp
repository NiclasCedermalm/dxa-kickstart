<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
         import="com.tridion.ambientdata.*, com.tridion.ambientdata.claimstore.*, java.net.*, java.util.*"%>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Claims</title>
    <link rel="stylesheet" href="${markup.versionedContent('/assets/css/main.css')}" type="text/css"/>
    <script src="${markup.versionedContent('/assets/scripts/header.js')}"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${markup.versionedContent('/assets/scripts/ie.js')}"></script>
    <![endif]-->
</head>
<body>
<main class="page-row page-row-expanded" role="main">
   <div class="container-fluid page-border">

       <div class="list-group">


    <%!
        public String convertToString(Object object) {
            String result = object.toString();
            if (object instanceof Collection) {
                result = "";
                for (Object o : (Collection)object) {
                    result += o.toString() + "<br/>";
                }

            }
            if (object instanceof String[]) {
                result = "";
                for (Object o : (String[])object) {
                    result +=o.toString() + "<br/>";
                }
            }
            if (object instanceof Map) {
                result = "";
                for (String key : ((Map<String, Object>)object).keySet()) {
                    Object value = ((Map<String, Object>)object).get(key);
                    if (value instanceof String[]) {
                        result += arrayToString((String[])value) + "<br/>";
                    } else if (value instanceof String) {
                        result += key + " = " + value + "<br/>";
                    }
                }

            }
            return result;
        }

        private String arrayToString(String[] array) {
            String result = "";
            for (String s : array) {
                result += " " + s;
            }
            return result;
        }


    %>

    <%
        ClaimStore store = AmbientDataContext.getCurrentClaimStore();
        if (store != null) {
            Map<URI, Object> claims = store.getAll();
            for (URI claim : claims.keySet()) {
                Object claimValue = claims.get(claim);

                out.println("<a href=\"#\" class=\"list-group-item\">");
                out.println("<h4 class=\"list-group-item-heading\">" + claim.toString() + "</h4>");
                out.println("<p class=\"list-group-item-text\">");
                out.println(convertToString(claims.get(claim)));
                out.println("</p></a>");
            }
        } else {
            out.println("no claimstore");
        }
    %>
    </div>
   </div>
</main>

<script src="${markup.versionedContent('/assets/scripts/main.js')}"></script>
</body></html>
