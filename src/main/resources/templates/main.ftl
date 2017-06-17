<#import '/spring.ftl' as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<#--<header>Главная страница</header>-->

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

    <script src="<@spring.url '/js/scripts.js'/>"></script>


</head>
<body style="background-image: url(<@spring.url '/images/mp_background.jpg'/>);">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><img
                        src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"/>
                </button>
                <p class="font_modal_header">Восстановление пароля</p>
                <hr>
            </div>
            <div class="modal-body" id="password_recovery_1">
                <p class="font_modal_text">Введите свое ФИО или адрес электронной почты, привязанный к профилю.
                </p>
                <div class="form-group">
                    <label class="sr-only sign_in_form" for="recover_email_value">Email</label>
                    <input class="modal_input_block font_placeholder_student_search" id="recover_email_value"
                           type="text" name="surname" placeholder="Email">
                </div>
            </div>
            <div class="modal-footer">
                <button class="modal_сonfirm_button font_student_search_button" onclick="recoverEmail()">Восстановить
                </button>
            </div>
            <div class="modal-body" style="display: none" id="password_recovery_2">
                <p class="font_modal_text_after_confirm_1">На ваш адрес электронной почты было отправлено письмо.</p>
                <p class="font_modal_text_after_confirm_2">В случае потери доступа к электронной почте обратитесь в
                    деканат. </p>
            </div>
        </div>
    </div>
</div>
<#include "registration_modal.ftl">


<!-- Modal End -->
<!-- Modal -->
<#--<div class="modal fade" id="myRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><img src="images/Modals_exit_button.png" alt="img"/>
                </button>
                <p class="font_modal_header">Регистрация</p>
                <hr>
            </div>
            <div class="modal-body" style="padding-top: 40px">
                <p class="font_modal_text_after_confirm_1">Такой пользователь уже существует.</p>
                <p class="font_modal_text_after_confirm_2">Проверьте указанный email на наличие письма с паролем для доступа в систему.  </p>
            </div>

        </div>
    </div>
</div>-->
<!-- Modal End -->


<div class="container">
    <div class="row form">

        <div class="col-md-6 col-md-offset-6 flex_row flex_justify_end">
        <#--<form action="<@spring.url '/login'/>" method="post" class="form-inline flexbox_aling_center" role="form">-->
        <#--<div class="form-group margin_left10px">-->
        <#--<label class="sr-only sign_in_form" for="email">Email</label>-->
        <#--<input type="email" class="form-control my_form_rules" id="email" name="email" placeholder="Логин">-->
        <#--</div>-->
        <#--<div class="form-group margin_left10px">-->
        <#--<label class="sr-only sign_in_form" for="password">Пароль</label>-->
        <#--<input type="password" class="form-control my_form_rules" id="password" name="password"-->
        <#--placeholder="Пароль">-->
        <#--<a href="#" class="light_blue_color forgot_password" onclick="show()" data-toggle="modal"-->
        <#--data-target="#myModal"-->
        <#--id="remember_password">Забыли?</a>-->
        <#--</div>-->

        <#--<div class="margin_left5px flexbox_aling_center">-->
        <#--<input id="sign-button" type="submit" value="" name="sign_in" width="25px;">-->
        <#--</div>-->

        <#--</form>-->
            <form action="/login" method="post" class="form-inline flexbox_aling_center" role="form">


            <#if ErrorMessageForReg??>
                <div class="already_exists " style="padding: 10px 10px; ">
                ${ErrorMessageForReg}
                </div>
            </#if>

                <div class="form-group margin_left10px" style="margin-left: 90px;">

                    <a href="#" class="light_blue_color forgot_password" onclick="show()" data-toggle="modal"
                       data-target="#myModal2"
                       id="remember_password"> Войти</a>
                </div>


            </form>


        </div>

    </div>
    <div class="row" id="logo">
        <div class="col-md-12"><img src="<@spring.url '/images/logo.png'/>" alt="img"/></div>
    </div>
    <div class="row" id="abbr_logo">
        <div class="col-md-12">
            <p id="font_abbr_logo">
                ВЫСШАЯ ШКОЛА ИНФОРМАЦИОННЫХ ТЕХНОЛОГИЙ И ИНФОРМАЦИОННЫХ СИСТЕМ
            </p>
        </div>
    </div>
    <div class="main_block_with_flexbox news_margin">
    <#if news??>
        <#list news as item>

            <#if (item?index == 3)><#break></#if>

            <a href="#" class="news_block">

                <#if item.upload1??>
                    <img class="news_img" src="<@spring.url '/uploads/news/${item.upload1}'/>" alt="img">
                <#else>
                    <img class="news_img" src="<@spring.url '/images/square_ph.png'/>" alt="img">
                </#if>

                <p id="font_news_header">${item.name}</p>
                <hr>
                <p id="font_news_text">
                    <#if (item.description?length >= 25) >
                    ${item.description?substring(0, 25)} ...
                    <#else>
                    ${item.description}
                    </#if>
                </p>
                <data class='font_news_time'>${item.datePublished.toLocalDate()}</data>
                <#assign time=item.datePublished.toLocalTime()/>
                <time class="font_news_time news_time">
                    <#if time.getHour() < 10>0</#if>
                ${time.getHour()}:
                    <#if time.getMinute() < 10>0</#if>${time.getMinute()}:
                    <#if time.getSecond() < 10>0</#if>${time.getSecond()}
                </time>
            </a>
        </#list>
    </#if>
    </div>
    <div class="row flexbox_aling_center" id="info">
        <img id="info-logo" src="<@spring.url '/images/info_logo.png'/>">
        <div class="flxbox_column">
            <p id='font_info_itis'>
                Высшая школа информационных технологий и информационных систем (ИТИС)
            </p>
            <p id="font_info">
                Адрес: 420008, Казань, ул. Кремлевская, 18.<br>
                Телефон: (843) 233-71-09<br>
                Факс: (843) 292-44-48<br>
                Адрес электронной почты: public.mail@kpfu.ru
            </p>
        </div>
    </div>
    <div class="row" id="footer">
        <div class="col-md-12" id='font_footer'>
            <hr id="footer_hr">
            <div class="flex_justify_space_between" id="footer_margin">
			        <span>
			        Казанский федеральный университет 2017 ©
			        </span>
                <span>
			        <a href="#" class="footer_link">ОБРАТНАЯ СВЯЗЬ</a>
			        <a href="#" class="footer_link">ТЕХНИЧЕСКАЯ ПОДДЕРЖКА</a>
			        <a href="#" class="footer_link">СОГЛАШЕНИЕ</a>
			        </span>
            </div>
        </div>
    </div>

</div>
<!-- container end -->


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
<script>
    function show() {
        $("#recover_email").show();
    }
    function loadMore() {
        $.ajax({
            url: "/main/load_more",
            type: "GET",
            success: function (news) {
                $("#news").append(news);
            }
        });
    }
    function recoverEmail() {
        $.ajax({
            url: "/main/forgot_password",
            data: {
                email: $("#recover_email_value").val()
            },
            type: "POST",
            success: function (text) {
                alert(text);
            }
        });
    }
</script>
</body>
</html>
