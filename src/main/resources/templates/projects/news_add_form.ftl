<#import "/spring.ftl" as spring />
<div class="event_news_admin_block">



        <div class="form_admin">

            <input class="news_admin_input font_placeholder_student_search"
                   value="<#if title??>${title}</#if>" id="news_title" type="text"
                   name="title"
                   placeholder="Введите заголовок">

            <input class="news_admin_input font_placeholder_student_search"
                   value="<#if tagsForm??>${tagsForm}</#if>" id="news_tags" type="text"
                   name="tagsForm"
                   placeholder="Введите теги через запятую">

        </div>

        <div class="form_admin">

                <textarea class="news_admin_textarea font_placeholder_student_search" id="news_body" name="body"
                          placeholder="Введите текст"><#if body??>${body}</#if></textarea>
        </div>


        <div class="news_admin_foter flex_justify_space_between">

            <label class="mini_button button_effect fileContainer">
                Добавить фото
                <input type="file" id="news_file" name="files" multiple>
            </label>

            <div style="margin-left: 70px">
                <button type="button" class="button_effect mini_button font_student_search_button" onclick="createNews(${project.id})">
                    Опубликовать
                </button>
            </div>
        </div>

</div>
