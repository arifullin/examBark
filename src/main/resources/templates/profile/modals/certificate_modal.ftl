<form action="/student/profile/order" method="post">
<!-- Modal reference -->
<div class="modal fade" id="referenceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="reference_modal_dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><img
                        src="/images/Modals_exit_button.png" alt="img"/>
                </button>
                <p class="font_reference_modal_header">Заказать справку</p>
                <hr/>
            </div>

            <div class="modal-body font_button_checkbox_radio ">
                <p>Количество</p>
                <button type="button" class="button_plus_minus" id="minus" aria-hidden="true"><img
                        src="/images/minus.png" alt="img"/>
                </button>

                <input id="count" class="modal_reference_input_block font_placeholder_student_search" name="count"
                       value="1" type="text" placeholder="" readonly style="text-align: center;"/>

                <button type="button" class="button_plus_minus" id="plus" aria-hidden="true"><img src="/images/plus.png"
                                                                                                  alt="img"/>
                </button>
            </div>

                <div class="reference_modal_footer" >
                    <button type="submit" class="reference_modal_сonfirm_button  font_student_search_button">Получить
                    </button>
                </div>

        </div>
    </div>
</div>
    </form>
<!-- Modal End -->
