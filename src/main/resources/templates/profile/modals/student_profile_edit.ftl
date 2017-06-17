<#import "/spring.ftl" as spring />
<form action="" method="post" enctype="multipart/form-data">
    <!-- Modal -->
    <div class="modal fade" id="edit_student_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width: 920px;">
            <div class="modal-content" style="height: auto!important;">
                <div class="modal-header flex_column">
                    <div class="flex_row flex_justify_space_between">
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header">${u.name} ${u.surname}</p>
                            <button type="submit" class="close" style=" margin-bottom: 5px;">
                                <img src="../images/floppy.png" width="20px;" height="20px;" alt="img"
                                     style="margin-left: 10px;">
                            <#--<img src="images/edit_pensil.png" width="20px;" height="20px;" alt="img" style="margin-left: 10px;" class="edit_block">-->
                            </button>
                        </div>
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header" style="margin-right: 10px;">Студент</p>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                <img src="../images/Modals_exit_button.png" alt="img"/>
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
                            <img class="profile-image" src="/uploads/User_Avatar/${u.avatarUrl}"
                                 style="width:270px; height:270px;">
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
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Телефон:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Новый пароль:
                            </div>
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Повторите пароль:
                            </div>
                        </div>
                        <div class="flex_column" style="width: 212px;">
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block">
                                        <option>11-401</option>
                                        <option>11-402</option>
                                        <option>11-403</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block">
                                        <option>Бюджет</option>
                                        <option>Контракт</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group font_placeholder_student_search">
                                <div class="styled-select">
                                    <select class="modal_input_block">
                                        <option>Староста</option>
                                        <option>Культорг</option>
                                    </select>
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
                        </div>
                        <div class="flex_column document_block" style="width: 200px; height: 298px; overflow: auto;">
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                <span>
                                    Добавить документ
                                </span>
                                <img src="../images/dl_cursor.png" width="13px" height="13px">
                            </a>
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                <span>
                                    Название документа
                                </span>
                                <img src="../images/Modals_exit_button.png" width="13px" height="13px">
                            </a>
                            <a href="#"
                               class="one_group_doc_min_block font_aside_blue_text flex_justify_space_between flexbox_aling_center">
                                <span>
                                    Название документа
                                </span>
                                <img src="../images/Modals_exit_button.png" width="13px" height="13px">
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal End -->
</form>