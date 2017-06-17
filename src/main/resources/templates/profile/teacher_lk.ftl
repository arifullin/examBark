<#import '/spring.ftl' as spring/>

<#include "../layout.ftl">

<@mainLayout header="Teacher Profile">
<div class="main_block_with_flexbox">
    <div>

        <!-- collapse start -->
        <div class="personal_area_block font_worker_search_students_and_groups_header">

            <a role="button" data-toggle="collapse" href="#timetable" aria-expanded="false"
               aria-controls="collapseExample">
                <div class="flex_row flexbox_aling_center white_background_color flex_justify_space_between shadow schedule_cursor_header">
                    <div class="padding6px10px">
                        Органайзер
                    </div>
                    <div class="schedule_cursor closed">
                        <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" width="20px" height="11px"
                             class="margin_right10px">
                    </div>
                </div>
            </a>

        </div>

        <#include "organizer/teacher_organizer.ftl"/>

        <!-- collapse open block start -->
        <div class="collapse font_group_project_name" id="timetable">
            <div class="well flex_row flex_justify_space_between blue_background_rgba_color personal_area_block border_radius_none">
                <div style="height: 300px;"></div>
            </div>
        </div>
        <!-- collapse open block end -->
        <!-- collapse end -->

        <!-- collapse start -->
        <div class="personal_area_block font_worker_search_students_and_groups_header margin_top20px">

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
        <div class="collapse font_group_project_name" id="groups_adn_projects">
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
                                        <img src="<@spring.url '/images/standart_ph.png'/>"
                                             class="personal_area_min_block_img">
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
                <#-- Show projects -->
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
                </div>
            </div>
        </div>
        <!-- collapse open block end -->
        <!-- collapse end -->

        <!-- collapse start -->
        <div class="personal_area_block font_worker_search_students_and_groups_header margin_top20px margin_bottom20px">
            <a role="button" data-toggle="collapse" href="#students_search" aria-expanded="false"
               aria-controls="collapseExample">
                <div class="flexbox_aling_center white_background_color flex_justify_space_between shadow search_cursor_header">
                            <span class="padding6px10px">
                            Поиск студентов
                            </span>
                    <div class="search_cursor closed">
                        <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" width="20px" height="11px"
                             class="margin_right10px">
                    </div>
                </div>
            </a>
        </div>
        <!-- collapse open block start -->
        <div class="collapse" style="margin-top: -20px;" id="students_search">
            <div class="well">
                <!-- white collapse block start -->
                <div class="hood white_background_color shadow">
                    <div class="flex_row flex_justify_space_between">
                        <div class="font_placeholder_student_search">
                            <div class="styled-select">
                                <select id="course" class="input_block ">
                                    <option selected>all</option>
                                    <#if courses??>
                                        <#list courses as course>
                                            <option>${course}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="font_placeholder_student_search">
                            <div class="styled-select">
                                <select id="course" class="input_block ">
                                    <option selected>all</option>
                                    <#if courses??>
                                        <#list courses as course>
                                            <option>${course}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <button id="searchBtn" class="mini_button font_student_search_button">
                            Найти
                        </button>
                    </div>

                    <!-- extended collapse start  -->
                    <div class="font_spoiler_cl_op" style="padding-top: 8px;">
                        <a class="extendable" role="button" data-toggle="collapse"
                           href="#advanced_search" aria-expanded="false" aria-controls="collapseExample">
                            <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" style="margin-right:5px;"> расширенный
                        </a>
                        <!-- collapse open block start -->
                        <div class="collapse" id="advanced_search" style="margin-top: 8px;">
                            <div class="well flex_row white_background_color" style="height: 170px;">
                                <div class="flex_column">
                                    <div class="form-group">
                                        <label class="sr-only sign_in_form" for="surname">Фамилия</label>
                                        <input class="input_block font_placeholder_student_search" id="surname"
                                               type="text" name="surname" placeholder="Фамилия">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only sign_in_form" for="name">Имя</label>
                                        <input class="input_block font_placeholder_student_search" id="name" type="text"
                                               name="name" placeholder="Имя">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only sign_in_form" for="patronymic">Отчество</label>
                                        <input class="input_block font_placeholder_student_search" type="text"
                                               id="patronymic" name="patronymic" placeholder="Отчество">
                                    </div>

                                    <div class="flex_row font_button_checkbox_radio">
                                        <div class="flex_column margin_right20px">
                                            <div class="flex_row margin_bottom_10px">
                                                <input type="checkbox" name="m" value="Мужской" id="m">
                                                <label class="checkbox-label chb-label-font" for="m">Мужской</label>

                                            </div>

                                            <div class="flex_row">
                                                <input type="checkbox" name="w" value="Женский" id="w">
                                                <label class="checkbox-label chb-label-font" for="w">Женский</label>

                                            </div>

                                        </div>
                                        <div class="flex_column">
                                            <div class="flex_row margin_bottom_10px">
                                                <input type="checkbox" name="budget" value="Бюджет" id="budget">
                                                <label class="checkbox-label chb-label-font" for="budget">Бюджет</label>

                                            </div>

                                            <div class="flex_row">
                                                <input type="checkbox" name="contract" value="Контракт" id="contract">
                                                <label class="checkbox-label chb-label-font"
                                                       for="contract">Контракт</label>

                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-left: 12px;" class="flex_column flex_justify_space_between">

                                    <input type="checkbox" name="captain" value="староста" id="captain"
                                           style="margin-right: 10px;">
                                    <label class="checkbox-label chb-label-font" for="captain">Староста</label>

                                    <input type="checkbox" name="captain2" value="профорг" id="captain2">
                                    <label class="checkbox-label chb-label-font" for="captain2">Профорг</label>

                                    <input type="checkbox" name="captain3" value="спорторг" id="captain3">
                                    <label class="checkbox-label chb-label-font" for="captain3">Спорторг</label>

                                    <input type="checkbox" name="captain4" value="соцорг" id="captain4">
                                    <label class="checkbox-label chb-label-font" for="captain4">Соцорг</label>

                                    <input type="checkbox" name="captain5" value="культорг" id="captain5">
                                    <label class="checkbox-label chb-label-font" for="captain5">Культорг</label>

                                </div>
                            </div>
                        </div>
                        <!-- collapse open block end  -->
                    </div>
                    <!-- extended collapse end -->
                </div>
                <!-- white collapse block end -->

                <!-- table start -->
                <div class="blue_background_rgba_color personal_area_block margin_bottom20px">
                    <table id="search_results" class="font_student_list block_width100" rules="all"
                           bordercolor="#008EA5">
                    </table>
                </div>
                <!-- table end -->
            </div>
            <!-- well end -->
        </div>
        <!-- collapse open block end -->
        <!-- collapse end -->

    </div>

    <aside class="aside_personal_area_main_block font_aside_personal_area">
        <div>
            <a href="#" data-toggle="modal" data-target="#profileModal"
               class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color">
                <img src="<@spring.url '/images/profile.png'/>" class="aside_personal_area_img">
                <span>Профиль</span>
            </a>
        </div>

        <#--<div>-->
            <#--<a href="" type="button" class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color"-->
               <#--data-toggle="modal" data-target=".bs-example-modal-sm">-->
                <#--<img src="../images/add_user.png" class="aside_personal_area_img">-->
                <#--<span>Добавить пользователя</span>-->
            <#--</a>-->
        <#--</div>-->
        <div>
            <a href="<@spring.url '/logout'/>" class="flexbox_aling_center flex_row margin_bottom20px blue_background_rgba_color">
                <img src="<@spring.url '/images/sign_out.png'/>" class="aside_personal_area_img">
                <span>Выход</span>
            </a>
        </div>

    <#-- Showing notifications -->
        <#list notifications as elem>

            <div class="blue_background_rgba_color padding10px margin_bottom20px">
                <!--             <div class="line"></div> -->
                <p class="font_notification_subject_and_data">
                ${elem.header}
                </p>
                <p class="font_notification_text">
                ${elem.body}
                </p>
                <span class="font_notification_subject_and_data">
                до
                </span>
                <time class="font_notification_subject_and_data">${elem.deadline.getHour()}
                    :${elem.deadline.getMinute()}</time>
                <data class="font_notification_subject_and_data">${elem.deadline.getDayOfMonth()}
                    .${elem.deadline.getMonthValue()}.${elem.deadline.getYear()}</data>
            </div>

        </#list>

        <#include "modals/teacher_profile_edit.ftl">
        <#include "modals/notification_modal.ftl">

    <#-- Showing create user modal -->
        <#include "modals/create_user_modal.ftl">

    </aside>
</div>
<script src="<@spring.url '/js/user_search.js'/>"></script>
<#if user??><input type="hidden" id="secret_used_id" value="${user.id}"></#if>
</@mainLayout>