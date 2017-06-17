<#import "/spring.ftl" as spring />
<#--<form action="" method="post" enctype="multipart/form-data">-->
<#--<div id="edit_student_modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">-->
<#--<div class="modal-dialog modal-lg large-modal" role="document">-->
<#--<div class="modal-content">-->
<#--<div class="modal-header">-->
<#--<div class="flexbox_aling_center flex_justify_space_between margin_bottom5px">-->
<#--<p class="font_modal_header">${user.name} ${user.surname}</p>-->
<#--<button type="button" class="close" style="margin-left: 10px;" data-dismiss="modal" aria-hidden="true">-->
<#--<img src="../images/Modals_exit_button.png" alt="img"/>-->
<#--</button>-->
<#--</div>-->
<#--<hr>-->
<#--</div>-->
<#--<form class="modal-body" id="send-notification-form">-->
<#--<div class="flexbox_aling_start" style="padding-left: 15px;">-->

<#--<div id="profile-edit-avatar">-->
<#--<img class="profile-image" src="/uploads/profile/${user.avatarUrl}">-->
<#--<@spring.formInput "editForm.avatar" "" "file"/>-->
<#--</div>-->

<#--<div id="profile-edit-docs">-->
<#--<p>Добавить документ<button type="button" class="close" style="margin-top: 7px;">-->
<#--<img src="../images/add_button.png" alt="img"/>-->
<#--</button></p>-->

<#--<!-- Iterate docs list: &ndash;&gt;-->
<#--<p>Добавить документ<button type="button" class="close" style="margin-top: 9px;">-->
<#--<img src="../images/Modals_exit_button.png" alt="img"/>-->
<#--</button></p>-->
<#--<p>Добавить документ<button type="button" class="close" style="margin-top: 9px;">-->
<#--<img src="../images/Modals_exit_button.png" alt="img"/>-->
<#--</button></p>-->
<#--</div>-->
<#--</div>-->
<#--<div class="pi-wrapper blue_background_rgba_color margin-hood">-->
<#--<div id="personal-info">-->
<#--<p>Мобильный телефон:</p>-->
<#--<@spring.formInput "editForm.phone"/>-->
<#--<p>Новый пароль:</p>-->
<#--<@spring.formPasswordInput "editForm.password"/>-->
<#--<p>Повторите пароль:</p>-->
<#--<@spring.formPasswordInput "editForm.passwordRepeat"/>-->
<#--</div>-->
<#--</div>-->
<#--<div class="modal-footer" style="margin-left: 250px;">-->
<#--<button class="modal_сonfirm_button  font_student_search_button" style="margin-top:20px;">Сохранить</button>-->
<#--</div>-->
<#--</form>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->
<#--</form>-->



<form action="<@spring.url '/student/profile'/>" method="post" enctype="multipart/form-data">
    <!-- Modal -->
    <div class="modal fade" id="edit_student_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width: 920px;">
            <div class="modal-content" style="height: auto!important;">
                <div class="modal-header flex_column">
                    <div class="flex_row flex_justify_space_between">
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header">${user.name} ${user.surname}</p>
                            <button type="submit" class="close" style=" margin-bottom: 5px;">
                                <img src="<@spring.url '/images/floppy.png'/>" width="20px;" height="20px;" alt="img"
                                     style="margin-left: 10px;">
                            <#--<img src="images/edit_pensil.png" width="20px;" height="20px;" alt="img" style="margin-left: 10px;" class="edit_block">-->
                            </button>
                        </div>
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header" style="margin-right: 10px;">Студент</p>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"/>
                            </button>
                        </div>
                    </div>
                    <div>
                        <hr>
                    </div>
                </div>
                <form class="modal-body" id="send-notification-form">
                    <div class="modal-body flex_row flex_justify_space_between" style="padding-bottom: 10px;">
                        <div style="width:270px;">
                            <img class="profile-image" src="<@spring.url '/uploads/profile/${user.avatarUrl}'/>"
                                 style="width:270px; height:270px;">
                        <@spring.formInput "editForm.avatar" 'class="btn"' "file"/>
                        </div>
                        <div class="flex_column font_for_input_text" style="width: 150px;">
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Курс:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Группа:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Основа:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Статус:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Телефон:
                            </div>
                        </div>
                        <div class="flex_column" style="width: 212px; margin-bottom: 10px">
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block" name="course">
                                        <option selected value="${user.group.course}">${user.group.course}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block" name="group">
                                        <option selected value="${user.group.num}">${user.group.num}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block" name="pay">
                                        <option selected value="${user.education}">
                                        <#switch user.education>
                                            <#case "BUDGET">
                                                БЮДЖЕТ
                                                <#break>
                                            <#case "CONTRACT">
                                                КОНТРАКТ
                                                <#break>
                                            <#case "GRANT">
                                                ГРАНТ
                                                <#break>
                                            <#default>
                                            ${user.education}
                                        </#switch>
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select name="status" class="modal_input_block">
                                        <option selected value="${user.socialStatus}">
                                        <#switch user.socialStatus>
                                            <#case "PROFORG">
                                                ПРОФОРГ
                                                <#break>
                                            <#case "STAROSTA">
                                                СТАРОСТА
                                                <#break>
                                            <#case "KULTORG">
                                                КУЛЬТОРГ
                                                <#break>
                                            <#case "SPORTORG">
                                                СПОРТОРГ
                                                <#break>
                                            <#case "SOCORG">
                                                СОЦОРГ
                                                <#break>
                                            <#default>
                                                -
                                        </#switch>
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div>
                            <@spring.formInput "editForm.phone" 'class="form-group modal_input_block font_placeholder_student_search" value="${telNumberToShowEdit}"'/>
                                    <#--<label class="sr-only sign_in_form" for="surname">Last_password</label>-->
                                    <#--<input class="modal_input_block font_placeholder_student_search" id="last_password" type="password" name="last_password" placeholder="Старый пароль">-->
                            </div>

                            <div style="display: none;">
                            <@spring.formPasswordInput "editForm.password" 'class="form-group modal_input_block font_placeholder_student_search"'/>
                                    <#--<label class="sr-only sign_in_form" for="surname">New_password</label>-->
                                    <#--<input class="modal_input_block font_placeholder_student_search" id="new_password" type="password" name="password" placeholder="Новый пароль">-->
                            </div>

                            <div style="display:none;">
                            <@spring.formPasswordInput "editForm.passwordRepeat" 'class="form-group modal_input_block font_placeholder_student_search"'/>
                            </div>
                        </div>
                        <div class="flex_column document_block" style="width: 200px; height: 298px; overflow: auto;">
                            <span class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                    Добавить документ
                                <label for="document" style="cursor: pointer; background-image: url(<@spring.url '/images/dl_cursor.png'/>); width: 13px; height: 13px;"></label>
                            </span>
                        <@spring.formInput "editForm.document" ' class="btn" style="opacity:0; position: absolute;" ' "file"/>


                        <#if user.documents??>
                            <#list user.documents as document>
                                        <#if document.name??>
                                        <a href="<@spring.url '/student/delete_document/${document.id}'/>"
                                           class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">

                                            <span>
                                                ${document.name}
                                            </span>

                                            <img src="<@spring.url '/images/Modals_exit_button.png'/>" width="13px" height="13px">

                                        </a>

                                        <#else>
                                            <img src="<@spring.url '/images/standart_ph.png'/>" class="personal_area_min_block_img">
                                        </#if>
                            </#list>
                        </#if>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal End -->
</form>