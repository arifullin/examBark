<#import "/spring.ftl" as spring />
<#if communities??>
    <#list communities as community>
    <div class="event_block group_block flex_row">
        <div class="event_block group_block flex_row">
            <a href="/group/${community.id}">
                <div>
                    <#if community.avatarUrl??>
                        <img src="<@spring.url '/uploads/Community_Avatar/${community.avatarUrl}'/>" class="group_img"
                             alt="img">
                    <#else>
                        <img src="<@spring.url '/images/square_ph.png'/>" class="group_img" alt="img">
                    </#if>
                </div>
            </a>
            <div style="width:100%;">
                <div class="flex_row">
                    <a href="/group/${community.id}">
                        <p class="font_event_header">
                        ${community.name}
                        </p>
                    </a>
                </div>
                <hr>
                <div class="main_block_with_flexbox">
                    <div class="flex_column">
                            <span class="font_mini_header">
                                <#if community.leader??>${community.leader.name} ${community.leader.surname}</#if>
                            </span>
                        <p class="font_events_text group_text text_overflow group_small_height">
                            <#if community.description??>
                    ${community.description}
                    </#if>
                        </p>
                    </div>
                    <div class="flex_row">
                        <div class="font_group_participants">
                        ${community.getUsersCount()}
                        </div>
                        <img src="<@spring.url '/images/Events_Page_participants.png'/>" class="group_participants_img">
                    </div>
                </div>
                <div class="group_button flex_justify_end">
                    <button class="event_button font_button_checkbox_radio"
                            onclick="location.href='/group/${community.id}'">
                        Открыть
                    </button>
                </div>
            </div>
        </div>
    </#list>
</#if>