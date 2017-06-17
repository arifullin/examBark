<#include "../layout.ftl">
<#import "/spring.ftl" as spring />
<@mainLayout header="Events">
<div class="main_block_with_flexbox">
    <div>
        <div>
            <#include "event_form.ftl">
        </div>
        <#list events as event>
            <div id="events">
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
                            <span id="font_event_time">
                                <#if event.dateStarted??>
                                    ${event.dateStarted.toString()?substring(event.dateStarted?index_of('T') + 1)}
                                </#if>
                            </span>
                            <span>
                            <data class="font_event_data">
                                <#if event.dateStarted??>
                                    ${event.dateStarted[0..(event.dateStarted?index_of('T')-1)]}
                                </#if>
                            </data>
                            <div class="font_event_data"><#if event.address??>${event.address}</#if></div>
                        </span>
                        </div>
                        <div class="flex_row">
                            <div class="flex_row event_participants_margin">
                                <div id="font_event_participants">
                                ${event.getUsersCount()}
                                </div>
                                <img src="<@spring.url '/images/Events_Page_participants.png'/>"
                                     class="event_participants_img">
                            </div>
                            <button class="event_button font_button_checkbox_radio"
                                    onclick="subscribeEvent(${event.id})" id="event_subscribe${event.id}">
                                <#if event.isSubscribed()>
                                    Не участвовать
                                <#else >
                                    Участвовать
                                </#if>
                            </button>
                        </div>
                    </div>

                    <p class="font_events_text"><#if event.description??>${event.description?html}</#if></p>

                    <div class="flex_justify_end">
                        <span id="font_event_author"><#if event.author??>${event.author.name}</#if></span>
                        <data class='font_event_author_time'>
                        ${event.getStartedDate()}
                        </data>
                        <time class='font_event_author_time'>
                        ${event.getStartedTime()}
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
                        <#if event.uploadUrl3??>
                            <img src="<@spring.url '/uploads/events/${event.uploadUrl3}'/>" class="event_img" alt="img">
                        <#else>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
        <div class="group_button flex_justify_end">
            <button class="show_more_block" id="font_show_more_block" onclick="loadMoreEvents(); return false">
                Показать больше событий
            </button>
        </div>
    </div>
    <#--<aside class="blue_background_rgba_color padding20px">-->
    <#-- TODO tags in events.ftl
        <div class="flexbox_aling_center">
            <input type="checkbox" id="tag1" class="checkbox">
            <label class="checkbox-label chb-label-font" id="font_tag_box" for="tag1">
                #ИТИС
            </label>

        </div>
        <div class="flexbox_aling_center">
            <input type="checkbox" id="tag2" class="checkbox">
            <label class="checkbox-label chb-label-font" id="font_tag_box" for="tag2">
                #КФУ
            </label>

        </div>
        -->
    <#--</aside>-->
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

<script src="<@spring.url '/js/bootstrap.min.js'/>"></script>

<script>

    function loadMoreEvents() {
        $.ajax({
            url: "/events/load_more",
            type: "GET",
            success: function (events) {
                $("#events").append(events);
            }
        });
    }
</script>
<script src="<@spring.url '/js/event.js'/>"></script>
</@mainLayout>