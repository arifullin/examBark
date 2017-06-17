<!-- Modal -->
<form method="post" action="<@spring.url '/main/generate_user'/>" id="generate_user">
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="registration_modal_dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><img
                            src="../images/Modals_exit_button.png" alt="img"/>
                    </button>
                    <p class="font_modal_header">Быстрая регистрация</p>
                    <hr>
                </div>
                <div class="modal-body">
                    <p id="font_footer" style="text-align: center">Для ознокомления с функционалом сайта будет создан
                        профиль со случайными данными
                    </p>
                    <div class="form-group">
                        <label class="sr-only sign_in_form" for="surname">Фамилия</label>
                        <input class="modal_input_block font_placeholder_student_search" id="surname" type="text"
                               name="surname" placeholder="Фамилия">
                    </div>
                    <div class="form-group">
                        <label class="sr-only sign_in_form" for="name">Имя</label>
                        <input class="modal_input_block font_placeholder_student_search" id="name" type="text"
                               name="name" placeholder="Имя">
                    </div>
                    <div class="form-group font_placeholder_student_search">
                        <select id="dropdown" name="userRole" onchange="clickDropdown()" class="modal_input_block">
                            <option value="ROLE_STUDENT">Студент</option>
                            <option value="ROLE_TEACHER">Преподователь</option>
                            <option value="ROLE_WORKER">Работник деканата</option>
                        </select>
                    </div>

                    <div class="form-group font_placeholder_student_search">
                        <select id="forJS" name="course" class="modal_input_block">
                            <option value="0">Курс</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>

                    <div class="form-group font_placeholder_student_search">
                        <select id="forJS" name="group" class="modal_input_block">
                            <option value="0">Группа</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                        </select>
                    </div>
                </div>

                <div class="reference_modal_footer">
                    <button type="submit"
                            class="button_effect reference_modal_сonfirm_button  font_student_search_button">
                        Зарегестрироваться
                    </button>
                </div>

            </div>
        </div>
    </div>
</form>
<!-- Modal End -->
