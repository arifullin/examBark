<#import '/spring.ftl' as spring/>

<#macro mainLayout header="">
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <#--<header>${header}</header>-->

    <!-- Bootstrap -->
    <link href="<@spring.url '/css/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Main style -->
    <link href="<@spring.url '/css/style_for_all_pages.css'/>" rel="stylesheet">

    <!-- Fonts style -->
    <link href="<@spring.url '/css/fonts_style.css'/>" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script src="<@spring.url '/js/jquery-1.11.1.min.js'/>"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<@spring.url '/css/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- plus and minus for certificate -->
    <script type="text/javascript" src="<@spring.url '/js/plus_minus.js'/>"></script>

    <!-- allot all checkboxes in table -->
    <script type="text/javascript" src="<@spring.url '/js/allot_checkboxes.js'/>"></script>

    <!-- hide and show buttons on edit group -->
    <script type="text/javascript" src="<@spring.url '/js/edit_group.js'/>"></script>

    <script type="text/javascript" src="<@spring.url '/js/spoiler.js'/>"></script>

    <script type="text/javascript" src="<@spring.url '/js/cursor.js'/>"></script>

    <script type="text/javascript" src="<@spring.url '/js/extender.js'/>"></script>

    <script type="text/javascript" src="<@spring.url '/js/scripts.js'/>"></script>


    <style type="text/css">
        .hide_block{
            display: none;
        }
    </style>

</head>
<body>
    <div class="container">
        <#include "include/header.ftl">
        <#include "include/navbar.ftl">
        <#nested>
        <#include "include/info.ftl">
        <#include "include/footer.ftl">
    </div>
</body>
</#macro>