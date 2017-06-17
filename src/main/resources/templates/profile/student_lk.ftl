<#import '/spring.ftl' as spring/>

<#include "../layout.ftl">

<@mainLayout header="Student Profile">
<div class="main_block_with_flexbox flex_justify_space_between">
    <div>

        <!-- collapse start -->
        <div class="personal_area_block font_worker_search_students_and_groups_header">

            <a role="button" data-toggle="collapse" href="#timetable" aria-expanded="false"
               aria-controls="collapseExample">
                <div class="flex_row flexbox_aling_center flex_justify_space_between white_background_color shadow schedule_cursor_header">
                    <div class="padding6px10px">
                        <span>Органайзер</span>
                    </div>
                    <div class="schedule_cursor closed">
                        <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" width="20px" height="11px"
                             class="margin_right10px">
                    </div>
                </div>
            </a>

        </div>
        <!-- collapse open block start -->
        <#--TODO include organizer-->
        <#include "organizer/student_organizer.ftl"/>
        <!-- collapse open block end -->
        <!-- collapse end -->


        <!-- collapse start -->
        <div class="personal_area_block font_worker_search_students_and_groups_header margin_top20px margin_bottom20px">

            <a role="button" data-toggle="collapse" href="#groups_adn_projects" aria-expanded="false"
               aria-controls="collapseExample">
                <div class="flex_row flexbox_aling_center white_background_color flex_justify_space_between shadow group_cursor_header">
                    <div class="padding6px10px" style="width: 150px;">
                        Сообщества
                    </div>
                    <div>
                        Проекты
                    </div>
                    <div class="group_cursor closed">
                        <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" width="20px" height="11px"
                             class="margin_right10px">
                    </div>
                </div>
            </a>

        </div>

        <!-- collapse open block start -->
        <div class="collapse font_group_project_name margin_bottom20px" style="margin-top: -20px;"
             id="groups_adn_projects">
            <div class="well flex_row flex_justify_space_between blue_background_rgba_color personal_area_block border_radius_none">

                <div>
                    <#if communities??>
                        <#list communities as community>
                            <a href="<@spring.url '/group/${community.id}'/>">
                                <div class="personal_area_min_block flex_row shadow">
                                    <#if community.avatarUrl??>
                                        <img src="<@spring.url '/uploads/Community_Avatar/${community.avatarUrl}'/>"
                                             class="personal_area_min_block_img">
                                    <#else>
                                        <img src="<@spring.url '/images/standart_ph.png'/>" class="personal_area_min_block_img">
                                    </#if>
                                    <p class="padding10px">
                                    ${community.name}
                                    </p>
                                </div>
                            </a>
                        </#list>
                    </#if>
                </div>

                <div>
                    <#if projects??>
                    <#list projects as pr>
                        <a href="<@spring.url '/project/${pr.id}'/>">
                            <div class="personal_area_min_block flex_row shadow">
                                <#if pr.avatarUrl??>
                                    <img src="<@spring.url '/uploads/Project_Avatar/${pr.avatarUrl}'/>"
                                         class="personal_area_min_block_img">
                                <#else>
                                    <img src="<@spring.url '/images/standart_ph.png'/>" class="personal_area_min_block_img">
                                </#if>
                                <p class="padding10px">
                                ${pr.projectName}
                                </p>
                            </div>
                        </a>
                    </#list>
                    </#if>

                </div>

            </div>
        </div>
        <!-- collapse open block end -->
        <!-- collapse end -->

    </div>

    <aside class="aside_personal_area_main_block font_aside_personal_area">
        <div>
            <a href="#" class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color"
               data-toggle="modal" data-target="#edit_student_modal">
                <img src="<@spring.url '/images/profile.png'/>" class="aside_personal_area_img">
                <span>Редактировать профиль</span>
            </a>
        </div>
        <div>
            <a href="#" data-toggle="modal" data-target="#referenceModal"
               class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color">
                <img src="<@spring.url '/images/certificate.png'/>" class="aside_personal_area_img">
                <span>Получить справку</span>
            </a>
        </div>
        <div>
            <a href="<@spring.url '/logout'/>" class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color">
                <img src="<@spring.url '/images/sign_out.png'/>" class="aside_personal_area_img">
                <span>Выход</span>
            </a>
        </div>

        <!-- notification -->

        <#if notifications?? && notifications?size != 0>

            <div class="notification_scrolbox">
                <div class="notification_scrolbox-outside">
                    <#list notifications as notif>
                        <div id="notif_${notif?index}" class="blue_background_rgba_color padding10px margin_bottom20px">
                            <!--             <div class="line"></div> -->
                            <p class="font_notification_subject_and_data">
                            ${notif.title}
                            </p>

                            <p class="font_notification_text">
                            ${notif.body}
                            </p>

                            <div style="display: inline-flex">
                                <#if notif.deadline?? && notif.time??>
                                    <span class="font_notification_subject_and_data">
                                    до
                                    </span>

                                <#-- Chnaged at 21:02, 23th May by Ramil-->
                                    <time class="font_notification_subject_and_data">${notif.time.toString()}</time>

                                    <data class="font_notification_subject_and_data">${notif.deadline.toString()}</data>

                                    <button onclick="ConfirmNotificationStudent(${notif?index}, ${notif.id}, ${userId})"
                                            type="submit"
                                            class="mini_button_notification font_student_search_button">
                                        Готово
                                    </button>
                                <#else>
                                    <button id="notif_${notif?index}"
                                            onclick="ConfirmNotificationStudent(${notif?index}, ${notif.id}, ${userId})"
                                            type="submit"
                                            class="mini_button_notification font_student_search_button">
                                        Готово
                                    </button>
                                </#if>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </#if>
        <#include "modals/profile_edit_modal.ftl">
        <#include "modals/certificate_modal.ftl">
    </aside>
</div>
</@mainLayout>