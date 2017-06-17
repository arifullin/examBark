<#import '/spring.ftl' as spring />

            <div class="modal-content" style="height: auto!important;">
                <div class="modal-header flex_column">
                    <div class="flex_row flex_justify_space_between">
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header">${u.name} ${u.surname}</p>
                            <button type="button" class="close" style=" margin-bottom: 5px;" id="edit">
                                <img src="<@spring.url '/images/edit_pensil.png'/>" width="20px;" height="20px;"
                                     alt="img"
                                     style="margin-left: 10px;" class="edit_block">
                            </button>
                            <button class="close hide_block" type="button"
                                    onclick="editStudent(this.id)" id="button${u.id}"
                                    style=" margin-bottom: 5px;">
                                <img src="<@spring.url '/images/floppy.png'/>" width="20px;" height="20px;" alt="img"
                                     style="margin-left: 10px;">
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
                    <div class="modal-body flex_row flex_justify_space_between"
                         style="padding-bottom: 10px;">
                        <div style="width:270px;">
                            <div style="width:270px!important; height: 270px; margin-bottom: 10px;"
                                 class="img_overflow">
                                <img class="profile-image" src="<@spring.url '/uploads/User_Avatar/${u.avatarUrl}'/>"
                                     style="width:270px; height:270px;">
                            </div>
                        <#--<@spring.formInput "editForm.avatar" 'class="btn"' "file"/>-->
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
                            <div>
                                <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                    Email:
                                </div>
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Телефон:
                            </div>

                        </div>
                        <div class="flex_column" style="width: 212px;">
                            <div class="hide_block">
                                <div class="form-group font_placeholder_student_search">
                                    <div class="styled-select">
                                        <select id="${u.id}" class="modal_input_block"
                                                onchange="loadGroups(this)">
                                        <#list courses as course>
                                            <#if course == u.group.course>
                                                <option selected>${course}</option>
                                            <#else>
                                                <option>${course}</option>
                                            </#if>
                                        </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group font_placeholder_student_search">
                                    <div class="styled-select">
                                        <select id="group-user${u.id}" class="modal_input_block">
                                        <#list groups as group>
                                            <#if group.course == u.group.course>
                                                <#if group.num == u.group.num>
                                                    <option selected>${group.num}</option>
                                                <#else>
                                                    <option>${group.num}</option>
                                                </#if>
                                            </#if>
                                        </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group font_placeholder_student_search">
                                    <div class="styled-select">
                                        <select id="education${u.id}" class="modal_input_block">
                                        <#switch u.education>
                                            <#case "BUDGET">
                                                <option selected>Бюджет</option>
                                                <option>Контракт</option>
                                                <#break>
                                            <#case "CONTRACT">
                                                <option selected>Контракт</option>
                                                <option>Бюджет</option>
                                                <#break>
                                        </#switch>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group font_placeholder_student_search">
                                    <div class="styled-select">
                                        <select id="social-status${u.id}" class="modal_input_block">
                                        <#if u.socialStatus ??>
                                            <#switch u.socialStatus>
                                                <#case "STAROSTA">
                                                    <option selected>Староста</option>
                                                    <option>Профорг</option>
                                                    <option>Соцорг</option>
                                                    <option>Культорг</option>
                                                    <option>Спорторг</option>
                                                    <option>Нет</option>
                                                    <#break>
                                                <#case "PROFORG">
                                                    <option selected>Профорг</option>
                                                    <option>Староста</option>
                                                    <option>Соцорг</option>
                                                    <option>Культорг</option>
                                                    <option>Спорторг</option>
                                                    <option>Нет</option>
                                                    <#break>
                                                <#case "SOCORG">
                                                    <option selected>Соцорг</option>
                                                    <option>Профорг</option>
                                                    <option>Староста</option>
                                                    <option>Культорг</option>
                                                    <option>Спорторг</option>
                                                    <option>Нет</option>
                                                    <#break>
                                                <#case "KULTORG">
                                                    <option selected>Культорг</option>
                                                    <option>Профорг</option>
                                                    <option>Соцорг</option>
                                                    <option>Староста</option>
                                                    <option>Спорторг</option>
                                                    <option>Нет</option>
                                                    <#break>
                                                <#case "SPORTORG">
                                                    <option selected>Спорторг</option>
                                                    <option>Профорг</option>
                                                    <option>Соцорг</option>
                                                    <option>Культорг</option>
                                                    <option>Староста</option>
                                                    <option>Нет</option>
                                                    <#break>
                                            </#switch>
                                        <#else>
                                            <option selected>Нет</option>
                                            <option>Профорг</option>
                                            <option>Соцорг</option>
                                            <option>Культорг</option>
                                            <option>Староста</option>
                                            <option>Спорторг</option>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                <#--<@spring.formInput "editForm.phone" 'class="form-group modal_input_block font_placeholder_student_search"'/>-->
                                    <div class="modal_input_block flexbox_aling_center"
                                         style="border: none!important; margin-bottom: 10px;">
                                    ${u.email}
                                    </div>
                                    <div class="modal_input_block flexbox_aling_center"
                                         style="border: none!important; margin-bottom: 10px;">
                                    ${u.phone}
                                    </div>
                                </div>
                            </div>
                            <div class="edit_block font_placeholder_student_search">
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                ${u.group.course}
                                </div>
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                ${u.group.num}
                                </div>
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                <#switch u.education>
                                    <#case "BUDGET">
                                        Бюджет
                                        <#break>
                                    <#case "CONTRACT">
                                        Контракт
                                        <#break>
                                </#switch>
                                </div>
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                <#if u.socialStatus ??>
                                    <#switch u.socialStatus>
                                        <#case "STAROSTA">
                                            Староста
                                            <#break>
                                        <#case "PROFORG">
                                            Профорг
                                            <#break>
                                        <#case "SOCORG">
                                            Соцорг
                                            <#break>
                                        <#case "KULTORG">
                                            Культорг
                                            <#break>
                                        <#case "SPORTORG">
                                            Спорторг
                                            <#break>
                                    </#switch>
                                <#else>
                                    Нет
                                </#if>
                                </div>
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                ${u.email}
                                </div>
                                <div class="modal_input_block flexbox_aling_center"
                                     style="border: none!important; margin-bottom: 10px;">
                                ${u.phone}
                                </div>
                            </div>
                        </div>
                    <#--<div>-->
                    <#--<@spring.formInput "editForm.phone" 'class="form-group modal_input_block font_placeholder_student_search"'/>-->

                    <#--</div>-->
                    <#--<div>-->
                    <#--<@spring.formPasswordInput "editForm.password" 'class="form-group modal_input_block font_placeholder_student_search"'/>-->
                    <#--</div>-->
                    <#--<div>-->
                    <#--<@spring.formPasswordInput "editForm.passwordRepeat" 'class="form-group modal_input_block font_placeholder_student_search"'/>-->
                    <#--</div>-->
                        <div class="flex_column document_block"
                             style="width: 200px; height: 270px; overflow: auto;">
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                <div>
                                    Скачать все
                                </div>
                                <img src="/images/dl_cursor.png" width="13px" height="13px">

                            </a>
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                                <span>
                                                    Название документа
                                                </span>
                                <img src="/images/dl_cursor.png" width="13px" height="13px">
                            </a>
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                                <span>
                                                    Название документа
                                                </span>
                                <img src="/images/dl_cursor.png" width="13px" height="13px">
                            </a>

                        </div>
                        <!-- allot all checkboxes in table -->
                        <script type="text/javascript" src="/js/allot_checkboxes.js"></script>

                        <script type="text/javascript" src="/js/edit_group.js"></script>

                        <script type="text/javascript" src="/js/course_user.js"></script>