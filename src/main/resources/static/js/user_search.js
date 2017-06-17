$(document).ready(function () {

// search main functionality
    $('#searchBtn').on('click', function (e) {

        console.log('Submit');

        e.preventDefault();
        var user_id = $('#secret_used_id').val();
        var searchForm = getSearchForm();
        searchForm.userId = $('#secret_used_id').val();

        fetch('/search/users/template', {
            method: 'POST',
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(searchForm)
        }).then(function (response) {

            console.log(response);

            if (response.status === 200) {
                return response.text();
            }
        }).then(function (html) {

            var results = $('#search_results');

            results.empty();

            results.append(html);

        }).catch(function (error) {
            console.log(error); // if error occurred
        })
    });


    $('#send-notification-form').on('submit', function (e) {

        e.preventDefault();

        var notificationForm = {};

        notificationForm.title = $(this).find('#title').val();
        notificationForm.body = $(this).find('#body').val();
        notificationForm.datetime = $(this).find('#datetime').val();
        notificationForm.time1 = $(this).find('#time1').val();
        notificationForm.time2 = $(this).find('#time2').val();
        notificationForm.deadline = $(this).find('#event_1').is(':checked');
        notificationForm.userId = $('#secret_used_id').val();
        notificationForm.users = [];

        var data = [];

        var status = $('#fio0').is(':checked');

        if (status) {
            $('#search_results').find('input[data-user-id]').map(function (i, element) {
                data.push($(element).data('user-id'));
            });
        } else {
            $('#search_results').find('input[data-user-id]')
                .filter(':checked')
                .map(function (i, element) {
                    data.push($(element).data('user-id'));
                });
        }

        notificationForm.users = data;

        console.log(notificationForm);

        var color = 'green';

        fetch('/notifications/publish', {
            method: 'POST',
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(notificationForm)
        }).then(function (response) {

            if (response.status === 500) {
                color = 'black';
            }
            if (response.status === 400) {
                color = 'red';
            }

            if (response.status === 200) {
                $('#send-notification-form')[0].reset();
            }

            return response.text();

        }).then(function (msg) {

            $('#message').text(msg).css('color', color);

        }).catch(function (msg) {
            console.log(msg);
        })
    });


    function getSearchForm() {
        var searchForm = {};

        var course = $('#course').val();
        searchForm.course = course === 'all' ? -1 : course;

        var group = $('#group').val();
        searchForm.group = group === 'all' ? -1 : group;

        searchForm.surname = $('#surname').val();
        searchForm.name = $('#name').val();
        searchForm.patronymic = $('#patronymic').val();
        searchForm.isMan = $('#m').is(':checked');
        searchForm.isWoman = $('#w').is(':checked');
        searchForm.isGrant = false; // TODO add grant checkbox
        searchForm.isBudget = $('#budget').is(':checked');
        searchForm.isContract = $('#contract').is(':checked');
        searchForm.isStarosta = $('#captain').is(':checked');
        searchForm.isProfOrg = $('#captain2').is(':checked');
        searchForm.isSportOrg = $('#captain3').is(':checked');
        searchForm.isSocialOrg = $('#captain4').is(':checked');
        searchForm.isCultureOrg = $('#captain5').is(':checked');
        return searchForm;
    }


// change course select option
    $('#course').on('change', function () {

        // load groups
        var value = this.value;

        if (value === 'all') {
            // set groups to all
            var groupOption = $('#group').empty();
            // append 'all'
            groupOption.append($('<option></option>').attr("value", 'all').text('all'));
            return;
        }

        fetch('/search/groups?course=' + value)
            .then(function (response) {
                console.log(response);
                return response.json(); // json content type
            })
            .then(function (json) {
                var groupsOption = $('#group'); // clear groups select
                groupsOption.empty();
                var option;

                option = $('<option></option>')
                    .attr("value", 'all').text('all');
                groupsOption.append(option);

                json.forEach(function (item) { // fill in with new values
                    option = $('<option></option>')
                        .attr("value", item).text(item);
                    groupsOption.append(option);
                });
            });
    });
});