<!-- Admin -->
<#import '/spring.ftl' as spring/>

<div class="event_news_admin_block">

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


    <form enctype="multipart/form-data" action="<@spring.url '/events/create'/>" method="POST">
    <div class="form_admin">
        <input class="news_admin_input font_placeholder_student_search"
               id="header"
               type="text"
               name="header"
               value="<#if title??>${title}</#if>"placeholder="Введите заголовок">

        <#--<input class="news_admin_input font_placeholder_student_search"-->
               <#--id="tags"-->
               <#--type="text"-->
               <#--name="tags"-->
               <#--placeholder="Введите теги">-->
    </div>
        <div class="form_admin">
            <textarea class="news_admin_textarea font_placeholder_student_search"
                      id="description" type="text" name="description"
                      placeholder="Введите описание"><#if body??>${body}</#if></textarea>
        </div>
        <input type="text" class="news_admin_input_place  font_placeholder_student_search"
               id="address"
               name="address"
               value="<#if address??>${address}</#if>"
               placeholder="Адрес">
        <div class="news_admin_foter flex_justify_space_between">
            <div class="flex_row">
                <input type="date" class="news_admin_input_date  font_placeholder_student_search"
                       id="date"
                       name="date"
                       value="<#if date??>${date}</#if>"
                       placeholder="дд.мм.гггг">

                <input class="news_admin_input_time font_placeholder_student_search"
                       type="number"
                       name="time1"
                       min="0"
                       max="23"
                       id="time1"
                       value="<#if time1??>${time1}</#if>"
                       placeholder="чч">

                <p class="font_news_admin_time_center">:</p>
                <input class="news_admin_input_time font_placeholder_student_search"
                       type="number"
                       id="time2"
                       min="0"
                       max="59"
                       name="time2"
                       value="<#if time2??>${time2}</#if>"
                       placeholder="мм">
            </div>


            <div class="">
                <label class="mini_button fileContainer">
                    Добавить фото
                    <input type="file" name="files" multiple style="width:0px;">
                </label>
            </div>
            <div style="margin-left: 70px">
                <button type="submit" class="mini_button font_student_search_button">
                    Опубликовать
                </button>
            </div>
        </div>
    </form>
</div>
