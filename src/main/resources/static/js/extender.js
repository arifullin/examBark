$(document).ready(function () {
    $('.extendable').click(function () {
        $(this).toggleClass('opened').toggleClass('closed');
        if ($(this).hasClass('opened')) {
            $(this).html('расширенный');
            $(this).prepend('<img src="../images/arrow_close.png" alt="close" style="margin-right:10px;" />');
        }
        else {
            $(this).html('расширенный');
            $(this).prepend('<img src="../images/arrow_open.png" alt="open" style="margin-right:10px;" />');
        }
    });
});