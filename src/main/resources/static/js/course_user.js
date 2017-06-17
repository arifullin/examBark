/**
 * Created by Daniel Shchepetov on 25.05.2017.
 */
function edit(){
    $('.hide_block').show();
    $('.edit_block').hide();
}

function hideModal(){
    $('.hide_block').hide();
    $('.edit_block').show();
}



function loadGroups(course_id) {
    // load groups
    var value = $("#" + course_id.id + " option:selected").text();
    fetch('/search/groups?course=' + value)
        .then(function (response) {
            console.log(response);
            return response.json(); // json content type
        })
        .then(function (json) {
            var groupsOption = $('#group-user' + course_id.id); // clear groups select
            groupsOption.empty();
            var option;
            json.forEach(function (item) { // fill in with new values
                option = $('<option></option>')
                    .attr("value", item).text(item);
                groupsOption.append(option);
            });
        });
};

function editStudent(user_id) {
    console.log('Submit');
    user_id = user_id.replace("button", "");
    var education = $("#education" + user_id + " option:selected").text();
    var social_status = $("#social-status" + user_id + " option:selected").text();
    var course = $("#" + user_id + " option:selected").text();
    var group = $('#group-user' + user_id + " option:selected").text();


    $.ajax({
        type: 'POST',
        url: '/worker/profile/edit_student',
        data: {
            userId: user_id,
            education: education,
            socialStatus: social_status,
            course: course,
            group: group

        },
        success: function (response) {

            $('#wrapper'+user_id).html(response);

           // $('.hide_block').hide();
          //  $('.edit_block').show();
        },
        error: function (response) {
            console.log(response);
        }
    });

}