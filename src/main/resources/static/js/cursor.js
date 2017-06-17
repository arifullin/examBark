$(document).ready(function () {
    $('div.schedule_cursor_header').click(function () {
        $('.schedule_cursor').toggleClass('opened').toggleClass('closed').show();
        if ($('.schedule_cursor').hasClass('opened')) {
            $('.schedule_cursor').html('');
            $('.schedule_cursor').prepend('<img src="../images/arrow_close.png" alt="close" style="margin-right:10px;" />');
        }
        else {
            $('.schedule_cursor').html('');
            $('.schedule_cursor').prepend('<img src="../images/arrow_open.png" alt="open" style="margin-right:10px;" />');
        }
    });
    $('div.group_cursor_header').click(function () {
        $('.group_cursor').toggleClass('opened').toggleClass('closed').show();
        if ($('.group_cursor').hasClass('opened')) {
            $('.group_cursor').html('');
            $('.group_cursor').prepend('<img src="../images/arrow_close.png" alt="close" style="margin-right:10px;" />');
        }
        else {
            $('.group_cursor').html('');
            $('.group_cursor').prepend('<img src="../images/arrow_open.png" alt="open" style="margin-right:10px;" />');
        }
    });
    $('div.search_cursor_header').click(function () {
        $('.search_cursor').toggleClass('opened').toggleClass('closed').show();
        if ($('.search_cursor').hasClass('opened')) {
            $('.search_cursor').html('');
            $('.search_cursor').prepend('<img src="../images/arrow_close.png" alt="close" style="margin-right:10px;" />');
        }
        else {
            $('.search_cursor').html('');
            $('.search_cursor').prepend('<img src="../images/arrow_open.png" alt="open" style="margin-right:10px;" />');
        }
    });
});