<#import "/spring.ftl" as spring />
<form action="<@spring.url '/worker/profile/edit'/>" method="post" enctype="multipart/form-data">
    <!-- Modal -->
    <div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 920px;" >
            <div class="modal-content" style="height: auto!important;">
                <div class="modal-header flex_column">
                    <div class="flex_row flex_justify_space_between">
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header">${user.name} ${user.surname}</p>
                        <@spring.formInput "editForm.name" "hidden"/>
                        <@spring.formInput "editForm.surname" "hidden"/>
                            <button type="button" class="close" style=" margin-bottom: 5px;" id="edit">
                                <img src="<@spring.url '/images/edit_pensil.png'/>" width="20px;" height="20px;" alt="img" style="margin-left: 10px;" class="edit_block">
                            </button>
                            <button type="submit" class="close hide_block" style=" margin-bottom: 5px;">
                                <img src="<@spring.url '/images/floppy.png'/>" width="20px;" height="20px;" alt="img" style="margin-left: 10px;">
                            </button>
                        </div>
                        <div class="flex_row flexbox_aling_center">
                            <p class="font_modal_header" style="margin-right: 10px;">Сотрудник</p>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" margin-bottom: 3px;">
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
                        <div style="width:270px!important;">
                            <div style="width:270px!important; height: 270px; margin-bottom: 10px;" class="img_overflow">
                                <img class="profile-image" src="<@spring.url '/uploads/profile/${user.avatarUrl}'/>" width="100%;">
                            </div>
                            <div class="hide_block" style="margin-bottom: 10px;">
                                <@spring.formInput "editForm.avatar" 'class="btn"' "file"/>
                            </div>
                        </div>
                        <div class="flex_column font_for_input_text" style="width: 150px!important;">
                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Должность:
                            </div>
                            <div style="height:30px;" class="edit_block margin_bottom10px flexbox_aling_center">
                                Почта:
                            </div>

                            <div style="height:30px;" class="margin_bottom10px flexbox_aling_center">
                                Телефон:
                            </div>

                        </div>

                        <div class="flex_column" style="width: 450px;">
                            <div class="hide_block">
                                <div class="form-group">
                                    <label class="sr-only sign_in_form" for="position">position</label>
                                    <input class="modal_input_block font_placeholder_student_search" id="position" required type="text" value="${positionToShow}" name="position" placeholder="Должность">
                                </div>
                                <div>
                                <@spring.formInput "editForm.phone" 'class="form-group modal_input_block font_placeholder_student_search" required value="${telNumber}"'/>
                                </div>
                            </div>
                            <div class="edit_block font_placeholder_student_search">
                                <div class="modal_input_block flexbox_aling_center" style="border: none!important; margin-bottom: 10px;">
                                ${positionToShow}
                                </div>
                                <div class="modal_input_block flexbox_aling_center" style="border: none!important; margin-bottom: 10px;">
                                ${mailToShow}
                                </div>
                                <div class="modal_input_block flexbox_aling_center" style="border: none!important; margin-bottom: 10px;">
                                ${telNumber}
                                </div>
                            <#if edit_status??>
                            ${edit_status}
                            </#if>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal End -->
</form>