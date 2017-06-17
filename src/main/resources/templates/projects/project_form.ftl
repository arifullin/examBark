<#import "/spring.ftl" as spring />
<!-- Admin -->

<div class="event_news_admin_block">
    <div class="form_admin">
        <input class="news_admin_input font_placeholder_student_search" id="surname" type="text" name="surname"
               placeholder="Введите заголовок">
    <@spring.formInput "editForm.name" "hidden"/>
    <#--<@spring.formInput "editForm.surname" "hidden"/>-->

        <input class="news_admin_input font_placeholder_student_search" id="surname" type="text" name="surname"
               placeholder="Введите заголовок">

    </div>
    <div class="form_admin">
        <textarea class="news_admin_textarea font_placeholder_student_search" id="surname" type="text" name="about"
                  placeholder="Введите описание"></textarea>
    </div>

    <input id="eventElement" type="text" class="news_admin_input_place  font_placeholder_student_search"
           placeholder="Адрес">

    <div class="news_admin_foter">

        <input id="eventElement" type="date" class="news_admin_input_date  font_placeholder_student_search"
               placeholder="дд.мм.гггг">
        <input id="eventElement" class="news_admin_input_time font_placeholder_student_search" type="number"
               name="time1" placeholder="чч">
        <p class="font_news_admin_time_center">:</p>
        <input id="eventElement" class="news_admin_input_time font_placeholder_student_search" type="number"
               name="time2" placeholder="мм">

        <div class="news_admin_checkbox">
            <input type="checkbox" name="captain" value="Событие" checked="checked" id="event_1"
                   onclick="clickCheckbox();">
            <label class="checkbox-label chb-label-font" for="event_1">Событие</label>
        </div>

        <div class="news_admin_add_photo">
            <a href="#" class="font_group_add_photo">Загрузить фото</a>
        </div>
        <div style="margin-left: 70px">
            <button class="mini_button font_student_search_button">
                Опубликовать
            </button>
        </div>
    </div>

</div>
<!-- Admin End -->