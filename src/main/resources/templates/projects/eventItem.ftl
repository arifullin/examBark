<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/news_tags.js"></script>
<#import "/spring.ftl" as spring />
<#if event??>
<div>
    <div class="event_block">
        <div id="event_header" class=" flex_row">
            <p class="font_event_header">${event.title}
            </p>
            <#if event.tags??>
                <#list event.tags as t>
                    <p class="font_events_tag">${t.name}
                    </p>
                </#list>
            </#if>
        </div>
        <hr class="event_hr">
        <div class="flex_row flex_justify_space_between">
            <div class="flexbox_aling_center">
                <span id="font_event_time">${event.getStartedTime()}</span>
                <span>
                            <data class="font_event_data">${event.getStartedDate()}</data>
                            <div class="font_event_data"><#if event.address??>${event.address}</#if></div>
                        </span>
            </div>
            <div class="flex_row">
                <div class="flex_row event_participants_margin">
                    <div id="font_event_participants">
                    ${event.getUsersCount()}
                    </div>
                    <img src="<@spring.url '/images/Events_Page_participants.png'/>" class="event_participants_img">
                </div>

                <button type="button" class="event_button font_button_checkbox_radio" onclick="subscribeEvent(${event.id})" id="event_subscribe${event.id}">
                    <#if event.isSubscribed()>
                        Не участвовать
                    <#else >
                        Участвовать
                    </#if>
                </button>

            </div>
        </div>
        <p class="font_events_text"><#if event.description??>${event.description}</#if></p>
        <div class="flex_justify_end">
            <span id="font_event_author"><#if event.author??>${event.author.name} ${event.author.surname}</#if></span>
            <data class='font_event_author_time'>
            ${event.getCreatedDate()}
            </data>
            <time class='font_event_author_time'>
            ${event.getCreatedTime()}
            </time>
        </div>
        <div class="event_block_images flex_justify_space_between">
            <#if event.uploadUrl1??>
                <img src="<@spring.url '/uploads/events/${event.uploadUrl1}'/>" class="event_img" alt="img">
            <#else>
            </#if>
            <#if event.uploadUrl2??>
                <img src="<@spring.url '/uploads/events/${event.uploadUrl2}'/>" class="event_img" alt="img">
            <#else>
            </#if>
            <#if event.uploadUrl33??>
                <img src="<@spring.url '/uploads/events/${event.uploadUrl3}'/>" class="event_img" alt="img">
            <#else>
            </#if>
        </div>
    </div>
</div>
</#if>
<script src="<@spring.url '/js/event.js'/>"></script>