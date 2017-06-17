<#import "/spring.ftl" as spring />
<#if news??>
    <#list news as n>
    <div class="event_block">
        <div id="event_header" class="flex_row">
            <p class="font_event_header">${n.name}
            </p>
            <#if n.tags??>
                <#list n.tags as tag>
                    <p class="font_events_tag">#${tag.name}
                    </p>
                </#list>
            </#if>
        </div>
        <hr class="event_hr">
        <div class="font_events_text group_text">
            <#if n.description??>
    ${n.description}</#if>
        </div>
        <div>
            <div class="flex_column">
                <div class="flex_justify_space_between flexbox_aling_center margin_top10px">
                    <div class="flex_justify_end">
                    <span id="font_event_author">
                        <#if n.author??>${n.author.name} ${n.author.surname}</#if>
                    </span>
                        <data class='font_event_author_time'>
                        ${n.getDate()}
                        </data>
                        <time class='font_event_author_time'>
                        ${n.getTime()}
                        </time>
                    </div>
                </div>
            </div>
        </div>
        <div class="event_block_images flex_justify_space_between">
            <#if n.upload1??><img src="<@spring.url '/uploads/news/${n.upload1}'/>" class="event_img" alt="img"></#if>
            <#if n.upload2??><img src="<@spring.url '/uploads/news/${n.upload2}'/>" class="event_img" alt="img"></#if>
            <#if n.upload3??><img src="<@spring.url '/uploads/news/${n.upload3}'/>" class="event_img" alt="img"></#if>
        </div>
    </div>
    </#list>

</#if>