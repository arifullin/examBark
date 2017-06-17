<#include "../layout.ftl">
<#import "/spring.ftl" as spring />

<@mainLayout header="News">
<!-- News container -->
<div class="main_block_with_flexbox">
    <div>

        <#if message??>
            <h3 style="color: #1c690c;">${message}</h3>
        </#if>


        <#if error??>
            <h3 style="color: #a12106;">${error}</h3>
        </#if>

        <!-- Admin -->

        <div class="event_news_admin_block">

            <form enctype="multipart/form-data" action="/news/create" method="post">

                <div class="form_admin">

                    <input class="news_admin_input font_placeholder_student_search" value="" id="header" type="text"
                           name="header"
                           placeholder="Введите заголовок">

                    <input class="news_admin_input font_placeholder_student_search" value="" id="tags" type="text"
                           name="tags"
                           placeholder="Введите теги через запятую">

                </div>

                <div class="form_admin">

                <textarea class="news_admin_textarea font_placeholder_student_search" id="body" name="body"
                          placeholder="Введите текс"></textarea>
                </div>

                <div class="news_admin_foter">

                    <div class="news_admin_add_photo">
                        <a href="#" class="font_group_add_photo">Загрузить фото</a>
                        <input type="file" name="files" multiple required>
                    </div>

                    <div style="margin-left: 70px">
                        <button type="submit" class="mini_button font_student_search_button">
                            Опубликовать
                        </button>
                    </div>
                </div>

            </form>

        </div>
        <!-- Admin End -->

        <div class="event_block">
            <div id="event_header" class=" flex_row">
                <p class="font_event_header">Заголовок новости
                </p>
                <p class="font_events_tag">#тег
                </p>
                <p class="font_events_tag">#тег
                </p>
                <p class="font_events_tag">#тег
                </p>
            </div>
            <hr class="event_hr">
            <div class="font_events_text group_text">
                Краткое описание новости. Краткое описание новости. Краткое описание. Краткое описание новости. Краткое
                описание новости новости новости. Краткое описание новости. Краткое описание. Краткое описание
                новости...
            </div>
            <div>
                <div class="flex_column">
                    <!-- <div class="flex_column"> -->
                    <div class="spoiler-body font_events_text" style="margin-right: 50px;">
                        Краткое описание новости. Краткое описание новости. Краткое описание новости. Краткое описание
                        новости. Краткое описание новости. Краткое описание новости. Краткое описание новости. Краткое
                        описание новости
                    </div>
                    <div class="flex_justify_space_between flexbox_aling_center margin_top10px">
                        <div class="spoiler-header closed font_spoiler_cl_op">
                            <img src="<@spring.url '/images/arrow_open.png'/>" alt="open" style="margin-right:5px;">
                            <span>
                                    развернуть
                                    </span>
                        </div>
                        <div class="flex_justify_end">
                                    <span id="font_event_author">Автор события
                                    </span>
                            <data class='font_event_author_time'>
                                16.06.2017
                            </data>
                            <time class='font_event_author_time'>
                                16:24
                            </time>
                        </div>
                    </div>
                </div>
            </div>
            <div class="event_block_images flex_justify_space_between ">
                <img src="<@spring.url 'images/square_ph.png'/>" class="event_img" alt="img">
                <img src="<@spring.url 'images/square_ph.png'/>" class="event_img" alt="img">
                <img src="<@spring.url 'images/square_ph.png'/>" class="event_img" alt="img">
            </div>
        </div>
        <div class="show_more_block" id="font_show_more_block">
            <a href="#">Показать больше</a>
        </div>

    </div>

    <aside class="blue_background_rgba_color padding20px">
        <div class="flexbox_aling_center">
            <input type="checkbox" id="tag1" class="checkbox">
            <label class="checkbox-label chb-label-font" id="font_tag_box" for="tag1">
                #тег
            </label>
        </div>
    </aside>
</div>

<!-- News container end -->
</@mainLayout>
