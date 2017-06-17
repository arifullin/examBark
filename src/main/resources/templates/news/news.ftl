<#include "../layout.ftl">

<@mainLayout header="News">

<!-- News container -->

<div class="main_block_with_flexbox">
    <div>

        <#if message??>
            <div class="alert alert-success">
                <p>${message}</p>
            </div>
        </#if>


        <#if error??>
            <div class="alert alert-danger pink_background_rgba_color">
                <p>${error}</p>
            </div>
        </#if>

        <div id="news">
            <#if user.userRole == "ROLE_WORKER" || user.userRole == "ROLE_TEACHER">
                <!-- Admin -->

                <div class="event_news_admin_block">

                    <form enctype="multipart/form-data" action="<@spring.url '/news/create'/>" method="post">

                        <div class="form_admin">

                            <input class="news_admin_input font_placeholder_student_search"
                                   value="<#if title??>${title}</#if>" id="title" type="text"
                                   name="title"
                                   placeholder="Введите заголовок">

                            <input class="news_admin_input font_placeholder_student_search"
                                   value="<#if tagsForm??>${tagsForm}</#if>" id="tags" type="text"
                                   name="tagsForm"
                                   placeholder="Введите теги через запятую">

                        </div>

                        <div class="form_admin">

                <textarea class="news_admin_textarea font_placeholder_student_search" id="body" name="body"
                          placeholder="Введите текст"><#if body??>${body}</#if></textarea>
                        </div>


                        <div class="news_admin_foter flex_justify_space_between">

                            <label class="mini_button fileContainer">
                                Добавить фото
                                <input type="file" name="files" multiple style="width:0px;">
                            </label>

                            <div style="margin-left: 70px">
                                <button type="submit" class="mini_button font_student_search_button">
                                    Опубликовать
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
                <!-- Admin End -->
            </#if>

            <div id="wrapper">
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
                        ${n.description}
                            </#if>
                        </div>
                        <div>
                            <div class="flex_column">
                                <div class="flex_justify_space_between flexbox_aling_center margin_top10px">
                                    <div class="flex_justify_end">
                                    <span id="font_event_author"><#if n.author??>${n.author.name} ${n.author.surname}</#if>
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
            </div>
        </div>
        <div class="show_more_block" id="font_show_more_block">
            <a href="#" onclick="loadMore(); return false;">Показать больше</a>
        </div>
    </div>
    <aside class="blue_background_rgba_color padding20px">
        <#if tags??>
            <#list tags as tag>
                <div class="flexbox_aling_center">
                    <input type="checkbox" id="tag${tag.id}" name="tag" class="checkbox" value="${tag.id}"/>
                    <label class="checkbox-label chb-label-font" id="font_tag_box" for="tag${tag.id}">
                        #${tag.name}
                    </label>
                </div>
            </#list>
        </#if>
    </aside>
</div>

    <#import  '/spring.ftl' as spirng />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url '/js/news_tags.js'/>"></script>
<script>

    function loadMore() {

        console.log('load news');

        $.ajax({
            url: "/news/load_more",
            type: "GET",
            success: function (news) {
                console.log(news);
                $("#wrapper").append(news);
            }
        });
    }

</script>


<!-- News container end -->
</@mainLayout>