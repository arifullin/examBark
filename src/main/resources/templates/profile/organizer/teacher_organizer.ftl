<div class="collapse font_group_project_name" id="timetable">
    <div class="well flex_row flex_justify_space_between blue_background_rgba_color personal_area_block border_radius_none">
        <div>

            <div class="timetble_menu ">


                <button type="" id="1"
                        class="button_effect timetable_menu_button_checked  timetable_menu_button font_group_add_photo">
                    понедельник
                </button>
                <button type="" id="2" class="button_effect  timetable_menu_button font_group_add_photo">
                    вторник
                </button>
                <button type="" id="3" class="button_effect  timetable_menu_button font_group_add_photo">
                    среда
                </button>
                <button type="" id="4" id="timetable_menu_button_checked"
                        class="button_effect  timetable_menu_button font_group_add_photo">
                    четверг
                </button>
                <button type="" id="5" class="button_effect  timetable_menu_button font_group_add_photo">
                    пятница
                </button>
                <button type="" id="6" class="button_effect  timetable_menu_button font_group_add_photo">
                    суббота
                </button>
                <button type="" id="7" class="button_effect  timetable_menu_button font_group_add_photo">
                    воскресенье
                </button>

            </div>

            <div id="timetable_1" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                            <p>-</p>

                            <p>15:05</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Основы предпринемательства</p>
                            <p>108</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>15:20</p>

                            <p>-</p>

                            <p>16:50</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Основы предпринемательства</p>

                            <p>1307</p>
                        </div>


                    </div>


                </div>

                <div class="timetable_events_block ">

                <#if events??>
                    <#list events as event>

                        <div class="timetable_event_element">
                            <div class="timetable_element_time">
                                <p>15:30</p>

                            </div>
                            <div class="timetable_event">
                                <p>${event.title}</p>
                            </div>

                        </div>

                    </#list>
                </#if>

                </div>

            </div>

            <div id="timetable_2" style="display: none" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>15:20</p>

                            <p>-</p>

                            <p>16:50</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Основы предпринемательства</p>

                            <p>1306</p>
                        </div>

                    </div>

                </div>

                <div class="timetable_events_block ">

                    <div class="timetable_event_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                        </div>
                        <div class="timetable_event">
                            <p>Собрание с ректором</p>
                        </div>

                    </div>

                </div>

            </div>

            <div id="timetable_3" style="display: none" class="timetble_menu font_timeteble_element">


            </div>

            <div id="timetable_4" style="display: none" class="timetble_menu font_timeteble_element">

            </div>

            <div id="timetable_5" style="display: none" class="timetble_menu font_timeteble_element">

            </div>

            <div id="timetable_6" style="display: none" class="timetble_menu font_timeteble_element">

            </div>

            <div id="timetable_7" style="display: none" class="timetble_menu font_timeteble_element">
            </div>


        </div>
    </div>
</div>
