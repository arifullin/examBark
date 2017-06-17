<#import '/spring.ftl' as spring/>

<!-- Menu -->
<div class="row" >
    <div class="col-md-12">
        <nav id="menu" class="flex_justify_space_between flexbox_aling_center">
            <div id="font_menu" class="flex_row flex_align_stretch">
                <div class="navbar_elem" onmouseover="navbarHover()">
                    <a href="<@spring.url '/news'/>" class="button_effect menu_link" style="padding-left: 20px;">НОВОСТИ</a>
                </div>
                <div class="navbar_elem" onmouseover="navbarHover()">
                    <a href="<@spring.url '/events'/>" class="button_effect menu_link">СОБЫТИЯ</a>
                </div>
                <div class="navbar_elem" onmouseover="navbarHover()">
                    <a href="<@spring.url '/groups'/>" class="button_effect menu_link">СООБЩЕСТВА</a>
                </div>
                <div class="navbar_elem" onmouseover="navbarHover()">
                    <a href="<@spring.url '/projects'/>" class="button_effect menu_link">ПРОЕКТЫ</a>
                </div>
            </div>

            <div id="font_menu_username">
                <a href="<@spring.url '/profile'/>" class="flex_row flexbox_aling_center">
                    <#if Session.user??>
                        <span>${Session.user.name} ${Session.user.surname}</span>
                        <div id="message_circle"><#if Session.notifyCount??>${Session.notifyCount}<#else>0</#if></div>
                    <#else>
                        <span>Пользователя нет)</span>
                        <div id="message_circle">0</div>
                    </#if>

                </a>
            </div>
        </nav>
    </div>
</div>
<!-- Menu end -->