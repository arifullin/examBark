<#import '/spring.ftl' as spring/>

<!-- modal send notification start -->
<div id="send-notification-modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal_notification_send" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="flexbox_aling_center flex_justify_space_between margin_bottom5px">
                    <p class="font_modal_header">Отправить уведомление</p>
                    <button type="button" class="close" style="margin-left: 10px;" data-dismiss="modal"
                            aria-hidden="true">
                        <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"/>
                    </button>
                </div>
                <hr>
            </div>
            <form class="modal-body" id="send-notification-form">
                <div class="form_admin">
                    <input class="news_admin_input font_placeholder_student_search" value="" id="title" type="text" name="title"
                           placeholder="Введите тему">

                </div>

                <span id="message"></span>

                <div class="form_admin">
                    <textarea class="news_admin_textarea font_placeholder_student_search" id="body" name="body"
                              placeholder="Введите описание"></textarea>
                </div>

                <div class="modal-footer" style="margin-bottom: -20px">
                    <div class="news_admin_foter">

                        <input id="datetime" type="date"
                               class="news_admin_input_date  font_placeholder_student_search" placeholder="дд.мм.гггг">
                        <input id="time1" min="0" max="59" class="news_admin_input_time font_placeholder_student_search"
                               type="number" name="time1" placeholder="чч">
                        <p class="font_news_admin_time_center">:</p>
                        <input id="time2" min="0" max="59" class="news_admin_input_time font_placeholder_student_search"
                               type="number" name="time2" placeholder="мм">

                        <div class="news_admin_checkbox">
                            <input type="checkbox" name="deadline" value="Событие" checked="checked" id="event_1"
                                   onclick="clickCheckbox();">
                            <label class="checkbox-label chb-label-font" for="event_1">Дедлайн</label>
                        </div>


                        <div style="margin-left: 255px">
                            <button class="mini_button font_student_search_button">
                                Отправить
                            </button>
                        </div>

                    </div>

                </div>

            </form>
        </div>
    </div>
</div>
<!-- modal send notification end -->