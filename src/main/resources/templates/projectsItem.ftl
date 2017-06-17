<#import "/spring.ftl" as spring />
<#if projects??>
    <#list projects as project>
    <div class="event_block group_block flex_row">
        <div>
            <#if project.avatarUrl??>
                <img src="<@spring.url '/uploads/Project_Avatar/${project.avatarUrl}'/>" class="group_img" alt="img">
            <#else>
                <img src="<@spring.url '/images/square_ph.png'/>" class="group_img" alt="img">
            </#if>
        </div>
        <div style="width:100%;">
            <div class="flex_row">
                <p class="font_event_header">
                ${project.projectName}
                </p>
            </div>
            <hr>
            <div class="main_block_with_flexbox">
                <div class="flex_column">
                            <span class="font_mini_header">
                                <#if project.leader??>${project.leader.name} ${project.leader.surname}</#if>
                            </span>
                    <p class="font_events_text group_text text_overflow group_small_height">
                        <#if project.description??>${project.description}</#if>
                    </p>
                </div>
                <div class="flex_row">
                    <div class="font_group_participants">
                    ${project.getUsersCount()}
                    </div>
                    <img src="<@spring.url 'images/Events_Page_participants.png'/>" class="group_participants_img">
                </div>
            </div>
            <div class="group_button flex_justify_end">
                <button class="event_button font_button_checkbox_radio" onclick="location.href='/project/${project.id}'">
                    Открыть
                </button>
            </div>
        </div>
    </div>
    </#list>
</#if>