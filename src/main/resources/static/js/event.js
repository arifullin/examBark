function subscribeEvent(id) {

    $.ajax({
        url: "/events/" + id + "/subscribe",
        type: "GET",
        success: function () {
            var number;
            if ($("#event_subscribe" + id).html() === ("Участвовать")) {
                $("#event_subscribe" + id).html("Не Участвовать");
                // number = parseInt($("#event_user_counter" + id).text()) + 1;
                // $("#event_user_counter" + id).html(number);
                // alert("Уч")
            }
            else {
                $("#event_subscribe" + id).html("Участвовать");
                // number = parseInt($("#event_user_counter" + id).text()) - 1;
                // $("#event_user_counter" + id).html(number);
                // alert("Нет")
            }
        }
    })
}