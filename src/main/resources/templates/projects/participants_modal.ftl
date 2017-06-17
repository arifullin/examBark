<#import "/spring.ftl" as spring />
<!-- Modal -->
<div class="modal fade" id="usersModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="group_participants_modal_dialog">
        <div class="modal-content">
            <div class="modal-header flex_column">
                <div class="flex_row flex_justify_space_between">
                    <div class="flex_row">
                        <p class="font_modal_header">Участники</p>
                        <button type="button" class="close hide_block" id="edit">
                            <img src="<@spring.url '/images/floppy.png'/>" alt="img" style="margin-left: 20px;"/>
                        </button>
                    </div>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img" width="18px;" height="18px;"/>
                    </button>
                </div>
                <div>
                    <hr>
                </div>
            </div>
            <div class="group_participants_modal-body" >

                <div class="modal_body_group_participants">

                <#if project.users??>
                    <#list project.users as u>
                    <a href="#" class="group_modal_participant_info_block flex_row flex_justify_space_between">
                        <div class="font_aside_black_text flexbox_aling_center flex_row">
                            <img src="<@spring.url '/images/small_ph.png'/>" class="group_modal_participant_img">
                            <div>
                            ${u.name} ${u.surname}
                            </div>
                        </div>
                        <div class="flexbox_aling_center">
                            <button type="button" class="close hide_block" data-dismiss="modal" aria-hidden="true">
                                <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img" width="18px;" height="18px;"/>
                            </button>
                        </div>
                    </a>
                    </#list>

                </#if>
                </div>
            </div>

        </div>
    </div>
</div>
