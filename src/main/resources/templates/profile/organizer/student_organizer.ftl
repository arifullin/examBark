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

                            <p>Григорян К.А., 108</p>
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

                            <p>Григорян К.А., 1307 (н.н.)</p>
                        </div>

                    </div>


                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>17:00</p>

                            <p>-</p>

                            <p>18:30</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Курс по выбору</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>18:40</p>

                            <p>-</p>

                            <p>20:10</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Курс по выбору</p>
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
                            <p>11:50</p>

                            <p>-</p>

                            <p>13:20</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Параллельное программирование</p>

                            <p>Новиков П. А., 1309</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                            <p>-</p>

                            <p>15:05</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Управление проектами</p>

                            <p>Моисеев Г.В., н.н. 1306</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>15:20</p>

                            <p>-</p>

                            <p>16:50</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Правовидение</p>

                            <p>Салихов И.И. в 108 к.2 (9н)</p>
                        </div>

                    </div>


                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>17:00</p>

                            <p>-</p>

                            <p>18:30</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Факультатив "1с"</p>

                            <p>Вафин Р., Киченина Т. в 1308</p>
                        </div>

                    </div>

                </div>

                <div class="timetable_events_block ">

                    <div class="timetable_event_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                        </div>
                        <div class="timetable_event">
                            <p>Приемка проекта</p>
                        </div>

                    </div>

                <#if events??>
                    <#list events as event>

                        <div class="timetable_element">
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

            <div id="timetable_3" style="display: none" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>11:50</p>

                            <p>-</p>

                            <p>13:20</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Программная инженерия</p>

                            <p>Кугуракова В. В., 1408</p>
                        </div>

                    </div>


                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                            <p>-</p>

                            <p>15:05</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Управление проектами</p>

                            <p>Моисеев Г.В. в 108 к.2</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>15:20</p>

                            <p>-</p>

                            <p>16:50</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Проектный практикум</p>
                        </div>

                    </div>


                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>17:00</p>

                            <p>-</p>

                            <p>18:30</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Курс по выбору</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>18:40</p>

                            <p>-</p>

                            <p>20:10</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Курс по выбору</p>
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

            <div id="timetable_4" style="display: none" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>8:30</p>

                            <p>-</p>

                            <p>10:00</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Параллельное программирование</p>

                            <p>Новиков П. А., 108</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>10:10</p>

                            <p>-</p>

                            <p>11:40</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Физика</p>

                            <p>Яцык, 1307</p>
                        </div>

                    </div>


                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>11:50</p>

                            <p>-</p>

                            <p>13:20</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Правоведение</p>
                            <p>Хасанов Р. А., 1310</p>
                        </div>

                    </div>

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>13:35</p>

                            <p>-</p>

                            <p>15:00</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Программная инженерия</p>
                            <p>Кугуракова В. В., 108</p>
                        </div>

                    </div>


                </div>

            <#-- event block -->
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

            <div id="timetable_5" style="display: none" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                    <div class="timetable_element">
                        <div class="timetable_element_time">
                            <p>14:00</p>

                            <p>-</p>

                            <p>15:30</p>
                        </div>
                        <div class="timetable_element_name">
                            <p>Физическая культура</p>
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

            <div id="timetable_6" style="display: none" class="timetble_menu font_timeteble_element">

                <div class="timetable_elements_block ">

                </div>

            </div>

            <div id="timetable_7" style="display: none" class="timetble_menu font_timeteble_element">
                <div class="timetable_elements_block ">

                </div>
            </div>


        </div>
    </div>
</div>
