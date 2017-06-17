<#import "/spring.ftl" as spring />
<div class="event_news_admin_block">

        <div class="form_admin">
            <input class="news_admin_input font_placeholder_student_search"
                   id="header"
                   type="text"
                   name="header"
                   placeholder="Введите заголовок">

            <input class="news_admin_input font_placeholder_student_search"
                   id="tags"
                   type="text"
                   name="tags"
                   placeholder="Введите теги">
        </div>

        <div class="form_admin">
            <textarea class="news_admin_textarea font_placeholder_student_search"
                      id="event_description" type="text" name="description"
                      placeholder="Введите описание"></textarea>
        </div>

        <input type="text" class="news_admin_input_place  font_placeholder_student_search"
               id="address"
               name="address"
               placeholder="Адрес">

        <div class="news_admin_foter flex_row flex_justify_space_between">

            <div class="flex_row">
                <input type="date" value="10.10.2017" class="news_admin_input_date  font_placeholder_student_search"
                       id="date"
                       name="date"
                       placeholder="дд.мм.гггг">

                <div class="flex_row">
                    <input class="news_admin_input_time font_placeholder_student_search"
                           type="number"
                           name="time1"
                           id="time1"
                           value="10"
                           max="59"
                           min="0"
                           placeholder="чч">

                    <p class="font_news_admin_time_center">:</p>

                    <input class="news_admin_input_time font_placeholder_student_search"
                           type="number"
                           id="time2"
                           name="time2"
                           value="30"
                           max="59"
                           min="0"
                           placeholder="мм">
                </div>
            </div>

            <div class="flex_row">
                <label class="button_effect mini_button fileContainer">
                    Добавить фото
                    <input type="file" id="event_files" name="files" multiple>
                </label>

                <div style="margin-left: 10px; margin-right: 2px;">
                    <button type="button" class="button_effect mini_button font_student_search_button" onclick="createMyEvent(${project.id})">
                        Опубликовать
                    </button>
                </div>

            </div>
        </div>

</div>

