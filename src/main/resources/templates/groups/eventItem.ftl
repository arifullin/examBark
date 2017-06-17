<#if event??>
<div>
    <div class="event_block">
        <div id="event_header" class=" flex_row">
            <p class="font_event_header">${event.title}
            </p>
            <p class="font_events_tag">#тег
            </p>
        </div>
        <hr class="event_hr">
        <div class="flex_row flex_justify_space_between">
            <div class="flexbox_aling_center">
                <span id="font_event_time">${event.getStartedTime()}</span>
                <span>
                            <data class="font_event_data">${event.getStartedDate()}</data>
                            <div class="font_event_data">место проведения</div>
                        </span>
            </div>
            <div class="flex_row">
                <div class="flex_row event_participants_margin">
                    <div id="font_event_participants">
                    ${event.getUsersCount()}
                    </div>
                    <img src="<@spring.url '/images/Events_Page_participants.png'/>" class="event_participants_img">
                </div>
                <button class="event_button font_button_checkbox_radio">
                    Не учавствовать
                </button>
            </div>
        </div>
        <p class="font_events_text">${event.description}</p>
        <div class="flex_justify_end">
            <span id="font_event_author">${event.author.name}</span>
            <data class='font_event_author_time'>
            ${event.getDate()}
            </data>
            <time class='font_event_author_time'>
            ${event.getTime()}
            </time>
        </div>
        <div class="event_block_images flex_justify_space_between">
            <#if event.uploadUrl1??>
                <img src="<@spring.url '/uploads/news/${event.uploadUrl1}'/>" class="event_img" alt="img">
            <#else>
            </#if>
            <#if event.uploadUrl2??>
                <img src="<@spring.url '/uploads/news/${event.uploadUrl2}'/>" class="event_img" alt="img">
            <#else>
            </#if>
            <#if event.uploadUrl33??>
                <img src="<@spring.url '/uploads/news/${event.uploadUrl3}'/>" class="event_img" alt="img">
            <#else>
            </#if>
        </div>
    </div>
</div>
</#if>