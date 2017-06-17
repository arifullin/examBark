<#include "../layout.ftl">
<#import "/spring.ftl" as spring />
<@mainLayout header="">
    <#if community??>
    <form action="<@spring.url '/group/update/${community.id}'/>" method="post" enctype="multipart/form-data"
          id="edit_project">
        <div class="shadow">
            <div id="one_group_header_img" style="width: 980px; height: 150px; overflow: hidden;">
                <#if community.avatarUrl??>
                    <div style="width: 980px; height: 150px; overflow: hidden;">
                        <img src="<@spring.url '/uploads/Community_Avatar/${community.avatarUrl}'/>"
                             id="one_group_main_img">
                    </div>
                <#else>
                    <img src="<@spring.url '/images/square_ph.png'/>" id="one_group_main_img">
                </#if>
                <div style="margin-top: -50px; margin-left: 21px;" class="hide_block button_effect">
                    <label class="mini_button fileContainer">
                        Добавить фото
                        <@spring.formInput "projectForm.avatar" "" "file"/>
                    </label>
                </div>
            </div>

            <div class="flex_row flex_justify_space_between flexbox_aling_center" id="one_group_header">

                <div class="font_one_page_header">
                    <span class="edit_block">${community.name}</span>
                    <@spring.formInput "projectForm.name" "class='hide_block'" "form='edit_project'"></@spring.formInput>
                    <#if privilege??>
                        <#if privilege=='ADMIN'||privilege=='LEADER'>
                            <button type="button" class="close" id="edit">
                                <img src="<@spring.url '/images/edit_pensil.png'/>" alt="img" class="edit_block"
                                     style="margin-left: 30px;"/>
                            </button>
                        </#if>
                    </#if>
                </div>
            <#--<div class="hide_block">-->
            <#--<button class="event_button one_group_header_button font_button_checkbox_radio" type="submit" form="edit_project">-->
            <#--Сохранить-->
            <#--</button>-->
            <#--</div>-->
                <#include "participants_modal.ftl">

                <div class="flex_row">
                    <a href="#" class="button_effect flex_row" data-toggle="modal" data-target="#usersModal">
                        <div id="font_event_participants">
                        ${user_counter}
                        </div>
                        <img src="<@spring.url '/images/Events_Page_participants.png'/>" class="event_participants_img">
                    </a>
                    <#if isSubscribed!=true>
                        <button onclick="location.href='/group/${community.id}/subscribe'" formmethod="post"
                                type="button"
                                class="event_button one_group_header_button button_effect font_button_checkbox_radio edit_block">
                            Вступить
                        </button>
                    <#else>
                        <button onclick="location.href='/group/${community.id}/unsubscribe'" formmethod="post"
                                type="button"
                                class="event_button one_group_header_button button_effect font_button_checkbox_radio edit_block">
                            Выйти
                        </button>
                    </#if>
                    <button class="event_button one_group_header_button font_button_checkbox_radio hide_block button_effect"
                            type="submit" form="edit_project">
                        Сохранить
                    </button>
                </div>

            </div>
        </div>
        <div class="flex_justify_space_between">
            <div>
                <div id="one_group_direction" class="event_block border_radius_none shadow_none font_group_direction">

                    <@spring.formTextarea "projectForm.description" "class='modal-textarea hide_block' style='width: 100%; height: 200px;display: none;' " ></@spring.formTextarea>


                    <span class="edit_block">
                        <#if community.description??>
                            ${community.description}
                        </#if>
                </span>
                <#--<@spring.formInput "projectForm.avatar" "" "file"/>-->
                </div>
                <h1>Новости</h1>
                <#if privilege??>
                    <#if privilege=="ADMIN"||privilege=="LEADER">
                        <#include "news_add_form.ftl"/>
                    </#if>
                </#if>
                <div id="project_news">

                    <#if community.news?? && community.news?size gt 0>
                        <#list community.news as ev>
                            <div class="event_block border_radius_none" id="pr_news_${ev.id}">
                                <div id="event_header" class=" flex_row flex_justify_space_between">

                                    <div class="flex_row">
                                        <p class="font_event_header">${ev.name}
                                        </p>
                                        <#if ev.tags??>
                                            <#list ev.tags as t>
                                                <p class="font_events_tag">${t.name}
                                                </p>
                                            </#list>
                                        </#if>
                                        <div class="hide_block">
                                        <#-- <input type="checkbox" id="main_page2" class="checkbox hide_block">
                                            <label class="checkbox-label chb-label-font margin_left20px hide_block"
                                                   id="font_tag_box"
                                                   for="main_page2">
                                                на главную
                                            </label>-->
                                        </div>
                                    </div>
                                    <div class="hide_block">
                                        <button type="button" class="close" style="display: block; right: 0px;"
                                                onclick="deleteNews(${community.id},${ev.id})">
                                            <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"
                                                 style="margin-right: 5px;"/>
                                        </button>
                                    </div>
                                </div>
                                <hr class="event_hr">
                                <div class="font_events_text group_text">
                                    <#if ev.description??>${ev.description}</#if>
                                </div>
                                <div>
                                    <div class="flex_column">
                                        <!-- <div class="flex_column"> -->
                                    <#-- <div class="spoiler-body font_events_text" style="margin-right: 50px;">
                                         Краткое описание новости. Краткое описание новости. Краткое описание
                                         новости.
                                         Краткое
                                         описание новости. Краткое описание новости. Краткое описание новости.
                                         Краткое
                                         описание
                                         новости. Краткое описание новости
                                     </div>-->
                                        <div class="flex_justify_space_between flexbox_aling_center margin_top10px">
                                        <#-- <div class="spoiler-header closed font_spoiler_cl_op">
                                             <img src="<@spring.url 'images/arrow_open.png'/>" alt="open"
                                                  style="margin-right:5px;">
                                             <span>
                                 развернуть
                                 </span>
                                         </div>-->
                                            <div class="flex_justify_end">
                                    <span id="font_event_author">${ev.author.name} ${ev.author.surname}
                                    </span>
                                                <data class='font_event_author_time'>
                                                ${ev.getDate}
                                                </data>
                                                <time class='font_event_author_time'>
                                                ${ev.getTime}
                                                </time>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="event_block_images flex_justify_space_between ">
                                    <#if ev.uploadUrl1??>
                                        <img src="<@spring.url '/uploads/news/${ev.uploadUrl1}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                    <#if ev.uploadUrl2??>
                                        <img src="<@spring.url '/uploads/news/${ev.uploadUrl2}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                    <#if ev.uploadUrl33??>
                                        <img src="<@spring.url '/uploads/news/${ev.uploadUrl3}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <div id="no_news">
                            В сообществе пока нет новостей
                        </div>
                    </#if>
                </div>
                <h1>События</h1>
                <#if privilege??>
                    <#if privilege=="ADMIN"||privilege=="LEADER">
                        <#include "event_add_form.ftl">
                    </#if>
                </#if>
                <div id="project_events">
                    <#if community.events?? && community.events?size gt 0>
                        <#list community.events as ev>
                            <div class="event_block border_radius_none" id="pr_ev_${ev.id}">
                                <div id="event_header" class=" flex_row flex_justify_space_between">
                                    <div class="flex_row">
                                        <p class="font_event_header">${ev.title}
                                        </p>
                                        <#if ev.tags??>
                                            <#list ev.tags as t>
                                                <p class="font_events_tag">${t.name}
                                                </p>
                                            </#list>
                                        </#if>
                                        <div class="hide_block">
                                            <input type="checkbox" id="main_page2" class="checkbox">
                                            <label class="checkbox-label chb-label-font margin_left20px"
                                                   id="font_tag_box"
                                                   for="main_page2">
                                                на главную
                                            </label>
                                        </div>
                                    </div>
                                    <div class="hide_block">
                                        <button type="button" class="close" style="display: block; right: 0px;"
                                                onclick="deleteEvent(${community.id},${ev.id})">
                                            <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"
                                                 style="margin-right: 5px;"/>
                                        </button>
                                    </div>
                                </div>
                                <hr class="event_hr">
                                <div class="flex_row flex_justify_space_between">
                                    <div class="flexbox_aling_center">
                            <span id="font_event_time">
                            ${ev.getStartedTime()}
                            </span>
                                        <span>
                                <data class="font_event_data">${ev.getStartedDate()}</data>
                                <div class="font_event_data">${ev.address}</div>
                            </span>
                                    </div>
                                    <div class="flex_row">
                                        <div id="font_event_participants">
                                        ${ev.getUsersCount()}
                                        </div>
                                        <img src="<@spring.url '/images/Events_Page_participants.png'/>"
                                             class="event_participants_img">
                                        <button type="button" class="event_button font_button_checkbox_radio"
                                                onclick="subscribeEvent(${ev.id})" id="event_subscribe${ev.id}">
                                            <#if ev.isSubscribed()>
                                                Не участвовать
                                            <#else >
                                                Участвовать
                                            </#if>
                                        </button>
                                    </div>
                                </div>
                                <p class="font_events_text"><#if ev.description??>${ev.description}</#if></p>
                                <div class="flex_justify_end">
                        <span id="font_event_author"><#if ev.author??>${ev.author.name} ${ev.author.surname}</#if>
                        </span>
                                    <data class='font_event_author_time'>
                                    ${ev.getCreatedDate()}
                                    </data>
                                    <time class='font_event_author_time'>
                                    ${ev.getCreatedTime()}
                                    </time>
                                </div>
                                <div class="event_block_images flex_justify_space_between">
                                    <#if ev.uploadUrl1??>
                                        <img src="<@spring.url '/uploads/events/${ev.uploadUrl1}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                    <#if ev.uploadUrl2??>
                                        <img src="<@spring.url '/uploads/events/${ev.uploadUrl2}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                    <#if ev.uploadUrl33??>
                                        <img src="<@spring.url '/uploads/events/${ev.uploadUrl3}'/>" class="event_img"
                                             alt="img">
                                    <#else>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else >
                        <div id="no_events">
                            В сообществе пока нет событий
                        </div>
                    </#if>
                </div>
            </div>

            <aside>

                <div class="shadow one_group_right_block">
                    <div class="flex_justify_space_between flexbox_aling_start" style="margin: auto 3px;">
                        <p class="font_aside_header">
                            Администрация
                        </p>
                        <button type="button" class="close hide_block">
                            <img src="<@spring.url '/images/add_button.png'/>" alt="img"/>
                        </button>
                    </div>
                    <hr>
                    <#if community.admins?? && community.admins?size gt 0>
                        <#list community.admins as adm>
                            <a href="#"
                               class="flex_row button_effect flexbox_aling_center flex_justify_space_between one_group_participant_info_block">
                                <div class="flexbox_aling_center">
                                    <img src="<@spring.url '/images/small_ph.png'/>" class="one_group_participant_img">
                                    <span class="font_aside_black_text">
                                    ${adm.name} ${adm.surname}
                            </span>
                                </div>
                                <button type="button" class="close hide_block">
                                    <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"
                                         style="margin-right: 5px;"/>
                                </button>
                            </a>
                        </#list>
                    <#else>
                        <a href="#"
                           class="flex_row button_effect flexbox_aling_center flex_justify_space_between one_group_participant_info_block">
                            <div class="flexbox_aling_center">
                                <span class="font_aside_black_text">
                            У сообщества нет администраторов
                                </span>
                            </div>
                        </a>
                    </#if>
                </div>

            <#--<div class="shadow one_group_right_block">-->
            <#--<p class="font_aside_header">-->
            <#--Документы-->
            <#--</p>-->
            <#--<hr style="margin-bottom: 10px;">-->
            <#--<a href="#"-->
            <#--class="one_group_doc_min_block button_effect font_aside_blue_text flex_justify_space_between flexbox_aling_center">-->
            <#--<span>-->
            <#--Название документа-->
            <#--</span>-->
            <#--<img src="../images/dl_cursor.png" width="13px" height="13px">-->
            <#--</a>-->
            <#--</div>-->
                <#if privilege == "LEADER">
                    <a href="<@spring.url '/community/${community.id}/delete'/>"
                       class="font_aside_blue_text flexbox_aling_center button_effect flex_justify_center">
                        Удалить сообщество
                    </a>
                </#if>
            </aside>
        </div>
    </form>
    </#if>
<script src="<@spring.url '/js/edit_group.js"'/>"></script>
<script src="<@spring.url '/js/communities.js">'/>"></script>
<script src="<@spring.url '/js/event.js'/>"></script>
</@mainLayout>