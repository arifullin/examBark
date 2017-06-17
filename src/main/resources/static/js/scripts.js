function Confirm() {
    document.getElementById("password_recovery_1").style.display = "none";
    document.getElementById("password_recovery_2").style.display = "block";
    document.getElementsByClassName('modal_сonfirm_button')[0].style.display = "none"

}

function clickRadio() {

    var c = document.getElementById('student');
    if (c.checked) {
        var elements = document.querySelectorAll("#form_of_training");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = false;
        }
        var elements2 = document.querySelectorAll("#forOpasity");
        for (var i = 0; i < elements.length; i++) {
            elements2[i].style.opacity = 1;
        }

    } else {
        var elements = document.querySelectorAll("#form_of_training");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = true;
        }
        var elements2 = document.querySelectorAll("#forOpasity");
        for (var i = 0; i < elements.length; i++) {
            elements2[i].style.opacity = 0.4;
        }

    }

}


function clickCheckbox() {

    var c = document.getElementById('event_1');
    if (c.checked) {
        var elements = document.querySelectorAll("#eventElement");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = false;
            elements[i].style.opacity = 1;
        }
    } else {
        var elements = document.querySelectorAll("#eventElement");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = true;
            elements[i].style.opacity = 0.4;
        }
    }

}

function ConfirmNotification() {

    $(".blue_background_rgba_color").click(function () {
        //document.getElementById("rest_" + this.id.replace("notif_","")).style.display = "block";
        $("#rest_" + this.id.replace("notif_", "")).fadeIn();
        $("#" + this.id).fadeOut();
    });

    $(".blue_background_rgba_color_zaebalsya").click(function () {

        $("#notif_" + this.id.replace("rest_", "")).fadeIn();
        $("#" + this.id).fadeOut();
    });


}

function ConfirmNotificationStudent(div_id, notif_id, user_id) {


    $.ajax({
        type: 'POST',
        url: '/student/profile/confirm',
        data: {notificationId: notif_id, userId: user_id},
        success: function () {
            $("#notif_" + div_id).fadeOut();
        }
    });

}


$(function () {
    $('.timetble_menu button').click(function () {

        var num = Number($(this).id);
        var elements = document.querySelectorAll(".timetable_menu_button");

        for (var i = 0; i < elements.length; i++) {

            elements[i].classList.remove("timetable_menu_button_checked");

            document.getElementById("timetable_" + (i + 1)).style.display = "none";

            //  $("#timetable_" + (i+1)).style("display","none");


        }
        //document.getElementById("timetable_" + this.id).style.display = "flex";

        $("#timetable_" + this.id).fadeIn();
        $(this).addClass('timetable_menu_button_checked');


    });

});

function clickDropdown() {

    var c = document.getElementById("dropdown").value;
    if (c == "ROLE_TEACHER" || c == "ROLE_WORKER") {
        var elements = document.querySelectorAll("#forJS");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = true;
            elements[i].style.opacity = 0.4;
        }


    } else {
        var elements = document.querySelectorAll("#forJS");
        for (var i = 0; i < elements.length; i++) {
            elements[i].disabled = false;
            elements[i].style.opacity = 1;
        }


    }

}

function navbarHover() {
    var elements = document.querySelectorAll(".navbar_elem");
    for(var i=0; i<elements.length; i++){
        elements[i].onmouseenter = function() {
            this.style.borderBottom = "solid 4px #005f6e";
            this.style.marginBottom = "-5px";
        }
        elements[i].onmouseleave = function() {
            this.style.borderBottom = "none";
            this.style.marginBottom = "0px";
        }
    }
}
