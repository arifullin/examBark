<#import "/spring.ftl" as spring />
<!-- modal add user start -->
<form method="post" action="<@spring.url '/worker/profile/create_user'/>" id="create_user_form">
    <div id="add-user-modal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm small_modal" role="document">
            <div class="modal-content small_modal_content">
                <div class="modal-header">
                    <div class="flexbox_aling_center flex_justify_space_between margin_bottom5px">
                        <p class="font_modal_header font_modal_header_small">Добавить пользователя</p>
                        <button type="button" class="close margin_left10px" data-dismiss="modal" aria-hidden="true">
                            <img src="<@spring.url '/images/Modals_exit_button.png'/>" alt="img"/>
                        </button>
                    </div>
                    <hr>
                </div>
                <div class="modal-body" id="add_user_body_block">
                    <div class="margin_bottom10px">
                        <label class="sr-only modal_input_block" for="surname">Фамилия
                        </label>

                        <@spring.formInput path="userForm.surname" attributes='class=\"input_block font_placeholder_student_search\" required id=\"surname\" type=\"text\" name=\"surname\" placeholder=\"Фамилия\"' fieldType="text"></@spring.formInput>


                        <!--<input class="input_block font_placeholder_student_search" id="surname" type="text" name="surname" placeholder="Фамилия">-->

                    </div>
                    <div class="margin_bottom10px">
                        <label class="sr-only modal_input_block" for="name">
                            Имя
                        </label>

                         <@spring.formInput path="userForm.name" attributes="class=\"input_block font_placeholder_student_search\" required id=\"name\" type=\"text\"  name=\"name\" placeholder=\"Имя\"" fieldType="text"></@spring.formInput>


                        <!--<input class="input_block font_placeholder_student_search" id="name" type="text"  name="name" placeholder="Имя">-->
                    </div>
                    <div class="margin_bottom10px">
                        <label class="sr-only modal_input_block" for="patronymic">
                            Отчество
                        </label>

                        <@spring.formInput path="userForm.patronymic" attributes="class=\"input_block font_placeholder_student_search\" required type=\"text\" id=\"patronymic\" name=\"patronymic\" placeholder=\"Отчество\"" fieldType="text"></@spring.formInput>


                       <!-- <input class="input_block font_placeholder_student_search" type="text" id="patronymic" name="patronymic" placeholder="Отчество">-->
                    </div>
                    <div class="margin_bottom10px">
                        <label class="sr-only modal_input_block" for="email">
                            Почта
                        </label>
                        <@spring.formInput path="userForm.email" attributes="class=\"input_block font_placeholder_student_search\" required type=\"email\" id=\"email\" name=\"email\" placeholder=\"Почта\"" fieldType="text"></@spring.formInput>

                        <!--<input class="input_block font_placeholder_student_search" type="email" id="email" name="email" placeholder="Почта">-->
                    </div>
                    <div class="flex_row font_button_checkbox_radio">

                        <div class="flex_column">

                            <div>
                                <label class="control control-radio">
                                    Студент
                                    <input type="radio" value="ROLE_STUDENT" name="pay" id="student" onclick="clickRadio();" checked/>
                                    <div class="control_indicator"></div>
                                </label>
                            </div>
                            <div class="flex_row">
                                <label class="control control-radio">
                                    Преподователь
                                    <input type="radio" value="ROLE_TEACHER" name="pay" onclick="clickRadio();"/>
                                    <div class="control_indicator"></div>
                                </label>
                            </div>
                            <div class="flex_row">
                                <label class="control control-radio">
                                    Сотрудник
                                    <input type="radio" value="ROLE_WORKER" name="pay" onclick="clickRadio();"/>
                                    <div class="control_indicator"></div>
                                </label>
                            </div>

                        </div>

                        <div style="margin-left: 30px" class="flex_column">

                            <div class="flex_row">
                                <label class="control control-radio">
                                    Мужской
                                    <input type="radio" value="MALE" name="gender" checked="checked" />
                                    <div class="control_indicator"></div>
                                </label>
                            </div>
                            <div class="flex_row">
                                <label class="control control-radio">
                                    Женский
                                    <input  type="radio" value="FEMALE" name="gender"/>
                                    <div class="control_indicator"></div>
                                </label>
                            </div>

                        </div>

                    </div>

                    <div  id="forOpasity"  class="flex_column font_button_checkbox_radio">

                        <div class="font_placeholder_student_search " style="margin-bottom: 10px">
                            <select name="course" class="modal_input_block">
                                <option value="0">Курс</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </div>
                        <div class="font_placeholder_student_search " style="margin-bottom: 10px">
                            <select  name="group" class="modal_input_block">
                                <option value="0">Группа</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </div>
                        <div class="font_placeholder_student_search" style="margin-bottom: 10px">
                            <select name="status" class="modal_input_block">
                                <option value="NONE">Статус</option>
                                <option value="STAROSTA">Cтароста</option>
                                <option value="SOCORG">Соцорг</option>
                                <option value="KULTORG">Культорг</option>
                                <option value="SPORTORG">Спорторг</option>
                                <option value="PROFORG">Профорг</option>
                            </select>
                        </div>

                        <div class="flex_row">
                            <label class="control control-radio">
                                Бюджет
                                <input  id="form_of_training" value="BUDGET" type="radio" name="trainingForm" checked />
                                <div class="control_indicator"></div>
                            </label>
                        </div>
                        <div class="flex_row">
                            <label class="control control-radio">
                                Контракт
                                <input id="form_of_training" value="CONTRACT" type="radio" name="trainingForm"/>
                                <div class="control_indicator"></div>
                            </label>
                        </div>
                    </div>

                    <#if userAlreadyExist??>
                    <div class="already_exists">
                        <p>${userAlreadyExist}</p>
                    </div>
                    </#if>
                </div>

                <div class="modal-footer" >
                    <button class="modal_сonfirm_button  font_student_search_button" type="submit" onclick="">Добавить</button>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- modal add user end -->